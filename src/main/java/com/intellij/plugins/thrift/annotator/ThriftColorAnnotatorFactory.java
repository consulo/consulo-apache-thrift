package com.intellij.plugins.thrift.annotator;

import com.intellij.plugins.thrift.ThriftLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.annotation.Annotator;
import consulo.language.editor.annotation.AnnotatorFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 27-Jun-22
 */
@ExtensionImpl
public class ThriftColorAnnotatorFactory implements AnnotatorFactory
{
	@Nullable
	@Override
	public Annotator createAnnotator()
	{
		return new ThriftColorAnnotator();
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return ThriftLanguage.INSTANCE;
	}
}
