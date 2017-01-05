package com.sencha.gxt.theme.blueext.client.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.tree.TreeBaseAppearance;

public class BlueTreeAppearance extends TreeBaseAppearance {

  public interface BlueTreeResources extends TreeResources, ClientBundle {

    @Source({"com/sencha/gxt/theme/base/client/tree/Tree.gss", "TreeDefault.gss"})
    TreeBaseStyle style();

  }

  public BlueTreeAppearance() {
    super((TreeResources) GWT.create(BlueTreeResources.class));
  }
}
