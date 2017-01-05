package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class GwtTestFormPanel extends CoreBaseTestCase {

  public void testPanel() {
    FormPanel form = new FormPanel();

    VerticalLayoutContainer con = new VerticalLayoutContainer();
    form.add(con);

    TextField field = new TextField();
    field.setAllowBlank(false);
    con.add(field);

    field = new TextField();
    field.setAllowBlank(false);
    con.add(new FieldLabel(field, "foo"));

    RootPanel.get().add(form);

    assertEquals(2, form.getFields().size());
  }

}
