// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftNamespaceImpl extends ThriftPsiCompositeElementImpl implements ThriftNamespace {

  public ThriftNamespaceImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitNamespace(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ThriftNamespaceScope getNamespaceScope() {
    return findChildByClass(ThriftNamespaceScope.class);
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

  @Override
  @Nullable
  public PsiElement getSTIdentifier() {
    return findChildByType(STIDENTIFIER);
  }

}
