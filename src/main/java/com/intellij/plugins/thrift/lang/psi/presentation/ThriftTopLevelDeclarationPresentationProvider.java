package com.intellij.plugins.thrift.lang.psi.presentation;

import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.component.util.Iconable;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.localize.LocalizeValue;
import consulo.navigation.ItemPresentation;
import consulo.navigation.ItemPresentationProvider;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;
import org.jspecify.annotations.Nullable;

/**
 * @author fkorotkov
 */
@ExtensionImpl
public class ThriftTopLevelDeclarationPresentationProvider implements ItemPresentationProvider<ThriftDeclaration> {
    @Nonnull
    @Override
    public Class<ThriftDeclaration> getItemClass() {
        return ThriftDeclaration.class;
    }

    @Nonnull
    @Override
    public ItemPresentation getPresentation(ThriftDeclaration item) {
        return new ItemPresentation() {
            @Override
            public LocalizeValue getPresentableText() {
                return LocalizeValue.ofNullable(item.getName());
            }

            @Override
            @RequiredReadAction
            public @Nullable String getLocationString() {
                return item.getContainingFile().getName();
            }

            @Override
            @RequiredReadAction
            public @Nullable Image getIcon() {
                return IconDescriptorUpdaters.getIcon(item, Iconable.ICON_FLAG_VISIBILITY);
            }
        };
    }
}
