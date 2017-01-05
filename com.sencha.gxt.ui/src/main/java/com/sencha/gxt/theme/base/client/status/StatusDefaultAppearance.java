package com.sencha.gxt.theme.base.client.status;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.widget.core.client.Status;

public class StatusDefaultAppearance implements Status.StatusAppearance {

  public interface StatusResources extends ClientBundle {
    @Source("com/sencha/gxt/theme/base/client/grid/loading.gif")
    ImageResource loading();

    @Source("Status.gss")
    StatusStyle style();

  }

  public interface StatusStyle extends CssResource {

    String status();

    String statusIcon();

    String statusText();

  }

  public interface Template extends XTemplates {

    @XTemplate(source = "Status.html")
    SafeHtml template(StatusStyle style);

  }

  private final StatusResources resources;
  private final StatusStyle style;
  private Template template;

  protected StatusDefaultAppearance() {
    this(GWT.<StatusResources>create(StatusResources.class), GWT.<Template>create(Template.class));
  }

  public StatusDefaultAppearance(StatusResources resources, Template template) {
    this.resources = resources;
    style = resources.style();
    style.ensureInjected();

    this.template = template;
  }

  @Override
  public XElement getHtmlElement(XElement parent) {
    return parent.selectNode("." + style.statusText());
  }


  @Override
  public void render(SafeHtmlBuilder sb) {
    sb.append(template.template(style));
  }

  @Override
  public ImageResource getBusyIcon() {
    return resources.loading();
  }

  @Override
  public void onUpdateIcon(XElement parent, ImageResource icon) {
    XElement iconWrap = parent.selectNode("." + style.statusIcon());
    iconWrap.setVisible(icon != null);
    if (icon != null) {
      iconWrap.removeChildren();
      iconWrap.appendChild(IconHelper.getElement(icon));
    }
  }

}
