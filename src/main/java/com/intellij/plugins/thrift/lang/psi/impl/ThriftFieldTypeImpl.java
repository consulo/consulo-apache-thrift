// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftFieldTypeImpl extends ThriftPsiCompositeElementImpl implements ThriftFieldType {

  public ThriftFieldTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitFieldType(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftBaseType getBaseType() {
    return findChildByClass(ThriftBaseType.class);
  }

  @Override
  @Nullable
  public ThriftContainerType getContainerType() {
    return findChildByClass(ThriftContainerType.class);
  }

  @Override
  @Nullable
  public ThriftCustomType getCustomType() {
    return findChildByClass(ThriftCustomType.class);
  }

}
