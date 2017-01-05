package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.Portlet;

public class GwtTestPortalLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    PortalLayoutContainer con = new PortalLayoutContainer(3);
    Portlet p1 = new Portlet();
    Portlet p2 = new Portlet();
    Portlet p3 = new Portlet();
    Portlet p4 = new Portlet();
    Portlet p5 = new Portlet();
    Portlet p6 = new Portlet();
    
    assertEquals(3, con.getColumnCount());
    
    con.add(p1, 0);
    con.add(p2, 0);
    con.add(p3, 1);
    con.add(p4, 1);
    con.add(p5, 2);
    
    assertEquals(0, con.getPortletColumn(p2));
    assertEquals(1, con.getPortletColumn(p4));
    assertEquals(2, con.getPortletColumn(p5));
    assertEquals(-1, con.getPortletColumn(p6));
    
    assertEquals(0, con.getPortletIndex(p3));
    assertEquals(1, con.getPortletIndex(p4));
  
    con.insert(p6, 1, 1);
    assertEquals(1, con.getPortletColumn(p6));
    
    assertEquals(0, con.getPortletIndex(p3));
    assertEquals(2, con.getPortletIndex(p4));
    assertEquals(1, con.getPortletIndex(p6));
    
    con.remove(p6,1);

    assertEquals(0, con.getPortletIndex(p3));
    assertEquals(1, con.getPortletIndex(p4));
    assertEquals(-1, con.getPortletIndex(p6));
     
  }
  
  public void testClear() {
    PortalLayoutContainer con = new PortalLayoutContainer(2);
    Portlet p1 = new Portlet();
    Portlet p2 = new Portlet();
    
    con.add(p1, 0);
    con.add(p2, 1);
    
    RootPanel.get().add(con);
    
    con.clear();
    con.add(new Portlet(), 0);
    
  }
  
}
