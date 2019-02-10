package com.intellij.plugins.thrift;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NonNls;

import javax.annotation.Nullable;
import consulo.apache.thrift.ThriftIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.ui.image.Image;

/**
 * Created by fkorotkov.
 */
public class ThriftFileType extends LanguageFileType
{
	public static final ThriftFileType INSTANCE = new ThriftFileType();

	@NonNls
	public static final String DEFAULT_EXTENSION = "thrift";

	protected ThriftFileType()
	{
		super(ThriftLanguage.INSTANCE);
	}

	@Nonnull
	@Override
	public String getName()
	{
		return ThriftBundle.message("thrift.name");
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

	@Nullable
	@Override
	public Image getIcon()
	{
		return ThriftIcons.FileTypes.ApacheThrift;
	}
}
