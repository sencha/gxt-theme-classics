package com.sencha.gxt.theme.gray.client.statusproxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.statusproxy.StatusProxyBaseAppearance;

public class GrayStatusProxyAppearance extends StatusProxyBaseAppearance {

  public interface GrayStatusProxyResources extends StatusProxyResources, ClientBundle {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/statusproxy/StatusProxy.gss", "GrayStatusProxy.gss"})
    GrayStatusProxyStyle style();

  }

  public interface GrayStatusProxyStyle extends StatusProxyStyle {
  }

  public GrayStatusProxyAppearance() {
    this(GWT.<GrayStatusProxyResources> create(GrayStatusProxyResources.class),
        GWT.<StatusProxyTemplates> create(StatusProxyTemplates.class));
  }

  public GrayStatusProxyAppearance(GrayStatusProxyResources resources, StatusProxyTemplates templates) {
    super(resources, templates);
  }

}
