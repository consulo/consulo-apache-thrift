package com.intellij.plugins.thrift.index;

import com.intellij.plugins.thrift.ThriftFileType;
import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftTopLevelDeclaration;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.util.function.Processor;
import consulo.index.io.DataIndexer;
import consulo.index.io.EnumeratorStringDescriptor;
import consulo.index.io.ID;
import consulo.index.io.KeyDescriptor;
import consulo.index.io.data.DataExternalizer;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.language.psi.scope.GlobalSearchScope;
import consulo.language.psi.stub.FileBasedIndex;
import consulo.language.psi.stub.FileBasedIndexExtension;
import consulo.language.psi.stub.FileContent;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.*;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftSubDeclarationIndex extends FileBasedIndexExtension<String, String> {
  public static final ID<String, String> THRIFT_DECLARATION_INDEX = ID.create("ThriftSubDeclarationIndex");
  private final EnumeratorStringDescriptor myKeyDescriptor = new EnumeratorStringDescriptor();
  private final FileBasedIndex.InputFilter myFilter = new FileBasedIndex.InputFilter() {
    @Override
    public boolean acceptInput(Project project, VirtualFile file) {
      return file.getFileType() == ThriftFileType.INSTANCE;
    }
  };
  private static final ThriftSubDeclarationIndex.MyIndexer myIndexer = new MyIndexer();

  @Nonnull
  @Override
  public ID<String, String> getName() {
    return THRIFT_DECLARATION_INDEX;
  }

  @Nonnull
  @Override
  public DataIndexer<String, String, FileContent> getIndexer() {
    return myIndexer;
  }

  @Override
  public KeyDescriptor<String> getKeyDescriptor() {
    return myKeyDescriptor;
  }

  @Override
  public DataExternalizer<String> getValueExternalizer() {
    return myKeyDescriptor;
  }

  @Override
  public FileBasedIndex.InputFilter getInputFilter() {
    return myFilter;
  }

  @Override
  public boolean dependsOnFileContent() {
    return true;
  }

  @Override
  public int getVersion() {
    return 2;
  }

  public static List<String> findAllKeys(Project project, GlobalSearchScope scope) {
    final List<String> result = new ArrayList<String>();
    FileBasedIndex.getInstance().processAllKeys(
      THRIFT_DECLARATION_INDEX,
      new Processor<String>() {
        @Override
        public boolean process(String name) {
          result.add(name);
          return true;
        }
      },
      scope,
      null
    );
    return result;
  }

  public static List<ThriftDeclaration> findDeclaration(@Nullable final String className,
                                                        @Nonnull final String name,
                                                        @Nonnull Project project,
                                                        @Nonnull GlobalSearchScope scope) {
    final List<ThriftDeclaration> result = new ArrayList<ThriftDeclaration>();
    final PsiManager manager = PsiManager.getInstance(project);
    FileBasedIndex.getInstance().getFilesWithKey(
      THRIFT_DECLARATION_INDEX,
      Collections.singleton(name),
      new Processor<VirtualFile>() {
        @Override
        public boolean process(VirtualFile file) {
          PsiFile psiFile = manager.findFile(file);
          if (psiFile != null) {
            for (PsiElement child : psiFile.getChildren()) {
              if (!(child instanceof ThriftTopLevelDeclaration)) {
                continue;
              }
              if (className != null && !className.equals(((ThriftTopLevelDeclaration)child).getName())) {
                continue;
              }
              for (ThriftDeclaration declaration : ((ThriftTopLevelDeclaration)child).findSubDeclarations()) {
                String subName = declaration.getName();
                if (subName != null && name.equals(subName)) {
                  result.add(declaration);
                }
              }
            }
          }
          return true;
        }
      },
      scope
    );
    return result;
  }

  private static class MyIndexer implements DataIndexer<String, String, FileContent> {
    @Nonnull
    @Override
    public Map<String, String> map(FileContent inputData) {
      Map<String, String> result = new HashMap<String, String>();
      for (PsiElement child : inputData.getPsiFile().getChildren()) {
        if (child instanceof ThriftTopLevelDeclaration) {
          String topLevelName = ((ThriftTopLevelDeclaration)child).getName();
          if (topLevelName != null) {
            for (ThriftDeclaration declaration : ((ThriftTopLevelDeclaration)child).findSubDeclarations()) {
              String subName = declaration.getName();
              if (subName != null) {
                result.put(subName, topLevelName);
              }
            }
          }
        }
      }

      return result;
    }
  }
}
