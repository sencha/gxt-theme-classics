package com.sencha.gxt.theme.blue.client.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource.Import;
import com.sencha.gxt.theme.base.client.grid.GridBaseAppearance;
import com.sencha.gxt.widget.core.client.grid.GridView.GridStateStyles;

public class BlueGridAppearance extends GridBaseAppearance {

  public interface BlueGridStyle extends GridStyle {
    
  }
  
  public interface BlueGridResources extends GridResources  {

    @Import(GridStateStyles.class)
    @Source({"com/sencha/gxt/theme/base/client/grid/Grid.gss", "BlueGrid.gss"})
    @Override
    BlueGridStyle css();
  }
  
  
  public BlueGridAppearance() {
    this(GWT.<BlueGridResources> create(BlueGridResources.class));
  }

  public BlueGridAppearance(BlueGridResources resources) {
    super(resources);
  }
}
