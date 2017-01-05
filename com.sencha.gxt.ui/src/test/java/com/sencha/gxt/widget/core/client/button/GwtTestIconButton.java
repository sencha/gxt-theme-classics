package com.sencha.gxt.widget.core.client.button;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class GwtTestIconButton extends CoreBaseTestCase {

  private int count = 0;

  public void testClickEvent() {
    IconButton button = new IconButton("-over", new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        count++;
      }
    });

    button.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        count++;
      }
    });

    RootPanel.get().add(button);

    button.getElement().click();

    assertEquals(2, count);
  }

}
