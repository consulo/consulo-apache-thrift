package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import consulo.document.util.TextRange;
import consulo.language.editor.ImplementationTextSelectioner;
import consulo.language.psi.PsiElement;

import javax.annotation.Nonnull;

public class ThriftImplementationTextSelectioner implements ImplementationTextSelectioner {
  @Override
  public int getTextStartOffset(@Nonnull PsiElement element) {
    if (element instanceof ThriftDefinitionName) {
      element = element.getParent();
    }

    final TextRange textRange = element.getTextRange();
    return textRange.getStartOffset();
  }

  @Override
  public int getTextEndOffset(@Nonnull PsiElement element) {
    if (element instanceof ThriftDefinitionName) {
      element = element.getParent();
    }

    final TextRange textRange = element.getTextRange();
    return textRange.getEndOffset();
  }
}
