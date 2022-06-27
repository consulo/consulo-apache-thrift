package com.intellij.plugins.thrift.highlight;

import com.intellij.plugins.thrift.ThriftLanguage;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftSyntaxHighlighterFactory extends SyntaxHighlighterFactory
{
	@Nonnull
	@Override
	public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile)
	{
		return new ThriftSyntaxHighlighter();
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return ThriftLanguage.INSTANCE;
	}
}
