package com.intellij.plugins.thrift.inspections;

import com.intellij.plugins.thrift.ThriftBundle;
import com.intellij.plugins.thrift.ThriftLanguage;
import com.intellij.plugins.thrift.lang.psi.ThriftInclude;
import com.intellij.plugins.thrift.lang.psi.ThriftVisitor;
import com.intellij.plugins.thrift.util.ThriftPsiUtil;
import consulo.annotation.component.ExtensionImpl;
import consulo.document.util.TextRange;
import consulo.language.Language;
import consulo.language.editor.inspection.LocalInspectionTool;
import consulo.language.editor.inspection.ProblemDescriptor;
import consulo.language.editor.inspection.ProblemHighlightType;
import consulo.language.editor.inspection.scheme.InspectionManager;
import consulo.language.editor.rawHighlight.HighlightDisplayLevel;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.util.collection.ArrayUtil;
import org.jetbrains.annotations.Nls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ExtensionImpl
public class ThriftUnresolvedIncludeInspection extends LocalInspectionTool
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
		return ThriftBundle.message("thrift.inspection.unresolved.include");
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
		return "ThriftUnresolvedInclude";
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
			public void visitInclude(@Nonnull ThriftInclude include)
			{
				if(ThriftPsiUtil.resolveInclude(include) == null)
				{
					PsiElement lastChild = include.getLastChild();
					result.add(manager.createProblemDescriptor(
							lastChild,
							TextRange.from(0, lastChild.getTextLength()),
							getDisplayName(),
							ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
							isOnTheFly
					));
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
