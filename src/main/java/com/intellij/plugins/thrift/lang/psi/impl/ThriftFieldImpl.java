// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftFieldImpl extends AbstractThriftDeclaration implements ThriftField {

  public ThriftFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitField(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftConstValue getConstValue() {
    return findChildByClass(ThriftConstValue.class);
  }

  @Override
  @Nullable
  public ThriftDefinitionName getDefinitionName() {
    return findChildByClass(ThriftDefinitionName.class);
  }

  @Override
  @Nullable
  public ThriftFieldID getFieldID() {
    return findChildByClass(ThriftFieldID.class);
  }

  @Override
  @Nullable
  public ThriftFieldReq getFieldReq() {
    return findChildByClass(ThriftFieldReq.class);
  }

  @Override
  @Nonnull
  public ThriftFieldType getFieldType() {
    return findNotNullChildByClass(ThriftFieldType.class);
  }

  @Override
  @Nullable
  public ThriftListSeparator getListSeparator() {
    return findChildByClass(ThriftListSeparator.class);
  }

  @Override
  @Nullable
  public ThriftTypeAnnotations getTypeAnnotations() {
    return findChildByClass(ThriftTypeAnnotations.class);
  }

  @Override
  @Nullable
  public ThriftXsdFieldOptions getXsdFieldOptions() {
    return findChildByClass(ThriftXsdFieldOptions.class);
  }

}
