package com.intellij.plugins.thrift;

import consulo.apache.thrift.icon.ThriftIconGroup;
import consulo.apache.thrift.localize.ThriftLocalize;
import consulo.language.file.LanguageFileType;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;

import jakarta.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
public class ThriftFileType extends LanguageFileType
{
	public static final ThriftFileType INSTANCE = new ThriftFileType();

	public static final String DEFAULT_EXTENSION = "thrift";

	protected ThriftFileType()
	{
		super(ThriftLanguage.INSTANCE);
	}

	@Nonnull
	@Override
	public String getId()
	{
		return "Thrift";
	}

	@Nonnull
	@Override
	public LocalizeValue getDescription()
	{
		return ThriftLocalize.thriftDescription();
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return DEFAULT_EXTENSION;
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return ThriftIconGroup.filetypesApachethrift();
	}
}
