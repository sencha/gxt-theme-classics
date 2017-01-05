package com.sencha.gxt.widget.core.client.button;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.dom.XDOM;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent;
import com.sencha.gxt.widget.core.client.event.BeforeSelectEvent.BeforeSelectHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Menu;

public class GwtTestButton extends CoreBaseTestCase {
  
  @SuppressWarnings("rawtypes")
  public void testCancelEvent() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    TextButton button = new TextButton("test button");

    button.addBeforeSelectHandler(new BeforeSelectHandler() {
      @Override
      public void onBeforeSelect(BeforeSelectEvent event) {
        map.put(event.getAssociatedType(), true);
        event.setCancelled(true);
      }
    });
    button.addSelectHandler(new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(button);

    button.getElement().getFirstChildElement().<XElement>cast().click();

    assertEquals(true, map.get(BeforeSelectEvent.getType()));
    assertNull(map.get(SelectEvent.getType()));
  }
  
  @SuppressWarnings("rawtypes")
  public void testClickEvent() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    TextButton button = new TextButton("text");

    button.addBeforeSelectHandler(new BeforeSelectHandler() {
      @Override
      public void onBeforeSelect(BeforeSelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    button.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    button.addDomHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    }, ClickEvent.getType());

    RootPanel.get().add(button);

    button.getElement().getFirstChildElement().<XElement>cast().click();

    assertEquals(true, map.get(BeforeSelectEvent.getType()));
    assertEquals(true, map.get(ClickEvent.getType()));
    assertEquals(true, map.get(SelectEvent.getType()));
  }
  
  @SuppressWarnings("rawtypes")
  public void testDisable() {
    final Map<Type, Object> map = new HashMap<Type, Object>();

    TextButton button = new TextButton();

    button.addBeforeSelectHandler(new BeforeSelectHandler() {
      @Override
      public void onBeforeSelect(BeforeSelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });
    button.addSelectHandler(new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        map.put(event.getAssociatedType(), true);
      }
    });

    RootPanel.get().add(button);
    button.disable();

    button.getElement().click();

    assertNull(map.get(BeforeSelectEvent.getType()));
    assertNull(map.get(SelectEvent.getType()));
  }
  
  public void testHtmlButton() {
    TextButton tb = new TextButton();
    tb.setHTML("Testing some <b>sanitizable</b> html");

    //force redraw for faster test
    tb.redraw();

    assertNotNull(tb.getElement().child("b"));
    assertFalse(tb.getElement().getInnerHTML().contains("&lt;b "));

    tb = new TextButton();
    tb.setHTML("Testing some <b attributes=\"forbidden\">un-sanitizable</b> html");

    //force redraw for faster test
    tb.redraw();

    System.out.println(tb.getElement().getInnerHTML());

    assertNull(tb.getElement().child("b"));
    assertTrue(tb.getElement().getInnerHTML().contains("&lt;b "));
  }
  
  public void testMenu() {
    Menu menu = new Menu();
    SplitButton button = new SplitButton("test");
    button.setMenu(menu);
    assertFalse(button.getMenu().isVisible());
    assertEquals(menu, button.getMenu());

    button.showMenu();
    assertTrue(button.getMenu().isVisible());

    button.hideMenu();
    assertFalse(button.getMenu().isVisible());
    
    button.setMenu(null);
    assertNull(button.getMenu());
    
    button.setMenu(menu);
  }
  
  public void testSafeHtmlButton() {
    TextButton tb = new TextButton();
    tb.setHTML(SafeHtmlUtils.fromSafeConstant("Testing some <b>sanitizable</b> html"));
    
    //force redraw for faster test
    tb.redraw();

    assertNotNull(tb.getElement().child("b"));
    assertFalse(tb.getElement().getInnerHTML().contains("&lt;b "));

    tb = new TextButton();
    tb.setHTML(SafeHtmlUtils.fromSafeConstant("Testing some <b attributes=\"forbidden\">un-sanitizable</b> html"));

    //force redraw for faster test
    tb.redraw();

    assertNotNull(tb.getElement().child("b"));
    assertFalse(tb.getElement().getInnerHTML().contains("&lt;b "));
  }

  public void testText() {
    TextButton btn = new TextButton("text");
    RootPanel.get().add(btn);
    
    assertEquals("text", btn.getText());
  }

  public void testTextButtonCellText() {
    String content = "Some text with something that looks like <b>html</b>";
    TextButtonCell cell = new TextButtonCell();
    SafeHtmlBuilder sb = new SafeHtmlBuilder();
    cell.render(new Context(0, 0, ""), content, sb);
    
    assertTrue(sb.toSafeHtml().asString().contains("Some text"));
    XElement elt = XDOM.create(sb.toSafeHtml());
    assertNull(elt.child("b"));
  }

  public void testTextButtonCtor() {
    String content = "Some text with something that looks like <b>html</b>";
    TextButton tb = new TextButton(content);
    
    assertEquals(content, tb.getElement().getInnerText().trim());
    assertNull(tb.getElement().child("b"));
  }

  public void testTextButtonSetter() {
    String content = "Some text with something that looks like <b>html</b>";
    TextButton tb = new TextButton();
    tb.setText(content);
    
    //force redraw for faster test
    tb.redraw();
    
    assertEquals(content, tb.getElement().getInnerText().trim());
    assertNull(tb.getElement().child("b"));
  }

}
