package com.intellij.plugins.thrift.highlight;

import com.intellij.plugins.thrift.lang.lexer._ThriftLexer;
import consulo.colorScheme.TextAttributesKey;
import consulo.language.ast.IElementType;
import consulo.language.editor.highlight.SyntaxHighlighterBase;
import consulo.language.lexer.Lexer;

import jakarta.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypeSets.BAD_TOKENS;
import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypeSets.OPERATORS;
import static com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes.*;

/**
 * Created by fkorotkov.
 */
public class ThriftSyntaxHighlighter extends SyntaxHighlighterBase {
  private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

  static {
    safeMap(ATTRIBUTES, OPERATORS, ThriftSyntaxHighlighterColors.OPERATION_SIGN);

    ATTRIBUTES.put(NUMBER, ThriftSyntaxHighlighterColors.NUMBER);
    ATTRIBUTES.put(INTEGER, ThriftSyntaxHighlighterColors.NUMBER);

    ATTRIBUTES.put(LITERAL, ThriftSyntaxHighlighterColors.STRING);

    ATTRIBUTES.put(LEFTBRACE, ThriftSyntaxHighlighterColors.PARENTHS);
    ATTRIBUTES.put(RIGHTBRACE, ThriftSyntaxHighlighterColors.PARENTHS);

    ATTRIBUTES.put(LEFTCURLYBRACE, ThriftSyntaxHighlighterColors.BRACES);
    ATTRIBUTES.put(RIGHTCURLYBRACE, ThriftSyntaxHighlighterColors.BRACES);

    ATTRIBUTES.put(LEFTBRACKET, ThriftSyntaxHighlighterColors.BRACKETS);
    ATTRIBUTES.put(RIGHTBRACKET, ThriftSyntaxHighlighterColors.BRACKETS);

    ATTRIBUTES.put(COMMA, ThriftSyntaxHighlighterColors.COMMA);
    ATTRIBUTES.put(SEMICOLON, ThriftSyntaxHighlighterColors.SEMICOLON);

    ATTRIBUTES.put(BLOCKCOMMENT, ThriftSyntaxHighlighterColors.BLOCK_COMMENT);
    ATTRIBUTES.put(COMMENT, ThriftSyntaxHighlighterColors.LINE_COMMENT);

      safeMap(ATTRIBUTES, BAD_TOKENS, ThriftSyntaxHighlighterColors.BAD_CHARACTER);
  }

  @Override
  @Nonnull
  public Lexer getHighlightingLexer() {
    return new _ThriftLexer();
  }

  @Override
  @Nonnull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(ATTRIBUTES.get(tokenType));
  }
}
