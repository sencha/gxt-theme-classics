package com.sencha.gxt.theme.base.client.status;

import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.widget.core.client.Status;

public abstract class BoxStatusBaseAppearance implements Status.StatusAppearance {

  public interface BoxStatusResources {
    @Source("com/sencha/gxt/theme/base/client/grid/loading.gif")
    ImageResource loading();

    BoxStatusStyle style();

  }

  public interface BoxStatusStyle extends CssResource {

    String status();

    String statusBox();

    String statusIcon();

    String statusText();

  }

  public interface BoxTemplate extends XTemplates {

    @XTemplate(source = "BoxStatus.html")
    SafeHtml render(BoxStatusStyle style);

  }

  private final BoxStatusResources resources;
  private final BoxStatusStyle style;
  private BoxTemplate template;

  public BoxStatusBaseAppearance(BoxStatusResources resources, BoxTemplate template) {
    this.resources = resources;
    style = resources.style();
    style.ensureInjected();

    this.template = template;
  }

  @Override
  public ImageResource getBusyIcon() {
    return resources.loading();
  }

  @Override
  public XElement getHtmlElement(XElement parent) {
    return parent.selectNode("." + style.statusText());
  }

  @Override
  public void render(SafeHtmlBuilder sb) {
    sb.append(template.render(style));
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
