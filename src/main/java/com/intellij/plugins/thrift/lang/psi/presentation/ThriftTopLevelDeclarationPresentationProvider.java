package com.intellij.plugins.thrift.lang.psi.presentation;

import javax.swing.Icon;

import org.jetbrains.annotations.Nullable;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.ItemPresentationProvider;
import com.intellij.openapi.util.Iconable;
import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import consulo.ide.IconDescriptorUpdaters;

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
      public Icon getIcon(boolean unused) {
        return IconDescriptorUpdaters.getIcon(item, Iconable.ICON_FLAG_VISIBILITY);
      }
    };
  }
}
