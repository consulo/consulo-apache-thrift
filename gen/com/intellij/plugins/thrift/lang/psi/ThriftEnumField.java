// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ThriftEnumField extends ThriftTopLevelDeclaration {

  @NotNull
  ThriftDefinitionName getDefinitionName();

  @Nullable
  ThriftIntConstant getIntConstant();

  @Nullable
  ThriftListSeparator getListSeparator();

  @Nullable
  ThriftTypeAnnotations getTypeAnnotations();

}
