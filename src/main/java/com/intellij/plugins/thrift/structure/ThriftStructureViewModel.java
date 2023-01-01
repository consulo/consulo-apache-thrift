/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.plugins.thrift.structure;

import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftSubDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftTopLevelDeclaration;
import consulo.application.AllIcons;
import consulo.fileEditor.structureView.StructureViewModel;
import consulo.fileEditor.structureView.StructureViewTreeElement;
import consulo.fileEditor.structureView.tree.*;
import consulo.ide.IdeBundle;
import consulo.language.editor.structureView.StructureViewModelBase;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;

/**
 * @author Fedor.Korotkov
 */
public class ThriftStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
  public ThriftStructureViewModel(@Nonnull PsiFile psiFile) {
    super(psiFile, new ThriftStructureViewElement(psiFile));
    withSorters(Sorter.ALPHA_SORTER);
    withSuitableClasses(ThriftDeclaration.class);
  }

  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
    return false;
  }

  @Nonnull
  @Override
  public Filter[] getFilters() {
    return new Filter[]{ourFieldsFilter};
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement element) {
    final Object value = element.getValue();
    return value instanceof ThriftSubDeclaration;
  }

  @Override
  public boolean shouldEnterElement(Object element) {
    return element instanceof ThriftTopLevelDeclaration;
  }


  private static final Filter ourFieldsFilter = new Filter() {
    @NonNls
    public static final String ID = "SHOW_FIELDS";

    @Override
    public boolean isVisible(TreeElement treeNode) {
      if (!(treeNode instanceof ThriftStructureViewElement)) {
        return true;
      }
      final PsiElement element = ((ThriftStructureViewElement) treeNode).getRealElement();

      return !(element instanceof ThriftSubDeclaration);
    }

    @Override
    public boolean isReverted() {
      return true;
    }

    @Override
    @Nonnull
    public ActionPresentation getPresentation() {
      return new ActionPresentationData(IdeBundle.message("action.structureview.show.fields"), null, AllIcons.Nodes.Field);
    }

    @Override
    @Nonnull
    public String getName() {
      return ID;
    }
  };
}
