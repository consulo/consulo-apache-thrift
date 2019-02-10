// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftServiceSuperNameImpl extends ThriftPsiCompositeElementImpl implements ThriftServiceSuperName {

  public ThriftServiceSuperNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitServiceSuperName(this);
    else super.accept(visitor);
  }

  @Override
  @Nonnull
  public ThriftCustomType getCustomType() {
    return findNotNullChildByClass(ThriftCustomType.class);
  }

}
