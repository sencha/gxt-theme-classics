package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;

public class GwtTestHtmlLayoutContainer extends CoreBaseTestCase {

  public void testContainerWithClasses() {
    SafeHtml html = SafeHtmlUtils.fromSafeConstant("Test html");
    SafeHtml html2 = SafeHtmlUtils.fromSafeConstant("Test html2");
    SafeHtml html3 = SafeHtmlUtils.fromSafeConstant("Test html3");
    HtmlLayoutContainer h = new HtmlLayoutContainer(html);
    assertEquals(h.getHTML().asString(), html.asString());
    h.setHTML(html2);
    assertEquals(h.getHTML().asString(), html2.asString());
    h.setHTML(html3);
    assertEquals(h.getHTML().asString(), html3.asString());

    SafeHtml container = SafeHtmlUtils.fromSafeConstant("<div><div class='cell1'></div><div class='cell2'></div><div class='cell3'></div></div>");
    HtmlLayoutContainer c = new HtmlLayoutContainer(container);
    TextButton t1 = new TextButton("Button 1");
    TextButton t2 = new TextButton("Button 2");
    TextButton t3 = new TextButton("Button 3");
    c.add(t1, new HtmlData(".cell1"));
    c.add(t2, new HtmlData(".cell2"));
    c.add(t3, new HtmlData(".cell3"));

    XElement currentSibling = c.getElement().getFirstChildElement().getFirstChildElement().cast();
    assertEquals(t1.getElement(),currentSibling.getFirstChildElement());

    currentSibling = currentSibling.getNextSiblingElement().cast();
    assertEquals(t2.getElement(),currentSibling.getFirstChildElement());

    currentSibling = currentSibling.getNextSiblingElement().cast();
    assertEquals(t3.getElement(),currentSibling.getFirstChildElement());

  }

  public void testContainerWithIds() {
    SafeHtml html = SafeHtmlUtils.fromSafeConstant("Test html");
    SafeHtml html2 = SafeHtmlUtils.fromSafeConstant("Test html2");
    SafeHtml html3 = SafeHtmlUtils.fromSafeConstant("Test html3");
    HtmlLayoutContainer h = new HtmlLayoutContainer(html);
    assertEquals(h.getHTML().asString(), html.asString());
    h.setHTML(html2);
    assertEquals(h.getHTML().asString(), html2.asString());
    h.setHTML(html3);
    assertEquals(h.getHTML().asString(), html3.asString());

    SafeHtml container = SafeHtmlUtils.fromSafeConstant("<div><div id='cell1'></div><div id='cell2'></div><div id='cell3'></div></div>");
    HtmlLayoutContainer c = new HtmlLayoutContainer(container);
    TextButton t1 = new TextButton("Button 1");
    TextButton t2 = new TextButton("Button 2");
    TextButton t3 = new TextButton("Button 3");
    c.add(t1, new HtmlData("#cell1"));
    c.add(t2, new HtmlData("*#cell2"));
    c.add(t3, new HtmlData("div#cell3"));

    XElement currentSibling = c.getElement().getFirstChildElement().getFirstChildElement().cast();
    assertEquals(t1.getElement(),currentSibling.getFirstChildElement());

    currentSibling = currentSibling.getNextSiblingElement().cast();
    assertEquals(t2.getElement(),currentSibling.getFirstChildElement());

    currentSibling = currentSibling.getNextSiblingElement().cast();
    assertEquals(t3.getElement(),currentSibling.getFirstChildElement());    
  }
}
