/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.plugins.thrift.lang.parser;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypeSets;
import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes;
import com.intellij.plugins.thrift.lang.lexer._ThriftLexer;
import com.intellij.plugins.thrift.lang.psi.ThriftFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import consulo.lang.LanguageVersion;

public class ThriftParserDefinition implements ParserDefinition {
  @Override
  @NotNull
  public Lexer createLexer(@NotNull LanguageVersion languageVersion) {
    return new _ThriftLexer();
  }

  @NotNull
  @Override
  public PsiParser createParser(@NotNull LanguageVersion languageVersion) {
    return new ThriftParser();
  }

  @NotNull
  @Override
  public IFileElementType getFileNodeType() {
    return ThriftTokenTypeSets.THRIFT_FILE;
  }

  @Override
  @NotNull
  public TokenSet getWhitespaceTokens(@NotNull LanguageVersion languageVersion) {
    return ThriftTokenTypeSets.WHITESPACES;
  }

  @Override
  @NotNull
  public TokenSet getCommentTokens(@NotNull LanguageVersion languageVersion) {
    return ThriftTokenTypeSets.COMMENTS;
  }

  @Override
  @NotNull
  public TokenSet getStringLiteralElements(@NotNull LanguageVersion languageVersion) {
    return ThriftTokenTypeSets.STRINGS;
  }

  @Override
  @NotNull
  public PsiElement createElement(ASTNode node) {
    return ThriftTokenTypes.Factory.createElement(node);
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new ThriftFile(viewProvider);
  }

  @NotNull
  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }
}
