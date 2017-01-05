package com.sencha.gxt.theme.blueext.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.ItemBaseAppearance;

public class BlueItemAppearance extends ItemBaseAppearance {

  public interface BlueItemResources extends ItemBaseAppearance.ItemResources, ClientBundle {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/menu/Item.gss",
            "com/sencha/gxt/theme/blue/client/menu/BlueItem.gss",
            "BlueItem.gss"})
    BlueItemStyle style();

    ImageResource itemOver();

  }

  public interface BlueItemStyle extends ItemStyle {

  }

  public BlueItemAppearance() {
    this(GWT.<BlueItemResources> create(BlueItemResources.class));
  }

  public BlueItemAppearance(BlueItemResources resources) {
    super(resources);
  }

}
