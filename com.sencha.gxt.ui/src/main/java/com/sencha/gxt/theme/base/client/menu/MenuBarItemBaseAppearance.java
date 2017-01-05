package com.sencha.gxt.theme.base.client.menu;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem.MenuBarItemAppearance;

public abstract class MenuBarItemBaseAppearance implements MenuBarItemAppearance {

  public interface MenuBarItemResources {

    MenuBarItemStyle css();

  }

  public interface MenuBarItemStyle extends CssResource {

    String active();

    String menuBarItem();

    String over();

  }

  private final MenuBarItemResources resources;

  public MenuBarItemBaseAppearance(MenuBarItemResources resources) {
    this.resources = resources;
    resources.css().ensureInjected();
  }

  public XElement getHtmlElement(XElement parent) {
    return parent;
  }

  public void onActive(XElement parent, boolean active) {
    parent.setClassName(resources.css().active(), active);
  }

  public void onOver(XElement parent, boolean over) {
    parent.setClassName(resources.css().over(), over);
  }

  public void render(SafeHtmlBuilder builder) {
    builder.appendHtmlConstant("<div class='" + resources.css().menuBarItem() + "'></div>");
  }

}
