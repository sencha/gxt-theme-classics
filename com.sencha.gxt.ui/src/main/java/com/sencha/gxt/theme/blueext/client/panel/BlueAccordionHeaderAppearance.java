package com.sencha.gxt.theme.blueext.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.widget.HeaderDefaultAppearance;

public class BlueAccordionHeaderAppearance extends HeaderDefaultAppearance {

  public interface BlueAccordionHeaderStyle extends HeaderStyle {
    String header();

    String headerIcon();

    String headerHasIcon();

    String headerText();

    String headerBar();
  }

  public interface BlueAccordionHeaderResources extends HeaderResources {

    @Source({"com/sencha/gxt/theme/base/client/widget/Header.gss", "BlueHeader.gss", "BlueAccordionHeader.gss"})
    BlueAccordionHeaderStyle style();

    @Source("light-hd.gif")
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource headerBackground();
  }

  public BlueAccordionHeaderAppearance() {
    super(GWT.<BlueAccordionHeaderResources> create(BlueAccordionHeaderResources.class));
  }

}
