package com.intellij.plugins.thrift.lang.psi;

import com.intellij.plugins.thrift.util.ThriftPsiUtil;
import consulo.document.util.TextRange;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiReferenceBase;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by fkorotkov.
 */
public class ThriftPrefixReference extends PsiReferenceBase<ThriftCustomType> {
  public ThriftPrefixReference(@Nonnull ThriftCustomType element, int offset) {
    super(element, TextRange.create(0, offset));
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    String fileName = getRangeInElement().substring(getElement().getText());
    ThriftInclude include = ThriftPsiUtil.findImportByPrefix(getElement().getContainingFile(), fileName);
    return ThriftPsiUtil.resolveInclude(include);
  }
}
