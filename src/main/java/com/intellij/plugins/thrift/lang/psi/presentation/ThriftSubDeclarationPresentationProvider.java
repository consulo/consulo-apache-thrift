package com.intellij.plugins.thrift.lang.psi.presentation;

import javax.annotation.Nullable;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProvider;
import com.intellij.openapi.util.Iconable;
import com.intellij.plugins.thrift.lang.psi.ThriftSubDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftTopLevelDeclaration;
import com.intellij.psi.util.PsiTreeUtil;
import consulo.ide.IconDescriptorUpdaters;
import consulo.ui.image.Image;

public class ThriftSubDeclarationPresentationProvider implements ItemPresentationProvider<ThriftSubDeclaration>
{
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
