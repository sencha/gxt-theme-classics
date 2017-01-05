package com.sencha.gxt.ui.resources.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.sencha.gxt.ui.resources.client.model.Plant;
import com.sencha.gxt.ui.resources.client.model.Stock;
import com.sencha.gxt.ui.resources.shared.BaseDto;
import com.sencha.gxt.ui.resources.shared.FolderDto;
import com.sencha.gxt.ui.resources.shared.MusicDto;

public class TestData {

  private static final String[] monthsAbbreviated = new String[] {
      "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

  private static long autoId = 0;

  public static List<Stock> getCompanies() {
    DateTimeFormat f = DateTimeFormat.getFormat("M/d h:mma");
    List<Stock> stocks = new ArrayList<Stock>();
    stocks.add(new Stock("3m Co", 71.72, 0.02, 0.03, f.parse("4/2 12:00am"), "Manufacturing"));
    stocks.add(new Stock("Alcoa Inc", 29.01, 0.42, 1.47, f.parse("4/1 12:00am"), "Manufacturing"));
    stocks.add(new Stock("Altria Group Inc", 83.81, 0.28, 0.34, f.parse("4/3 12:00am"), "Manufacturing"));
    stocks.add(new Stock("American Express Company", 52.55, 0.01, 0.02, f.parse("4/8 12:00am"), "Finance"));
    stocks.add(new Stock("American International Group, Inc.", 64.13, 0.31, 0.49, f.parse("4/1 12:00am"), "Services"));
    stocks.add(new Stock("AT&T Inc.", 31.61, -0.48, -1.54, f.parse("4/8 12:00am"), "Services"));
    stocks.add(new Stock("Boeing Co.", 75.43, 0.53, 0.71, f.parse("4/8 12:00am"), "Manufacturing"));
    stocks.add(new Stock("Caterpillar Inc.", 67.27, 0.92, 1.39, f.parse("4/1 12:00am"), "Services"));
    stocks.add(new Stock("Citigroup, Inc.", 49.37, 0.02, 0.04, f.parse("4/4 12:00am"), "Finance"));
    stocks.add(new Stock("E.I. du Pont de Nemours and Company", 40.48, 0.51, 1.28, f.parse("4/1 12:00am"),
        "Manufacturing"));
    stocks.add(new Stock("Exxon Mobil Corp", 68.1, -0.43, -0.64, f.parse("4/3 12:00am"), "Manufacturing"));
    stocks.add(new Stock("General Electric Company", 34.14, -0.08, -0.23, f.parse("4/3 12:00am"), "Manufacturing"));
    stocks.add(new Stock("General Motors Corporation", 30.27, 1.09, 3.74, f.parse("4/3 12:00am"), "Automotive"));
    stocks.add(new Stock("Hewlett-Packard Co.", 36.53, -0.03, -0.08, f.parse("4/3 12:00am"), "Computer"));
    stocks.add(new Stock("Honeywell Intl Inc", 38.77, 0.05, 0.13, f.parse("4/3 12:00am"), "Manufacturing"));
    stocks.add(new Stock("Intel Corporation", 19.88, 0.31, 1.58, f.parse("4/2 12:00am"), "Computer"));
    stocks.add(new Stock("International Business Machines", 81.41, 0.44, 0.54, f.parse("4/1 12:00am"), "Computer"));
    stocks.add(new Stock("Johnson & Johnson", 64.72, 0.06, 0.09, f.parse("4/2 12:00am"), "Medical"));
    stocks.add(new Stock("JP Morgan & Chase & Co", 45.73, 0.07, 0.15, f.parse("4/2 12:00am"), "Finance"));
    stocks.add(new Stock("McDonald\"s Corporation", 36.76, 0.86, 2.40, f.parse("4/2 12:00am"), "Food"));
    stocks.add(new Stock("Merck & Co., Inc.", 40.96, 0.41, 1.01, f.parse("4/2 12:00am"), "Medical"));
    stocks.add(new Stock("Microsoft Corporation", 25.84, 0.14, 0.54, f.parse("4/2 12:00am"), "Computer"));
    stocks.add(new Stock("Pfizer Inc", 27.96, 0.4, 1.45, f.parse("4/8 12:00am"), "Services"));
    stocks.add(new Stock("The Coca-Cola Company", 45.07, 0.26, 0.58, f.parse("4/1 12:00am"), "Food"));
    stocks.add(new Stock("The Home Depot, Inc.", 34.64, 0.35, 1.02, f.parse("4/8 12:00am"), "Retail"));
    stocks.add(new Stock("The Procter & Gamble Company", 61.91, 0.01, 0.02, f.parse("4/1 12:00am"), "Manufacturing"));
    stocks.add(new Stock("United Technologies Corporation", 63.26, 0.55, 0.88, f.parse("4/1 12:00am"), "Computer"));
    stocks.add(new Stock("Verizon Communications", 35.57, 0.39, 1.11, f.parse("4/3 12:00am"), "Services"));
    stocks.add(new Stock("Wal-Mart Stores, Inc.", 45.45, 0.73, 1.63, f.parse("4/3 12:00am"), "Retail"));
    stocks.add(new Stock("Walt Disney Company (The) (Holding Company)", 29.89, 0.24, 0.81, f.parse("4/1 12:00am"),
        "Services"));
    return stocks;
  }

  public static FolderDto getMusicRootFolder() {
    FolderDto root = makeFolder("Root");

    FolderDto author = makeFolder("Beethoven");
    List<BaseDto> children = new ArrayList<BaseDto>();
    children.add(author);
    root.setChildren(children);

    FolderDto genre = makeFolder("Quartets");
    author.addChild(genre);

    genre.addChild(makeMusic("Six String Quartets", author, genre));
    genre.addChild(makeMusic("Three String Quartets", author, genre));
    genre.addChild(makeMusic("Grosse Fugue for String Quartets", author, genre));

    genre = makeFolder("Sonatas");
    author.addChild(genre);

    genre.addChild(makeMusic("Sonata in A Minor", author, genre));
    genre.addChild(makeMusic("Sonata in F Major", author, genre));

    genre = makeFolder("Concertos");
    author.addChild(genre);

    genre.addChild(makeMusic("No. 1 - C", author, genre));
    genre.addChild(makeMusic("No. 2 - B-Flat Major", author, genre));
    genre.addChild(makeMusic("No. 3 - C Minor", author, genre));
    genre.addChild(makeMusic("No. 4 - G Major", author, genre));
    genre.addChild(makeMusic("No. 5 - E-Flat Major", author, genre));

    genre = makeFolder("Symphonies");
    author.addChild(genre);

    genre.addChild(makeMusic("No. 1 - C Major", author, genre));
    genre.addChild(makeMusic("No. 2 - D Major", author, genre));
    genre.addChild(makeMusic("No. 3 - E-Flat Major", author, genre));
    genre.addChild(makeMusic("No. 4 - B-Flat Major", author, genre));
    genre.addChild(makeMusic("No. 5 - C Minor", author, genre));
    genre.addChild(makeMusic("No. 6 - F Major", author, genre));
    genre.addChild(makeMusic("No. 7 - A Major", author, genre));
    genre.addChild(makeMusic("No. 8 - F Major", author, genre));
    genre.addChild(makeMusic("No. 9 - D Minor", author, genre));

    author = makeFolder("Brahms");
    root.addChild(author);

    genre = makeFolder("Concertos");
    author.addChild(genre);

    genre.addChild(makeMusic("Violin Concerto", author, genre));
    genre.addChild(makeMusic("Double Concerto - A Minor", author, genre));
    genre.addChild(makeMusic("Piano Concerto No. 1 - D Minor", author, genre));
    genre.addChild(makeMusic("Piano Concerto No. 2 - B-Flat Major", author, genre));

    genre = makeFolder("Quartets");
    author.addChild(genre);

    genre.addChild(makeMusic("Piano Quartet No. 1 - G Minor", author, genre));
    genre.addChild(makeMusic("Piano Quartet No. 2 - A Major", author, genre));
    genre.addChild(makeMusic("Piano Quartet No. 3 - C Minor", author, genre));
    genre.addChild(makeMusic("String Quartet No. 3 - B-Flat Minor", author, genre));

    genre = makeFolder("Sonatas");
    author.addChild(genre);

    genre.addChild(makeMusic("Two Sonatas for Clarinet - F Minor", author, genre));
    genre.addChild(makeMusic("Two Sonatas for Clarinet - E-Flat Major", author, genre));

    genre = makeFolder("Symphonies");
    author.addChild(genre);

    genre.addChild(makeMusic("No. 1 - C Minor", author, genre));
    genre.addChild(makeMusic("No. 2 - D Minor", author, genre));
    genre.addChild(makeMusic("No. 3 - F Major", author, genre));
    genre.addChild(makeMusic("No. 4 - E Minor", author, genre));

    author = makeFolder("Mozart");
    root.addChild(author);

    genre = makeFolder("Concertos");
    author.addChild(genre);

    genre.addChild(makeMusic("Piano Concerto No. 12", author, genre));
    genre.addChild(makeMusic("Piano Concerto No. 17", author, genre));
    genre.addChild(makeMusic("Clarinet Concerto", author, genre));
    genre.addChild(makeMusic("Violin Concerto No. 5", author, genre));
    genre.addChild(makeMusic("Violin Concerto No. 4", author, genre));

    return root;
  }

  public static FolderDto getNodeLeaf() {
    BaseDto child = new MusicDto(1l, "leaf", "", "");

    FolderDto theFolder = new FolderDto(2l, "Folder");
    theFolder.setChildren((List<BaseDto>) new ArrayList<BaseDto>());

    theFolder.addChild(child);

    FolderDto theReturn = new FolderDto(0l, "Root");
    theReturn.setChildren((List<BaseDto>) new ArrayList<BaseDto>());

    theReturn.addChild(theFolder);

    return theReturn;
  }

  public static List<Plant> getPlants() {
    List<Plant> plants = new ArrayList<Plant>();
    plants.add(new Plant("Bloodroot", "Mostly Shady", 2.44, "03/15/2006", true));
    plants.add(new Plant("Columbine", "Shade", 9.37, "03/15/2006", true));
    plants.add(new Plant("Marsh Marigold", "Mostly Sunny", 6.81, "05/17/2006", false));
    plants.add(new Plant("Cowslip", "Mostly Shady", 9.90, "03/06/2006", true));
    plants.add(new Plant("Dutchman's-Breeches", "Mostly Shady", 6.44, "01/20/2006", true));
    plants.add(new Plant("Ginger, Wild", "Mostly Shady", 9.03, "04/18/2006", true));
    plants.add(new Plant("Hepatica", "Sunny", 4.45, "01/26/2006", true));
    plants.add(new Plant("Liverleaf", "Mostly Sunny", 3.99, "01/02/2006", true));
    plants.add(new Plant("Jack-In-The-Pulpit", "Mostly Shady", 3.23, "02/01/2006", true));
    plants.add(new Plant("Mayapple", "Mostly Shady", 2.98, "06/05/2006", true));
    plants.add(new Plant("Phlox, Woodland", "Sun or Shade", 2.80, "01/22/2006", false));
    plants.add(new Plant("Phlox, Blue", "Sun or Shade", 5.59, "02/16/2006", false));
    plants.add(new Plant("Spring-Beauty", "Mostly Shady", 6.59, "02/01/2006", true));
    plants.add(new Plant("Trillium", "Sun or Shade", 3.90, "04/29/2006", false));
    plants.add(new Plant("Wake Robin", "Sun or Shade", 3.20, "02/21/2006", false));
    plants.add(new Plant("Violet, Dog-Tooth", "Shade", 9.04, "02/01/2006", true));
    plants.add(new Plant("Trout Lily", "Shade", 6.94, "03/24/2006", true));
    plants.add(new Plant("Adder's-Tongue", "Mostly Shady", 6.59, "02/01/2006", true));
    plants.add(new Plant("Trillium", "Shade", 9.58, "04/13/2006", true));
    plants.add(new Plant("Anemone", "Mostly Shady", 8.86, "12/26/2006", true));
    plants.add(new Plant("Grecian Windflower", "Mostly Shady", 9.16, "07/10/2006", true));
    plants.add(new Plant("Bee Balm", "Shade", 4.59, "05/03/2006", true));
    plants.add(new Plant("Bergamot", "Shade", 7.16, "04/27/2006", true));
    plants.add(new Plant("Black-Eyed Susan", "Sunny", 9.80, "06/18/2006", false));
    plants.add(new Plant("Buttercup", "Shade", 2.57, "06/10/2006", true));
    plants.add(new Plant("Crowfoot", "Shade", 9.34, "04/03/2006", true));
    plants.add(new Plant("Butterfly Weed", "Sunny", 2.78, "06/30/2006", false));
    plants.add(new Plant("Cinquefoil", "Shade", 7.06, "05/25/2006", true));
    plants.add(new Plant("Primrose", "Sunny", 6.56, "01/30/2006", false));
    plants.add(new Plant("Gentian", "Sun or Shade", 7.81, "05/18/2006", false));
    plants.add(new Plant("Greek Valerian", "Shade", 3.41, "04/03/2006", true));
    plants.add(new Plant("California Poppy", "Mostly Shady", 2.78, "05/13/2006", false));
    plants.add(new Plant("Shooting Star", "Shade", 7.06, "07/11/2006", true));
    plants.add(new Plant("Snakeroot", "Sunny", 6.56, "02/22/2006", false));
    plants.add(new Plant("Cardinal Flower", "Shade", 7.81, "05/18/2006", false));
    return plants;
  }

  public static String[] getShortMonths(int n) {
    String[] mths = new String[n];
    for (int c = 0; c < n; c++)
      mths[c] = monthsAbbreviated[c];
    return mths;
  }

  public static List<Stock> getShortStocks() {
    List<Stock> stocks = new ArrayList<Stock>();

    stocks.add(new Stock("Apple", "AAPL", 125.64, 123.43));
    stocks.add(new Stock("Cisco Systems", "CSCO", 25.84, 26.3));
    stocks.add(new Stock("Google Inc.", "GOOG", 516.2, 512.6));
    stocks.add(new Stock("Intel", "INTC", 21.36, 21.53));
    stocks.add(new Stock("Microsoft", "MSFT", 29.56, 29.72));
    stocks.add(new Stock("Nokia (ADR)", "NOK", 27.83, 27.93));
    stocks.add(new Stock("Oracle", "ORCL", 18.73, 18.98));
    stocks.add(new Stock("Starbucks", "SBUX", 27.33, 27.36));
    stocks.add(new Stock("Yahoo! Inc.", "YHOO", 26.97, 27.29));
    stocks.add(new Stock("Comcast", "CMCSA", 25.9, 26.4));
    stocks.add(new Stock("Sirius Satellite", "SIRI", 2.77, 2.74));

    return stocks;
  }

  private static FolderDto makeFolder(String name) {
    FolderDto theReturn = new FolderDto(++autoId, name);
    theReturn.setChildren((List<BaseDto>) new ArrayList<BaseDto>());
    return theReturn;
  }

  private static MusicDto makeMusic(String name, FolderDto author, FolderDto genre) {
    return makeMusic(name, author.getName(), genre.getName());
  }

  private static MusicDto makeMusic(String name, String author, String genre) {
    return new MusicDto(++autoId, name, genre, author);
  }
}
