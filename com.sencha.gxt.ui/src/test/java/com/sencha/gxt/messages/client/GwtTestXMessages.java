package com.sencha.gxt.messages.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.junit.client.WithProperties;
import com.google.gwt.junit.client.WithProperties.Property;

public class GwtTestXMessages extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "com.sencha.gxt.messages.MessagesTest";
  }

  // aka default
  @WithProperties(@Property(name = "locale", value = "en_US"))
  public void testEn_US() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "ca"))
  public void testCa() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "cs"))
  public void testCs() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "da"))
  public void testDa() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "de"))
  public void testDe() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "el_GR"))
  public void testEl_GR() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "es"))
  public void testEs() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "fr"))
  public void testFr() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "hu"))
  public void testHu() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "it"))
  public void testIt() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "ja"))
  public void testJa() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "nl"))
  public void testNl() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "no"))
  public void testNo() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "pl"))
  public void testPl() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "pt"))
  public void testPt() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "pt_BR"))
  public void testPt_BR() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "pt_PT"))
  public void testPt_PT() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "ru"))
  public void testRu() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "sl"))
  public void testSl() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "tr"))
  public void testTr() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "uk"))
  public void testUk() {
    assertNotNull(DefaultMessages.getMessages());
  }

  @WithProperties(@Property(name = "locale", value = "zh"))
  public void testZh() {
    assertNotNull(DefaultMessages.getMessages());
  }

}
