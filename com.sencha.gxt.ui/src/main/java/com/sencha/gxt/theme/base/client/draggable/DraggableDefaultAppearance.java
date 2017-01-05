package com.sencha.gxt.theme.base.client.draggable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.fx.client.Draggable.DraggableAppearance;

public class DraggableDefaultAppearance implements DraggableAppearance {

  public interface DraggableResources extends ClientBundle {

    @Source("Draggable.gss")
    DraggableStyle style();

  }

  public interface DraggableStyle extends CssResource {

    String cursor();

    String proxy();

  }

  private final DraggableStyle style;
  private String proxyClass;

  public DraggableDefaultAppearance() {
    this(GWT.<DraggableResources> create(DraggableResources.class));
  }

  public DraggableDefaultAppearance(DraggableResources resources) {
    this.style = resources.style();
    StyleInjectorHelper.ensureInjected(style, true);
    proxyClass = style.proxy();
  }

  @Override
  public void addUnselectableStyle(Element element) {
    element.addClassName(CommonStyles.get().unselectable());
    element.addClassName(style.cursor());
  }

  @Override
  public Element createProxy() {
    XElement proxyEl = Document.get().createDivElement().cast();
    proxyEl.setVisibility(false);
    proxyEl.setClassName(proxyClass);
    proxyEl.disableTextSelection(true);
    return proxyEl;
  }

  @Override
  public void removeUnselectableStyle(Element element) {
    element.removeClassName(CommonStyles.get().unselectable());
    element.removeClassName(style.cursor());
  }

  @Override
  public void setProxyStyle(String proxyClass) {
    this.proxyClass = proxyClass;
  }

}
