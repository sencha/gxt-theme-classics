package com.sencha.gxt.theme.blue.client.container;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.base.client.container.BorderLayoutBaseAppearance;

public class BlueBorderLayoutAppearance extends BorderLayoutBaseAppearance {

  public interface BlueBorderLayoutResources extends BorderLayoutResources {
    @Override
    @Source({"com/sencha/gxt/theme/base/client/container/BorderLayout.gss", "BlueBorderLayout.gss"})
    public BlueBorderLayoutStyle css();
  }

  public interface BlueBorderLayoutStyle extends BorderLayoutStyle {

  }

  public BlueBorderLayoutAppearance() {
    super(GWT.<BlueBorderLayoutResources> create(BlueBorderLayoutResources.class));
  }

}
