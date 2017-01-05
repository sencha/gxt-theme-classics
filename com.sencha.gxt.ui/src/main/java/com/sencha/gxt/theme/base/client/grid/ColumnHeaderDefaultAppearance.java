package com.sencha.gxt.theme.base.client.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.widget.core.client.grid.ColumnHeader.ColumnHeaderAppearance;
import com.sencha.gxt.widget.core.client.grid.ColumnHeader.ColumnHeaderStyles;

public class ColumnHeaderDefaultAppearance implements ColumnHeaderAppearance {

  public interface ColumnHeaderResources extends ClientBundle {

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource columnHeader();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource columnHeaderOver();

    /** @return the menu icon */
    ImageResource columnsIcon();

    /** @return the grid header marker */
    ImageResource sortAsc();

    /** @return the grid header marker */
    ImageResource sortDesc();

    /** @return the menu icon */
    ImageResource sortAscendingIcon();

    /** @return the menu icon */
    ImageResource sortDescendingIcon();

    /** @return the column dnd indicator 1 */
    ImageResource columnMoveTop();

    /** @return the column dnd indicator 2 */
    ImageResource columnMoveBottom();

    @Source("ColumnHeader.gss")
    DefaultColumnHeaderStyles style();

  }
  public interface DefaultColumnHeaderStyles extends ColumnHeaderStyles {

  }

  public interface ColumnHeaderTemplate extends XTemplates {
    @XTemplate(source = "ColumnHeader.html")
    SafeHtml render(ColumnHeaderStyles style);
  }

  private final ColumnHeaderResources resources;
  private final ColumnHeaderStyles style;
  private ColumnHeaderTemplate templates = GWT.create(ColumnHeaderTemplate.class);

  public ColumnHeaderDefaultAppearance() {
    this(GWT.<ColumnHeaderResources> create(ColumnHeaderResources.class));
  }

  public ColumnHeaderDefaultAppearance(ColumnHeaderResources resources) {
    this.resources = resources;
    this.style = this.resources.style();

    StyleInjectorHelper.ensureInjected(style, true);
  }

  @Override
  public ImageResource columnsIcon() {
    return resources.columnsIcon();
  }

  @Override
  public void render(SafeHtmlBuilder sb) {
    sb.append(templates.render(style));
  }

  @Override
  public ImageResource sortAscendingIcon() {
    return resources.sortAscendingIcon();
  }

  @Override
  public ImageResource sortDescendingIcon() {
    return resources.sortDescendingIcon();
  }

  @Override
  public ColumnHeaderStyles styles() {
    return style;
  }

  @Override
  public String columnsWrapSelector() {
    return "." + style.headerInner();
  }

  @Override
  public int getColumnMenuWidth() {
    return 14;
  }
}
