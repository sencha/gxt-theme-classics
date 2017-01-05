package com.sencha.gxt.theme.base.client.menu;

import com.google.gwt.resources.client.CssResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.widget.core.client.menu.Item;

public abstract class ItemBaseAppearance implements Item.ItemAppearance {

  public interface ItemResources {

    ItemStyle style();

  }

  public interface ItemStyle extends CssResource {

    String active();

  }

  private final ItemStyle style;

  public ItemBaseAppearance(ItemResources resources) {
    style = resources.style();
    StyleInjectorHelper.ensureInjected(this.style, true);
  }

  public void onActivate(XElement parent) {
    parent.addClassName(style.active());
  }

  public void onDeactivate(XElement parent) {
    parent.removeClassName(style.active());
  }

}
