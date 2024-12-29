package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.document.util.TextRange;
import consulo.language.Language;
import consulo.language.editor.ImplementationTextSelectioner;
import consulo.language.psi.PsiElement;

import jakarta.annotation.Nonnull;

@ExtensionImpl
public class ThriftImplementationTextSelectioner implements ImplementationTextSelectioner
{
	@RequiredReadAction
	@Override
	public int getTextStartOffset(@Nonnull PsiElement element)
	{
		if(element instanceof ThriftDefinitionName)
		{
			element = element.getParent();
		}

		final TextRange textRange = element.getTextRange();
		return textRange.getStartOffset();
	}

	@RequiredReadAction
	@Override
	public int getTextEndOffset(@Nonnull PsiElement element)
	{
		if(element instanceof ThriftDefinitionName)
		{
			element = element.getParent();
		}

		final TextRange textRange = element.getTextRange();
		return textRange.getEndOffset();
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return ThriftLanguage.INSTANCE;
	}
}
