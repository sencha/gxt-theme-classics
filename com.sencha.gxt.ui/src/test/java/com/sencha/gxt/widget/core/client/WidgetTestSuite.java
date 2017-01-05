package com.sencha.gxt.widget.core.client;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.ui.client.UITestCase;
import com.sencha.gxt.widget.core.client.button.ButtonTestSuite;
import com.sencha.gxt.widget.core.client.container.ContainerTestSuite;
import com.sencha.gxt.widget.core.client.form.FormTestSuite;
import com.sencha.gxt.widget.core.client.form.validator.ValidatorTestSuite;
import com.sencha.gxt.widget.core.client.grid.GridTestSuite;
import com.sencha.gxt.widget.core.client.info.InfoTestSuite;
import com.sencha.gxt.widget.core.client.menu.MenuTestSuite;
import com.sencha.gxt.widget.core.client.selection.SelectionTestSuite;
import com.sencha.gxt.widget.core.client.tips.TipsTestSuite;
import com.sencha.gxt.widget.core.client.toolbar.GwtTestPagingToolBar;
import com.sencha.gxt.widget.core.client.tree.TreeTestSuite;
import junit.framework.Test;

public class WidgetTestSuite extends UITestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Widget test suite");
    suite.addTestSuite(GwtTestComponent.class);
    suite.addTestSuite(GwtTestComponentSizing.class);
    suite.addTestSuite(GwtTestComponentHelper.class);
    suite.addTestSuite(GwtTestContentPanel.class);
    suite.addTestSuite(GwtTestDialog.class);
    suite.addTestSuite(GwtTestDatePicker.class);
    suite.addTestSuite(GwtTestModalPanel.class);
    suite.addTestSuite(GwtTestPagingToolBar.class);
    suite.addTestSuite(GwtTestSlider.class);
    suite.addTestSuite(GwtTestStatus.class);
    suite.addTestSuite(GwtTestTabPanel.class);
    suite.addTestSuite(GwtTestMessageBox.class);
    suite.addTestSuite(GwtTestWidgetComponent.class);
    suite.addTestSuite(GwtTestWindow.class);
    suite.addTestSuite(GwtTestWindowManager.class);
    suite.addTestSuite(GwtTestFramedPanel.class);
    //suite.addTestSuite(GwtListView.class);//doesn't do anything
    suite.addTestSuite(GwtTestSplitBar.class);
    suite.addTestSuite(GwtTestProgressBar.class);
    suite.addTestSuite(GwtTestStoreFilterField.class);
    suite.addTestSuite(GwtTestLabelToolItem.class);

    suite.addTest(ButtonTestSuite.suite());
    suite.addTest(ContainerTestSuite.suite());
    suite.addTest(FormTestSuite.suite());
    suite.addTest(GridTestSuite.suite());
    suite.addTest(MenuTestSuite.suite());
    suite.addTest(SelectionTestSuite.suite());
    suite.addTest(TipsTestSuite.suite());
    suite.addTest(TreeTestSuite.suite());
    suite.addTest(ValidatorTestSuite.suite());
    suite.addTest(InfoTestSuite.suite());

    return suite;
  }

}
