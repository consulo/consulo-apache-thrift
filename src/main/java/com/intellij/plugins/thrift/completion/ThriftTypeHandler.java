package com.intellij.plugins.thrift.completion;

import javax.annotation.Nonnull;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.psi.PsiFile;

/**
 * Created by fkorotkov.
 */
public class ThriftTypeHandler extends TypedHandlerDelegate {
  @Override
  public Result charTyped(char c, Project project, @Nonnull Editor editor, @Nonnull PsiFile file) {
    if (c == '<' && file.getLanguage() instanceof ThriftLanguage) {
      int offset = editor.getCaretModel().getOffset();
      editor.getDocument().insertString(offset, ">");
      editor.getCaretModel().moveToOffset(offset);
      return Result.STOP;
    }
    return super.charTyped(c, project, editor, file);
  }
}
