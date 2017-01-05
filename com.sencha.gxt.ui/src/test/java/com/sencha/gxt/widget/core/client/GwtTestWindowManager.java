package com.sencha.gxt.widget.core.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.RegisterEvent;
import com.sencha.gxt.widget.core.client.event.RegisterEvent.RegisterHandler;
import com.sencha.gxt.widget.core.client.event.UnregisterEvent;
import com.sencha.gxt.widget.core.client.event.UnregisterEvent.UnregisterHandler;

public class GwtTestWindowManager extends CoreBaseTestCase {

  public void testWindowManager() {
    final Set<Type<?>> events = new HashSet<Type<?>>();
    
    WindowManager mgr = WindowManager.get();
    mgr.addRegisterHandler(new RegisterHandler<Widget>() {
      
      @Override
      public void onRegister(RegisterEvent<Widget> event) {
        events.add(RegisterEvent.getType());
      }
    });
    
    mgr.addUnregisterHandler(new UnregisterHandler<Widget>() {
      
      @Override
      public void onUnregister(UnregisterEvent<Widget> event) {
        events.add(UnregisterEvent.getType());
      }
    });
    
    Window window = new Window();
    window.show();
    window.hide();
    
    assertTrue(events.contains(RegisterEvent.getType()));
    assertTrue(events.contains(UnregisterEvent.getType()));
  }
  
  public void testNonWindows() {
    WindowManager mgr = WindowManager.get();
    
    mgr.register(new TextButton());
    Window window = new Window();
    
    window.show();
    window.hide();
  }
}
