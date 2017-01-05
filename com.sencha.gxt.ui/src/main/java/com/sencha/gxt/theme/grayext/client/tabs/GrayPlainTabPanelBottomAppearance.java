package com.sencha.gxt.theme.grayext.client.tabs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.theme.gray.client.tabs.GrayPlainTabPanelAppearance.GrayPlainTabPanelResources;
import com.sencha.gxt.theme.gray.client.tabs.GrayPlainTabPanelAppearance.GrayPlainTabPanelStyle;
import com.sencha.gxt.theme.gray.client.tabs.GrayTabPanelBottomAppearance;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel.PlainTabPanelBottomAppearance;

/**
 * A gray-coloured appearance for {@link PlainTabPanel} with tabs below the
 * content area. This appearance differs from
 * {@link GrayTabPanelBottomAppearance} in that it has a simplified tab strip.
 */
public class GrayPlainTabPanelBottomAppearance extends GrayTabPanelBottomAppearance implements PlainTabPanelBottomAppearance {

  public interface PlainTabPanelBottomTemplates extends BottomTemplate {

    @XTemplate(source = "TabPanelBottom.html")
    SafeHtml render(TabPanelStyle style);

    @XTemplate(source = "PlainTabPanelBottom.html")
    SafeHtml renderPlain(GrayPlainTabPanelStyle style);

  }

  protected PlainTabPanelBottomTemplates template;
  protected GrayPlainTabPanelResources resources;

  public GrayPlainTabPanelBottomAppearance() {
    this(GWT.<GrayPlainTabPanelResources> create(GrayPlainTabPanelResources.class),
        GWT.<PlainTabPanelBottomTemplates> create(PlainTabPanelBottomTemplates.class),
        GWT.<ItemTemplate> create(ItemTemplate.class));
  }

  public GrayPlainTabPanelBottomAppearance(GrayPlainTabPanelResources resources, PlainTabPanelBottomTemplates template,
      ItemTemplate itemTemplate) {
    super(resources, template, itemTemplate);
    this.resources = resources;
    this.template = template;
  }

  @Override
  public void render(SafeHtmlBuilder builder) {
    builder.append(template.renderPlain(resources.style()));
  }

}
