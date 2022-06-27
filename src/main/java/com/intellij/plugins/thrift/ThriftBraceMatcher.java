package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.BracePair;
import consulo.language.Language;
import consulo.language.PairedBraceMatcher;
import consulo.language.ast.IElementType;
import consulo.language.psi.PsiFile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftBraceMatcher implements PairedBraceMatcher
{
	private static final BracePair[] PAIRS = new BracePair[]{
			new BracePair(ThriftTokenTypes.LEFTCURLYBRACE, ThriftTokenTypes.RIGHTCURLYBRACE, false),
			new BracePair(ThriftTokenTypes.LEFTBRACE, ThriftTokenTypes.RIGHTBRACE, false),
			new BracePair(ThriftTokenTypes.LEFTBRACKET, ThriftTokenTypes.RIGHTBRACKET, false),
			new BracePair(ThriftTokenTypes.LT, ThriftTokenTypes.GT, false)
	};

	@Override
	public BracePair[] getPairs()
	{
		return PAIRS;
	}

	@Override
	public boolean isPairedBracesAllowedBeforeType(@Nonnull IElementType lbraceType, @Nullable IElementType contextType)
	{
		return true;
	}

	@Override
	public int getCodeConstructStart(PsiFile file, int openingBraceOffset)
	{
		return openingBraceOffset;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return ThriftLanguage.INSTANCE;
	}
}
