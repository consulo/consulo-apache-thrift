// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftFieldReqImpl extends ThriftPsiCompositeElementImpl implements ThriftFieldReq {

  public ThriftFieldReqImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitFieldReq(this);
    else super.accept(visitor);
  }

}
