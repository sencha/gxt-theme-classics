package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestHtmlEditor extends CoreBaseTestCase {

  public void testHtmlEditor() {
    HtmlEditor e = new HtmlEditor();
    e.setValue("foo");
    RootPanel.get().add(e);

    assertEquals("foo", e.getValue());
  }

  public void testSourceEditMode() {
    HtmlEditor editor = new HtmlEditor();
    
    assertTrue(editor.isEnableSourceEditMode());
    
    editor.setSourceEditMode(true);
    assertTrue(editor.isSourceEditMode());
    
    RootPanel.get().add(editor);
    
    assertTrue(editor.isSourceEditMode());
    editor.setSourceEditMode(false);
    
    assertFalse(editor.isSourceEditMode());
    
    editor.setSourceEditMode(true);
    editor.setValue("test");
    
    assertEquals("test", editor.getValue());
  }

}
