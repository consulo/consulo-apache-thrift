// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.IDENTIFIER;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.plugins.thrift.lang.psi.ThriftServiceSuperName;
import com.intellij.plugins.thrift.lang.psi.ThriftVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

public class ThriftServiceSuperNameImpl extends ThriftPsiCompositeElementImpl implements ThriftServiceSuperName {

  public ThriftServiceSuperNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitServiceSuperName(this);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}
