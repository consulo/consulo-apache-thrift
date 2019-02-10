// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftXsdAttrsImpl extends ThriftPsiCompositeElementImpl implements ThriftXsdAttrs {

  public ThriftXsdAttrsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitXsdAttrs(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftFields getFields() {
    return findChildByClass(ThriftFields.class);
  }

}
