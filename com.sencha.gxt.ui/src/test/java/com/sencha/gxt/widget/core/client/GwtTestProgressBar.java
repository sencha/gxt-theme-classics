package com.sencha.gxt.widget.core.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.ui.client.UITestCase;

public class GwtTestProgressBar extends UITestCase {

  public void testEvents() {
    ProgressBar bar = new ProgressBar();
    bar.addValueChangeHandler(new ValueChangeHandler<Double>() {
      
      @Override
      public void onValueChange(ValueChangeEvent<Double> event) {
        assertEquals(.5, event.getValue());
      }
    });
    
    RootPanel.get().add(bar);
    
    bar.updateProgress(.5, "50%");
   
  }
  
}
