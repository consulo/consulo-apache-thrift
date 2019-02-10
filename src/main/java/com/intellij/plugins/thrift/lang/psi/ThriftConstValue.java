// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import javax.annotation.Nullable;

import com.intellij.psi.PsiElement;

public interface ThriftConstValue extends ThriftPsiCompositeElement {

  @Nullable
  ThriftConstList getConstList();

  @Nullable
  ThriftConstMap getConstMap();

  @Nullable
  ThriftDoubleConstant getDoubleConstant();

  @Nullable
  ThriftIntConstant getIntConstant();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getLiteral();

}
