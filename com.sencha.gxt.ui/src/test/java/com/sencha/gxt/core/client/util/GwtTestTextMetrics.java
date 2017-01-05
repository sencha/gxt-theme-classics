package com.sencha.gxt.core.client.util;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.StyleInjector;
import com.sencha.gxt.core.shared.CoreBaseTestCase;
import com.sencha.gxt.core.client.dom.XElement;

/**
 * None of this class works with htmlunit since it doesn't actually render anything
 */
//@DoNotRunWith(Platform.HtmlUnitLayout)
public class GwtTestTextMetrics extends CoreBaseTestCase {

  private static final String TEXT = "some text to test with";

  @Override
  protected void gwtSetUp() throws Exception {
    String css = ".someclass {} .otherclass {font-weight: bold;}";
    StyleInjector.inject(css, true);
  }

  public void testBindClassName() {
    //bind to simple class
    TextMetrics.get().bind("someclass");
    Size s1 = TextMetrics.get().getSize(TEXT);

    //bind to class with options
    TextMetrics.get().bind("otherclass");
    Size s2 = TextMetrics.get().getSize(TEXT);

    assertTrue(s1.getWidth() + " <= " + s2.getWidth(), s1.getWidth() <= s2.getWidth());
    assertEquals(s1.getHeight(), s2.getHeight());
  }

  public void testBindAttachedElement() {
    Size attachedSize1, attachedSize2, attachedSize3;
    XElement toAttach = null;

    //try/finally is to make sure that the element gets cut out again no matter any exception
    try {
      toAttach = Document.get().createDivElement().cast();
      toAttach.addClassName("someclass");
      Document.get().getBody().appendChild(toAttach);

      //bind once, copying properties
      TextMetrics.get().bind(toAttach);
      attachedSize1 = TextMetrics.get().getSize(TEXT);

      //tweak element
      toAttach.addClassName("otherclass");
      attachedSize2 = TextMetrics.get().getSize(TEXT);

      //bind again since properties have changed
      TextMetrics.get().bind(toAttach);
      attachedSize3 = TextMetrics.get().getSize(TEXT);

      assertEquals(attachedSize1, attachedSize2);
      assertTrue(attachedSize1.getWidth() + " <= " + attachedSize3.getWidth(), attachedSize1.getWidth() <= attachedSize3.getWidth());
      assertEquals(attachedSize1.getHeight(), attachedSize3.getHeight());
    } finally {
      if (toAttach != null) {
        toAttach.removeFromParent();
      }
    }
  }

  public void testBindDetachedElement() {
    Size size1, size2, size3;
    XElement elt = null;
    elt = Document.get().createDivElement().cast();
    elt.addClassName("someclass");

    //bind once, copying properties
    TextMetrics.get().bind(elt);
    size1 = TextMetrics.get().getSize(TEXT);

    //tweak element
    elt.addClassName("otherclass");
    size2 = TextMetrics.get().getSize(TEXT);

    //bind again since properties have changed
    TextMetrics.get().bind(elt);
    size3 = TextMetrics.get().getSize(TEXT);

    assertEquals(size1, size2);
    assertTrue(size1.getWidth() + " <= " + size3.getWidth(), size1.getWidth() <= size3.getWidth());
    assertEquals(size1.getHeight(), size3.getHeight());
  }

}