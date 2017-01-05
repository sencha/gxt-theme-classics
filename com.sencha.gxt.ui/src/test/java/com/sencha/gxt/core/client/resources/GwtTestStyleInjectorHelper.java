package com.sencha.gxt.core.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.sencha.gxt.core.shared.CoreBaseTestCase;

public class GwtTestStyleInjectorHelper extends CoreBaseTestCase {
  interface NeverInjectedCssResource extends CssResource {
  }

  interface RepeatedlyInjectedCssResource extends CssResource {
  }

  interface InjectedCssResource extends CssResource {
  }

  interface TestBundle extends ClientBundle {
    @Source("test.gss")
    InjectedCssResource injectedCssResource();

    @Source("test.gss")
    NeverInjectedCssResource neverInjectedCssResource();

    @Source("test.gss")
    RepeatedlyInjectedCssResource repeatedlyInjectedCssResource();
  }

  public void testInjection() {
    TestBundle testBundle = GWT.create(TestBundle.class);

    Document document = Document.get();
    final int originalStyleElementCount = document.getElementsByTagName("style").getLength();

    // injectedCssResource will be injected
    InjectedCssResource injectedCssResource = testBundle.injectedCssResource();

    assertFalse(StyleInjectorHelper.isInjected(injectedCssResource));
    StyleInjectorHelper.ensureInjected(injectedCssResource, true);
    assertTrue(StyleInjectorHelper.isInjected(injectedCssResource));
    final int oneInjectionStyleElementCount = document.getElementsByTagName("style").getLength();
    assertEquals(oneInjectionStyleElementCount, originalStyleElementCount + 1);

    // neverInjectedCssResource is never injected
    NeverInjectedCssResource neverInjectedCssResource = testBundle.neverInjectedCssResource();
    assertFalse(StyleInjectorHelper.isInjected(neverInjectedCssResource));
  }

  public void testNonInjection() {
    TestBundle testBundle = GWT.create(TestBundle.class);

    // neverInjectedCssResource is never injected
    NeverInjectedCssResource neverInjectedCssResource = testBundle.neverInjectedCssResource();
    assertFalse(StyleInjectorHelper.isInjected(neverInjectedCssResource));
  }

  public void testRepeatedInjection() {
    TestBundle testBundle = GWT.create(TestBundle.class);

    Document document = Document.get();
    final int originalStyleElementCount = document.getElementsByTagName("style").getLength();

    RepeatedlyInjectedCssResource repeatedlyInjectedCssResource = testBundle.repeatedlyInjectedCssResource();

    // First injection
    assertFalse(StyleInjectorHelper.isInjected(repeatedlyInjectedCssResource));
    StyleInjectorHelper.ensureInjected(repeatedlyInjectedCssResource, true);
    assertTrue(StyleInjectorHelper.isInjected(repeatedlyInjectedCssResource));
    final int oneInjectionStyleElementCount = document.getElementsByTagName("style").getLength();
    assertEquals(oneInjectionStyleElementCount, originalStyleElementCount + 1);

    // Second injection
    StyleInjectorHelper.ensureInjected(repeatedlyInjectedCssResource, true);
    assertTrue(StyleInjectorHelper.isInjected(repeatedlyInjectedCssResource));
    final int twoInjectionsStyleElementCount = document.getElementsByTagName("style").getLength();
    assertEquals(twoInjectionsStyleElementCount, originalStyleElementCount + 1);
  }
}
