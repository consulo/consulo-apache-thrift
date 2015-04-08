package com.intellij.plugins.thrift;

import org.jetbrains.annotations.NotNull;
import com.intellij.icons.AllIcons;
import com.intellij.ide.IconDescriptor;
import com.intellij.ide.IconDescriptorUpdater;
import com.intellij.plugins.thrift.lang.psi.ThriftConst;
import com.intellij.plugins.thrift.lang.psi.ThriftEnum;
import com.intellij.plugins.thrift.lang.psi.ThriftException;
import com.intellij.plugins.thrift.lang.psi.ThriftField;
import com.intellij.plugins.thrift.lang.psi.ThriftFunction;
import com.intellij.plugins.thrift.lang.psi.ThriftService;
import com.intellij.plugins.thrift.lang.psi.ThriftStruct;
import com.intellij.plugins.thrift.lang.psi.ThriftTypedef;
import com.intellij.plugins.thrift.lang.psi.ThriftUnion;
import com.intellij.psi.PsiElement;

public class ThriftIconProvider implements IconDescriptorUpdater
{
	@Override
	public void updateIcon(@NotNull IconDescriptor iconDescriptor, @NotNull PsiElement element, int i)
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
