// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

import javax.annotation.Nonnull;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftIntConstantImpl extends ThriftPsiCompositeElementImpl implements ThriftIntConstant {

  public ThriftIntConstantImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitIntConstant(this);
    else super.accept(visitor);
  }

  @Override
  @Nonnull
  public PsiElement getInteger() {
    return findNotNullChildByType(INTEGER);
  }

}
