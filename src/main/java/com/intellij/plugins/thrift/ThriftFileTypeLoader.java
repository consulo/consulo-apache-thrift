package com.intellij.plugins.thrift;

import consulo.virtualFileSystem.fileType.FileTypeConsumer;
import consulo.virtualFileSystem.fileType.FileTypeFactory;

import javax.annotation.Nonnull;

/**
 * Created by fkorotkov.
 */
public class ThriftFileTypeLoader extends FileTypeFactory {
  @Override
  public void createFileTypes(@Nonnull FileTypeConsumer fileTypeConsumer) {
    fileTypeConsumer.consume(ThriftFileType.INSTANCE);
  }
}
