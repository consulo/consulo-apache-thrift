package com.intellij.plugins.thrift;

import consulo.component.util.localize.AbstractBundle;
import org.jetbrains.annotations.PropertyKey;

/**
 * Created by fkorotkov.
 */
public class ThriftBundle extends AbstractBundle
{
	private static final String BUNDLE = "com.intellij.plugins.thrift.ThriftBundle";
	private static final ThriftBundle ourInstance = new ThriftBundle();

	private ThriftBundle()
	{
		super(BUNDLE);
	}

	public static String message(@PropertyKey(resourceBundle = BUNDLE) String key)
	{
		return ourInstance.getMessage(key);
	}

	public static String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params)
	{
		return ourInstance.getMessage(key, params);
	}
}
