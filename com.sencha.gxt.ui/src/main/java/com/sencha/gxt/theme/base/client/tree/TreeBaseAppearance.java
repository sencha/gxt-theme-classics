package com.sencha.gxt.theme.base.client.tree;

import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.AbstractImagePrototype.ImagePrototypeElement;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import com.sencha.gxt.widget.core.client.tree.Tree.Joint;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeAppearance;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.sencha.gxt.widget.core.client.tree.TreeView.TreeViewRenderMode;

public abstract class TreeBaseAppearance implements TreeAppearance {

  public interface TreeBaseStyle extends CssResource {
    String check();

    String container();

    String dragOver();

    String drop();

    String element();

    String icon();

    String indent();

    String joint();

    String node();

    String over();

    String selected();

    String text();

    String tree();

  }

  public interface TreeResources {

    ImageResource checked();

    ImageResource folderClosed();

    ImageResource folderOpened();

    @Source("treeCollapsed.png")
    ImageResource jointCollapsedIcon();

    @Source("treeExpanded.png")
    ImageResource jointExpandedIcon();

    @Source("loading.gif")
    ImageResource loadingIcon();

    ImageResource partialChecked();

    TreeBaseStyle style();

    ImageResource unchecked();

  }

  private final TreeResources resources;
  private final TreeBaseStyle style;

  protected int indentWidth = 17;

  public TreeBaseAppearance(TreeResources resources) {
    this.resources = resources;
    this.style = this.resources.style();
    this.style.ensureInjected();
  }

  @Override
  public ImageResource closeNodeIcon() {
    return resources.folderClosed();
  }

  @Override
  public String elementSelector() {
    return "." + style.element();
  }

  public XElement findIconElement(XElement target) {
    return target.selectNode("." + style.icon());
  }

  @Override
  public XElement findJointElement(XElement target) {
    return target.selectNode("." + style.joint());
  }

  @Override
  public XElement getCheckElement(XElement container) {
    return container.getChildNodes().getItem(2).cast();
  }

  @Override
  public XElement getContainerElement(XElement node) {
    return node.getFirstChildElement().cast();
  }

  @Override
  public XElement getIconElement(XElement container) {
    return container.getChildNodes().getItem(3).cast();
  }

  @Override
  public XElement getJointElement(XElement container) {
    return container.getChildNodes().getItem(1).cast();
  }

  @Override
  public XElement getTextElement(XElement container) {
    return container.getChildNodes().getItem(4).cast();
  }

  @Override
  public boolean isCheckElement(XElement target) {
    return target.hasClassName(style.check());
  }

  @Override
  public boolean isJointElement(XElement target) {
    return target.hasClassName(style.joint());
  }

  @Override
  public String itemSelector() {
    return "." + style.node();
  }

  @Override
  public ImageResource loadingIcon() {
    return resources.loadingIcon();
  }

  @Override
  public XElement onCheckChange(XElement node, XElement checkElement, boolean checkable, CheckState checked) {
    if (checkElement != null) {
      ImagePrototypeElement checkProtoEl = checkElement.cast();
      if (checkable) {
        switch (checked) {
          case CHECKED:
            AbstractImagePrototype.create(resources.checked()).applyTo(checkProtoEl);
            break;
          case UNCHECKED:
            AbstractImagePrototype.create(resources.unchecked()).applyTo(checkProtoEl);
            break;
          case PARTIAL:
            AbstractImagePrototype.create(resources.partialChecked()).applyTo(checkProtoEl);
            break;
        }
      } else {
        removeImage(checkProtoEl);
      }
    }
    return checkElement;
  }

  @Override
  public void onDropOver(XElement node, boolean over) {
    node.setClassName(style.dragOver(), over);
  }

  @Override
  public void onHover(XElement node, boolean over) {
    node.setClassName(style.over(), over);
  }

  @Override
  public XElement onIconChange(XElement node, XElement iconElement, ImageResource icon) {
    if (iconElement != null) {
      ImagePrototypeElement iconProtoEl = iconElement.cast();
      if (icon != null) {
        AbstractImagePrototype.create(icon).applyTo(iconProtoEl);
      } else {
        removeImage(iconProtoEl);
      }
    }
    return iconElement;
  }

  @Override
  public XElement onJointChange(XElement node, XElement jointElement, Joint joint, TreeStyle ts) {
    if (jointElement != null) {
      ImagePrototypeElement jointProtoEl = jointElement.cast();
      switch (joint) {
        case COLLAPSED:
          AbstractImagePrototype.create(
              ts.getJointCloseIcon() == null ? resources.jointCollapsedIcon() : ts.getJointCloseIcon()
          ).applyTo(jointProtoEl);
          break;
        case EXPANDED:
          AbstractImagePrototype.create(
              ts.getJointOpenIcon() == null ? resources.jointExpandedIcon() : ts.getJointOpenIcon()
          ).applyTo(jointProtoEl);
          break;
        default:
          removeImage(jointProtoEl);
          break;
      }
    }
    return jointElement;
  }

  @Override
  public void onSelect(XElement node, boolean select) {
    node.setClassName(style.selected(), select);
  }

  @Override
  public ImageResource openNodeIcon() {
    return resources.folderOpened();
  }

  @Override
  public void render(SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<div class=" + style.tree() + " style=\"position: relative;\">"
                          + "<table cellpadding=0 cellspacing=0 width=100%><tr><td></td></tr></table>"
                          + "</div>");
  }

  @Override
  public void renderContainer(SafeHtmlBuilder sb) {
    sb.appendHtmlConstant("<div class='" + style.container() + "' role='group'></div>");
  }

  @Override
  public void renderNode(SafeHtmlBuilder sb, String id, SafeHtml html, TreeStyle ts, ImageResource icon,
      boolean checkable, CheckState checked, Joint joint, int level, TreeViewRenderMode renderMode) {

    if (renderMode == TreeViewRenderMode.ALL || renderMode == TreeViewRenderMode.BUFFER_WRAP) {
      sb.appendHtmlConstant("<div id=\"" + SafeHtmlUtils.htmlEscape(id) + "\" class=\"" + style.node() + "\">");

      sb.appendHtmlConstant("<div class=\"" + style.element() + "\">");
    }

    if (renderMode == TreeViewRenderMode.ALL || renderMode == TreeViewRenderMode.BUFFER_BODY) {

      sb.appendHtmlConstant(getIndentMarkup(level));

      ImagePrototypeElement jointProtoEl = DOM.createDiv().cast();
      jointProtoEl.addClassName(style.joint());
      switch (joint) {
        case COLLAPSED:
          AbstractImagePrototype.create(
              ts.getJointCloseIcon() == null ? resources.jointCollapsedIcon() : ts.getJointCloseIcon()
          ).applyTo(jointProtoEl);
          break;
        case EXPANDED:
          AbstractImagePrototype.create(
              ts.getJointOpenIcon() == null ? resources.jointExpandedIcon() : ts.getJointOpenIcon()
          ).applyTo(jointProtoEl);
          break;
        default:
          break;
      }
      sb.appendHtmlConstant(jointProtoEl.getString());

      ImagePrototypeElement checkProtoEl = DOM.createDiv().cast();
      checkProtoEl.addClassName(style.check());
      if (checkable) {
        switch (checked) {
          case CHECKED:
            AbstractImagePrototype.create(resources.checked()).applyTo(checkProtoEl);
            break;
          case UNCHECKED:
            AbstractImagePrototype.create(resources.unchecked()).applyTo(checkProtoEl);
            break;
          case PARTIAL:
            AbstractImagePrototype.create(resources.partialChecked()).applyTo(checkProtoEl);
            break;
        }
      }
      sb.appendHtmlConstant(checkProtoEl.getString());

      ImagePrototypeElement iconProtoEl = DOM.createDiv().cast();
      iconProtoEl.addClassName(style.icon());
      if (icon != null) {
        AbstractImagePrototype.create(icon).applyTo(iconProtoEl);
      }
      sb.appendHtmlConstant(iconProtoEl.getString());

      sb.appendHtmlConstant("<div class=\"" + style.text() + "\">" + html.asString() + "</div>");
    }

    if (renderMode == TreeViewRenderMode.ALL || renderMode == TreeViewRenderMode.BUFFER_WRAP) {
      sb.appendHtmlConstant("</div>");
      sb.appendHtmlConstant("</div>");
    }

  }

  @Override
  public String textSelector() {
    return "." + style.text();
  }

  protected String getIndentMarkup(int level) {
    return "<div class=\"" + style.indent() + "\" style=\"width: " + (indentWidth * level) + "px;\"></div>";
  }

  private void removeImage(ImagePrototypeElement imageProtoEl) {
    imageProtoEl.getStyle().clearWidth();
    imageProtoEl.getStyle().clearHeight();
    imageProtoEl.getStyle().clearProperty("background");
  }

}