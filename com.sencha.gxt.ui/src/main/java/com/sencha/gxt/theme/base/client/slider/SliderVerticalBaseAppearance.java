package com.sencha.gxt.theme.base.client.slider;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Point;

public abstract class SliderVerticalBaseAppearance extends SliderBaseAppearance {

  public interface BaseSliderVerticalStyle extends SliderStyle {
  }

  public interface SliderVerticalResources extends SliderResources {

    @Override
    BaseSliderVerticalStyle style();

  }

  public interface SliderVerticalTemplate extends XTemplates {

    @XTemplate(source = "SliderVertical.html")
    SafeHtml template(SliderStyle style, SafeStyles offset, SafeStyles innerHeight);

  }

  protected final SliderVerticalTemplate template;

  private final SliderVerticalResources resources;

  public SliderVerticalBaseAppearance(SliderVerticalResources resources, SliderVerticalTemplate template) {
    super(resources);
    this.resources = resources;
    this.template = template;
  }

  @Override
  public int getClickedValue(Context context, Element parent, Point location) {
    Element innerEl = getInnerEl(parent);
    return location.getY() - XElement.as(innerEl).getTop(false);
  }

  @Override
  public int getSliderLength(XElement parent) {
    return getInnerEl(parent).getOffsetHeight();
  }

  @Override
  public boolean isVertical() {
    return true;
  }

  @Override
  public void render(double fractionalValue, int width, int height, SafeHtmlBuilder sb) {
    if (height == -1) {
      // default
      height = 200;
    }

    SafeStyles offsetStyles = createThumbStyles(fractionalValue, height);
    SafeStyles heightStyle = SafeStylesUtils.fromTrustedString("");

    // ends
    height -= getTrackPadding();
    heightStyle = SafeStylesUtils.fromTrustedString("height: " + height + "px;");

    sb.append(template.template(resources.style(), offsetStyles, heightStyle));
  }

  protected SafeStyles createThumbStyles(double fractionalValue, int height) {
    int halfThumb = getHalfThumbSize();
    int innerHeight = height;
    int offset = (int) (innerHeight - ((fractionalValue * innerHeight) - halfThumb));

    offset = innerHeight - offset;
    return SafeStylesUtils.fromTrustedString("bottom:" + offset + "px;");
  }

  protected int getHalfThumbSize() {
    return resources.style().halfThumb();
  }

  protected int getTrackPadding() {
    return 14;
  }

  @Override
  public void setThumbPosition(Element parent, int pos) {
    XElement thumbElement = XElement.as(getThumb(parent));
    thumbElement.getStyle().setBottom(pos, Unit.PX);
  }

}
