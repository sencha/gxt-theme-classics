package com.sencha.gxt.widget.core.client.tips;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.theme.base.client.tips.TipDefaultAppearance;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.tips.Tip.TipAppearance;

public class GwtTestQuickTip extends CoreBaseTestCase {
  
  public void testQuickTipBlowUp() {
    HTML html = new HTML("Hover over <span qtitle='Title:' qtip='qtip Works'>this for quik tip to display.</span>");
    
    TipAppearance appearance = new TipDefaultAppearance();
    new QuickTip(html, appearance);
    
    RootPanel.get().add(html);
  }
  
}
