package com.sencha.gxt.theme.gray.client.status;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.status.BoxStatusBaseAppearance;
import com.sencha.gxt.widget.core.client.Status.BoxStatusAppearance;

public class GrayBoxStatusAppearance extends BoxStatusBaseAppearance implements BoxStatusAppearance {

  public interface GrayBoxStatusStyle extends BoxStatusStyle {

  }

  public interface GrayBoxStatusResources extends BoxStatusResources, ClientBundle {

    @Override
    @Source({"com/sencha/gxt/theme/base/client/status/Status.gss", "GrayBoxStatus.gss"})
    GrayBoxStatusStyle style();

  }

  public GrayBoxStatusAppearance() {
    this(GWT.<GrayBoxStatusResources> create(GrayBoxStatusResources.class), GWT.<BoxTemplate> create(BoxTemplate.class));
  }

  public GrayBoxStatusAppearance(GrayBoxStatusResources resources, BoxTemplate template) {
    super(resources, template);
  }

}
