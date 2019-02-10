// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import java.util.List;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftFunctionImpl extends AbstractThriftDeclaration implements ThriftFunction {

  public ThriftFunctionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitFunction(this);
    else super.accept(visitor);
  }

  @Override
  @Nonnull
  public ThriftDefinitionName getDefinitionName() {
    return findNotNullChildByClass(ThriftDefinitionName.class);
  }

  @Override
  @Nonnull
  public List<ThriftField> getFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ThriftField.class);
  }

  @Override
  @Nonnull
  public ThriftFunctionType getFunctionType() {
    return findNotNullChildByClass(ThriftFunctionType.class);
  }

  @Override
  @Nullable
  public ThriftThrows getThrows() {
    return findChildByClass(ThriftThrows.class);
  }

  @Override
  @Nullable
  public ThriftTypeAnnotations getTypeAnnotations() {
    return findChildByClass(ThriftTypeAnnotations.class);
  }

}
