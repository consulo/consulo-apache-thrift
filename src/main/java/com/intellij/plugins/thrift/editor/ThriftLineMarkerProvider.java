package com.intellij.plugins.thrift.editor;

import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import com.intellij.plugins.thrift.util.ThriftPsiUtil;
import consulo.application.AllIcons;
import consulo.codeEditor.markup.GutterIconRenderer;
import consulo.language.editor.DaemonBundle;
import consulo.language.editor.Pass;
import consulo.language.editor.gutter.GutterIconNavigationHandler;
import consulo.language.editor.gutter.LineMarkerInfo;
import consulo.language.editor.gutter.LineMarkerProvider;
import consulo.language.editor.ui.DefaultPsiElementCellRenderer;
import consulo.language.editor.ui.PsiElementListNavigator;
import consulo.language.psi.NavigatablePsiElement;
import consulo.language.psi.PsiElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.Function;

/**
 * Created by fkorotkov.
 */
public class ThriftLineMarkerProvider implements LineMarkerProvider {
  @Nullable
  @Override
  public LineMarkerInfo getLineMarkerInfo(@Nonnull PsiElement element) {
    if (element instanceof ThriftDefinitionName) {
      return findImplementationsAndCreateMarker((ThriftDefinitionName)element);
    }
    return null;
  }

  @Nullable
  private LineMarkerInfo findImplementationsAndCreateMarker(final ThriftDefinitionName definitionName) {
    final List<NavigatablePsiElement> implementations = ThriftPsiUtil.findImplementations(definitionName);
    if (implementations.isEmpty()) {
      return null;
    }
    return new LineMarkerInfo<PsiElement>(
      definitionName,
      definitionName.getTextRange(),
      AllIcons.Gutter.ImplementedMethod,
      Pass.UPDATE_ALL,
      new Function<PsiElement, String>() {
        @Override
        public String apply(PsiElement element) {
          return DaemonBundle.message("interface.is.implemented.too.many");
        }
      },
      new GutterIconNavigationHandler<PsiElement>() {
        @Override
        public void navigate(MouseEvent e, PsiElement elt) {
          PsiElementListNavigator.openTargets(
            e,
            implementations.toArray(new NavigatablePsiElement[implementations.size()]),
            DaemonBundle.message("navigation.title.implementation.method", definitionName.getText(), implementations.size()),
            "Implementations of " + definitionName.getText(),
            new DefaultPsiElementCellRenderer()
          );
        }
      },
      GutterIconRenderer.Alignment.RIGHT
    );
  }
}
