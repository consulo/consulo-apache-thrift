// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;
import com.intellij.plugins.thrift.lang.psi.*;
import com.intellij.plugins.thrift.util.ThriftPsiUtil;

public class ThriftTypeAnnotationListImpl extends ThriftPsiCompositeElementImpl implements ThriftTypeAnnotationList {

  public ThriftTypeAnnotationListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitTypeAnnotationList(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ThriftTypeAnnotation> getTypeAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ThriftTypeAnnotation.class);
  }

}
