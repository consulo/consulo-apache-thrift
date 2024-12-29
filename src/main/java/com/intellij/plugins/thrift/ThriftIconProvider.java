package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.lang.psi.*;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.AllIcons;
import consulo.language.icon.IconDescriptor;
import consulo.language.icon.IconDescriptorUpdater;
import consulo.language.psi.PsiElement;

import jakarta.annotation.Nonnull;

@ExtensionImpl
public class ThriftIconProvider implements IconDescriptorUpdater
{
	@Override
	public void updateIcon(@Nonnull IconDescriptor iconDescriptor, @Nonnull PsiElement element, int i)
	{
		if(element instanceof ThriftConst)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.Value);
		}
		if(element instanceof ThriftEnum)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.Enum);
		}
		if(element instanceof ThriftException)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.ExceptionClass);
		}
		if(element instanceof ThriftService)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.Static);
		}
		if(element instanceof ThriftStruct)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.Struct);
		}
		if(element instanceof ThriftTypedef)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.TypeAlias);
		}
		if(element instanceof ThriftUnion)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.AnonymousClass);
		}
		if(element instanceof ThriftField)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.Field);
		}
		if(element instanceof ThriftFunction)
		{
			iconDescriptor.setMainIcon(AllIcons.Nodes.Function);
		}
	}
}
