package com.intellij.plugins.thrift.lang.psi.presentation;

import javax.annotation.Nullable;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProvider;
import com.intellij.openapi.util.Iconable;
import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import consulo.ide.IconDescriptorUpdaters;
import consulo.ui.image.Image;

/**
 * Created by fkorotkov.
 */
public class ThriftTopLevelDeclarationPresentationProvider implements ItemPresentationProvider<ThriftDeclaration> {
  @Override
  public ItemPresentation getPresentation(final ThriftDeclaration item) {
    return new ItemPresentation() {
      @Nullable
      @Override
      public String getPresentableText() {
        return item.getName();
      }

      @Nullable
      @Override
      public String getLocationString() {
        return item.getContainingFile().getName();
      }

      @Nullable
      @Override
      public Image getIcon() {
        return IconDescriptorUpdaters.getIcon(item, Iconable.ICON_FLAG_VISIBILITY);
      }
    };
  }
}
