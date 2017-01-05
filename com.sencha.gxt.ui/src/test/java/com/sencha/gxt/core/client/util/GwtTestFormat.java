package com.sencha.gxt.core.client.util;

import com.sencha.gxt.core.shared.CoreBaseTestCase;

public class GwtTestFormat extends CoreBaseTestCase {

  public void testCamelCase() {
    String s = "camel-test";
    s = Format.camelize(s);
    assertEquals("camelTest", s);
  }

  public void testCapitalize() {
    String s = "JOHNDOE";
    s = Format.capitalize(s);
    assertEquals("Johndoe", s);
  }

  public void testCurrency() {
    assertEquals("US$14.03", Format.currency(14.03));
  }

  public void testDecimal() {
    assertEquals("-14", Format.decimal(-14));
    assertEquals("19.77", Format.decimal(19.77));
    assertEquals("1", Format.decimal(0.9999999));
  }

  public void testEllipse() {
    assertEquals("The qui...", Format.ellipse("The quick brown fox", 10));
    assertEquals("The quick brown fox", Format.ellipse("The quick brown fox", 21));
  }

  public void testFileSize() {
    assertEquals("500 bytes", Format.fileSize(500));
    assertEquals("4 KB", Format.fileSize(5000));
  }

}
