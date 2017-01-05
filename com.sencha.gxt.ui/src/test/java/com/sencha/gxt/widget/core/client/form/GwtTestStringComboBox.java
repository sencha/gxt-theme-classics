package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestStringComboBox extends CoreBaseTestCase {

  public void testAutoCreate() {
    StringComboBox combo = new StringComboBox();
    combo.setAddUserValues(true);

    combo.add("one");
    combo.add("two");

    RootPanel.get().add(combo);

    combo.setText("three");

    // will cause getByValue to be called which in turn creates the new record in the store
    combo.isCurrentValid();

    assertEquals(3, combo.getStore().size());
    assertEquals("three", combo.getStore().get(2));
  }

}
