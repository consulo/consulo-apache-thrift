package com.intellij.plugins.thrift.annotator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.plugins.thrift.highlight.ThriftSyntaxHighlighterColors;
import com.intellij.plugins.thrift.lang.lexer.ThriftTokenTypes;
import com.intellij.plugins.thrift.lang.psi.*;
import com.intellij.plugins.thrift.util.ThriftUtils;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;

/**
 * Created by fkorotkov.
 */
public class ThriftColorAnnotator extends ThriftVisitor implements Annotator
{
	private AnnotationHolder myAnnotationHolder;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder)
	{
		myAnnotationHolder = holder;
		element.accept(this);
	}

	@Override
	public void visitElement(PsiElement element)
	{
		if(element instanceof LeafPsiElement)
		{
			IElementType tokenType = ((LeafPsiElement) element).getElementType();
			if(tokenType == ThriftTokenTypes.IDENTIFIER && ThriftUtils.getKeywords().contains(element.getText()))
			{
				annotateKeyword(element);
			}
		}
	}

	@Override
	public void visitCustomType(@NotNull ThriftCustomType o)
	{
		PsiElement identifier = o.getIdentifier();
		for(PsiReference psiReference : o.getReferences())
		{
			PsiElement resolved = psiReference.resolve();
			if(resolved == null)
			{
				continue;
			}
			if(resolved instanceof ThriftDefinitionName)
			{
				resolved = resolved.getParent();
			}
			highlightName(resolved, identifier);
			break;
		}
	}

	@Override
	public void visitStruct(@NotNull ThriftStruct o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitUnion(@NotNull ThriftUnion o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitTypedef(@NotNull ThriftTypedef o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitService(@NotNull ThriftService o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitEnum(@NotNull ThriftEnum o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitException(@NotNull ThriftException o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitField(@NotNull ThriftField o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitConst(@NotNull ThriftConst o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitEnumField(@NotNull ThriftEnumField o)
	{
		highlightName(o, o.getDefinitionName());
	}

	@Override
	public void visitSenum(@NotNull ThriftSenum o)
	{
		highlightName(o, o.getDefinitionName());
	}

	private static TextAttributesKey getAttributesKey(PsiElement element)
	{
		if(element instanceof ThriftService || element instanceof ThriftUnion || element instanceof ThriftStruct || element instanceof
				ThriftException || element instanceof ThriftEnum || element instanceof ThriftSenum)
		{
			return DefaultLanguageHighlighterColors.CLASS_NAME;
		}
		else if(element instanceof ThriftTypedef)
		{
			return DefaultLanguageHighlighterColors.TYPE_ALIAS_NAME;
		}
		else if(element instanceof ThriftConst || element instanceof ThriftEnumField)
		{
			return DefaultLanguageHighlighterColors.STATIC_FIELD;
		}
		else if(element instanceof ThriftField)
		{
			if(element.getParent() instanceof ThriftThrows)
			{
				return DefaultLanguageHighlighterColors.LOCAL_VARIABLE;
			}
			return DefaultLanguageHighlighterColors.INSTANCE_FIELD;
		}
		return null;
	}

	private void highlightName(@NotNull PsiElement parent, @Nullable PsiElement target)
	{
		if(target == null)
		{
			return;
		}

		if(target instanceof ThriftDefinitionName)
		{
			target = ((ThriftDefinitionName) target).getNameIdentifier();
		}

		TextAttributesKey attributesKey = getAttributesKey(parent);
		if(attributesKey == null)
		{
			return;
		}
		Annotation annotation = myAnnotationHolder.createInfoAnnotation(target, null);
		annotation.setTextAttributes(attributesKey);
	}

	private void annotateKeyword(@NotNull PsiElement element)
	{
		final Annotation annotation = myAnnotationHolder.createInfoAnnotation(element, null);
		annotation.setTextAttributes(TextAttributesKey.find(ThriftSyntaxHighlighterColors.THRIFT_KEYWORD));
	}
}