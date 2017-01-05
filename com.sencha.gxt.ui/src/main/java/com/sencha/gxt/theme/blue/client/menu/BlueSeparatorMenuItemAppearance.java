package com.sencha.gxt.theme.blue.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.SeparatorMenuItemBaseAppearance;

public class BlueSeparatorMenuItemAppearance extends SeparatorMenuItemBaseAppearance {

  public interface BlueSeparatorMenuItemResources extends ClientBundle, SeparatorMenuItemResources {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/menu/Item.gss",
            "com/sencha/gxt/theme/blue/client/menu/BlueItem.gss",
            "com/sencha/gxt/theme/base/client/menu/SeparatorMenuItem.gss",
            "BlueSeparatorMenuItem.gss"})
    BlueSeparatorMenuItemStyle style();

    ImageResource itemOver();
  }

  public interface BlueSeparatorMenuItemStyle extends SeparatorMenuItemStyle {
  }

  public BlueSeparatorMenuItemAppearance() {
    this(GWT.<BlueSeparatorMenuItemResources> create(BlueSeparatorMenuItemResources.class),
        GWT.<SeparatorMenuItemTemplate> create(SeparatorMenuItemTemplate.class));
  }

  public BlueSeparatorMenuItemAppearance(BlueSeparatorMenuItemResources resources,
      SeparatorMenuItemTemplate template) {
    super(resources, template);
  }

}
