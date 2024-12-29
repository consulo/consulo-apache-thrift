package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypeSets;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.ast.TokenType;
import consulo.language.editor.action.FileQuoteHandler;
import consulo.language.editor.action.SimpleTokenSetQuoteHandler;
import consulo.util.collection.ArrayUtil;
import consulo.virtualFileSystem.fileType.FileType;

import jakarta.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftQuoteHandler extends SimpleTokenSetQuoteHandler implements FileQuoteHandler
{
	public ThriftQuoteHandler()
	{
		super(ArrayUtil.append(ThriftTokenTypeSets.STRINGS.getTypes(), TokenType.BAD_CHARACTER));
	}

	@Nonnull
	@Override
	public FileType getFileType()
	{
		return ThriftFileType.INSTANCE;
	}
}
