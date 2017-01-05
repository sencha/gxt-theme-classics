package com.sencha.gxt.theme.gray.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.SeparatorMenuItemBaseAppearance;

public class GraySeparatorMenuItemAppearance extends SeparatorMenuItemBaseAppearance {

  public interface GraySeparatorMenuItemResources extends ClientBundle, SeparatorMenuItemResources {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/menu/Item.gss",
            "com/sencha/gxt/theme/gray/client/menu/GrayItem.gss",
            "com/sencha/gxt/theme/base/client/menu/SeparatorMenuItem.gss",
            "GraySeparatorMenuItem.gss"})
    GraySeparatorMenuItemStyle style();

    ImageResource itemOver();
  }

  public interface GraySeparatorMenuItemStyle extends SeparatorMenuItemStyle {
  }

  public GraySeparatorMenuItemAppearance() {
    this(GWT.<GraySeparatorMenuItemResources> create(GraySeparatorMenuItemResources.class),
        GWT.<SeparatorMenuItemTemplate> create(SeparatorMenuItemTemplate.class));
  }

  public GraySeparatorMenuItemAppearance(GraySeparatorMenuItemResources resources,
      SeparatorMenuItemTemplate template) {
    super(resources, template);
  }

}
