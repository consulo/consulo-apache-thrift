package com.intellij.plugins.thrift.inspections;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nls;

import javax.annotation.Nullable;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.openapi.util.TextRange;
import com.intellij.plugins.thrift.ThriftBundle;
import com.intellij.plugins.thrift.lang.psi.ThriftInclude;
import com.intellij.plugins.thrift.lang.psi.ThriftVisitor;
import com.intellij.plugins.thrift.util.ThriftPsiUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.ArrayUtil;

public class ThriftUnresolvedIncludeInspection extends LocalInspectionTool {

  @Nonnull
  public String getGroupDisplayName() {
    return ThriftBundle.message("inspections.group.name");
  }

  @Nls
  @Nonnull
  @Override
  public String getDisplayName() {
    return ThriftBundle.message("thrift.inspection.unresolved.include");
  }

  @Override
  public boolean isEnabledByDefault() {
    return true;
  }

  @Nonnull
  @Override
  public String getShortName() {
    return "ThriftUnresolvedInclude";
  }

  @Nullable
  @Override
  public ProblemDescriptor[] checkFile(@Nonnull PsiFile file, @Nonnull final InspectionManager manager, final boolean isOnTheFly) {
    final List<ProblemDescriptor> result = new ArrayList<ProblemDescriptor>();
    new ThriftVisitor() {

      @Override
      public void visitInclude(@Nonnull ThriftInclude include) {
        if (ThriftPsiUtil.resolveInclude(include) == null) {
          PsiElement lastChild = include.getLastChild();
          result.add(manager.createProblemDescriptor(
            lastChild,
            TextRange.from(0, lastChild.getTextLength()),
            getDisplayName(),
            ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
            isOnTheFly
          ));
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
