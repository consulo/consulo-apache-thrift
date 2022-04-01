package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypeSets;
import consulo.language.ast.TokenType;
import consulo.language.editor.action.SimpleTokenSetQuoteHandler;
import consulo.util.collection.ArrayUtil;

/**
 * Created by fkorotkov.
 */
public class ThriftQuoteHandler extends SimpleTokenSetQuoteHandler {
  public ThriftQuoteHandler() {
    super(ArrayUtil.append(ThriftTokenTypeSets.STRINGS.getTypes(), TokenType.BAD_CHARACTER));
  }
}
