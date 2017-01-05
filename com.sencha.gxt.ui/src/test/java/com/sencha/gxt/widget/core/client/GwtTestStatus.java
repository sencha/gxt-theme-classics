package com.sencha.gxt.widget.core.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.Status.StatusAppearance;

public class GwtTestStatus extends CoreBaseTestCase {

  public void testStatus() {
    Status status = new Status();
    RootPanel.get().add(status);
  }
  
  public void testIcon() {
    StatusAppearance ap = GWT.create(StatusAppearance.class);
    Status s = new Status();
    s.setIcon(ap.getBusyIcon());
    assertEquals(ap.getBusyIcon(), s.getIcon());
  }
  
}
