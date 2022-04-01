package com.intellij.plugins.thrift.util;

import java.util.Set;

/**
 * Created by fkorotkov.
 */
public class ThriftUtils {
  private static final Set<String> keywords = Set.of(
    "const", "cpp_include", "enum", "exception", "include", "namespace",
    "php_namespace", "senum", "service", "struct", "typedef", "union",
    "xsd_namespace", "cpp", "java", "d", "py", "py.twisted", "perl", "rb", "js", "st",
    "cocoa", "csharp", "c_glib", "go", "php", "delphi",
    "required", "optional", "xsd_optional",
    "xsd_nillable", "oneway", "throws", "bool", "byte", "i16", "i32", "i64", "double",
    "string", "binary", "slist", "list", "map", "set", "cpp_type", "void", "true", "false"
  );

  public static Set<String> getKeywords() {
    return keywords;
  }
}
