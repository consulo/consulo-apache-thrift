// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import  com.intellij.navigation.NavigationItem;
import  com.intellij.psi.PsiNameIdentifierOwner;

public interface ThriftDefinitionName extends PsiNamedElement, NavigationItem, PsiNameIdentifierOwner {

  @Nonnull
  PsiElement getIdentifier();

  @Nonnull
  PsiElement setName(String name);

  @Nullable
  @NonNls
  String getName();

  @Nonnull
  PsiElement getNameIdentifier();

}
