package com.sencha.gxt.theme.gray.client.slider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.cell.core.client.SliderCell.VerticalSliderAppearance;
import com.sencha.gxt.theme.base.client.slider.SliderVerticalBaseAppearance;

public class GraySliderVerticalAppearance extends SliderVerticalBaseAppearance implements VerticalSliderAppearance {

  public interface GraySliderVerticalResources extends SliderVerticalResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/slider/Slider.gss",
        "com/sencha/gxt/theme/base/client/slider/SliderVertical.gss", "GraySliderVertical.gss"})
    GrayVerticalSliderStyle style();

    ImageResource thumbVertical();

    ImageResource thumbVerticalDown();

    ImageResource thumbVerticalOver();

    ImageResource trackVerticalBottom();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource trackVerticalMiddle();

    ImageResource trackVerticalTop();

  }

  public interface GrayVerticalSliderStyle extends BaseSliderVerticalStyle, CssResource {
  }

  public GraySliderVerticalAppearance() {
    this(GWT.<GraySliderVerticalResources> create(GraySliderVerticalResources.class),
        GWT.<SliderVerticalTemplate> create(SliderVerticalTemplate.class));
  }

  public GraySliderVerticalAppearance(GraySliderVerticalResources resources, SliderVerticalTemplate template) {
    super(resources, template);
  }

}
