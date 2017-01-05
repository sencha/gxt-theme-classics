package com.sencha.gxt.xtemplates;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.xtemplates.client.GwtTestXTemplateWindowsNewLine;
import com.sencha.gxt.xtemplates.client.GwtTestXTemplates;
import com.sencha.gxt.xtemplates.client.GwtTestXTemplatesFormaters;
import com.sencha.gxt.xtemplates.client.GwtTestXTemplatesLoopingIF;
import com.sencha.gxt.xtemplates.client.GwtTestXTemplatesOther;

public class XTemplatesTestSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("XTempaltes test suite");
    suite.addTestSuite(GwtTestXTemplates.class);
    suite.addTestSuite(GwtTestXTemplatesOther.class);
    suite.addTestSuite(GwtTestXTemplatesFormaters.class);
    suite.addTestSuite(GwtTestXTemplatesLoopingIF.class);
    suite.addTestSuite(GwtTestXTemplateWindowsNewLine.class);
    return suite;
  }
  
}
