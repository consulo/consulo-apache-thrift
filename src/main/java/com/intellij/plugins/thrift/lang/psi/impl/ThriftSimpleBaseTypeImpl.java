// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftSimpleBaseTypeImpl extends ThriftPsiCompositeElementImpl implements ThriftSimpleBaseType {

  public ThriftSimpleBaseTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitSimpleBaseType(this);
    else super.accept(visitor);
  }

}
