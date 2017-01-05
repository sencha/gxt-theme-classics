package com.sencha.gxt.theme.blueext.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.MenuItemBaseAppearance;

public class BlueMenuItemAppearance extends MenuItemBaseAppearance {

  public interface BlueMenuItemResources extends MenuItemBaseAppearance.MenuItemResources, ClientBundle {

    ImageResource menuParent();

    @Override
    @Source({"com/sencha/gxt/theme/base/client/menu/Item.gss",
            "com/sencha/gxt/theme/blue/client/menu/BlueItem.gss",
            "com/sencha/gxt/theme/base/client/menu/MenuItem.gss",
            "BlueMenuItem.gss"})
    BlueMenuItemStyle style();

    ImageResource itemOver();
  }

  public interface BlueMenuItemStyle extends MenuItemBaseAppearance.MenuItemStyle {
  }

  public BlueMenuItemAppearance() {
    this(GWT.<BlueMenuItemResources> create(BlueMenuItemResources.class),
        GWT.<MenuItemTemplate> create(MenuItemTemplate.class));
  }

  public BlueMenuItemAppearance(BlueMenuItemResources resources, MenuItemTemplate template) {
    super(resources, template);
  }

}
