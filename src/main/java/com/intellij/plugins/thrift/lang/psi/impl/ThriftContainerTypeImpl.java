// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftContainerTypeImpl extends ThriftPsiCompositeElementImpl implements ThriftContainerType {

  public ThriftContainerTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitContainerType(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftListType getListType() {
    return findChildByClass(ThriftListType.class);
  }

  @Override
  @Nullable
  public ThriftMapType getMapType() {
    return findChildByClass(ThriftMapType.class);
  }

  @Override
  @Nullable
  public ThriftSetType getSetType() {
    return findChildByClass(ThriftSetType.class);
  }

  @Override
  @Nullable
  public ThriftTypeAnnotations getTypeAnnotations() {
    return findChildByClass(ThriftTypeAnnotations.class);
  }

}
