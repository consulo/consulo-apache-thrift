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
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.language.psi.scope.GlobalSearchScope;
import consulo.language.psi.stub.FileBasedIndex;
import consulo.language.psi.stub.FileContent;
import consulo.language.psi.stub.ScalarIndexExtension;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;

import jakarta.annotation.Nonnull;
import java.util.*;

/**
 * Created by fkorotkov.
 */
@ExtensionImpl
public class ThriftDeclarationIndex extends ScalarIndexExtension<String> {
  public static final ID<String, Void> THRIFT_DECLARATION_INDEX = ID.create("ThriftDeclarationIndex");
  private final EnumeratorStringDescriptor myKeyDescriptor = new EnumeratorStringDescriptor();
  private final FileBasedIndex.InputFilter myFilter = new FileBasedIndex.InputFilter() {
    @Override
    public boolean acceptInput(Project project, VirtualFile file) {
      return file.getFileType() == ThriftFileType.INSTANCE;
    }
  };
  private static final ThriftDeclarationIndex.MyIndexer myIndexer = new MyIndexer();

  @Nonnull
  @Override
  public ID<String, Void> getName() {
    return THRIFT_DECLARATION_INDEX;
  }

  @Nonnull
  @Override
  public DataIndexer<String, Void, FileContent> getIndexer() {
    return myIndexer;
  }

  @Override
  public KeyDescriptor<String> getKeyDescriptor() {
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

  public static List<ThriftDeclaration> findDeclaration(@Nonnull final String name,
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
              if (child instanceof ThriftTopLevelDeclaration && name.equals(((ThriftDeclaration)child).getName())) {
                result.add((ThriftDeclaration)child);
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

  private static class MyIndexer implements DataIndexer<String, Void, FileContent> {
    @Nonnull
    @Override
    public Map<String, Void> map(FileContent inputData) {
      Map<String, Void> result = new HashMap<String, Void>();
      for (PsiElement child : inputData.getPsiFile().getChildren()) {
        if (child instanceof ThriftTopLevelDeclaration) {
          String name = ((ThriftDeclaration)child).getName();
          if (name != null) {
            result.put(name, null);
          }
        }
      }

      return result;
    }
  }
}
