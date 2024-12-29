package com.intellij.plugins.thrift.lang.psi.presentation;

import com.intellij.plugins.thrift.lang.psi.ThriftSubDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftTopLevelDeclaration;
import consulo.annotation.component.ExtensionImpl;
import consulo.component.util.Iconable;
import consulo.language.icon.IconDescriptorUpdaters;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.navigation.ItemPresentation;
import consulo.navigation.ItemPresentationProvider;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@ExtensionImpl
public class ThriftSubDeclarationPresentationProvider implements ItemPresentationProvider<ThriftSubDeclaration>
{
	@Nonnull
	@Override
	public Class<ThriftSubDeclaration> getItemClass()
	{
		return ThriftSubDeclaration.class;
	}

	@Nonnull
	@Override
	public ItemPresentation getPresentation(final ThriftSubDeclaration item)
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
				ThriftTopLevelDeclaration topLevelDeclaration = PsiTreeUtil.getParentOfType(item, ThriftTopLevelDeclaration.class, true);
				return topLevelDeclaration != null ? topLevelDeclaration.getName() : item.getContainingFile().getName();
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
