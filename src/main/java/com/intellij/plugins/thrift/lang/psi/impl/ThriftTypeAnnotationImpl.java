// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftTypeAnnotationImpl extends ThriftPsiCompositeElementImpl implements ThriftTypeAnnotation {

  public ThriftTypeAnnotationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitTypeAnnotation(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftListSeparator getListSeparator() {
    return findChildByClass(ThriftListSeparator.class);
  }

  @Override
  @Nonnull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

  @Override
  @Nonnull
  public PsiElement getLiteral() {
    return findNotNullChildByType(LITERAL);
  }

}
