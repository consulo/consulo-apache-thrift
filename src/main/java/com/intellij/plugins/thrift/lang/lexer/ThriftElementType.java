package com.intellij.plugins.thrift.lang.lexer;

import com.intellij.plugins.thrift.ThriftLanguage;
import consulo.language.ast.IElementType;

import jakarta.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
public class ThriftElementType extends IElementType {
  public ThriftElementType(@Nonnull String debugName) {
    super(debugName, ThriftLanguage.INSTANCE);
  }
}
