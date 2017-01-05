package com.sencha.gxt.widget.core.client.tips;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class GwtTestToolTip extends CoreBaseTestCase {

  public void testUpdateText() {
    TextButton button = new TextButton();

    ToolTip t = new ToolTip(button);
    
    RootPanel.get().add(button);

    ToolTipConfig c = new ToolTipConfig("title", "test");
    t.update(c);

    assertEquals(c.getBody(), t.body);
    assertEquals(c.getTitle(), t.title);

    t.show();
    // TODO
    // assertEquals(c.getTitle(), t.getHeading());
    // assertEquals(c.getText(), t.getBody().getInnerHTML());
    //
    // c = new ToolTipConfig("titlenew", null);
    // t.update(c);
    //
    // assertEquals(c.getText(), t.text);
    // assertEquals(c.getTitle(), t.title);
    //
    // assertEquals(c.getTitle(), t.getHeading());
    // assertEquals(1, t.getBody().getInnerHTML().length());
  }

  public void testSetText() {
    // Given there is a widget
    TextButton button = new TextButton();

    // When a tooltip is set with text
    button.setToolTip("Line 1 <br/> Line 2");
    RootPanel.get().add(button);

    // Then the retrieved html will be escaped
    SafeHtml html = button.getToolTip().body;
    assertEquals("Line 1 &lt;br/&gt; Line 2", html.asString());
  }

  public void testSetHtml()  {
    // Given there is a widget
    TextButton button = new TextButton();

    // When a tooltip is set with safe html
    button.setToolTip(SafeHtmlUtils.fromSafeConstant("Line 1 <br/> Line 2"));
    RootPanel.get().add(button);

    // Then the retrieved html will be escaped
    SafeHtml html = button.getToolTip().body;
    assertEquals("Line 1 <br/> Line 2", html.asString());
  }

  public void testSetEmptyHtml() {
    // Given there is a widget
    TextButton button = new TextButton();

    // When a tooltip is set with safe html empty string
    button.setToolTip(SafeHtmlUtils.EMPTY_SAFE_HTML);
    RootPanel.get().add(button);

    // Then the retrieved html will be blank
    SafeHtml html = button.getToolTip().body;
    assertEquals("", html.asString());
  }

  public void testSetNullHtml() {
    // Given there is a widget
    TextButton button = new TextButton();

    // When a tooltip is set with safe html as null
    button.setToolTip((SafeHtml) null);
    RootPanel.get().add(button);

    // Then the retrieved html will be also be null (although this isn't valid in practice)
    SafeHtml html = button.getToolTip().body;
    assertNull(html);
  }

}
