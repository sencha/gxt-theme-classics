package com.sencha.gxt.widget.core.client.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.junit.DoNotRunWith;
import com.google.gwt.junit.Platform;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.CoreBaseTestCase;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;
import com.sencha.gxt.widget.core.client.treegrid.TreeGridView;

public class GwtTestTreeChars extends CoreBaseTestCase {

  private TreeGridViewExt<Model> treeGridView;
  private Model model1;
  private Model model2;
  private Model model3;
  private Model model4;

  @DoNotRunWith(Platform.HtmlUnitLayout)
  public void testTree() {
    // Given a tree exists and those models contain ids with illegal id chars
    createTreeGrid();

    // When the tree is invisible and attached
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        // then no exception is thrown on get element
        treeGridView.getElement(treeGridView.findNode(model1));
        treeGridView.getElement(treeGridView.findNode(model2));
        treeGridView.getElement(treeGridView.findNode(model3));
        treeGridView.getElement(treeGridView.findNode(model4));

        finishTest();
      }
    });

    delayTestFinish(10000);
  }

  private TreeStore<Model> getStore() {
    TreeStore<Model> store = new TreeStore<Model>(new ModelKeyProvider<Model>() {
      @Override
      public String getKey(Model item) {
        return item.getId();
      }
    });
    return store;
  }

  public class TreeGridViewExt<M> extends TreeGridView<M> {
    @Override
    public TreeNode<M> findNode(M m) {
      return super.findNode(m);
    }
  }

  public TreeGrid<Model> createTreeGrid() {
    model1 = new Model("1$abc", "A");
    model2 = new Model("2(def)", "B");
    model3 = new Model("$ab-zxy", "C");
    model4 = new Model("x-widget-3_iEaJ9$BWq9SpEC__36", "D");

    TreeStore<Model> store = getStore();
    store.add(model1);
    store.add(model2);
    store.add(model3);
    store.add(model4);

    TreeGridExt<Model> tree = getTreeGrid(store);
    tree.setAutoLoad(true);
    tree.setVisible(false);

    RootPanel.get().add(tree);

    return tree;
  }

  private TreeGridExt<Model> getTreeGrid(TreeStore<Model> store) {
    ColumnConfig<Model, String> cc1 = new ColumnConfig<Model, String>(new ValueProvider<Model, String>() {
      @Override
      public String getValue(Model object) {
        return object.getName();
      }

      @Override
      public void setValue(Model object, String value) {
      }

      @Override
      public String getPath() {
        return "name";
      }
    });
    cc1.setHeader("Name");

    List<ColumnConfig<Model, ?>> l = new ArrayList<ColumnConfig<Model, ?>>();
    l.add(cc1);
    ColumnModel<Model> cm = new ColumnModel<Model>(l);

    treeGridView = new TreeGridViewExt<Model>();

    final TreeGridExt<Model> tree = new TreeGridExt<Model>(store, cm, cc1);
    tree.setView(treeGridView);
    tree.setWidth(300);
    tree.setTitle("TreeTitle");
    tree.getView().setAutoExpandColumn(cc1);

    return tree;
  }

  public class TreeGridExt<M> extends TreeGrid<M> {
    public TreeGridExt(TreeStore<M> store, ColumnModel<M> cm, ColumnConfig<M, ?> treeColumn) {
      super(store, cm, treeColumn);
    }

    public TreeNode<M> getFirstNode() {
      Set<String> keyset = nodes.keySet();
      return nodes.get(keyset.iterator().next());
    }
  }

  public class Model {
    private String id;
    private String name;

    public Model(String id, String name) {
      this.id = id;
      this.name = name;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

}
