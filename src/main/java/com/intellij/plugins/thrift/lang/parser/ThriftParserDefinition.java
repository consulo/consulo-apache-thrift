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

import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypeSets;
import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes;
import com.intellij.plugins.thrift.lang.lexer._ThriftLexer;
import com.intellij.plugins.thrift.lang.psi.ThriftFile;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IFileElementType;
import consulo.language.ast.TokenSet;
import consulo.language.file.FileViewProvider;
import consulo.language.lexer.Lexer;
import consulo.language.parser.ParserDefinition;
import consulo.language.parser.PsiParser;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.version.LanguageVersion;

import javax.annotation.Nonnull;

@ExtensionImpl
public class ThriftParserDefinition implements ParserDefinition
{
	@Nonnull
	@Override
	public Language getLanguage()
	{
		return ThriftLanguage.INSTANCE;
	}

	@Override
	@Nonnull
	public Lexer createLexer(@Nonnull LanguageVersion languageVersion)
	{
		return new _ThriftLexer();
	}

	@Nonnull
	@Override
	public PsiParser createParser(@Nonnull LanguageVersion languageVersion)
	{
		return new ThriftParser();
	}

	@Nonnull
	@Override
	public IFileElementType getFileNodeType()
	{
		return ThriftTokenTypeSets.THRIFT_FILE;
	}

	@Override
	@Nonnull
	public TokenSet getWhitespaceTokens(@Nonnull LanguageVersion languageVersion)
	{
		return ThriftTokenTypeSets.WHITESPACES;
	}

	@Override
	@Nonnull
	public TokenSet getCommentTokens(@Nonnull LanguageVersion languageVersion)
	{
		return ThriftTokenTypeSets.COMMENTS;
	}

	@Override
	@Nonnull
	public TokenSet getStringLiteralElements(@Nonnull LanguageVersion languageVersion)
	{
		return ThriftTokenTypeSets.STRINGS;
	}

	@Override
	@Nonnull
	public PsiElement createElement(ASTNode node)
	{
		return ThriftTokenTypes.Factory.createElement(node);
	}

	@Override
	public PsiFile createFile(FileViewProvider viewProvider)
	{
		return new ThriftFile(viewProvider);
	}

	@Nonnull
	@Override
	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right)
	{
		return SpaceRequirements.MAY;
	}
}
