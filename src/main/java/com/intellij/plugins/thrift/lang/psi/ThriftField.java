// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ThriftField extends ThriftSubDeclaration {

  @Nullable
  ThriftConstValue getConstValue();

  @Nullable
  ThriftDefinitionName getDefinitionName();

  @Nullable
  ThriftFieldID getFieldID();

  @Nullable
  ThriftFieldReq getFieldReq();

  @Nonnull
  ThriftFieldType getFieldType();

  @Nullable
  ThriftListSeparator getListSeparator();

  @Nullable
  ThriftTypeAnnotations getTypeAnnotations();

  @Nullable
  ThriftXsdFieldOptions getXsdFieldOptions();

}
