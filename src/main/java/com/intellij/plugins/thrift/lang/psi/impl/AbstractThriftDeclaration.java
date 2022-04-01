package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import consulo.language.ast.ASTNode;
import consulo.language.impl.psi.LeafPsiElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.language.util.IncorrectOperationException;
import consulo.navigation.ItemPresentation;
import consulo.navigation.ItemPresentationProviders;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by fkorotkov.
 */
public class AbstractThriftDeclaration extends ThriftPsiCompositeElementImpl implements ThriftDeclaration {
  public AbstractThriftDeclaration(@Nonnull ASTNode node) {
    super(node);
  }

  @Nullable
  public ThriftDefinitionName getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, ThriftDefinitionName.class);
  }

  @Override
  public String getName() {
    PsiElement identifier = getIdentifier();
    return identifier != null ? identifier.getText() : super.getName();
  }

  @Override
  public PsiElement setName(@NonNls @Nonnull String name) throws IncorrectOperationException {
    ThriftDefinitionName definitionName = getIdentifier();
    PsiElement id = definitionName != null ? definitionName.getFirstChild() : null;
    if (id instanceof LeafPsiElement) {
      ((LeafPsiElement)id).replaceWithText(name);
    }
    return this;
  }

  @Override
  public ItemPresentation getPresentation() {
    return ItemPresentationProviders.getItemPresentation(this);
  }

  @Nonnull
  @Override
  public PsiElement getNavigationElement() {
    ThriftDefinitionName name = getIdentifier();
    return name != null ? name : super.getNavigationElement();
  }
}
