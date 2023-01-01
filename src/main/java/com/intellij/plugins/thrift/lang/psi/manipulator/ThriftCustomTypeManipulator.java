package com.intellij.plugins.thrift.lang.psi.manipulator;

import com.intellij.plugins.thrift.lang.psi.ThriftCustomType;
import consulo.annotation.component.ExtensionImpl;
import consulo.document.util.TextRange;
import consulo.language.impl.psi.LeafPsiElement;
import consulo.language.psi.AbstractElementManipulator;
import consulo.language.psi.PsiElement;
import consulo.language.util.IncorrectOperationException;

import javax.annotation.Nonnull;

@ExtensionImpl
public class ThriftCustomTypeManipulator extends AbstractElementManipulator<ThriftCustomType>
{
	@Override
	public ThriftCustomType handleContentChange(ThriftCustomType element, TextRange range, String newContent)
			throws IncorrectOperationException
	{
		String oldText = element.getText();
		String newText = oldText.substring(0, range.getStartOffset()) + newContent + oldText.substring(range.getEndOffset());
		PsiElement child = element.getFirstChild();
		if(child instanceof LeafPsiElement)
		{
			((LeafPsiElement) child).replaceWithText(newText);
			return element;
		}
		throw new IncorrectOperationException("bad PSI");
	}

	@Nonnull
	@Override
	public Class<ThriftCustomType> getElementClass()
	{
		return ThriftCustomType.class;
	}
}
