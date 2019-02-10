// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftConstValueImpl extends ThriftPsiCompositeElementImpl implements ThriftConstValue {

  public ThriftConstValueImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitConstValue(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftConstList getConstList() {
    return findChildByClass(ThriftConstList.class);
  }

  @Override
  @Nullable
  public ThriftConstMap getConstMap() {
    return findChildByClass(ThriftConstMap.class);
  }

  @Override
  @Nullable
  public ThriftDoubleConstant getDoubleConstant() {
    return findChildByClass(ThriftDoubleConstant.class);
  }

  @Override
  @Nullable
  public ThriftIntConstant getIntConstant() {
    return findChildByClass(ThriftIntConstant.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getLiteral() {
    return findChildByType(LITERAL);
  }

}
