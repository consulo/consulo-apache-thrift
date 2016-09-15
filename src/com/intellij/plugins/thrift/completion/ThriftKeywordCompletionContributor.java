package com.intellij.plugins.thrift.completion;

import static com.intellij.patterns.PlatformPatterns.psiElement;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes;
import com.intellij.plugins.thrift.util.ThriftUtils;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.util.ProcessingContext;
import consulo.codeInsight.completion.CompletionProvider;

/**
 * Created by fkorotkov.
 */
public class ThriftKeywordCompletionContributor extends CompletionContributor
{
	public ThriftKeywordCompletionContributor()
	{
		extend(CompletionType.BASIC, psiElement().andOr(psiElement(ThriftTokenTypes.IDENTIFIER), psiElement(ThriftTokenTypes.STIDENTIFIER)), new CompletionProvider()
		{
			@Override
			public void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result)
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

	private Collection<String> suggestKeywords(@NotNull PsiElement position)
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
}
