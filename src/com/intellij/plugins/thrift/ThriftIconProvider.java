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
import icons.ThriftIcons;

public class ThriftIconProvider implements IconDescriptorUpdater
{
	@Override
	public void updateIcon(@NotNull IconDescriptor iconDescriptor, @NotNull PsiElement element, int i)
	{
		if(element instanceof ThriftConst)
		{
			iconDescriptor.setMainIcon(ThriftIcons.CONST);
		}
		if(element instanceof ThriftEnum)
		{
			iconDescriptor.setMainIcon(ThriftIcons.ENUM);
		}
		if(element instanceof ThriftException)
		{
			iconDescriptor.setMainIcon(ThriftIcons.EXCEPTION);
		}
		if(element instanceof ThriftService)
		{
			iconDescriptor.setMainIcon(ThriftIcons.SERVICE);
		}
		if(element instanceof ThriftStruct)
		{
			iconDescriptor.setMainIcon(ThriftIcons.STRUCT);
		}
		if(element instanceof ThriftTypedef)
		{
			iconDescriptor.setMainIcon(ThriftIcons.TYPEDEF);
		}
		if(element instanceof ThriftUnion)
		{
			iconDescriptor.setMainIcon(ThriftIcons.UNION);
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
