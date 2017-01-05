package com.sencha.gxt.theme.base.client.frame;

import com.sencha.gxt.core.client.dom.XElement;

/**
 * Interface for frames that can be expanded / collapsed.
 */
public interface CollapsibleFrame extends Frame {

  /**
   * Returns the element who's visibility will be "toggled" for expanding and
   * collapsing.
   * 
   * @param parent the parent element
   * @return the collapse target element
   */
  XElement getCollapseElem(XElement parent);
}
