package com.sencha.gxt.theme.blueext.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.menu.MenuBarBaseAppearance;

public class BlueMenuBarAppearance extends MenuBarBaseAppearance {

  public interface BlueMenuBarResources extends MenuBarResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/menu/MenuBar.gss", "BlueMenuBar.gss"})
    BlueMenuBarStyle css();

    @ImageOptions(repeatStyle=RepeatStyle.Horizontal)
    ImageResource background();

  }

  public interface BlueMenuBarStyle extends MenuBarStyle {
  }

  public BlueMenuBarAppearance() {
    this(GWT.<BlueMenuBarResources> create(BlueMenuBarResources.class));
  }

  public BlueMenuBarAppearance(BlueMenuBarResources resources) {
    super(resources);
  }

}
