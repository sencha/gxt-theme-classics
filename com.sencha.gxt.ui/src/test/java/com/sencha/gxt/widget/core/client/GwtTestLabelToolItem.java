package com.sencha.gxt.widget.core.client;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

public class GwtTestLabelToolItem extends CoreBaseTestCase {

  public void testLabelToolItemText() {
    // Given a label tool item has text
    LabelToolItem item = new LabelToolItem("Line 1 <br> Line 2");
    RootPanel.get().add(item);

    // When getting that label
    String label = item.getElement().getInnerHTML();

    // Then the text is escaped
    assertEquals("Line 1 &lt;br&gt; Line 2", label);
  }

  public void testLabelToolItemHtml() {
    // Given a label tool item has safe html
    LabelToolItem item = new LabelToolItem(SafeHtmlUtils.fromSafeConstant("Line 1 <br> Line 2"));
    RootPanel.get().add(item);

    // When getting that label
    String label = item.getElement().getInnerHTML();

    // Then the label has html not escaped
    assertEquals("Line 1 <br> Line 2", label);
  }

  public void testLabelToolItemEmptyHtml() {
    // Given a label tool item has safe html that is empty
    LabelToolItem item = new LabelToolItem(SafeHtmlUtils.EMPTY_SAFE_HTML);
    RootPanel.get().add(item);

    // When getting that label
    String label = item.getElement().getInnerHTML();

    // Then the label is blank
    assertEquals("", label);
  }

  public void testLabelToolItemNullHtml() {
    try {
      try {
        // Given a label tool item has safe html that is null it should NPE because we assign the value
        // directly to setInnerSafeHtml, which we know isn't null safe
        LabelToolItem item = new LabelToolItem((SafeHtml) null);
        fail();
      } catch (NullPointerException e) {
        // pass in java mode
      }
    } catch (JavaScriptException e) {
      // pass in javascript mode
    }
  }

}
