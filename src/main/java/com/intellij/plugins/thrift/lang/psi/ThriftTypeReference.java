package com.intellij.plugins.thrift.lang.psi;

import com.intellij.plugins.thrift.util.ThriftPsiUtil;
import consulo.application.util.function.Processor;
import consulo.document.util.TextRange;
import consulo.language.editor.completion.lookup.LookupElementBuilder;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiReferenceBase;
import consulo.util.collection.ArrayUtil;
import consulo.util.io.FileUtil;
import consulo.util.io.PathUtil;
import consulo.util.lang.Pair;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by fkorotkov.
 */
public class ThriftTypeReference extends PsiReferenceBase<ThriftCustomType> {
  public ThriftTypeReference(@Nonnull ThriftCustomType element, int offset) {
    super(element, TextRange.create(offset, element.getTextLength()));
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    return processComponentAndFile(new Function<Pair<String, PsiFile>, PsiElement>() {
      @Override
      public PsiElement apply(@Nullable Pair<String, PsiFile> pair) {
        return pair != null ? ThriftPsiUtil.findDeclaration(pair.getFirst(), pair.getSecond()) : null;
      }
    });
  }

  @Nonnull
  @Override
  public Object[] getVariants() {
    Object[] result = processComponentAndFile(new Function<Pair<String, PsiFile>, Object[]>() {
      @Override
      public Object[] apply(Pair<String, PsiFile> pair) {
        final List<Object> result = new ArrayList<Object>();
        PsiFile psiFile = pair.getSecond();
        ThriftPsiUtil.processDeclarations(psiFile, new Processor<ThriftDeclaration>() {
          @Override
          public boolean process(ThriftDeclaration declaration) {
            result.add(declaration);
            return true;
          }
        });
        if (isSimple()) {
          ThriftPsiUtil.processIncludes(getElement().getContainingFile(), new Processor<ThriftInclude>() {
            @Override
            public boolean process(ThriftInclude include) {
              String path = include.getPath();
              String fileName = PathUtil.getFileName(path);
              result.add(LookupElementBuilder.create(FileUtil.getNameWithoutExtension(fileName)));
              return true;
            }
          });
        }
        return ArrayUtil.toObjectArray(result);
      }
    });
    return result != null ? result : PsiElement.EMPTY_ARRAY;
  }

  private boolean isSimple() {
    return getRangeInElement().getStartOffset() == 0;
  }

  @Nullable
  private <T> T processComponentAndFile(@Nonnull Function<Pair<String, PsiFile>, T> fun) {
    final String name = getElement().getText();
    int index = getRangeInElement().getStartOffset();
    if (index > 0) {
      String fileName = name.substring(0, index - 1);
      String componentName = name.substring(index);
      ThriftInclude include = ThriftPsiUtil.findImportByPrefix(getElement().getContainingFile(), fileName);
      PsiFile includedFile = ThriftPsiUtil.resolveInclude(include);
      return includedFile != null ? fun.apply(Pair.create(componentName, includedFile)) : null;
    }
    else {
      return fun.apply(Pair.create(name, getElement().getContainingFile()));
    }
  }
}
