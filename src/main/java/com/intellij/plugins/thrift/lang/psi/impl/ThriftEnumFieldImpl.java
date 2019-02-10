// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import com.intellij.plugins.thrift.lang.psi.ThriftEnumField;
import com.intellij.plugins.thrift.lang.psi.ThriftIntConstant;
import com.intellij.plugins.thrift.lang.psi.ThriftListSeparator;
import com.intellij.plugins.thrift.lang.psi.ThriftTypeAnnotations;
import com.intellij.plugins.thrift.lang.psi.ThriftVisitor;
import com.intellij.psi.PsiElementVisitor;

public class ThriftEnumFieldImpl extends ThriftTopLevelDeclarationImpl implements ThriftEnumField {

  public ThriftEnumFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitEnumField(this);
    else super.accept(visitor);
  }

  @Override
  @Nonnull
  public ThriftDefinitionName getDefinitionName() {
    return findNotNullChildByClass(ThriftDefinitionName.class);
  }

  @Override
  @Nullable
  public ThriftIntConstant getIntConstant() {
    return findChildByClass(ThriftIntConstant.class);
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

}
