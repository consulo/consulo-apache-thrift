package com.intellij.plugins.thrift.util;

import com.intellij.plugins.thrift.ThriftClassContributor;
import com.intellij.plugins.thrift.ThriftFileType;
import com.intellij.plugins.thrift.lang.psi.*;
import consulo.application.Application;
import consulo.application.util.function.Processor;
import consulo.ide.navigation.ChooseByNameContributor;
import consulo.ide.navigation.GotoClassOrTypeContributor;
import consulo.language.impl.psi.LeafPsiElement;
import consulo.language.psi.*;
import consulo.language.psi.path.FileReferenceSet;
import consulo.module.content.ProjectRootManager;
import consulo.navigation.NavigationItem;
import consulo.util.io.FileUtil;
import consulo.util.io.PathUtil;
import consulo.util.lang.StringUtil;
import consulo.util.lang.ref.Ref;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fkorotkov.
 */
public class ThriftPsiUtil {
  @Nullable
  public static PsiFile resolveInclude(@Nullable ThriftInclude include) {
    if (include == null) {
      return null;
    }
    final PsiFileSystemItem target = getReferenceSet(include).resolve();
    if (target instanceof PsiFile) {
      return (PsiFile)target;
    }
    // check current dir
    PsiFile psiFile = include.getContainingFile();
    if (psiFile == null) {
      return null;
    }
    PsiDirectory directory = psiFile.getContainingDirectory();
    String includePath = include.getPath();
    PsiFile fileInDir = directory != null ? directory.findFile(PathUtil.getFileName(includePath)) : null;
    if (fileInDir != null) {
      return fileInDir;
    }

    ProjectRootManager rootManager = ProjectRootManager.getInstance(include.getProject());
    VirtualFile[] contentRoots = rootManager.getContentSourceRoots();
    for (VirtualFile contentRoot : contentRoots) {
      VirtualFile includedVirtualFile = contentRoot.findFileByRelativePath(includePath);
      if (includedVirtualFile != null) {
        return include.getManager().findFile(includedVirtualFile);
      }
    }

    return null;
  }

  @Nonnull
  public static PsiReference[] getReferences(@Nonnull ThriftInclude include) {
    return getReferenceSet(include).getAllReferences();
  }

  @Nonnull
  private static FileReferenceSet getReferenceSet(@Nonnull ThriftInclude include) {
    final PsiElement element = include.getLastChild();
    final String path = getPath(include);
    return new FileReferenceSet(
      path, include, element.getStartOffsetInParent() + 1, null, true, true, new FileType[]{ThriftFileType.INSTANCE}
    );
  }

  @Nonnull
  public static String getPath(ThriftInclude include) {
    PsiElement element = include.getLastChild();
    return StringUtil.unquoteString(element.getText());
  }

  @Nonnull
  public static PsiReference[] getReferences(ThriftCustomType type) {
    String text = type.getText();
    int index = text.lastIndexOf(".");
    if (index == -1) {
      return new PsiReference[]{new ThriftTypeReference(type, 0)};
    }
    else {
      return new PsiReference[]{
        new ThriftPrefixReference(type, index),
        new ThriftTypeReference(type, index + 1)
      };
    }
  }

  @Nullable
  public static ThriftDefinitionName findDeclaration(@Nonnull final String name, @Nullable PsiFile containingFile) {
    if (containingFile == null) {
      return null;
    }
    final Ref<ThriftDefinitionName> result = new Ref<ThriftDefinitionName>();
    processDeclarations(containingFile, new Processor<ThriftDeclaration>() {
      @Override
      public boolean process(ThriftDeclaration declaration) {
        if (name.equals(declaration.getName())) {
          result.set(declaration.getIdentifier());
        }
        return true;
      }
    });
    return result.get();
  }

  public static void processDeclarations(@Nullable PsiFile psiFile, @Nonnull Processor<ThriftDeclaration> processor) {
    processElements(psiFile, processor, ThriftDeclaration.class);
  }

  @Nullable
  public static ThriftInclude findImportByPrefix(@Nonnull PsiFile psiFile, @Nonnull final String fileName) {
    final Ref<ThriftInclude> result = new Ref<ThriftInclude>();
    processIncludes(psiFile, new Processor<ThriftInclude>() {
      @Override
      public boolean process(ThriftInclude include) {
        String path = include.getPath();
        if (FileUtil.getNameWithoutExtension(path).endsWith(fileName)) {
          result.set(include);
          return false;
        }
        return true;
      }
    });
    return result.get();
  }

  public static void processIncludes(@Nullable PsiFile psiFile, @Nonnull Processor<ThriftInclude> processor) {
    processElements(psiFile, processor, ThriftInclude.class);
  }

  public static <T> void processElements(@Nullable PsiFile psiFile, @Nonnull Processor<T> processor, Class<? extends T> clazz) {
    if (psiFile == null) {
      return;
    }
    for (PsiElement child : psiFile.getChildren()) {
      if (clazz.isInstance(child) && !processor.process((T)child)) {
        break;
      }
    }
  }

  @Nonnull
  public static List<NavigatablePsiElement> findImplementations(ThriftDefinitionName definitionName) {
    final List<NavigatablePsiElement> implementations = new ArrayList<NavigatablePsiElement>();
    processImplementations(definitionName, new Processor<NavigatablePsiElement>() {
      @Override
      public boolean process(NavigatablePsiElement element) {
        implementations.add(element);
        return true;
      }
    });
    return implementations;
  }

  public static void processImplementations(ThriftDefinitionName definitionName, @Nonnull Processor<NavigatablePsiElement> processor) {
    String name = definitionName.getText();
    for (ChooseByNameContributor contributor : Application.get().getExtensionList(GotoClassOrTypeContributor.class)) {
      if (!(contributor instanceof ThriftClassContributor)) {
        for (NavigationItem navigationItem : contributor.getItemsByName(name, name, definitionName.getProject(), false)) {
          if (navigationItem instanceof NavigatablePsiElement && !processor.process((NavigatablePsiElement)navigationItem)) {
            return;
          }
        }
      }
    }
  }

  @Nonnull
  public static PsiElement setName(@Nonnull ThriftDefinitionName definitionName, String name) {
    PsiElement child = definitionName.getFirstChild();
    if (child instanceof LeafPsiElement) {
      ((LeafPsiElement)child).replaceWithText(name);
    }
    return definitionName;
  }

  @Nullable
  @NonNls
  public static String getName(@Nonnull ThriftDefinitionName definitionName) {
    return definitionName.getText();
  }

  @Nonnull
  public static PsiElement getNameIdentifier(@Nonnull ThriftDefinitionName definitionName) {
    return definitionName;
  }
}
