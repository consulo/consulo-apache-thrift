package com.intellij.plugins.thrift;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import consulo.apache.thrift.icon.ThriftIconGroup;
import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.ui.image.Image;

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
	public String getDescription()
	{
		return ThriftBundle.message("thrift.description");
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
		return ThriftIconGroup.fileTypesApacheThrift();
	}
}
