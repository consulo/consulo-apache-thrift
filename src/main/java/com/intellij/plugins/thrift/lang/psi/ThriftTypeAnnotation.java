// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.psi.PsiElement;

public interface ThriftTypeAnnotation extends ThriftPsiCompositeElement {

  @Nullable
  ThriftListSeparator getListSeparator();

  @Nonnull
  PsiElement getIdentifier();

  @Nonnull
  PsiElement getLiteral();

}
