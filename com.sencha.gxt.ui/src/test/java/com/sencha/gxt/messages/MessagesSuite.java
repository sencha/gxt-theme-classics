package com.sencha.gxt.messages;

import com.google.gwt.junit.tools.GWTTestSuite;

import com.sencha.gxt.messages.client.GwtTestXMessages;
import junit.framework.Test;

public class MessagesSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("Messages test suite");
    suite.addTestSuite(GwtTestXMessages.class);
    suite.addTestSuite(TestXMessages.class);

    return suite;
  }

}
