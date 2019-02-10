// This is a generated file. Not intended for manual editing.
package com.intellij.plugins.thrift.lang.psi;

import javax.annotation.Nonnull;

import com.intellij.psi.PsiElementVisitor;
import  com.intellij.psi.PsiNameIdentifierOwner;

public class ThriftVisitor extends PsiElementVisitor {

  public void visitBaseType(@Nonnull ThriftBaseType o) {
    visitPsiCompositeElement(o);
  }

  public void visitConst(@Nonnull ThriftConst o) {
    visitTopLevelDeclaration(o);
  }

  public void visitConstList(@Nonnull ThriftConstList o) {
    visitPsiCompositeElement(o);
  }

  public void visitConstMap(@Nonnull ThriftConstMap o) {
    visitPsiCompositeElement(o);
  }

  public void visitConstValue(@Nonnull ThriftConstValue o) {
    visitPsiCompositeElement(o);
  }

  public void visitContainerType(@Nonnull ThriftContainerType o) {
    visitPsiCompositeElement(o);
  }

  public void visitCppType(@Nonnull ThriftCppType o) {
    visitPsiCompositeElement(o);
  }

  public void visitCustomType(@Nonnull ThriftCustomType o) {
    visitPsiCompositeElement(o);
  }

  public void visitDefinitionName(@Nonnull ThriftDefinitionName o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitDoubleConstant(@Nonnull ThriftDoubleConstant o) {
    visitPsiCompositeElement(o);
  }

  public void visitEnum(@Nonnull ThriftEnum o) {
    visitTopLevelDeclaration(o);
  }

  public void visitException(@Nonnull ThriftException o) {
    visitTopLevelDeclaration(o);
  }

  public void visitField(@Nonnull ThriftField o) {
    visitSubDeclaration(o);
  }

  public void visitFieldID(@Nonnull ThriftFieldID o) {
    visitPsiCompositeElement(o);
  }

  public void visitFieldReq(@Nonnull ThriftFieldReq o) {
    visitPsiCompositeElement(o);
  }

  public void visitFieldType(@Nonnull ThriftFieldType o) {
    visitPsiCompositeElement(o);
  }

  public void visitFunction(@Nonnull ThriftFunction o) {
    visitSubDeclaration(o);
  }

  public void visitFunctionType(@Nonnull ThriftFunctionType o) {
    visitPsiCompositeElement(o);
  }

  public void visitGenericType(@Nonnull ThriftGenericType o) {
    visitPsiCompositeElement(o);
  }

  public void visitInclude(@Nonnull ThriftInclude o) {
    visitPsiCompositeElement(o);
  }

  public void visitIntConstant(@Nonnull ThriftIntConstant o) {
    visitPsiCompositeElement(o);
  }

  public void visitListSeparator(@Nonnull ThriftListSeparator o) {
    visitPsiCompositeElement(o);
  }

  public void visitListType(@Nonnull ThriftListType o) {
    visitPsiCompositeElement(o);
  }

  public void visitMapType(@Nonnull ThriftMapType o) {
    visitPsiCompositeElement(o);
  }

  public void visitNamespace(@Nonnull ThriftNamespace o) {
    visitPsiCompositeElement(o);
  }

  public void visitNamespaceScope(@Nonnull ThriftNamespaceScope o) {
    visitPsiCompositeElement(o);
  }

  public void visitSenum(@Nonnull ThriftSenum o) {
    visitTopLevelDeclaration(o);
  }

  public void visitService(@Nonnull ThriftService o) {
    visitTopLevelDeclaration(o);
  }

  public void visitServiceSuperName(@Nonnull ThriftServiceSuperName o) {
    visitPsiCompositeElement(o);
  }

  public void visitSetType(@Nonnull ThriftSetType o) {
    visitPsiCompositeElement(o);
  }

  public void visitSimpleBaseType(@Nonnull ThriftSimpleBaseType o) {
    visitPsiCompositeElement(o);
  }

  public void visitStruct(@Nonnull ThriftStruct o) {
    visitTopLevelDeclaration(o);
  }

  public void visitThrows(@Nonnull ThriftThrows o) {
    visitPsiCompositeElement(o);
  }

  public void visitTypeAnnotation(@Nonnull ThriftTypeAnnotation o) {
    visitPsiCompositeElement(o);
  }

  public void visitTypeAnnotationList(@Nonnull ThriftTypeAnnotationList o) {
    visitPsiCompositeElement(o);
  }

  public void visitTypeAnnotations(@Nonnull ThriftTypeAnnotations o) {
    visitPsiCompositeElement(o);
  }

  public void visitTypedef(@Nonnull ThriftTypedef o) {
    visitTopLevelDeclaration(o);
  }

  public void visitUnion(@Nonnull ThriftUnion o) {
    visitTopLevelDeclaration(o);
  }

  public void visitXsdAttrs(@Nonnull ThriftXsdAttrs o) {
    visitPsiCompositeElement(o);
  }

  public void visitXsdFieldOptions(@Nonnull ThriftXsdFieldOptions o) {
    visitPsiCompositeElement(o);
  }

  public void visitEnumField(@Nonnull ThriftEnumField o) {
    visitTopLevelDeclaration(o);
  }

  public void visitEnumFields(@Nonnull ThriftEnumFields o) {
    visitDeclarationBody(o);
  }

  public void visitFields(@Nonnull ThriftFields o) {
    visitDeclarationBody(o);
  }

  public void visitSemunField(@Nonnull ThriftSemunField o) {
    visitPsiCompositeElement(o);
  }

  public void visitSenumBody(@Nonnull ThriftSenumBody o) {
    visitDeclarationBody(o);
  }

  public void visitServiceBody(@Nonnull ThriftServiceBody o) {
    visitDeclarationBody(o);
  }

  public void visitPsiNameIdentifierOwner(@Nonnull PsiNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitDeclarationBody(@Nonnull ThriftDeclarationBody o) {
    visitPsiCompositeElement(o);
  }

  public void visitSubDeclaration(@Nonnull ThriftSubDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitTopLevelDeclaration(@Nonnull ThriftTopLevelDeclaration o) {
    visitPsiCompositeElement(o);
  }

  public void visitPsiCompositeElement(@Nonnull ThriftPsiCompositeElement o) {
    visitElement(o);
  }

}
