package com.sencha.gxt.theme.gray.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.dom.client.Element;
import com.sencha.gxt.widget.core.client.menu.HeaderMenuItem.HeaderMenuItemAppearance;

public class GrayHeaderMenuItemAppearance extends GrayItemAppearance implements HeaderMenuItemAppearance {

  public interface GrayHeaderMenuItemResources extends GrayItemResources {

    @Source("GrayHeaderMenuItem.gss")
    GrayHeaderMenuItemStyle headerStyle();

  }

  public interface GrayHeaderMenuItemStyle extends CssResource {

    public String menuText();

  }

  private GrayHeaderMenuItemStyle headerStyle;

  public GrayHeaderMenuItemAppearance() {
    this(GWT.<GrayHeaderMenuItemResources> create(GrayHeaderMenuItemResources.class));
  }

  public GrayHeaderMenuItemAppearance(GrayHeaderMenuItemResources resources) {
    super(resources);
    headerStyle = resources.headerStyle();
    headerStyle.ensureInjected();
  }

  @Override
  public void applyItemStyle(Element element) {
    element.addClassName(headerStyle.menuText());
  }

}
