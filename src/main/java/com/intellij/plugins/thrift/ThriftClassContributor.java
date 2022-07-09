package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.index.ThriftDeclarationIndex;
import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftTopLevelDeclaration;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.util.function.Processor;
import consulo.content.scope.SearchScope;
import consulo.ide.navigation.GotoClassOrTypeContributor;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.language.psi.search.FindSymbolParameters;
import consulo.language.psi.stub.FileBasedIndex;
import consulo.language.psi.stub.IdFilter;
import consulo.navigation.NavigationItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftClassContributor implements GotoClassOrTypeContributor
{
	@Override
	public void processNames(@Nonnull Processor<String> processor, @Nonnull SearchScope searchScope, @Nullable IdFilter idFilter)
	{
		FileBasedIndex.getInstance().processAllKeys(ThriftDeclarationIndex.THRIFT_DECLARATION_INDEX, processor, searchScope, idFilter);
	}

	@Override
	public void processElementsWithName(@Nonnull String name, @Nonnull Processor<NavigationItem> processor, @Nonnull FindSymbolParameters findSymbolParameters)
	{
		final PsiManager manager = PsiManager.getInstance(findSymbolParameters.getProject());
		FileBasedIndex.getInstance().getFilesWithKey(ThriftDeclarationIndex.THRIFT_DECLARATION_INDEX, Collections.singleton(name), file -> {
					PsiFile psiFile = manager.findFile(file);
					if(psiFile != null)
					{
						for(PsiElement child : psiFile.getChildren())
						{
							if(child instanceof ThriftTopLevelDeclaration && name.equals(((ThriftDeclaration) child).getName()))
							{
								if(!processor.process((ThriftDeclaration) child))
								{
									return false;
								}
							}
						}
					}
					return true;
				},
				findSymbolParameters.getSearchScope()
		);
	}
}
