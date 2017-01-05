package com.sencha.gxt.theme.gray.client.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.tree.TreeBaseAppearance;

public class GrayTreeAppearance extends TreeBaseAppearance {

  public interface GrayTreeResources extends TreeResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/tree/Tree.gss", "TreeDefault.gss"})
    TreeBaseStyle style();

  }

  public GrayTreeAppearance() {
    super((TreeResources) GWT.create(GrayTreeResources.class));
  }
}
