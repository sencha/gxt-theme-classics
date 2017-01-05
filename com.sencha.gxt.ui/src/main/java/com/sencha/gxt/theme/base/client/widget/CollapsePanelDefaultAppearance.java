package com.sencha.gxt.theme.base.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.widget.core.client.CollapsePanel.CollapsePanelAppearance;

public class CollapsePanelDefaultAppearance implements CollapsePanelAppearance {

  public interface CollapsePanelResources extends ClientBundle {
    
    @Source("CollapsePanel.gss")
    CollapsePanelStyle style();
  }
  
  public interface CollapsePanelStyle extends CssResource {
    String panel();

    String noHeader();

    String iconWrap();

    String textWrap();

    String west();
    
    String east();
    
    String north();
    
    String south();
  }
  
  private final CollapsePanelResources resources;
  private final CollapsePanelStyle style;
  
  public CollapsePanelDefaultAppearance() {
    this(GWT.<CollapsePanelResources>create(CollapsePanelResources.class));
  }
  
  public CollapsePanelDefaultAppearance(CollapsePanelResources resources) {
    this.resources = resources;
    this.style = this.resources.style();
    
    StyleInjectorHelper.ensureInjected(style, true);
  }

  @Override
  public void render(SafeHtmlBuilder sb, LayoutRegion region, boolean header) {
    String cls = style.panel();

    switch (region) {
      case WEST:
        cls += " " + style.west();
        break;
      case EAST:
        cls += " " + style.east();
        break;
      case NORTH:
        cls += " " + style.north();
        break;
      case SOUTH:
        cls += " " + style.south();
        break;
      default:
        // empty
    }

    if (!header) {
      cls += " " + style.noHeader();
    }

    cls += " " + ThemeStyles.get().style().border();

    sb.appendHtmlConstant("<div class='" + cls + "'>");
    sb.appendHtmlConstant("<div class='" + style.iconWrap() + "'></div>");
    sb.appendHtmlConstant("<div class='" + style.textWrap() + "'></div>");
    sb.appendHtmlConstant("</div>");
  }

  @Override
  public XElement iconWrap(XElement parent) {
    return parent.selectNode("." + style.iconWrap()).cast();
  }

  @Override
  public XElement textWrap(XElement parent) {
    return parent.selectNode("." + style.textWrap()).cast();
  }

}
