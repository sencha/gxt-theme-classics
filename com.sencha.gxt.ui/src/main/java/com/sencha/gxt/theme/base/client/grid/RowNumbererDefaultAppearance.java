package com.sencha.gxt.theme.base.client.grid;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.Import;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.widget.core.client.grid.GridView.GridStateStyles;
import com.sencha.gxt.widget.core.client.grid.RowNumberer.RowNumbererAppearance;

/**
 * Default appearance for the RowNumberer column config.
 */
public class RowNumbererDefaultAppearance implements RowNumbererAppearance {

  public interface RowNumbererResources extends ClientBundle {
    @Import(GridStateStyles.class)
    @Source("RowNumberer.gss")
    RowNumbererStyles styles();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource specialColumn();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    ImageResource specialColumnSelected();
  }
  public interface RowNumbererStyles extends CssResource{
    String numberer();
    String cell();
  }

  private final RowNumbererResources resources;

  public RowNumbererDefaultAppearance() {
    this(GWT.<RowNumbererResources>create(RowNumbererResources.class));
  }

  public RowNumbererDefaultAppearance(RowNumbererResources resources) {
    this.resources = resources;
    StyleInjectorHelper.ensureInjected(resources.styles(), false);
  }

  @Override
  public String getCellClassName() {
    return resources.styles().cell();
  }

  @Override
  public void renderCell(int rowNumber, SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<div class='"+resources.styles().numberer()+"'>").append(rowNumber).appendHtmlConstant("</div>");
  }

  @Override
  public SafeHtml renderHeader() {
    return SafeHtmlUtils.EMPTY_SAFE_HTML;
  }
}
