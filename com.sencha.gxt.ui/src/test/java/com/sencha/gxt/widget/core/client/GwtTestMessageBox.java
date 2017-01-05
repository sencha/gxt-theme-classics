package com.sencha.gxt.widget.core.client;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.box.MultiLinePromptMessageBox;
import com.sencha.gxt.widget.core.client.box.PromptMessageBox;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public class GwtTestMessageBox extends CoreBaseTestCase {

  public void testDefaultTextHeight() {
    final MultiLinePromptMessageBox box = new MultiLinePromptMessageBox("Address", "Please enter your address:");
    box.addShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        finishTest();
      }
    });

    delayTestFinish(200);
    box.show();
  }
  
  public void testInput() {
    final PromptMessageBox box = new PromptMessageBox("Address", "Please enter your name:");

    box.addShowHandler(new ShowHandler() {
      @Override
      public void onShow(ShowEvent event) {
        box.getField().setValue("joe");
        box.hide();
      }
    });

    box.addHideHandler(new HideHandler() {
      @Override
      public void onHide(HideEvent event) {
        assertEquals("joe", box.getValue());
        finishTest();
      }
    });
    
    delayTestFinish(200);
    box.show();
  }

  public void testSetText() {
    // Given there is a text with html
    String text = "Line 1 <br>Line 2";

    // When the message box uses that text with html
    final MessageBox msgBox = new MessageBox("Two Lines:");
    msgBox.setPredefinedButtons(PredefinedButton.OK);
    msgBox.setIcon(MessageBox.ICONS.info());
    msgBox.setMessage(text);
    msgBox.show();

    // And when the innerHtml of the message is retrieved
    XElement messageEl = msgBox.getMessageBoxAppearance().getMessageElement(msgBox.getElement());
    String contentInnerHtml = messageEl.getInnerHTML();

    // Then it should container escaped html
    assertEquals("Line 1 &lt;br&gt;Line 2", contentInnerHtml);
  }

  public void testSetHtml() {
    // Given there is a text with html
    SafeHtml safeHtml = SafeHtmlUtils.fromSafeConstant("Line 1 <br>Line 2");

    // When the message box uses that text
    final MessageBox msgBox = new MessageBox("Two Lines:");
    msgBox.setPredefinedButtons(PredefinedButton.OK);
    msgBox.setIcon(MessageBox.ICONS.info());
    msgBox.setMessage(safeHtml);
    msgBox.show();

    // And when the innerHtml of the message is retrieved
    XElement messageEl = msgBox.getMessageBoxAppearance().getMessageElement(msgBox.getElement());
    String contentInnerHtml = messageEl.getInnerHTML();

    // Then it should container a non escaped html
    assertEquals("Line 1 <br>Line 2", contentInnerHtml);
  }

  public void testSetNullHtml() {
    // Given the html is null
    SafeHtml safeHtml = null;

    // When the message box uses that text
    final MessageBox msgBox = new MessageBox("Two Lines:");
    msgBox.setPredefinedButtons(PredefinedButton.OK);
    msgBox.setIcon(MessageBox.ICONS.info());
    msgBox.setMessage(safeHtml);
    msgBox.show();

    // And when the innerHtml of the message is retrieved
    XElement messageEl = msgBox.getMessageBoxAppearance().getMessageElement(msgBox.getElement());
    String contentInnerHtml = messageEl.getInnerHTML();

    // Then it should be blank
    assertEquals("", contentInnerHtml);
  }

}
