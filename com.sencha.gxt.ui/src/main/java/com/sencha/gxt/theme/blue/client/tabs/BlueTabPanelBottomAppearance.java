package com.sencha.gxt.theme.blue.client.tabs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelBottomAppearance;

public class BlueTabPanelBottomAppearance extends BlueTabPanelAppearance implements TabPanelBottomAppearance {

  public interface BottomTemplate extends Template {

    @XTemplate(source = "TabPanelBottom.html")
    SafeHtml render(TabPanelStyle style);

  }

  public BlueTabPanelBottomAppearance() {
    this(GWT.<BlueTabPanelResources> create(BlueTabPanelResources.class), GWT
        .<BottomTemplate> create(BottomTemplate.class), GWT.<ItemTemplate> create(ItemTemplate.class));
  }

  public BlueTabPanelBottomAppearance(BlueTabPanelResources resources, BottomTemplate template,
      ItemTemplate itemTemplate) {
    super(resources, template, itemTemplate);
  }

  @Override
  public XElement getBar(XElement parent) {
    return parent.getLastChild().cast();
  }

  @Override
  public void onScrolling(XElement parent, boolean scrolling) {
    parent.selectNode("." + style.tabFooter()).setClassName(style.tabScrolling(), scrolling);
  }

  @Override
  public XElement getBody(XElement parent) {
    return parent.getFirstChildElement().cast();
  }

  public XElement getStrip(XElement parent) {
    return getBar(parent).selectNode("." + style.tabStripBottom());
  }

}
