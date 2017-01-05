package com.sencha.gxt.theme.base.client.widget;

import com.google.gwt.core.client.GWT;

public class GrayCollapsePanelAppearance extends CollapsePanelDefaultAppearance {

  public interface GrayCollapsePanelResources extends CollapsePanelResources {

    @Source({"CollapsePanel.gss", "GrayCollapsePanel.gss"})
    GrayCollapsePanelStyle style();
  }

  public interface GrayCollapsePanelStyle extends CollapsePanelStyle {
  }

  public GrayCollapsePanelAppearance() {
    this(GWT.<GrayCollapsePanelResources>create(GrayCollapsePanelResources.class));
  }

  public GrayCollapsePanelAppearance(GrayCollapsePanelResources resources) {
    super(resources);
  }

}
