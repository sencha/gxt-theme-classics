package com.sencha.gxt.theme.base.client.info;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;

public class DefaultInfoConfigDefaultAppearance implements DefaultInfoConfig.DefaultInfoConfigAppearance {

  public interface DefaultInfoConfigStyle extends CssResource {
    String infoTitle();

    String infoMessage();
  }

  public interface InfoConfigResources extends ClientBundle {
    @Source("InfoDefault.gss")
    DefaultInfoConfigStyle style();
  }

  private InfoConfigResources resources;
  private DefaultInfoConfigStyle style;

  public DefaultInfoConfigDefaultAppearance() {
    this((InfoConfigResources)GWT.create(InfoConfigResources.class));
  }

  public DefaultInfoConfigDefaultAppearance(InfoConfigResources resources) {
    this.resources = resources;
    this.style = this.resources.style();
    this.style.ensureInjected();
  }

  @Override
  public void render(SafeHtmlBuilder sb, SafeHtml title, SafeHtml message) {

    if (title != SafeHtmlUtils.EMPTY_SAFE_HTML) {
      sb.appendHtmlConstant("<div class='" + style.infoTitle() + "'>");
      sb.append(title);
      sb.appendHtmlConstant("</div>");
    }
    if (message != SafeHtmlUtils.EMPTY_SAFE_HTML) {
      sb.appendHtmlConstant("<div class='" + style.infoMessage() + "'>");
      sb.append(message);
      sb.appendHtmlConstant("</div>");
    }
  }

}
