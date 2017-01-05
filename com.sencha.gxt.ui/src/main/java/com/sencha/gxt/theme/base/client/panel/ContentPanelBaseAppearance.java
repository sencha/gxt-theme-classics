package com.sencha.gxt.theme.base.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.button.IconButton.IconConfig;
import com.sencha.gxt.widget.core.client.button.ToolButton;

public abstract class ContentPanelBaseAppearance implements ContentPanelAppearance {

  public interface ContentPanelResources extends ClientBundle {

    @Source("ContentPanel.gss")
    ContentPanelStyle style();

  }

  public interface ContentPanelStyle extends CssResource {

    String body();

    String bodyWrap();

    String footer();

    String header();

    String panel();

    String noHeader();

  }

  public interface ContentPanelTemplate extends XTemplates {
    @XTemplate(source = "ContentPanel.html")
    SafeHtml render(ContentPanelStyle style);
  }

  protected ContentPanelTemplate template;
  protected final ContentPanelStyle style;
  protected final ContentPanelResources resources;

  public ContentPanelBaseAppearance() {
    this((ContentPanelResources) GWT.create(ContentPanelResources.class));
  }

  public ContentPanelBaseAppearance(ContentPanelResources resources) {
    this(resources, GWT.<ContentPanelTemplate> create(ContentPanelTemplate.class));
  }

  public ContentPanelBaseAppearance(ContentPanelResources resources, ContentPanelTemplate template) {
    this.resources = resources;
    this.style = this.resources.style();

    StyleInjectorHelper.ensureInjected(this.style, true);
    this.template = template;
  }

  @Override
  public void onHideHeader(XElement parent, boolean hide) {
    parent.selectNode("." + style.header()).setVisible(!hide);
    parent.setClassName(style.noHeader(), hide);
  }

  @Override
  public void onBodyBorder(XElement parent, boolean border) {
    getBodyWrap(parent).applyStyles(!border ? "border: 0px" : "");
  }

  @Override
  public XElement getBodyWrap(XElement parent) {
    return parent.selectNode("." + style.bodyWrap());
  }

  @Override
  public XElement getContentElem(XElement parent) {
    return parent.selectNode("." + style.body());
  }

  @Override
  public XElement getFooterElem(XElement parent) {
    return parent.selectNode("." + style.footer());
  }

  @Override
  public int getFrameHeight(XElement parent) {
    return getHeaderElem(parent).isVisible() ? 1 : 2;
  }

  @Override
  public int getFrameWidth(XElement parent) {
    return 2;
  }

  @Override
  public Size getHeaderSize(XElement parent) {
    Element head = parent.getFirstChildElement();
    return new Size(head.getOffsetWidth(), head.getOffsetHeight());
  }

  @Override
  public XElement getHeaderElem(XElement parent) {
    return parent.selectNode("." + style.header());
  }

  @Override
  public void render(SafeHtmlBuilder sb) {
    sb.append(template.render(style));
  }

  @Override
  public IconConfig collapseIcon() {
    return ToolButton.UP;
  }

  @Override
  public IconConfig expandIcon() {
    return ToolButton.DOWN;
  }

}
