// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftEnumImpl extends ThriftTopLevelDeclarationImpl implements ThriftEnum {

  public ThriftEnumImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitEnum(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftDefinitionName getDefinitionName() {
    return findChildByClass(ThriftDefinitionName.class);
  }

  @Override
  @Nullable
  public ThriftTypeAnnotations getTypeAnnotations() {
    return findChildByClass(ThriftTypeAnnotations.class);
  }

  @Override
  @Nullable
  public ThriftEnumFields getEnumFields() {
    return findChildByClass(ThriftEnumFields.class);
  }

}
