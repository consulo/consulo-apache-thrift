// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftFieldIDImpl extends ThriftPsiCompositeElementImpl implements ThriftFieldID {

  public ThriftFieldIDImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitFieldID(this);
    else super.accept(visitor);
  }

  @Override
  @Nonnull
  public ThriftIntConstant getIntConstant() {
    return findNotNullChildByClass(ThriftIntConstant.class);
  }

}
