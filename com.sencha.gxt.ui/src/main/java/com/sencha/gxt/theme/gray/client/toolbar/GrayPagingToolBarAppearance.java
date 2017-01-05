package com.sencha.gxt.theme.gray.client.toolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.theme.base.client.toolbar.PagingToolBarBaseAppearance;

public class GrayPagingToolBarAppearance extends PagingToolBarBaseAppearance {
  public interface GrayPagingToolBarResources extends PagingToolBarResources, ClientBundle {
    ImageResource first();

    ImageResource prev();

    ImageResource next();

    ImageResource last();

    ImageResource refresh();

    ImageResource loading();
  }

  public GrayPagingToolBarAppearance() {
    this(GWT.<GrayPagingToolBarResources> create(GrayPagingToolBarResources.class));
  }

  public GrayPagingToolBarAppearance(GrayPagingToolBarResources resources) {
    super(resources);
  }
}
