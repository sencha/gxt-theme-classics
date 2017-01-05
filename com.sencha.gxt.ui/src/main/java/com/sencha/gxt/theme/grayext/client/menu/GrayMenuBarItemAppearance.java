package com.sencha.gxt.theme.grayext.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.menu.MenuBarItemBaseAppearance;

public class GrayMenuBarItemAppearance extends MenuBarItemBaseAppearance {

  public interface GrayMenuBarItemResources extends MenuBarItemResources, ClientBundle {
    @Source({"com/sencha/gxt/theme/base/client/menu/MenuBarItem.gss", "GrayMenuBarItem.gss"})
    GrayMenuBarItemStyle css();
  }
  
  public interface GrayMenuBarItemStyle extends MenuBarItemStyle {
  }
  
  public GrayMenuBarItemAppearance() {
    this(GWT.<GrayMenuBarItemResources>create(GrayMenuBarItemResources.class));
  }
  
  public GrayMenuBarItemAppearance(GrayMenuBarItemResources resources) {
    super(resources);
  }

}
