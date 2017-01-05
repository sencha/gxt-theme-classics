package com.sencha.gxt.theme.grayext.client.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.sencha.gxt.theme.base.client.button.ButtonGroupBaseAppearance;
import com.sencha.gxt.theme.base.client.button.ButtonGroupBaseTableFrameResources;
import com.sencha.gxt.theme.base.client.frame.TableFrame;

public class GrayButtonGroupAppearance extends ButtonGroupBaseAppearance {

  public interface GrayButtonGroupTableFrameResources extends ButtonGroupBaseTableFrameResources {

    @Source({
        "com/sencha/gxt/theme/base/client/frame/TableFrame.gss",
        "com/sencha/gxt/theme/base/client/button/ButtonGroupTableFrame.gss"})
    @Override
    ButtonGroupTableFrameStyle style();

    @Source("com/sencha/gxt/core/public/clear.gif")
    @ImageOptions(repeatStyle = RepeatStyle.Both)
    ImageResource background();

    @Source("groupTopLeftBorder.gif")
    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Override
    ImageResource topLeftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Source("groupTopBorder.gif")
    @Override
    ImageResource topBorder();

    @Override
    @Source("groupTopRightBorder.gif")
    ImageResource topRightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Source("groupLeftBorder.gif")
    @Override
    ImageResource leftBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Vertical)
    @Source("groupRightBorder.gif")
    @Override
    ImageResource rightBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Source("groupBottomBorder.gif")
    @Override
    ImageResource bottomBorder();

    @ImageOptions(repeatStyle = RepeatStyle.Horizontal)
    @Source("groupTopNoHeadBorder.gif")
    ImageResource topNoHeadBorder();
  }

  public interface GrayButtonGroupResources extends ButtonGroupResources {
    @Source({"com/sencha/gxt/theme/base/client/button/ButtonGroup.gss", "GrayButtonGroup.gss"})
    ButtonGroupStyle css();
  }

  public GrayButtonGroupAppearance() {
    this(GWT.<GrayButtonGroupResources> create(GrayButtonGroupResources.class));
  }

  public GrayButtonGroupAppearance(GrayButtonGroupResources resources) {
    super(resources, GWT.<GroupTemplate> create(GroupTemplate.class), new TableFrame(
        GWT.<GrayButtonGroupTableFrameResources> create(GrayButtonGroupTableFrameResources.class)));
  }

}
