package com.intellij.plugins.thrift;

import consulo.annotation.component.ExtensionImpl;
import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;

import jakarta.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftFileTypeLoader extends FileTypeFactory {
  @Override
  public void createFileTypes(@Nonnull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(ThriftFileType.INSTANCE);
  }
}
