package com.sencha.gxt.widget.core.client;

import com.google.gwt.user.client.ui.RootPanel;

public class GwtTestSlider extends CoreBaseTestCase {

  public void testHorizontal() {
    Slider s = new Slider();
    s.setValue(10);

    RootPanel.get().add(s);
  }

  public void testVertical() {
    Slider s = new Slider(true);
    s.setValue(10);

    RootPanel.get().add(s);
  }

  public void testClearSliderField() {
    Slider slider = new Slider();
    
    RootPanel.get().add(slider);
    
    slider.setValue(100);
    assertEquals(slider.getValue(), slider.getValue().intValue(), 100);
    
    slider.clear();
    assertEquals(null, slider.getValue());
  }
}
