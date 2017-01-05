package com.sencha.gxt.theme.blue.client.tabs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel.PlainTabPanelAppearance;

/**
 * A blue-colored appearance for {@link PlainTabPanel}. This appearance differs
 * from {@link BlueTabPanelAppearance} in that it has a simplified tab strip.
 */
public class BluePlainTabPanelAppearance extends BlueTabPanelAppearance implements PlainTabPanelAppearance {

  public interface BluePlainTabPanelResources extends BlueTabPanelResources {

    @Source({
        "com/sencha/gxt/theme/base/client/tabs/TabPanel.gss", "BlueTabPanel.gss",
        "com/sencha/gxt/theme/base/client/tabs/PlainTabPanel.gss", "BluePlainTabPanel.gss"})
    BluePlainTabPanelStyle style();

  }

  public interface BluePlainTabPanelStyle extends BlueTabPanelStyle {

    String tabStripSpacer();

  }

  public interface PlainTabPanelTemplates extends Template {

    @XTemplate(source = "com/sencha/gxt/theme/base/client/tabs/TabPanel.html")
    SafeHtml render(TabPanelStyle style);

    @XTemplate(source = "com/sencha/gxt/theme/base/client/tabs/PlainTabPanel.html")
    SafeHtml renderPlain(BluePlainTabPanelStyle style);

  }

  private final PlainTabPanelTemplates template;
  private final BluePlainTabPanelStyle style;

  public BluePlainTabPanelAppearance() {
    this(GWT.<BluePlainTabPanelResources> create(BluePlainTabPanelResources.class),
        GWT.<PlainTabPanelTemplates> create(PlainTabPanelTemplates.class),
        GWT.<ItemTemplate> create(ItemTemplate.class));
  }

  public BluePlainTabPanelAppearance(BluePlainTabPanelResources resources, PlainTabPanelTemplates template,
      ItemTemplate itemTemplate) {
    super(resources, template, itemTemplate);
    this.style = resources.style();
    this.template = template;
  }

  @Override
  public void render(SafeHtmlBuilder builder) {
    builder.append(template.renderPlain(style));
  }

}
