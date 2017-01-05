package com.sencha.gxt.widget.core.client.form;

import java.util.Iterator;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestCheckBoxGroup extends CoreBaseTestCase {

  public void testCheckBoxGroup() {
    CheckBox check1 = new CheckBox();
    CheckBox check2 = new CheckBox();
    CheckBox check3 = new CheckBox();

    check2.setValue(true);
    check3.setValue(true);

    ToggleGroup group = new ToggleGroup();

    group.add(check1);
    group.add(check2);
    group.add(check3);

    RootPanel.get().add(check1);
    RootPanel.get().add(check2);
    RootPanel.get().add(check3);

    Iterator<HasValue<Boolean>> iter = group.iterator();
    int count = 0;
    while (iter.hasNext()) {
      iter.next();
      count++;
    }

    assertTrue(count == 3);

    check1.setValue(true, true);

    assertTrue(check1.getValue());
    assertFalse(check2.getValue());
  }

}
