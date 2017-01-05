package com.sencha.gxt.widget.core.client.container;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestNorthSouthContainer extends CoreBaseTestCase {

  public void testContainer() {
    ContentPanel cp1 = new ContentPanel();
    ContentPanel cp2 = new ContentPanel();
    ContentPanel cp3 = new ContentPanel();
    
    NorthSouthContainer con = new NorthSouthContainer();
    con.setSouthWidget(cp3);
    assertEquals(1,con.getElement().getChildCount());
    assertEquals(cp3.getElement(), con.getElement().getChild(0));
    
    con.setNorthWidget(cp1);
    assertEquals(2,con.getElement().getChildCount());
    assertEquals(cp1.getElement(), con.getElement().getChild(0));
    assertEquals(cp3.getElement(), con.getElement().getChild(1));
    
    con.setWidget(cp2);
    assertEquals(3,con.getElement().getChildCount());
    assertEquals(cp1.getElement(), con.getElement().getChild(0));
    assertEquals(cp2.getElement(), con.getElement().getChild(1));
    assertEquals(cp3.getElement(), con.getElement().getChild(2));
    
    con.remove(cp1);
    assertEquals(2,con.getElement().getChildCount());
    assertEquals(cp2.getElement(), con.getElement().getChild(0));
    assertEquals(cp3.getElement(), con.getElement().getChild(1));
    
    con.remove(cp2);
    assertEquals(1,con.getElement().getChildCount());
    assertEquals(cp3.getElement(), con.getElement().getChild(0));
    
    con.remove(cp3);
    assertEquals(0,con.getElement().getChildCount());
    
    
    con.setWidget(cp2);
    con.setSouthWidget(cp3);
    con.setNorthWidget(cp1);

    assertEquals(cp1.getElement(), con.getElement().getChild(0));
    assertEquals(cp2.getElement(), con.getElement().getChild(1));
    assertEquals(cp3.getElement(), con.getElement().getChild(2));
    
    con.remove(0);
    con.remove(0);
    con.remove(0);

    assertEquals(0,con.getElement().getChildCount());

    con.setNorthWidget(cp1);
    con.setSouthWidget(cp3);
    con.setWidget(cp2);

    assertEquals(cp1.getElement(), con.getElement().getChild(0));
    assertEquals(cp2.getElement(), con.getElement().getChild(1));
    assertEquals(cp3.getElement(), con.getElement().getChild(2));

    
    
  }
  
}
