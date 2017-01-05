package com.sencha.gxt.messages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TestXMessages extends TestCase {

  private class PropertyFile {
    Properties properties;
    String name;
  }

  private Properties properties_en;
  private List<PropertyFile> propertiesFiles;

  @Before
  public void setUp() throws Exception {
    // base the property files off of the english
    properties_en = getProperties("XMessages.properties");

    // get all of the property files for working with
    propertiesFiles = getPropertyFiles();
  }

  @Test
  public void testPropertiesSizes() throws Exception {
    // Given english properties has the expected property size
    int expectedSize = properties_en.size();

    int sizeProblem = 0;
    // When loop through each xmessages properties file
    for (PropertyFile propertyFile : propertiesFiles) {
      // And property sizes don't match up increment
      if (expectedSize != propertyFile.properties.size()) {
        sizeProblem++;
        System.out.println(propertyFile.name + " expectedSize=" + expectedSize + " but was size="
            + propertyFile.properties.size());
      }
    }

    // Then if all xmessages properties should match english
    Assert.assertEquals(0, sizeProblem);
  }

  @Test
  public void testPropertyDifferences() throws Exception {
    // Given property files
    // And no keys are missing
    int missing = 0;

    // And looping through the files
    for (PropertyFile propertyFile : propertiesFiles) {

      // And loop through the keys based on the english properties
      for (Map.Entry property : properties_en.entrySet()) {
        Object key = (String) property.getKey();

        // When we try to get the key out of another properties file
        if (!propertyFile.properties.containsKey(key)) {
          System.out.println(propertyFile.name + " missing key=" + key);

          // Then the increment the value if its null b/c its expected
          missing++;
        }

      }
    }

    // Then assert how many keys are missing in total
    Assert.assertEquals(0, missing);
  }

  /**
   * Get the properties file as Properties "XMessages.properties"
   */
  private Properties getProperties(String propertiesFile) throws Exception {
    String path = getPath();
    path = path + propertiesFile;

    File propsFile = new File(path);

    Properties properties = new Properties();
    FileInputStream fis = new FileInputStream(propsFile);
    properties.load(fis);
    fis.close();

    return properties;
  }

  /**
   * Get a list of the properties as a model for getting the name easily.
   */
  private List<PropertyFile> getPropertyFiles() throws Exception {
    List<String> names = getPropertieFilesNames();

    List<PropertyFile> propertiesFiles = new ArrayList<PropertyFile>();
    for (String name : names) {
      PropertyFile f = new PropertyFile();
      f.name = name;
      f.properties = getProperties(name);
      propertiesFiles.add(f);
    }

    return propertiesFiles;
  }

  /**
   * Get all of the possible property files.
   */
  private List<String> getPropertieFilesNames() {
    String path = getPath();
    File dir = new File(path);

    FilenameFilter filter = new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.matches(".*?_.*?.properties");
      }
    };
    String[] propertyFiles = dir.list(filter);
    return Arrays.asList(propertyFiles);
  }

  /**
   * Get the path to the properties files.
   */
  private String getPath() {
    String path = TestXMessages.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    path = path.replaceFirst("target.*", "src/main/java/com/sencha/gxt/messages/client/");
    return path;
  }

}