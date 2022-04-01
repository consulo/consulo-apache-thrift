package com.intellij.plugins.thrift.highlight;

import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterFactory;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import javax.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
public class ThriftSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
  @Nonnull
  @Override
  public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
    return new ThriftSyntaxHighlighter();
  }
}
