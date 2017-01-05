package com.sencha.gxt.theme.base.client.grid;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Import;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.widget.core.client.grid.GridView.GridStateStyles;
import com.sencha.gxt.widget.core.client.grid.RowExpander.RowExpanderAppearance;

public class RowExpanderDefaultAppearance<M> implements RowExpanderAppearance<M> {

  public interface RowExpanderResources extends ClientBundle {
    @Import(GridStateStyles.class)
    @Source("RowExpander.gss")
    RowExpanderStyle style();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource specialColumn();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource specialColumnSelected();
  }

  public interface RowExpanderStyle extends CssResource {
    String rowCollapsed();

    String rowExpanded();

    String rowExpander();

    String hasExpander();

    String cell();
  }

  private final RowExpanderResources resources;
  private final RowExpanderStyle style;

  public RowExpanderDefaultAppearance() {
    this(GWT.<RowExpanderResources> create(RowExpanderResources.class));
  }

  public RowExpanderDefaultAppearance(RowExpanderResources resources) {
    this.resources = resources;
    this.style = this.resources.style();

    StyleInjectorHelper.ensureInjected(style, false);
  }

  @Override
  public void finishInit(XElement gridParent) {
    gridParent.addClassName(style.hasExpander());
  }

  @Override
  public String getCellClassName() {
    return style.cell();
  }

  @Override
  public String getRowStyles(M model, int rowIndex) {
    return style.rowCollapsed();
  }

  @Override
  public boolean isExpanded(XElement row) {
    return row.hasClassName(style.rowExpanded());
  }

  @Override
  public void onExpand(XElement row, boolean expand) {
    if (expand) {
      row.replaceClassName(style.rowCollapsed(), style.rowExpanded());
    } else {
      row.replaceClassName(style.rowExpanded(), style.rowCollapsed());
    }
  }

  @Override
  public void renderExpander(Context context, M value, SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<div class='" + style.rowExpander() + "'>&nbsp;</div>");
  }

  @Override
  public boolean isExpandElement(XElement target) {
    return target.hasClassName(style.rowExpander());
  }

}
