package com.intellij.plugins.thrift.inspections;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nls;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.plugins.thrift.ThriftBundle;
import com.intellij.plugins.thrift.lang.psi.ThriftCustomType;
import com.intellij.plugins.thrift.lang.psi.ThriftVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.util.ArrayUtil;

public class ThriftUnresolvedSymbolInspection extends LocalInspectionTool {

  @Nonnull
  public String getGroupDisplayName() {
    return ThriftBundle.message("inspections.group.name");
  }

  @Nls
  @Nonnull
  @Override
  public String getDisplayName() {
    return ThriftBundle.message("thrift.inspection.unresolved.symbol");
  }

  @Override
  public boolean isEnabledByDefault() {
    return true;
  }

  @Nonnull
  @Override
  public String getShortName() {
    return "ThriftUnresolvedSymbol";
  }

  @Nullable
  @Override
  public ProblemDescriptor[] checkFile(@Nonnull PsiFile file, @Nonnull final InspectionManager manager, final boolean isOnTheFly) {
    final List<ProblemDescriptor> result = new ArrayList<ProblemDescriptor>();
    new ThriftVisitor() {
      @Override
      public void visitCustomType(@Nonnull ThriftCustomType type) {
        for (PsiReference reference : type.getReferences()) {
          if (reference.resolve() == null) {
            result.add(manager.createProblemDescriptor(
              reference.getElement(),
              reference.getRangeInElement(),
              getDisplayName(),
              ProblemHighlightType.ERROR,
              isOnTheFly
            ));
          }
        }
      }

      public void visitElement(PsiElement element) {
        super.visitElement(element);
        element.acceptChildren(this);
      }
    }.visitFile(file);
    return ArrayUtil.toObjectArray(result, ProblemDescriptor.class);
  }
}
