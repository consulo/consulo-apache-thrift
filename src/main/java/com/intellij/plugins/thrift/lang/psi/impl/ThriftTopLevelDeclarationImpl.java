package com.intellij.plugins.thrift.lang.psi.impl;

import com.intellij.plugins.thrift.lang.psi.ThriftDeclaration;
import com.intellij.plugins.thrift.lang.psi.ThriftDeclarationBody;
import com.intellij.plugins.thrift.lang.psi.ThriftTopLevelDeclaration;
import consulo.language.ast.ASTNode;
import consulo.language.psi.util.PsiTreeUtil;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class ThriftTopLevelDeclarationImpl extends AbstractThriftDeclaration implements ThriftTopLevelDeclaration {
  public ThriftTopLevelDeclarationImpl(@Nonnull ASTNode node) {
    super(node);
  }

  @Override
  public List<ThriftDeclaration> findSubDeclarations() {
    ThriftDeclarationBody body = PsiTreeUtil.getChildOfType(this, ThriftDeclarationBody.class);
    if (body == null) {
      return Collections.emptyList();
    }
    return PsiTreeUtil.getChildrenOfTypeAsList(body, ThriftDeclaration.class);
  }
}
