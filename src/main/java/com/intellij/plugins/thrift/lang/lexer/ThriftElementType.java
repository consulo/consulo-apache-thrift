package com.intellij.plugins.thrift.lang.lexer;

import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
public class ThriftElementType extends IElementType {
  public ThriftElementType(@Nonnull @NonNls String debugName) {
    super(debugName, ThriftLanguage.INSTANCE);
  }
}
