package com.sencha.gxt.theme.base.client.grid;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.resources.client.CssResource.Import;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.grid.GridView.GridStateStyles;
import com.sencha.gxt.widget.core.client.grid.GroupSummaryView;
import com.sencha.gxt.widget.core.client.grid.GroupSummaryView.GroupSummaryViewAppearance;

public class GroupSummaryViewDefaultAppearance extends GroupingViewDefaultAppearance implements GroupSummaryViewAppearance {
  public interface GroupSummaryViewResources extends GroupingViewResources {
    @Override
    @Import(GridStateStyles.class)
    @Source({"GroupingView.gss", "GroupSummaryView.gss"})
    GroupSummaryViewStyle style();
  }

  public interface GroupSummaryViewStyle extends GroupingViewStyle, GroupSummaryView.GroupSummaryViewStyle {
    String hideSummaries();
  }


  public GroupSummaryViewDefaultAppearance() {
    this(GWT.<GroupSummaryViewResources>create(GroupSummaryViewResources.class));
  }

  public GroupSummaryViewDefaultAppearance(GroupSummaryViewResources resources) {
    super(resources);
  }

  @Override
  public void toggleSummaries(XElement parent, boolean visible) {
    parent.setClassName(style().hideSummaries(), !visible);
  }

  @Override
  public NodeList<Element> getSummaries(XElement table) {
    return table.select("." + style().summaryRow());
  }

  @Override
  public int getGroupIndex(XElement group) {
    return group.getParentElement().<XElement>cast().indexOf(group) / 3;
  }

  @Override
  public GroupSummaryViewStyle style() {
    return (GroupSummaryViewStyle) super.style();
  }
}
