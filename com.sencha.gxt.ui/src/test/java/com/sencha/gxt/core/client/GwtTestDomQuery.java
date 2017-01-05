package com.sencha.gxt.core.client;

import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.shared.CoreBaseTestCase;
import com.sencha.gxt.core.client.dom.DomQuery;
import com.sencha.gxt.core.client.dom.XElement;

public class GwtTestDomQuery extends CoreBaseTestCase {

  @Override
  protected void gwtTearDown() throws Exception {
    super.gwtTearDown();

    RootPanel.get().clear();

    XElement body = Document.get().getBody().cast();
    body.removeAllChildren();
  }

  public void testQueryClass() {
    // Given a div has an id and class
    DivElement div = Document.get().createDivElement();
    div.setId("test-id");
    div.setClassName("test-class");

    // And that div is attached
    BodyElement body = Document.get().getBody();
    body.appendChild(div);

    // When the dom query the tag name
    NodeList<Element> elements = DomQuery.select("div");

    // Then there will be
    assertEquals(1, elements.getLength());
    assertEquals("test-id", elements.getItem(0).getId());
    assertEquals("test-class", elements.getItem(0).getClassName());

    // When the dom query the id
    elements = DomQuery.select(".test-class");

    // Then there id and class will exist
    assertEquals(1, elements.getLength());
    assertEquals("test-id", elements.getItem(0).getId());
    assertEquals("test-class", elements.getItem(0).getClassName());
  }

  public void testQueryId() {
    // Given a div has an id
    DivElement div = Document.get().createDivElement();
    div.setId("0x-widget-3_iEaJ9BWq9SpEC__36");

    // And that div is attached
    BodyElement body = Document.get().getBody();
    body.appendChild(div);

    // When DomQuery select is used
    NodeList<Element> elements = DomQuery.select("#0x-widget-3_iEaJ9BWq9SpEC__36");

    // Then one element is found
    assertTrue(elements.getLength() == 1);
  }

}
