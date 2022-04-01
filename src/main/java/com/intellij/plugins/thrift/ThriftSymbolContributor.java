package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.index.ThriftSubDeclarationIndex;
import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import consulo.ide.navigation.ChooseByNameContributor;
import consulo.language.psi.scope.GlobalSearchScope;
import consulo.navigation.NavigationItem;
import consulo.project.Project;
import consulo.util.collection.ArrayUtil;

import javax.annotation.Nonnull;
import java.util.List;

public class ThriftSymbolContributor implements ChooseByNameContributor {
  @Nonnull
  @Override
  public String[] getNames(Project project, boolean includeNonProjectItems) {
    return ArrayUtil.toStringArray(ThriftSubDeclarationIndex.findAllKeys(project, getScope(project, includeNonProjectItems)));
  }

  @Nonnull
  @Override
  public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
    List<ThriftDeclaration> declarations =
      ThriftSubDeclarationIndex.findDeclaration(null, name, project, getScope(project, includeNonProjectItems));
    return declarations.toArray(new NavigationItem[declarations.size()]);
  }

  private GlobalSearchScope getScope(Project project, boolean includeNonProjectItems) {
    return includeNonProjectItems ? GlobalSearchScope.allScope(project) : GlobalSearchScope.projectScope(project);
  }
}
