package com.intellij.plugins.thrift.lang.psi.presentation;

import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import consulo.annotation.component.ExtensionImpl;
import consulo.component.util.Iconable;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.navigation.ItemPresentation;
import consulo.navigation.ItemPresentationProvider;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftTopLevelDeclarationPresentationProvider implements ItemPresentationProvider<ThriftDeclaration>
{
	@Nonnull
	@Override
	public Class<ThriftDeclaration> getItemClass()
	{
		return ThriftDeclaration.class;
	}

	@Nonnull
	@Override
	public ItemPresentation getPresentation(final ThriftDeclaration item)
	{
		return new ItemPresentation()
		{
			@Nullable
			@Override
			public String getPresentableText()
			{
				return item.getName();
			}

			@Nullable
			@Override
			public String getLocationString()
			{
				return item.getContainingFile().getName();
			}

			@Nullable
			@Override
			public Image getIcon()
			{
				return IconDescriptorUpdaters.getIcon(item, Iconable.ICON_FLAG_VISIBILITY);
			}
		};
	}
}
