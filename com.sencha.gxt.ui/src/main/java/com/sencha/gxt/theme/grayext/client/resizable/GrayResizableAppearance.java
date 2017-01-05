package com.sencha.gxt.theme.grayext.client.resizable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.resizable.ResizableBaseAppearance;

public class GrayResizableAppearance extends ResizableBaseAppearance {

  public interface GrayResizableResources extends ResizableResources, ClientBundle {

    ImageResource handleEast();

    ImageResource handleNortheast();

    ImageResource handleNorthwest();

    ImageResource handleSouth();

    ImageResource handleSoutheast();

    ImageResource handleSouthwest();

    @Source({"com/sencha/gxt/theme/base/client/resizable/Resizable.gss", "GrayResizable.gss"})
    GrayResizableStyle style();

  }

  public interface GrayResizableStyle extends ResizableStyle {
  }

  public GrayResizableAppearance() {
    this(GWT.<GrayResizableResources> create(GrayResizableResources.class));
  }

  public GrayResizableAppearance(ResizableResources resources) {
    super(resources);
  }

}
