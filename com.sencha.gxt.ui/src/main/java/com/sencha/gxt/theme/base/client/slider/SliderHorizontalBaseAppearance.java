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

public abstract class SliderHorizontalBaseAppearance extends SliderBaseAppearance {

  public interface SliderHorizontalResources extends SliderResources {

    @Override
    SliderHorizontalStyle style();

  }

  public interface SliderHorizontalStyle extends SliderStyle {
  }

  public interface SliderHorizontalTemplate extends XTemplates {

    @XTemplate(source = "SliderHorizontal.html")
    SafeHtml template(SliderStyle style, SafeStyles width, SafeStyles offset);

  }

  private final SliderHorizontalResources resources;
  private final SliderHorizontalTemplate template;

  public SliderHorizontalBaseAppearance(SliderHorizontalResources resources, SliderHorizontalTemplate template) {
    super(resources);
    this.resources = resources;
    this.template = template;
  }

  @Override
  public int getSliderLength(XElement parent) {
    return getInnerEl(parent).getOffsetWidth();
  }

  @Override
  public int getClickedValue(Context context, Element parent, Point location) {
    Element innerEl = getInnerEl(parent);
    return location.getX() - XElement.as(innerEl).getLeft(false);
  }

  @Override
  public void render(double fractionalValue, int width, int height, SafeHtmlBuilder sb) {
    if (width == -1) {
      // default
      width = 200;
    }

    // padding
    width -= getTrackPadding();

    SafeStyles offsetStyles = createThumbStyles(fractionalValue, width);
    SafeStyles widthStyle = SafeStylesUtils.fromTrustedString("width: " + width + "px;");
    sb.append(template.template(resources.style(), widthStyle, offsetStyles));
  }

  @Override
  public void setThumbPosition(Element parent, int pos) {
    XElement thumbElement = XElement.as(getThumb(parent));
    int halfThumbSize = getHalfThumbWidth();
    pos = Math.max(-halfThumbSize, pos);
    thumbElement.getStyle().setLeft(pos, Unit.PX);
  }

  @Override
  protected Element getEndEl(Element parent) {
    return parent.getFirstChildElement().getFirstChildElement();
  }

  @Override
  protected Element getInnerEl(Element parent) {
    return parent.getFirstChildElement().getFirstChildElement().getFirstChildElement();
  }

  @Override
  public boolean isVertical() {
    return false;
  }

  protected SafeStyles createThumbStyles(double fractionalValue, int width) {
    int halfThumbWidth = getHalfThumbWidth();
    int maxTrackLength = width - getHalfThumbWidth();
    int offset = (int) (fractionalValue * maxTrackLength) - halfThumbWidth;
    offset = Math.max(-halfThumbWidth, offset);
    offset = Math.min(maxTrackLength + halfThumbWidth, offset);

    SafeStyles offsetStyles = SafeStylesUtils.fromTrustedString("left:" + offset + "px;");
    return offsetStyles;
  }

  protected int getHalfThumbWidth() {
    return 7;
  }

  protected int getTrackPadding() {
    return 7;
  }

}
