package com.intellij.plugins.thrift;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
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
