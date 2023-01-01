package com.intellij.plugins.thrift.lang.psi;

import consulo.language.psi.PsiNamedElement;
import consulo.navigation.NavigationItem;

/**
 * Created by fkorotkov.
 */
public interface ThriftDeclaration extends ThriftPsiCompositeElement, PsiNamedElement, NavigationItem {
  ThriftDefinitionName getIdentifier();
}
