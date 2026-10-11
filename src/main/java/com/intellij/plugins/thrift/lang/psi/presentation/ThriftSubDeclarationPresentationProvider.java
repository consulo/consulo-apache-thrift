package com.intellij.plugins.thrift.lang.psi.presentation;

import com.intellij.plugins.thrift.lang.psi.ThriftSubDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftTopLevelDeclaration;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.component.util.Iconable;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.localize.LocalizeValue;
import consulo.navigation.ItemPresentation;
import consulo.navigation.ItemPresentationProvider;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;
import org.jspecify.annotations.Nullable;

@ExtensionImpl
public class ThriftSubDeclarationPresentationProvider implements ItemPresentationProvider<ThriftSubDeclaration> {
    @Nonnull
    @Override
    public Class<ThriftSubDeclaration> getItemClass() {
        return ThriftSubDeclaration.class;
    }

    @Nonnull
    @Override
    public ItemPresentation getPresentation(ThriftSubDeclaration item) {
        return new ItemPresentation() {
            @Override
            public LocalizeValue getPresentableText() {
                return LocalizeValue.ofNullable(item.getName());
            }

            @Override
            @RequiredReadAction
            public @Nullable String getLocationString() {
                ThriftTopLevelDeclaration topLevelDeclaration = PsiTreeUtil.getParentOfType(item, ThriftTopLevelDeclaration.class, true);
                return topLevelDeclaration != null ? topLevelDeclaration.getName() : item.getContainingFile().getName();
            }

            @Override
            @RequiredReadAction
            public @Nullable Image getIcon() {
                return IconDescriptorUpdaters.getIcon(item, Iconable.ICON_FLAG_VISIBILITY);
            }
        };
    }
}
