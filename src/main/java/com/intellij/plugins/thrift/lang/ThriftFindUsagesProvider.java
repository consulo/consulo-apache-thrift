package com.intellij.plugins.thrift.lang;

import javax.annotation.Nonnull;

import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import com.intellij.psi.PsiElement;

import javax.annotation.Nullable;

public class ThriftFindUsagesProvider implements FindUsagesProvider {
  @Nullable
  @Override
  public WordsScanner getWordsScanner() {
    return null;
  }

  @Override
  public boolean canFindUsagesFor(@Nonnull PsiElement psiElement) {
    return psiElement instanceof ThriftDefinitionName;
  }

  @Nullable
  @Override
  public String getHelpId(@Nonnull PsiElement psiElement) {
    return null;
  }

  @Nonnull
  @Override
  public String getType(@Nonnull PsiElement element) {
    return "reference";
  }

  @Nonnull
  @Override
  public String getDescriptiveName(@Nonnull PsiElement element) {
    return getNodeText(element, false);
  }

  @Nonnull
  @Override
  public String getNodeText(@Nonnull PsiElement element, boolean useFullName) {
    String result = element instanceof ThriftDefinitionName ? ((ThriftDefinitionName)element).getName() : element.getText();
    return StringUtil.notNullize(result);
  }
}
