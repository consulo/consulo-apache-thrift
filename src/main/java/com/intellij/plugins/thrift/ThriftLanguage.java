package com.intellij.plugins.thrift;

import consulo.language.Language;

/**
 * Created by fkorotkov.
 */
public class ThriftLanguage extends Language {
  public static ThriftLanguage INSTANCE = new ThriftLanguage();

  protected ThriftLanguage() {
    super("thrift");
  }

  @Override
  public String getDisplayName() {
    return "Apache Thrift";
  }
}
