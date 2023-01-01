package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.CodeDocumentationAwareCommenter;
import consulo.language.Language;
import consulo.language.ast.IElementType;
import consulo.language.psi.PsiComment;

import javax.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftCommenter implements CodeDocumentationAwareCommenter
{
	@Override
	public String getLineCommentPrefix()
	{
		return "//";
	}

	@Override
	public String getBlockCommentPrefix()
	{
		return "/*";
	}

	@Override
	public String getBlockCommentSuffix()
	{
		return "*/";
	}

	@Override
	public String getCommentedBlockCommentPrefix()
	{
		return null;
	}

	@Override
	public String getCommentedBlockCommentSuffix()
	{
		return null;
	}

	@Override
	public IElementType getLineCommentTokenType()
	{
		return ThriftTokenTypes.COMMENT;
	}

	@Override
	public IElementType getBlockCommentTokenType()
	{
		return ThriftTokenTypes.BLOCKCOMMENT;
	}

	@Override
	public IElementType getDocumentationCommentTokenType()
	{
		return null;
	}

	@Override
	public String getDocumentationCommentPrefix()
	{
		return "/**";
	}

	@Override
	public String getDocumentationCommentLinePrefix()
	{
		return "*";
	}

	@Override
	public String getDocumentationCommentSuffix()
	{
		return "**/";
	}

	@Override
	public boolean isDocumentationComment(PsiComment element)
	{
		//noinspection ConstantConditions
		return element.getText().startsWith(getDocumentationCommentPrefix());
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return ThriftLanguage.INSTANCE;
	}
}
