package com.intellij.plugins.thrift.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NonNls;
import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.thrift.ThriftClassContributor;
import com.intellij.plugins.thrift.ThriftFileType;
import com.intellij.plugins.thrift.lang.psi.ThriftCustomType;
import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftDefinitionName;
import com.intellij.plugins.thrift.lang.psi.ThriftInclude;
import com.intellij.plugins.thrift.lang.psi.ThriftPrefixReference;
import com.intellij.plugins.thrift.lang.psi.ThriftTypeReference;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.PathUtil;
import com.intellij.util.Processor;

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
    for (ChooseByNameContributor contributor : ChooseByNameContributor.CLASS_EP_NAME.getExtensionList()) {
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
