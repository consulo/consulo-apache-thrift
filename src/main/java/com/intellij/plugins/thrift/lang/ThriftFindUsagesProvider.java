package com.intellij.plugins.thrift.lang;

import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import consulo.language.cacheBuilder.WordsScanner;
import consulo.language.findUsage.FindUsagesProvider;
import consulo.language.psi.PsiElement;
import consulo.util.lang.StringUtil;

import javax.annotation.Nonnull;
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
