package com.sencha.gxt.widget.core.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public class GwtTestDialog extends CoreBaseTestCase {
  
  public void testButtons() {
    Dialog d = new Dialog();
    // when true unit tests widths are wrong
    d.getButtonBar().setEnableOverflow(false);
    d.setPredefinedButtons(PredefinedButton.OK, PredefinedButton.CANCEL);
    RootPanel.get().add(d);
    
    assertEquals(2, d.getButtonBar().getWidgetCount());
    
    d.setPredefinedButtons(PredefinedButton.OK);
    assertEquals(1, d.getButtonBar().getWidgetCount());
  }
  
  @SuppressWarnings("rawtypes")
  public void testWindowEvents() {
    final Set<Type> events = new HashSet<Type>();
    Dialog d = new Dialog();
    d.addShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        events.add(event.getAssociatedType());
      }
    });
    d.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        events.add(event.getAssociatedType());
      }
    });
    
    d.show();
    d.hide();

    assertTrue(events.contains(ShowEvent.getType()));
    assertTrue(events.contains(HideEvent.getType()));
  }

}
