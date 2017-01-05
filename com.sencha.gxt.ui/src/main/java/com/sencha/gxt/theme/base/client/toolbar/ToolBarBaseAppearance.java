package com.sencha.gxt.theme.base.client.toolbar;

import com.sencha.gxt.theme.base.client.container.HBoxLayoutDefaultAppearance;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar.ToolBarAppearance;

public abstract class ToolBarBaseAppearance extends HBoxLayoutDefaultAppearance implements ToolBarAppearance {

  public interface ToolBarBaseStyle {
    String toolBar();

    String moreButton();
  }

}
