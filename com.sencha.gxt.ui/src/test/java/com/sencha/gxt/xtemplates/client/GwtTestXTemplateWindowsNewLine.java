package com.sencha.gxt.xtemplates.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.dom.XElement;

public class GwtTestXTemplateWindowsNewLine extends XTemplateTestCase {
  interface SlashRSlashN extends XTemplates {
    @XTemplate(source="WindowsNewlines.html")
    SafeHtml template(String string);
  }



  public void testSampleFileWithWindowsNewLineChars(){
    SlashRSlashN template = GWT.create(SlashRSlashN.class);
    SafeHtml result = template.template("test");
    
    assertTrue(result.asString().contains("test"));
    XElement element = XDOM.create(result);
    assertTrue(element.getChildCount() > 0);
    assertEquals("div", element.getTagName().toLowerCase());
  }
}
