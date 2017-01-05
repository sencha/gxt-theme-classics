package com.sencha.gxt.theme.base.client.listview;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.ListView.ListViewAppearance;

@SuppressWarnings("javadoc")
public class ListViewDefaultAppearance<M> implements ListViewAppearance<M> {


  /**
   * The default resources for the list view.
   */
  public interface ListViewDefaultResources extends ClientBundle {



    /**
     * Returns the default list view style names.
     *
     * @return the default list view style names
     */
    @Source("ListView.gss")
    ListViewDefaultStyle css();

    /**
     * Returns the image resource for the selected item background.
     *
     * @return selected background image resource
     */
    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    ImageResource selectedBackground();
  }

  /**
   * The default styles for the list view.
   */
  public interface ListViewDefaultStyle extends CssResource {

    /**
     * Returns the name of the style representing the item.
     *
     * @return the style for the item
     */
    String item();

    /**
     * Returns the name of the style representing the cursor over state.
     *
     * @return the style for the cursor over state
     */
    String over();

    /**
     * Returns the name of the style representing the selected state.
     *
     * @return the style of the selected state
     */
    String sel();

    /**
     * Returns the name of the style representing the list view root.
     *
     * @return the style of the list view
     */
    String view();
  }

  protected ListViewDefaultResources resources;
  protected ListViewDefaultStyle style;

  public ListViewDefaultAppearance() {
    this(GWT.<ListViewDefaultResources>create(ListViewDefaultResources.class));
  }
  
  public ListViewDefaultAppearance(ListViewDefaultResources resources) {
    this.resources = resources;
    this.style = resources.css();
    this.style.ensureInjected();
  }

  @Override
  public Element findCellParent(XElement item) {
    return item;
  }

  @Override
  public Element findElement(XElement child) {
    return child.findParentElement("." + style.item(), 10);
  }

  @Override
  public List<Element> findElements(XElement parent) {
    NodeList<Element> nodes = parent.select("." + style.item());
    List<Element> temp = new ArrayList<Element>();
    for (int i = 0; i < nodes.getLength(); i++) {
      temp.add(nodes.getItem(i));
    }

    return temp;
  }

  @Override
  public void onOver(XElement item, boolean over) {
    item.setClassName(style.over(), over);
  }

  @Override
  public void onSelect(XElement item, boolean select) {
    item.setClassName(style.sel(), select);
  }

  @Override
  public void render(SafeHtmlBuilder builder) {
    builder.appendHtmlConstant("<div class=\"" + style.view() + "\"></div>");
  }

  @Override
  public void renderEnd(SafeHtmlBuilder builder) {
  }

  @Override
  public void renderItem(SafeHtmlBuilder sb, SafeHtml content) {
    sb.appendHtmlConstant("<div class='" + style.item() + "'>");
    sb.append(content);
    sb.appendHtmlConstant("</div>");

  }
}