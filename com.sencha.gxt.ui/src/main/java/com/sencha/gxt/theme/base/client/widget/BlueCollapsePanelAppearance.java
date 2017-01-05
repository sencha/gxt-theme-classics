package com.sencha.gxt.theme.base.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.theme.base.client.widget.CollapsePanelDefaultAppearance.CollapsePanelResources;
import com.sencha.gxt.theme.base.client.widget.CollapsePanelDefaultAppearance.CollapsePanelStyle;
import com.sencha.gxt.widget.core.client.CollapsePanel.CollapsePanelAppearance;

public class BlueCollapsePanelAppearance extends CollapsePanelDefaultAppearance {

  public interface BlueCollapsePanelResources extends CollapsePanelResources {

    @Source({"CollapsePanel.gss", "BlueCollapsePanel.gss"})
    BlueCollapsePanelStyle style();
  }

  public interface BlueCollapsePanelStyle extends CollapsePanelStyle {
  }

  public BlueCollapsePanelAppearance() {
    this(GWT.<BlueCollapsePanelResources>create(BlueCollapsePanelResources.class));
  }

  public BlueCollapsePanelAppearance(BlueCollapsePanelResources resources) {
    super(resources);
  }

}
