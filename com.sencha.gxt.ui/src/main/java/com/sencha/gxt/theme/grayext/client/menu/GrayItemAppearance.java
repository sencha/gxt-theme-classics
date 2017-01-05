package com.sencha.gxt.theme.grayext.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.menu.ItemBaseAppearance;

public class GrayItemAppearance extends ItemBaseAppearance {

  public interface GrayItemResources extends ItemBaseAppearance.ItemResources, ClientBundle {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/menu/Item.gss",
            "com/sencha/gxt/theme/gray/client/menu/GrayItem.gss",
            "GrayItem.gss"})
    GrayItemStyle style();

    ImageResource itemOver();

  }

  public interface GrayItemStyle extends ItemStyle {

  }

  public GrayItemAppearance() {
    this(GWT.<GrayItemResources> create(GrayItemResources.class));
  }

  public GrayItemAppearance(GrayItemResources resources) {
    super(resources);
  }

}
