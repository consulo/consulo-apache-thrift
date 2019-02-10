// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;
import com.intellij.plugins.thrift.util.ThriftPsiUtil;

public class ThriftDefinitionNameImpl extends ThriftPsiCompositeElementImpl implements ThriftDefinitionName {

  public ThriftDefinitionNameImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitDefinitionName(this);
    else super.accept(visitor);
  }

  @Override
  @Nonnull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

  @Nonnull
  public PsiElement setName(String name) {
    return ThriftPsiUtil.setName(this, name);
  }

  @Nullable
  @NonNls
  public String getName() {
    return ThriftPsiUtil.getName(this);
  }

  @Nonnull
  public PsiElement getNameIdentifier() {
    return ThriftPsiUtil.getNameIdentifier(this);
  }

}
