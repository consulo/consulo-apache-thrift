package com.intellij.plugins.thrift.editor;

import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import com.intellij.plugins.thrift.util.ThriftPsiUtil;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.AllIcons;
import consulo.codeEditor.markup.GutterIconRenderer;
import consulo.language.Language;
import consulo.language.editor.Pass;
import consulo.language.editor.gutter.LineMarkerInfo;
import consulo.language.editor.gutter.LineMarkerProvider;
import consulo.language.editor.localize.DaemonLocalize;
import consulo.language.editor.ui.navigation.PsiTargetNavigationService;
import consulo.language.psi.NavigatablePsiElement;
import consulo.language.psi.PsiElement;
import consulo.localize.LocalizeValue;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftLineMarkerProvider implements LineMarkerProvider {
    private final PsiTargetNavigationService myPsiTargetNavigationService;

    @Inject
    public ThriftLineMarkerProvider(PsiTargetNavigationService psiTargetNavigationService) {
        myPsiTargetNavigationService = psiTargetNavigationService;
    }

    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(@Nonnull PsiElement element) {
        if (element instanceof ThriftDefinitionName) {
            return findImplementationsAndCreateMarker((ThriftDefinitionName) element);
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
            element -> DaemonLocalize.interfaceIsImplementedTooMany().get(),
            (e, elt) -> myPsiTargetNavigationService
                .newNavigator(() -> implementations)
                .title(DaemonLocalize.navigationTitleImplementationMethod(definitionName.getText(), implementations.size()))
                .findUsagesTitle(LocalizeValue.localizeTODO("Implementations of " + definitionName.getText()))
                .navigate(e, definitionName.getProject()),
            GutterIconRenderer.Alignment.RIGHT
        );
    }

    @Nonnull
    @Override
    public Language getLanguage() {
        return ThriftLanguage.INSTANCE;
    }
}
