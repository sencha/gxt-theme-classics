package com.sencha.gxt.widget.core.client.form;

import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.ui.client.UITestCase;

public class GwtTestFieldSet extends UITestCase {

   public void testFieldSet() {
     FieldSet fieldSet = new FieldSet();
     fieldSet.setCollapsible(true);
     
     RootPanel.get().add(fieldSet);
   }
   
   public void testExpandCollapse() {
     FieldSet fieldSet = new FieldSet();
     fieldSet.setCollapsible(true);
     
     fieldSet.collapse();
     fieldSet.expand();
     
     RootPanel.get().add(fieldSet);
     
     fieldSet.collapse();
     fieldSet.expand();
   }
  
//  public void testNotifyShowHideOnCollapse() {
//
//    final FieldSet p = new FieldSet();
//
//    p.setCollapsible(true);
//
//    p.add(new Text("test") {
//      @Override
//      protected void notifyShow() {
//        super.notifyShow();
//        p.setData("notifyShowCalled", true);
//      }
//
//      @Override
//      protected void notifyHide() {
//        super.notifyHide();
//        p.setData("notifyHideCalled", true);
//      }
//    });
//    RootPanel.get().add(p);
//
//    p.collapse();
//    assertTrue((Boolean) p.getData("notifyHideCalled"));
//
//    p.expand();
//    assertTrue((Boolean) p.getData("notifyShowCalled"));
//
//  }
}
