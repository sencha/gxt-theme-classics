package com.sencha.gxt.core.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.shared.CoreBaseTestCase;
import com.sencha.gxt.core.client.dom.DomHelper;
import com.sencha.gxt.core.client.dom.XDOM;

public class GwtTestDomHelper extends CoreBaseTestCase {

  public void testOverwrite() {
    SafeHtml html = SafeHtmlUtils.fromSafeConstant("test");
    DomHelper.overwrite(Document.get().createDivElement(), html);
  }

  public void testInsertHtml() {
    Element table = XDOM.create(SafeHtmlUtils.fromSafeConstant("<table><tbody><tr><td>1</td></tr></tbody></table>"));
    Element tbody = table.getFirstChildElement();
    SafeHtml html = SafeHtmlUtils.fromSafeConstant("<tr><td>2</td></tr><tr><td>3</td></tr>");
    DomHelper.insertHtml("afterBegin", tbody, html);

    // this code fails with HtmlUnti
    // assertEquals(3, tbody.getChildCount());
  }

}
