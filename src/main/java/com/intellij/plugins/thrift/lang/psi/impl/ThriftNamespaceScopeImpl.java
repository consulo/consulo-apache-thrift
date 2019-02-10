// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;

public class ThriftNamespaceScopeImpl extends ThriftPsiCompositeElementImpl implements ThriftNamespaceScope {

  public ThriftNamespaceScopeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitNamespaceScope(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getMultiply() {
    return findChildByType(MULTIPLY);
  }

}
