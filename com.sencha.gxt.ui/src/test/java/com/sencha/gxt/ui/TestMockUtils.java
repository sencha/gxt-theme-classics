package com.sencha.gxt.ui;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import com.google.gwt.junit.GWTMockUtilities;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class TestMockUtils extends TestCase {
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    GWTMockUtilities.disarm();
  }
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    GWTMockUtilities.restore();
  }

  public void testComponents() {
    Component w = mock(Component.class);

    TextButton tb = mock(TextButton.class);

    ToolBar toolBar = mock(ToolBar.class);

    toolBar.add(mock(ToolButton.class));

    ComboBox<?> cb = mock(ComboBox.class);

    ContentPanel cp = mock(ContentPanel.class);

    cp.addTool(mock(IconButton.class));

    Grid<?> g = mock(Grid.class);

    BorderLayoutContainer blc = mock(BorderLayoutContainer.class);

    blc.setNorthWidget(w);
    blc.setSouthWidget(toolBar);
    blc.setWestWidget(tb);
    blc.setEastWidget(cb);
    blc.setWidget(cp);
    cp.setWidget(g);

    FormPanel fp = mock(FormPanel.class);

    fp.setWidget(blc);
  }


  public void testOtherUtils() {
    MessageBox mb = mock(MessageBox.class);
    assertNotNull(mb);

    CheckBoxSelectionModel<?> cbsm = mock(CheckBoxSelectionModel.class);
    assertNotNull(cbsm);
  }
}
