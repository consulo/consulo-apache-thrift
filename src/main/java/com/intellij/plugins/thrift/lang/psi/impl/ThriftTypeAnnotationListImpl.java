// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import java.util.List;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;

import javax.annotation.Nonnull;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftTypeAnnotationListImpl extends ThriftPsiCompositeElementImpl implements ThriftTypeAnnotationList {

  public ThriftTypeAnnotationListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitTypeAnnotationList(this);
    else super.accept(visitor);
  }

  @Override
  @Nonnull
  public List<ThriftTypeAnnotation> getTypeAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ThriftTypeAnnotation.class);
  }

}
