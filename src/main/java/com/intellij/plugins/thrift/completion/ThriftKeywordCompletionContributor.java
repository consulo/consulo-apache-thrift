package com.intellij.plugins.thrift.completion;

import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes;
import com.intellij.plugins.thrift.util.ThriftUtils;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.completion.*;
import consulo.language.editor.completion.lookup.LookupElementBuilder;
import consulo.language.impl.ast.TreeUtil;
import consulo.language.impl.parser.GeneratedParserUtilBase;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiFileFactory;
import consulo.language.util.ProcessingContext;

import javax.annotation.Nonnull;
import java.util.Collection;

import static consulo.language.pattern.StandardPatterns.psiElement;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftKeywordCompletionContributor extends CompletionContributor
{
	public ThriftKeywordCompletionContributor()
	{
		extend(CompletionType.BASIC, psiElement().andOr(psiElement(ThriftTokenTypes.IDENTIFIER), psiElement(ThriftTokenTypes.STIDENTIFIER)), new CompletionProvider()
		{
			@Override
			public void addCompletions(@Nonnull CompletionParameters parameters, ProcessingContext context, @Nonnull CompletionResultSet result)
			{
				final Collection<String> suggestedKeywords = suggestKeywords(parameters.getPosition());
				suggestedKeywords.retainAll(ThriftUtils.getKeywords());
				for(String keyword : suggestedKeywords)
				{
					result.addElement(LookupElementBuilder.create(keyword));
				}
			}
		});
	}

	private Collection<String> suggestKeywords(@Nonnull PsiElement position)
	{
		PsiFile psiFile = position.getContainingFile();
		PsiElement topLevelElement = position;
		while(!(topLevelElement.getParent() instanceof PsiFile))
		{
			topLevelElement = topLevelElement.getParent();
		}
		PsiFile file = PsiFileFactory.getInstance(psiFile.getProject()).createFileFromText("a.thrift", ThriftLanguage.INSTANCE, topLevelElement.getText(), true, false);
		GeneratedParserUtilBase.CompletionState state = new GeneratedParserUtilBase.CompletionState(position.getTextOffset() - topLevelElement.getTextOffset());
		file.putUserData(GeneratedParserUtilBase.COMPLETION_STATE_KEY, state);
		TreeUtil.ensureParsed(file.getNode());
		return state.items;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return Language.ANY;
	}
}
