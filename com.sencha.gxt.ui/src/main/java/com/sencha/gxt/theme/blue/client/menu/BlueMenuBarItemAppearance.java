package com.sencha.gxt.theme.blue.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.menu.MenuBarItemBaseAppearance;

public class BlueMenuBarItemAppearance extends MenuBarItemBaseAppearance {

  public interface BlueMenuBarItemResources extends MenuBarItemResources, ClientBundle {
    @Source({"com/sencha/gxt/theme/base/client/menu/MenuBarItem.gss", "BlueMenuBarItem.gss"})
    BlueMenuBarItemStyle css();
  }
  
  public interface BlueMenuBarItemStyle extends MenuBarItemStyle {
  }
  
  public BlueMenuBarItemAppearance() {
    this(GWT.<BlueMenuBarItemResources>create(BlueMenuBarItemResources.class));
  }
  
  public BlueMenuBarItemAppearance(BlueMenuBarItemResources resources) {
    super(resources);
  }

}
