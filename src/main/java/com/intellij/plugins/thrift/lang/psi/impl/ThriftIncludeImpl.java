// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.plugins.thrift.lang.psi.*;
import com.intellij.plugins.thrift.util.ThriftPsiUtil;
import com.intellij.psi.PsiReference;

public class ThriftIncludeImpl extends ThriftPsiCompositeElementImpl implements ThriftInclude {

  public ThriftIncludeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@Nonnull PsiElementVisitor visitor) {
    if (visitor instanceof ThriftVisitor) ((ThriftVisitor)visitor).visitInclude(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getLiteral() {
    return findChildByType(LITERAL);
  }

  @Nonnull
  public PsiReference[] getReferences() {
    return ThriftPsiUtil.getReferences(this);
  }

  @Nonnull
  public String getPath() {
    return ThriftPsiUtil.getPath(this);
  }

}
