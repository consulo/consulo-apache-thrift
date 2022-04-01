package com.intellij.plugins.thrift.completion;

import com.intellij.plugins.thrift.ThriftLanguage;
import consulo.codeEditor.Editor;
import consulo.language.editor.action.TypedHandlerDelegate;
import consulo.language.psi.PsiFile;
import consulo.project.Project;

import javax.annotation.Nonnull;

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
