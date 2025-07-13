package com.intellij.plugins.thrift;

import consulo.apache.thrift.localize.ThriftLocalize;
import consulo.language.Language;
import consulo.localize.LocalizeValue;
import jakarta.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
public class ThriftLanguage extends Language {
  public static ThriftLanguage INSTANCE = new ThriftLanguage();

  protected ThriftLanguage() {
    super("thrift");
  }

  @Nonnull
  @Override
  public LocalizeValue getDisplayName() {
    return ThriftLocalize.thriftLanguageDisplayName();
  }
}
