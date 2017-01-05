package com.sencha.gxt.widget.core.client.container;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.core.client.util.TextMetrics;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;

public class GwtTestHorizontalLayoutContainer extends CoreBaseTestCase {

  public void testContainer() {
    HorizontalLayoutContainer con = new HorizontalLayoutContainer();
    con.setPixelSize(400, 400);

    final HTML html = new HTML();
    final HTML html2 = new HTML();
    final HTML html3 = new HTML();
    final HTML html4 = new HTML();
    html3.setPixelSize(100, 100);
    con.add(html, new HorizontalLayoutData(0.3, 1));
    con.add(html2, new HorizontalLayoutData(0.7, 1));
    con.add(html3, new HorizontalLayoutData(-1, 1));
    con.add(html4, new HorizontalLayoutData(200, 1));

    RootPanel.get().add(con);

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        assertEquals(30, html.getOffsetWidth());
        assertEquals(400, html.getOffsetHeight());
        assertEquals(70, html2.getOffsetWidth());
        assertEquals(400, html2.getOffsetHeight());
        assertEquals(100, html3.getOffsetWidth());
        assertEquals(400, html3.getOffsetHeight());
        assertEquals(200, html4.getOffsetWidth());
        assertEquals(400, html4.getOffsetHeight());
        finishTest();
      }
    });

    delayTestFinish(200);
  }

  public void testScrollSupport() {
    HorizontalLayoutContainer con = new HorizontalLayoutContainer();

    assertNotNull(con.getScrollSupport());

    con.setScrollMode(ScrollMode.AUTO);

    assertEquals(ScrollMode.AUTO, con.getScrollMode());
  }


  @DoNotRunWith(Platform.HtmlUnitLayout)
  public void testSizingSample() {
    final FramedPanel panel = new FramedPanel();
    panel.setPixelSize(400, 300);
    panel.setCollapsible(true);
    panel.setAnimCollapse(false);
    HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();

    final Label a;
    final Label b;
    final Label c;
    hlc.add(a = createLabel("Test Label A"), new HorizontalLayoutData(-1, 1, new Margins(4)));
    hlc.add(b = createLabel("Test Label B"), new HorizontalLayoutData(1, 1, new Margins(4, 0, 4, 0)));
    hlc.add(c = createLabel("Test Label C"), new HorizontalLayoutData(-1, 1, new Margins(4)));
    panel.setWidget(hlc);

    RootPanel.get().add(panel);


    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        //verify initial sizes
        Size aSize = a.getElement().<XElement>cast().getSize();
        Size bSize = b.getElement().<XElement>cast().getSize();
        Size cSize = c.getElement().<XElement>cast().getSize();

        assertEquals(bSize.getHeight(), aSize.getHeight());
        assertEquals(bSize.getHeight(), cSize.getHeight());

        TextMetrics.get().bind(a.getElement());
        final int bApproxWidth = 400 - (TextMetrics.get().getWidth("Test Label A") + TextMetrics.get().getWidth("Test Label C"));
        assertTrue(bSize.getWidth() < bApproxWidth);

        // collapse so HLC doesnt have size
        panel.collapse();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            // force layout (tab panel changing would have nearly the same effect)
            panel.forceLayout();

            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
              @Override
              public void execute() {
                //expand and retest
                panel.expand();
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                  @Override
                  public void execute() {
                    Size bSizeNew = b.getElement().<XElement>cast().getSize();

                    assertTrue(bSizeNew.getWidth() < bApproxWidth);
                    finishTest();
                  }
                });
              }
            });

          }
        });
      }
    });
    delayTestFinish(5000);

  }
  private Label createLabel(String text) {
    Label label = new Label(text);
    label.getElement().getStyle().setProperty("whiteSpace", "nowrap");
    label.addStyleName(ThemeStyles.get().style().border());
    label.addStyleName("pad-text white-bg");

    return label;
  }

}
