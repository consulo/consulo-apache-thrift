// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftMapTypeImpl extends ThriftPsiCompositeElementImpl implements ThriftMapType {

  public ThriftMapTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitMapType(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftCppType getCppType() {
    return findChildByClass(ThriftCppType.class);
  }

  @Override
  @Nullable
  public ThriftGenericType getGenericType() {
    return findChildByClass(ThriftGenericType.class);
  }

}
