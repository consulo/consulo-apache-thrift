package com.intellij.plugins.thrift.highlight;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
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
