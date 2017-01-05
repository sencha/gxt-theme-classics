package com.sencha.gxt.data;

import junit.framework.Test;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.sencha.gxt.data.client.GwtTestPropertyAccess;
import com.sencha.gxt.data.client.loader.GwtTestJsoReader;
import com.sencha.gxt.data.client.loader.GwtTestJsonReader;
import com.sencha.gxt.data.client.loader.GwtTestLoader;
import com.sencha.gxt.data.client.loader.GwtTestXmlReader;
import com.sencha.gxt.data.client.writer.GwtTestUrlEncodingWriter;
import com.sencha.gxt.data.client.writer.GwtTestXmlWriter;
import com.sencha.gxt.data.servlets.DataServletsTestSuite;
import com.sencha.gxt.data.shared.RecordTest;
import com.sencha.gxt.data.shared.TestListStore;
import com.sencha.gxt.data.shared.TestTreeStore;

public class DataTestSuite {
  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Data unit test suite");
    // client
    suite.addTestSuite(GwtTestPropertyAccess.class);
    // client.writer
    suite.addTestSuite(GwtTestUrlEncodingWriter.class);
    suite.addTestSuite(GwtTestXmlWriter.class);
    // client.loader
    suite.addTestSuite(GwtTestJsonReader.class);
    suite.addTestSuite(GwtTestJsoReader.class);
    suite.addTestSuite(GwtTestLoader.class);
    suite.addTestSuite(GwtTestXmlReader.class);
    suite.addTest(DataServletsTestSuite.suite());

    // shared
    suite.addTestSuite(TestListStore.class);
    suite.addTestSuite(TestTreeStore.class);
    suite.addTestSuite(RecordTest.class);

    return suite;
  }
}
