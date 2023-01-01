package com.intellij.plugins.thrift;

import com.intellij.plugins.thrift.util.ThriftTestUtils;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

/**
 * Created by fkorotkov.
 */
abstract public class ThriftCodeInsightFixtureTestCase extends LightPlatformCodeInsightFixtureTestCase
{
  @Override
  protected String getTestDataPath() {
    return ThriftTestUtils.BASE_TEST_DATA_PATH + "/" + getBasePath();
  }
}
