package com.sencha.gxt.widget.core.client.toolbar;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent.BeforeLoadHandler;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;

public class GwtTestPagingToolBar extends CoreBaseTestCase {

  public void testToolBar() {
    PagingToolBar bar = new PagingToolBar(10);
    RootPanel.get().add(bar);
  }

  public void testCancelState() {
    final PagingToolBar bar = new PagingToolBar(20);
    RootPanel.get().add(bar);

    final boolean[] loaded = {false};
    final PagingLoader<PagingLoadConfig, PagingLoadResult<?>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<?>>(new DataProxy<PagingLoadConfig, PagingLoadResult<?>>() {
      @Override
      public void load(PagingLoadConfig loadConfig, Callback<PagingLoadResult<?>, Throwable> callback) {
        loaded[0] = true;
        callback.onSuccess(new PagingLoadResultBean<Object>());
      }
    });
    bar.bind(loader);

    bar.doEnableButtons(true);

    final HandlerRegistration beforeLoadHandler = loader.addBeforeLoadHandler(new BeforeLoadHandler<PagingLoadConfig>() {
      @Override
      public void onBeforeLoad(BeforeLoadEvent<PagingLoadConfig> event) {
        event.setCancelled(true);
      }
    });

    bar.refresh();

    assertFalse(bar.isButtonsEnabled());

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        assertTrue(bar.isButtonsEnabled());
        assertFalse(loaded[0]);

        beforeLoadHandler.removeHandler();

        bar.refresh();

        assertTrue(bar.isButtonsEnabled());
        assertTrue(loaded[0]);

        finishTest();
      }
    });
    delayTestFinish(1000);
  }

  public void testPagingOperations() {
    final DeferredDummyProxy proxy = new DeferredDummyProxy();
    final PagingLoader<PagingLoadConfig, PagingLoadResult<Object>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Object>>(proxy);

    final PagingToolBar bar = new PagingToolBar(5);
    RootPanel.get().add(bar);
    bar.bind(loader);
    delayTestFinish(1000);

    bar.refresh();
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        assertEquals(1, proxy.loaded);
        assertEquals(1, bar.getActivePage());
        assertEquals(1000, bar.getTotalPages() * bar.getPageSize());
        proxy.loaded = 0;

        bar.next();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            assertEquals(1, proxy.loaded);
            assertEquals(2, bar.getActivePage());
            proxy.loaded = 0;

            bar.last();
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
              @Override
              public void execute() {
                assertEquals(1, proxy.loaded);
                assertEquals(bar.getTotalPages(), bar.getActivePage());
                proxy.loaded = 0;

                bar.previous();
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                  @Override
                  public void execute() {
                    assertEquals(1, proxy.loaded);
                    assertEquals(bar.getTotalPages() - 1, bar.getActivePage());

                    finishTest();
                  }
                });
              }
            });
          }
        });
      }
    });


  }

  public void testBeyondEnd() {
    final DeferredDummyProxy proxy = new DeferredDummyProxy();
    final PagingLoader<PagingLoadConfig, PagingLoadResult<Object>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Object>>(proxy);

    final PagingToolBar bar = new PagingToolBar(5);
    RootPanel.get().add(bar);
    bar.bind(loader);
    delayTestFinish(1000);

    bar.refresh();

    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        assertEquals(1, proxy.loaded);
        proxy.loaded = 0;

        //loaded correctly, so move the goalpost
        proxy.nextTotal = 500;

        //move to what the toolbar thinks is end
        bar.last();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            //now we're at the end even though it moved
            assertEquals(bar.getTotalPages(), bar.getActivePage());
            //it should have taken two loads
            assertEquals(2, proxy.loaded);
            finishTest();
          }
        });
      }
    });
  }

  public void testPagingOperationsSync() {
    final SynchronousDummyProxy proxy = new SynchronousDummyProxy();
    final PagingLoader<PagingLoadConfig, PagingLoadResult<Object>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Object>>(proxy);

    final PagingToolBar bar = new PagingToolBar(5);
    RootPanel.get().add(bar);
    bar.bind(loader);

    bar.refresh();

    assertEquals(1, proxy.loaded);
    assertEquals(1, bar.getActivePage());
    assertEquals(1000, bar.getTotalPages() * bar.getPageSize());
    proxy.loaded = 0;

    bar.next();

    assertEquals(1, proxy.loaded);
    assertEquals(2, bar.getActivePage());
    proxy.loaded = 0;

    bar.last();

    assertEquals(1, proxy.loaded);
    assertEquals(bar.getTotalPages(), bar.getActivePage());
    proxy.loaded = 0;

    bar.previous();

    assertEquals(1, proxy.loaded);
    assertEquals(bar.getTotalPages() - 1, bar.getActivePage());

  }

  public void testBeyondEndSync() {
    final SynchronousDummyProxy proxy = new SynchronousDummyProxy();
    final PagingLoader<PagingLoadConfig, PagingLoadResult<Object>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<Object>>(proxy);

    final PagingToolBar bar = new PagingToolBar(5);
    RootPanel.get().add(bar);
    bar.bind(loader);

    bar.refresh();
    assertEquals(1, proxy.loaded);
    proxy.loaded = 0;

    //loaded correctly, so move the goalpost
    proxy.nextTotal = 500;

    //move to what the toolbar thinks is end
    bar.last();

    //it should have taken two loads
    assertEquals(2, proxy.loaded);
    //now we're at the end even though it moved
    assertEquals(bar.getTotalPages(), bar.getActivePage());
  }

  public abstract static class DummyProxy implements DataProxy<PagingLoadConfig, PagingLoadResult<Object>> {
    public List<Object> nextData = Arrays.asList(new Object(), new Object(), new Object(), new Object(), new Object());
    public int nextOffset;
    public int nextTotal = 1000;
    public int loaded = 0;
  }

  public static class DeferredDummyProxy extends DummyProxy {
    @Override
    public void load(PagingLoadConfig loadConfig, final Callback<PagingLoadResult<Object>, Throwable> callback) {
      nextOffset = loadConfig.getOffset();
      Scheduler.get().scheduleFinally(new ScheduledCommand() {
        @Override
        public void execute() {
          callback.onSuccess(new PagingLoadResultBean<Object>(nextData, nextTotal, nextOffset));
          loaded++;
        }
      });
    }
  }

  public static class SynchronousDummyProxy extends DummyProxy {
    @Override
    public void load(PagingLoadConfig loadConfig, final Callback<PagingLoadResult<Object>, Throwable> callback) {
      nextOffset = loadConfig.getOffset();
      callback.onSuccess(new PagingLoadResultBean<Object>(nextData, nextTotal, nextOffset));
      loaded++;
    }
  }
}
