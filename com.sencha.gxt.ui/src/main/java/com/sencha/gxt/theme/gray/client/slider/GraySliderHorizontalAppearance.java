package com.sencha.gxt.theme.gray.client.slider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.cell.core.client.SliderCell.HorizontalSliderAppearance;
import com.sencha.gxt.theme.base.client.slider.SliderHorizontalBaseAppearance;

public class GraySliderHorizontalAppearance extends SliderHorizontalBaseAppearance implements HorizontalSliderAppearance {

  public interface GrayHorizontalSliderStyle extends SliderHorizontalStyle, CssResource {
  }

  public interface GraySliderHorizontalResources extends SliderHorizontalResources, ClientBundle {

    @Source({
        "com/sencha/gxt/theme/base/client/slider/Slider.gss",
        "com/sencha/gxt/theme/base/client/slider/SliderHorizontal.gss", "GraySliderHorizontal.gss"})
    GrayHorizontalSliderStyle style();

    ImageResource thumbHorizontal();

    ImageResource thumbHorizontalDown();

    ImageResource thumbHorizontalOver();

    ImageResource trackHorizontalLeft();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource trackHorizontalMiddle();

    ImageResource trackHorizontalRight();

  }

  public GraySliderHorizontalAppearance() {
    this(GWT.<GraySliderHorizontalResources> create(GraySliderHorizontalResources.class),
        GWT.<SliderHorizontalTemplate> create(SliderHorizontalTemplate.class));
  }

  public GraySliderHorizontalAppearance(GraySliderHorizontalResources resources,
      SliderHorizontalTemplate template) {
    super(resources, template);
  }

}
