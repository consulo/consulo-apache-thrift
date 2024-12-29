package com.intellij.plugins.thrift.inspections;

import com.intellij.plugins.thrift.ThriftBundle;
import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.plugins.thrift.lang.psi.ThriftCustomType;
import com.intellij.plugins.thrift.lang.psi.ThriftVisitor;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.inspection.LocalInspectionTool;
import consulo.language.editor.inspection.ProblemDescriptor;
import consulo.language.editor.inspection.ProblemHighlightType;
import consulo.language.editor.inspection.scheme.InspectionManager;
import consulo.language.editor.rawHighlight.HighlightDisplayLevel;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiReference;
import consulo.util.collection.ArrayUtil;
import org.jetbrains.annotations.Nls;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ExtensionImpl
public class ThriftUnresolvedSymbolInspection extends LocalInspectionTool
{
	@Nullable
	@Override
	public Language getLanguage()
	{
		return ThriftLanguage.INSTANCE;
	}

	@Nonnull
	public String getGroupDisplayName()
	{
		return ThriftBundle.message("inspections.group.name");
	}

	@Nls
	@Nonnull
	@Override
	public String getDisplayName()
	{
		return ThriftBundle.message("thrift.inspection.unresolved.symbol");
	}

	@Override
	public boolean isEnabledByDefault()
	{
		return true;
	}

	@Nonnull
	@Override
	public String getShortName()
	{
		return "ThriftUnresolvedSymbol";
	}

	@Nonnull
	@Override
	public HighlightDisplayLevel getDefaultLevel()
	{
		return HighlightDisplayLevel.ERROR;
	}

	@Nullable
	@Override
	public ProblemDescriptor[] checkFile(@Nonnull PsiFile file, @Nonnull final InspectionManager manager, final boolean isOnTheFly)
	{
		final List<ProblemDescriptor> result = new ArrayList<ProblemDescriptor>();
		new ThriftVisitor()
		{
			@Override
			public void visitCustomType(@Nonnull ThriftCustomType type)
			{
				for(PsiReference reference : type.getReferences())
				{
					if(reference.resolve() == null)
					{
						result.add(manager.createProblemDescriptor(
								reference.getElement(),
								reference.getRangeInElement(),
								getDisplayName(),
								ProblemHighlightType.ERROR,
								isOnTheFly
						));
					}
				}
			}

			public void visitElement(PsiElement element)
			{
				super.visitElement(element);
				element.acceptChildren(this);
			}
		}.visitFile(file);
		return ArrayUtil.toObjectArray(result, ProblemDescriptor.class);
	}
}
