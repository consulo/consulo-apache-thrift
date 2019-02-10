// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ThriftInclude extends ThriftPsiCompositeElement {

  @Nullable
  PsiElement getLiteral();

  @Nonnull
  PsiReference[] getReferences();

  @Nonnull
  String getPath();

}
