package com.sencha.gxt.theme.gray.client.box;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.gray.client.window.GrayWindowAppearance;

public class GrayMessageBoxAppearance extends GrayWindowAppearance {

  public interface GrayMessageBoxResources extends GrayWindowResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/panel/ContentPanel.gss",
        "com/sencha/gxt/theme/gray/client/window/GrayWindow.gss"})
    @Override
    GrayWindowStyle style();

  }

  public GrayMessageBoxAppearance() {
    super((GrayMessageBoxResources) GWT.create(GrayMessageBoxResources.class));
  }
}
