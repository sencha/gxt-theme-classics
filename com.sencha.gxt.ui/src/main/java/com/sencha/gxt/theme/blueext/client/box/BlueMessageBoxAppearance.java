package com.sencha.gxt.theme.blueext.client.box;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.blue.client.window.BlueWindowAppearance;

public class BlueMessageBoxAppearance extends BlueWindowAppearance {

  public interface BlueMessageBoxResources extends BlueWindowResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/panel/ContentPanel.gss",
        "com/sencha/gxt/theme/blue/client/window/BlueWindow.gss"})
    @Override
    BlueWindowStyle style();

  }

  public BlueMessageBoxAppearance() {
    super((BlueMessageBoxResources) GWT.create(BlueMessageBoxResources.class));
  }
}
