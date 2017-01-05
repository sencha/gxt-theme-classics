package com.sencha.gxt.core.client.dom.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.shared.CoreBaseTestCase;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.shared.FastMap;

public class GwtTestComputedStyleImpl extends CoreBaseTestCase {

  public void testGetStyleAttribute() {
    StyleInjector.inject(".GwtTestComputedStyleImpl-testGetStyleAttribute {cursor:pointer;}", true);

    List<String> propertyNames = new ArrayList<String>();
    propertyNames.add("top");
    propertyNames.add("left");
    propertyNames.add("cursor");
    propertyNames.add("display");
    

    Widget button = new Widget(){{
      setElement(Document.get().createDivElement());
      getElement().setInnerText("test");
    }};
    button.getElement().<XElement>cast().setXY(123, 456);
    button.addStyleName("GwtTestComputedStyleImpl-testGetStyleAttribute");

    RootPanel.get().add(button);
    FastMap<String> styles = button.getElement().<XElement>cast().getComputedStyle(propertyNames);

    //check direct element.style attributes
    assertEquals("456px", styles.get(propertyNames.get(0)));
    assertEquals("123px", styles.get(propertyNames.get(1)));

    //check css applied attributes
    assertEquals("pointer", styles.get(propertyNames.get(2)));

    //check default attributes
    assertEquals("block", styles.get(propertyNames.get(3)));
  }

  public void testHelperMethods() {
    ComputedStyleImpl computedStyle = GWT.create(ComputedStyleImpl.class);

    // getPropertyName
    assertEquals("string", computedStyle.getPropertyName("string"));
    
    //this is a weird test, since it just tests the replace-with
    if (GXT.isIE8()) {
      assertEquals("styleFloat", computedStyle.getPropertyName("float"));
    } else {
      assertEquals("cssFloat", computedStyle.getPropertyName("float"));
    }

    // camelCache
    List<String> camel = new ArrayList<String>();
    camel.add("background-color");
    camel.add("font-family");
    camel = computedStyle.checkCamelCache(camel);
    assertEquals("backgroundColor", camel.get(0));
    assertEquals("fontFamily", camel.get(1));

    // hyphenCache
    camel = computedStyle.checkHyphenCache(camel);
    assertEquals("background-color", camel.get(0));
    assertEquals("font-family", camel.get(1));
  }

}
