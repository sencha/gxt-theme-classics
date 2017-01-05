package com.sencha.gxt.theme.grayext.client.panel;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.panel.ContentPanelBaseAppearance;
import com.sencha.gxt.theme.base.client.widget.HeaderDefaultAppearance;
import com.sencha.gxt.theme.gray.client.panel.GrayHeaderAppearance;

public class GrayContentPanelAppearance extends ContentPanelBaseAppearance {

  public interface GrayContentPanelResources extends ContentPanelResources {

    @Source({"com/sencha/gxt/theme/base/client/panel/ContentPanel.gss", "GrayContentPanel.gss"})
    @Override
    GrayContentPanelStyle style();

  }

  public interface GrayContentPanelStyle extends ContentPanelStyle {

  }

  public GrayContentPanelAppearance() {
    super(GWT.<GrayContentPanelResources> create(GrayContentPanelResources.class),
        GWT.<ContentPanelTemplate> create(ContentPanelTemplate.class));
  }

  public GrayContentPanelAppearance(GrayContentPanelResources resources) {
    super(resources, GWT.<ContentPanelTemplate> create(ContentPanelTemplate.class));
  }

  @Override
  public HeaderDefaultAppearance getHeaderAppearance() {
    return new GrayHeaderAppearance();
  }

}
