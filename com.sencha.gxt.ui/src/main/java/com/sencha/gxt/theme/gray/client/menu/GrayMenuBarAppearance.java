package com.sencha.gxt.theme.gray.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.menu.MenuBarBaseAppearance;

public class GrayMenuBarAppearance extends MenuBarBaseAppearance {

  public interface GrayMenuBarResources extends MenuBarResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/menu/MenuBar.gss", "GrayMenuBar.gss"})
    GrayMenuBarStyle css();

    @ImageOptions(repeatStyle=RepeatStyle.Horizontal)
    ImageResource background();

  }

  public interface GrayMenuBarStyle extends MenuBarStyle {
  }

  public GrayMenuBarAppearance() {
    this(GWT.<GrayMenuBarResources> create(GrayMenuBarResources.class));
  }

  public GrayMenuBarAppearance(GrayMenuBarResources resources) {
    super(resources);
  }

}
