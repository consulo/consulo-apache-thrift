// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ThriftFunction extends ThriftSubDeclaration {

  @Nonnull
  ThriftDefinitionName getDefinitionName();

  @Nonnull
  List<ThriftField> getFieldList();

  @Nonnull
  ThriftFunctionType getFunctionType();

  @Nullable
  ThriftThrows getThrows();

  @Nullable
  ThriftTypeAnnotations getTypeAnnotations();

}
