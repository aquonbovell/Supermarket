import java.util.Random;

/**
 * The programmers' names are: Aquon Bovell
 * The Supermarket class has many methods and fields for the management of its
 * inventory, the amount of items sold, the amount of items purchased, the
 * amount of customers that the Supermarket class has, the amount of items
 * spoilt and the vendors the Supermarket has.
 * The Supermarket class initally has a randomly generated stock when
 * constructed and the proceeds to executes a determined amount of iterations
 * that can be passed at the command line or use the default amount which is 50.
 * During each iteration there is a set of actions are performed:
 * 1) A random event: the event happens every 10 iterations and there are 5
 * types.
 * 2) The spoiltValue of each item is the Supermarket's inventory decreases by
 * one
 * 3) The customer buys an amount between 1 and 20 of each type that is
 * available to sell randomly.
 * 4) Then those items that are spoilt are them removed from the Supermarket's
 * inventory and the Supermarket must turn to it's vendors to restock and pays
 * for them.
 * 5) The Supermarket can only buys items if there is enought money to do so
 * 6) At the end of each day th vendor restocks and the process is repeated
 * until the Supermarket runs out of money or the determined amount of
 * iterations have been executed.
 * Also, the Supermarket has two mode the default mode is non verbose, where no
 * system message or detailed actions are displays to the screen, the other mode
 * is verbose, where every action performed is displayed to the screen.
 * Also, the Supermarket creates a log of what the customers bought, what the
 * Supermarket bought from the different vendors, the amount of spoilt items and
 * any random events that happened.
 */
public class Supermarket {
  // defines the amount of total iterations that the Supermarket must undergo if
  // no iteration are passed at the command line and is the default amount
  public final int DEFAULT_TOTAL_ITERATIONS = 50;
  // defines the maximum of amount of fruits that the Supermarket maintains
  public final int MAXIMUM_NUMBER_OF_FRUITS = 100;
  // defines the maximum of amount of vegtables that the Supermarket maintains
  public final int MAXIMUM_NUMBER_OF_VEGETABLES = 200;
  // defines the number of different items that the Supermarket has
  public final int NUMBER_OF_TYPES_OF_ITEMS = 10;
  // defines the number of customers that the Supermarket has
  public final int NUMBER_OF_CUSTOMERS = 10;
  // defines the modes in which to run the Supermarket
  private boolean verbose;
  // defines the total amount of iterations that the Supermarket has to undergoes
  private int totalIterations;
  // holds the number of iterations that were actually carried out
  private int totalPerformedIterations;
  // holds the profit the that Supermarket made from the sale and purchasing of
  // items, loss of spoilt items and random events
  private double profit;
  // holds the losses that the randon event caused
  private int lossesCausedbyRandomEvent;
  // defines the starting amount that the Supermarket initally has on hand
  private double cashOnHand = 4640_000;
  // holds the total of each fruit the customer buys per cycle
  private int[] fruitsSold;
  // holds the total of each fruit the customer buys per cycle
  private int[] vegetablesSold;
  // holds the total of each item the Supermarket buys per cycle
  private int[] itemsPurchased;
  // holds the total of each item that has spoilt per cycle
  private int[] numberOfSpoiltItems;
  // holds the amount of customers that the Supermarket has
  private Customer[] customers;
  // creates a fruits are here reference
  private FruitsAreHere fruitsAreHere;
  // creates a tasty vegetables reference
  private TastyVegetables tastyVegetables;
  // creates a all You can eat reference
  private AllYouCanEat allYouCanEat;
  // hold all the avocados the Supermarket has at any time
  private Avocado[] avocadoInventory;
  // hold all the bananas the Supermarket has at any time
  private Banana[] bananaInventory;
  // hold all the carrots the Supermarket has at any time
  private Carrot[] carrotInventory;
  // hold all the cucumbers the Supermarket has at any time
  private Cucumber[] cucumberInventory;
  // hold all the lettuces the Supermarket has at any time
  private Lettuce[] lettuceInventory;
  // hold all the limes the Supermarket has at any time
  private Lime[] limeInventory;
  // hold all the mangoes the Supermarket has at any time
  private Mango[] mangoInventory;
  // hold all the onions the Supermarket has at any time
  private Onion[] onionInventory;
  // hold all the parsleys the Supermarket has at any time
  private Parsley[] parsleyInventory;
  // hold all the watermelons the Supermarket has at any time
  private Watermelon[] watermelonInventory;
  /*
   * 
   */
  Supermarket() {
    Random rand = new Random();
    totalIterations = DEFAULT_TOTAL_ITERATIONS;
    fruitsSold = new int[5];
    vegetablesSold = new int[5];
    itemsPurchased = new int[NUMBER_OF_TYPES_OF_ITEMS];
    numberOfSpoiltItems = new int[NUMBER_OF_TYPES_OF_ITEMS];
    customers = new Customer[NUMBER_OF_CUSTOMERS];
    fruitsAreHere = new FruitsAreHere();
    tastyVegetables = new TastyVegetables();
    allYouCanEat = new AllYouCanEat();

    avocadoInventory = new Avocado[rand.nextInt(101)];
    bananaInventory = new Banana[rand.nextInt(101)];
    carrotInventory = new Carrot[rand.nextInt(101)];
    cucumberInventory = new Cucumber[rand.nextInt(101)];
    lettuceInventory = new Lettuce[rand.nextInt(101)];
    limeInventory = new Lime[rand.nextInt(101)];
    mangoInventory = new Mango[rand.nextInt(101)];
    parsleyInventory = new Parsley[rand.nextInt(101)];
    onionInventory = new Onion[rand.nextInt(101)];
    watermelonInventory = new Watermelon[rand.nextInt(101)];
    initCustomers();
    initFruitsInventory(avocadoInventory);
    initFruitsInventory(bananaInventory);
    initVegetablesInventory(carrotInventory);
    initVegetablesInventory(cucumberInventory);
    initFruitsInventory(limeInventory);
    initVegetablesInventory(lettuceInventory);
    initFruitsInventory(mangoInventory);
    initVegetablesInventory(onionInventory);
    initVegetablesInventory(parsleyInventory);
    initFruitsInventory(watermelonInventory);
  }

  public void run() {
    totalPerformedIterations = 0;
    Output.createLogFile();
    try {
      for (int i = 0; i < totalIterations; i++) {
        Output.appendToLogFile(new String("Day :" + (1 + i)));
        ++totalPerformedIterations;
        if ((i + 1) % 10 == 0 && i != 0) {
          // execute a random event every ten cycles
          randomEvent();
        }
        // decrement spoilt value for all items
        decreaseItemsSpoiltValue();

        // sell items to customers
        try {
          sellGoods();
        } catch (Exception e) {
        }

        // remove all spoilt items
        removeSpoiltAvocados();
        removeSpoiltBananas();
        removeSpoiltCarrots();
        removeSpoiltCucumbers();
        removeSpoiltLettuces();
        removeSpoiltLimes();
        removeSpoiltOnions();
        removeSpoiltParsleys();
        removeSpoiltMangoes();
        removeSpoiltWatermelons();

        // restock on items in the supermarket

        buyGoods();

        // reset the customer getAvailablity for the next day
        Customer.reset();

        // restock the vendors
        fruitsAreHere.restock();
        allYouCanEat.restock();
        tastyVegetables.restock();
        calculateProfit();
        reset();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    // output the totals
    System.out.println("Total number of cycles performed: " + totalPerformedIterations);
    System.out.println("Total supermarket profit: $" + profit);
    System.out.println("Total vendor profit: $"
        + (allYouCanEat.getProfit() + fruitsAreHere.getProfit() + tastyVegetables.getProfit()));
  }

  public void turnOnVerbose() {
    verbose = true;
  }

  private void initCustomers() {
    for (int index = 0; index < customers.length; index++) {
      customers[index] = new Customer();
    }
  }

  private void initFruitsInventory(Fruit[] inventory) {
    if (inventory instanceof Watermelon[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Watermelon();
      }
    } else if (inventory instanceof Avocado[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Avocado();
      }
    } else if (inventory instanceof Mango[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Mango();
      }
    } else if (inventory instanceof Lime[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Lime();
      }
    } else if (inventory instanceof Banana[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Banana();
      }
    }
  }

  private void initVegetablesInventory(Vegetable[] inventory) {
    if (inventory instanceof Onion[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Onion();
      }
    } else if (inventory instanceof Parsley[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Parsley();
      }
    } else if (inventory instanceof Lettuce[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Lettuce();
      }
    } else if (inventory instanceof Carrot[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Carrot();
      }
    } else if (inventory instanceof Cucumber[]) {
      for (int i = 0; i < inventory.length; i++) {
        inventory[i] = new Cucumber();
      }
    }
  }

  private void decreaseItemsSpoiltValue() {
    decreaseInventorySpoiltValue(avocadoInventory);
    decreaseInventorySpoiltValue(bananaInventory);
    decreaseInventorySpoiltValue(carrotInventory);
    decreaseInventorySpoiltValue(cucumberInventory);
    decreaseInventorySpoiltValue(limeInventory);
    decreaseInventorySpoiltValue(lettuceInventory);
    decreaseInventorySpoiltValue(mangoInventory);
    decreaseInventorySpoiltValue(onionInventory);
    decreaseInventorySpoiltValue(parsleyInventory);
    decreaseInventorySpoiltValue(watermelonInventory);
  }

  private void decreaseInventorySpoiltValue(Fruit[] inventory) {
    for (int i = 0; i < inventory.length; i++) {
      inventory[i].decreaseSpoiltValue();
    }
  }

  private void decreaseInventorySpoiltValue(Vegetable[] inventory) {
    for (int i = 0; i < inventory.length; i++) {
      inventory[i].decreaseSpoiltValue();
    }
  }

  private void randomEvent() {
    Random rand = new Random();
    switch (rand.nextInt(5)) {
      case 0:
        Output.appendToLogFile("Fridge Breaks Down");
        int cost = rand.nextInt(101);
        lossesCausedbyRandomEvent += cost;
        break;

      case 1:
        int numberOfHours = rand.nextInt(5) + 1;
        Output.appendToLogFile("Electricity Goes Off");
        for (int i = 0; i < numberOfHours; i++) {
          spoilFruits(avocadoInventory);
          spoilFruits(bananaInventory);
          spoilVegetables(carrotInventory);
          spoilVegetables(cucumberInventory);
          spoilVegetables(lettuceInventory);
          spoilFruits(limeInventory);
          spoilFruits(mangoInventory);
          spoilVegetables(onionInventory);
          spoilVegetables(parsleyInventory);
          spoilFruits(watermelonInventory);
        }
        break;

      case 2:
        int vendorSelection = rand.nextInt(3);
        if (vendorSelection == 0) {
          fruitsAreHere.isNotAvailable();
          Output.appendToLogFile("Fruits Are Here Does Not Deliver The Goods");
        } else if (vendorSelection == 1) {
          Output.appendToLogFile("Tasty Vegetables Does Not Deliver The Goods");
        } else if (vendorSelection == 2) {
          allYouCanEat.isNotAvailable();
          Output.appendToLogFile("All You Can Eat Does Not Deliver The Goods");
        }
        break;

      case 3:
        // spoil a random percentage of the total amount of each item
        Output.appendToLogFile("Items spoil faster than expected");
        spoilFruitsFaster(avocadoInventory);
        spoilFruitsFaster(bananaInventory);
        spoilVegetablesFaster(carrotInventory);
        spoilVegetablesFaster(cucumberInventory);
        spoilVegetablesFaster(lettuceInventory);
        spoilFruitsFaster(limeInventory);
        spoilFruitsFaster(mangoInventory);
        spoilVegetablesFaster(onionInventory);
        spoilVegetablesFaster(parsleyInventory);
        spoilFruitsFaster(watermelonInventory);
        break;

      case 4:
        Output.appendToLogFile("No purchases are made");
        Customer.willNotPurchase();
        break;
      default:
        break;
    }
  }

  private void spoilFruits(Fruit[] inventory) {
    int numberOfUnsploitFruits = 0;
    for (int j = 0; j < inventory.length; j++) {
      if (inventory[j].getSpoiltValue() > 0) {
        numberOfUnsploitFruits++;
      }
    }
    int numberOfFruitsToSpoil = (int) Math.ceil((0.01 * numberOfUnsploitFruits));
    int numberOfSpoiltFruits = 0;
    for (int j = 0; j < inventory.length; j++) {
      if (numberOfSpoiltFruits < numberOfFruitsToSpoil && inventory[j].getSpoiltValue() > 0) {
        inventory[j].spoil();
        numberOfSpoiltFruits++;
      }
    }
  }

  private void spoilVegetables(Vegetable[] inventory) {
    int numberOfUnsploitVegetables = 0;
    for (int j = 0; j < inventory.length; j++) {
      if (inventory[j].getSpoiltValue() > 0) {
        numberOfUnsploitVegetables++;
      }
    }
    int numberOfVegetablesToSpoil = (int) Math.ceil((0.01 * numberOfUnsploitVegetables));
    int numberOfSpoiltVegetables = 0;
    for (int j = 0; j < inventory.length; j++) {
      if (numberOfSpoiltVegetables < numberOfVegetablesToSpoil && inventory[j].getSpoiltValue() > 0) {
        inventory[j].spoil();
        numberOfSpoiltVegetables++;
      }
    }
  }

  private void spoilFruitsFaster(Fruit[] inventory) {
    Random rand = new Random();
    double percentage = (rand.nextDouble(9) + 1);
    int numberOfFruitsToSpoil = (int) Math.ceil(((percentage / 100.00) * inventory.length));
    int numberOfSpoiltFruits = 0;
    for (int j = 0; j < inventory.length; j++) {
      if (numberOfSpoiltFruits < numberOfFruitsToSpoil && inventory[j].getSpoiltValue() > 0) {
        inventory[j].spoil();
        numberOfSpoiltFruits++;
      }
    }
  }

  private void spoilVegetablesFaster(Vegetable[] inventory) {
    Random rand = new Random();
    double percentage = (rand.nextDouble(9) + 1);
    int numberOfVegetablesToSpoil = (int) Math.ceil(((percentage / 100.00) * inventory.length));
    int numberOfSpoiltVegetables = 0;
    for (int j = 0; j < inventory.length; j++) {
      if (numberOfSpoiltVegetables < numberOfVegetablesToSpoil && inventory[j].getSpoiltValue() > 0) {
        inventory[j].spoil();
        numberOfSpoiltVegetables++;
      }
    }
  }

  private void sellAvocados(int items) throws Exception {
    if (avocadoInventory.length == 0) {
      Output.appendToLogFile("FruitNotAvailable: Avocados");
      throw new Exception("FruitNotAvailable: Avocados");
    } else {
      int numberOfUnsploitAvocados = 0;
      int numberOfSploitAvocados = 0;
      for (int j = 0; j < avocadoInventory.length; j++) {
        if (avocadoInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitAvocados++;
        } else {
          numberOfUnsploitAvocados++;
        }
      }
      Avocado[] availableItems = new Avocado[numberOfUnsploitAvocados];
      int k = 0;
      for (int j = 0; j < avocadoInventory.length; j++) {
        if (avocadoInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = avocadoInventory[j];
        }
      }
      if (items >= availableItems.length) {
        Avocado[] updatedInventory = new Avocado[numberOfSploitAvocados];
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Avocado",
            (availableItems.length * Avocado.SELLING_PRICE));
        fruitsSold[0] += availableItems.length;
        k = 0;
        for (int j = 0; j < avocadoInventory.length; j++) {
          if (avocadoInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = avocadoInventory[j];
          }
        }
        avocadoInventory = updatedInventory;

      } else {
        Avocado[] updatedInventory = new Avocado[availableItems.length - items
            + numberOfSploitAvocados];
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Avocado",
            (items * Avocado.SELLING_PRICE));
        fruitsSold[0] += items;
        k = 0;
        for (int j = 0; j < avocadoInventory.length; j++) {
          if (avocadoInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = avocadoInventory[j];
          }
        }
        int shiftIndex = numberOfSploitAvocados;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = avocadoInventory[k++];
        }
        avocadoInventory = updatedInventory;

      }
    }
  }

  private void sellBananas(int items) throws Exception {
    if (bananaInventory.length == 0) {
      Output.appendToLogFile("FruitNotAvailable: Bananas");
      throw new Exception("FruitNotAvailable: Bananas");
    } else {
      int numberOfUnsploitBananas = 0;
      int numberOfSploitBananas = 0;
      for (int j = 0; j < bananaInventory.length; j++) {
        if (bananaInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitBananas++;
        } else {
          numberOfUnsploitBananas++;
        }
      }
      Banana[] availableItems = new Banana[numberOfUnsploitBananas];
      int k = 0;
      for (int j = 0; j < bananaInventory.length; j++) {
        if (bananaInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = bananaInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Banana[] updatedInventory = new Banana[numberOfSploitBananas];
        fruitsSold[1] += availableItems.length;
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Banana",
            (availableItems.length * Banana.SELLING_PRICE));
        k = 0;
        for (int j = 0; j < bananaInventory.length; j++) {
          if (bananaInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = bananaInventory[j];
          }
        }
        bananaInventory = updatedInventory;

      } else {
        Banana[] updatedInventory = new Banana[availableItems.length - items
            + numberOfSploitBananas];
        fruitsSold[1] += items;
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Banana",
            (items * Banana.SELLING_PRICE));
        k = 0;
        for (int j = 0; j < bananaInventory.length; j++) {
          if (bananaInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = bananaInventory[j];
          }
        }
        int shiftIndex = numberOfSploitBananas;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = bananaInventory[k++];
        }
        bananaInventory = updatedInventory;
      }
    }
  }

  private void sellCarrots(int items) throws Exception {
    if (carrotInventory.length == 0) {
      Output.appendToLogFile("VegetableNotAvailable: Carrots");
      throw new Exception("VegetableNotAvailable: Carrots");
    } else {
      int numberOfUnsploitCarrots = 0;
      int numberOfSploitCarrots = 0;
      for (int j = 0; j < carrotInventory.length; j++) {
        if (carrotInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitCarrots++;
        } else {
          numberOfUnsploitCarrots++;
        }
      }
      Carrot[] availableItems = new Carrot[numberOfUnsploitCarrots];
      int k = 0;
      for (int j = 0; j < carrotInventory.length; j++) {
        if (carrotInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = carrotInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Carrot[] updatedInventory = new Carrot[numberOfSploitCarrots];
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Carrot",
            (availableItems.length * Carrot.SELLING_PRICE));
        vegetablesSold[0] += availableItems.length;
        k = 0;
        for (int j = 0; j < carrotInventory.length; j++) {
          if (carrotInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = carrotInventory[j];
          }
        }
        carrotInventory = updatedInventory;

      } else {
        Carrot[] updatedInventory = new Carrot[availableItems.length - items
            + numberOfSploitCarrots];
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Carrot",
            (items * Carrot.SELLING_PRICE));
        vegetablesSold[0] += items;
        k = 0;
        for (int j = 0; j < carrotInventory.length; j++) {
          if (carrotInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = carrotInventory[j];
          }
        }
        int shiftIndex = numberOfSploitCarrots;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = carrotInventory[k++];
        }
        carrotInventory = updatedInventory;
      }
    }
  }

  private void sellCucumbers(int items) throws Exception {
    if (cucumberInventory.length == 0) {
      Output.appendToLogFile("VegetableNotAvailable: Cucumbers");
      throw new Exception("VegetableNotAvailable: Cucumbers");
    } else {
      int numberOfUnsploitCucumbers = 0;
      int numberOfSploitCucumbers = 0;
      for (int j = 0; j < cucumberInventory.length; j++) {
        if (cucumberInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitCucumbers++;
        } else {
          numberOfUnsploitCucumbers++;
        }
      }
      Cucumber[] availableItems = new Cucumber[numberOfUnsploitCucumbers];
      int k = 0;
      for (int j = 0; j < cucumberInventory.length; j++) {
        if (cucumberInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = cucumberInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Cucumber[] updatedInventory = new Cucumber[numberOfSploitCucumbers];
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Cucumber",
            (availableItems.length * Cucumber.SELLING_PRICE));
        vegetablesSold[1] += availableItems.length;
        k = 0;
        for (int j = 0; j < cucumberInventory.length; j++) {
          if (cucumberInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = cucumberInventory[j];
          }
        }
        cucumberInventory = updatedInventory;

      } else {
        Cucumber[] updatedInventory = new Cucumber[availableItems.length - items
            + numberOfSploitCucumbers];
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Cucumber",
            (items * Cucumber.SELLING_PRICE));
        vegetablesSold[1] += items;
        k = 0;
        for (int j = 0; j < cucumberInventory.length; j++) {
          if (cucumberInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = cucumberInventory[j];
          }
        }
        int shiftIndex = numberOfSploitCucumbers;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = cucumberInventory[k++];
        }
        cucumberInventory = updatedInventory;
      }
    }
  }

  private void sellLettuces(int items) throws Exception {
    if (lettuceInventory.length == 0) {
      Output.appendToLogFile("VegetableNotAvailable: Lettuces");
      throw new Exception("VegetableNotAvailable: Lettuces");
    } else {
      int numberOfUnsploitLettuces = 0;
      int numberOfSploitLettuces = 0;
      for (int j = 0; j < lettuceInventory.length; j++) {
        if (lettuceInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitLettuces++;
        } else {
          numberOfUnsploitLettuces++;
        }
      }
      Lettuce[] availableItems = new Lettuce[numberOfUnsploitLettuces];
      int k = 0;
      for (int j = 0; j < lettuceInventory.length; j++) {
        if (lettuceInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = lettuceInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Lettuce[] updatedInventory = new Lettuce[numberOfSploitLettuces];
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Lettuce",
            (availableItems.length * Lettuce.SELLING_PRICE));
        vegetablesSold[2] += availableItems.length;
        k = 0;
        for (int j = 0; j < lettuceInventory.length; j++) {
          if (lettuceInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = lettuceInventory[j];
          }
        }
        lettuceInventory = updatedInventory;

      } else {
        Lettuce[] updatedInventory = new Lettuce[availableItems.length - items
            + numberOfSploitLettuces];
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Lettuce",
            (items * Lettuce.SELLING_PRICE));
        vegetablesSold[2] += items;
        k = 0;
        for (int j = 0; j < lettuceInventory.length; j++) {
          if (lettuceInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = lettuceInventory[j];
          }
        }
        int shiftIndex = numberOfSploitLettuces;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = lettuceInventory[k++];
        }
        lettuceInventory = updatedInventory;
      }
    }
  }

  private void sellLimes(int items) throws Exception {
    if (limeInventory.length == 0) {
      Output.appendToLogFile("FruitNotAvailable: Limes");
      throw new Exception("FruitNotAvailable: Limes");
    } else {
      int numberOfUnsploitLimes = 0;
      int numberOfSploitLimes = 0;
      for (int j = 0; j < limeInventory.length; j++) {
        if (limeInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitLimes++;
        } else {
          numberOfUnsploitLimes++;
        }
      }
      Lime[] availableItems = new Lime[numberOfUnsploitLimes];
      int k = 0;
      for (int j = 0; j < limeInventory.length; j++) {
        if (limeInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = limeInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Lime[] updatedInventory = new Lime[numberOfSploitLimes];
        fruitsSold[2] += availableItems.length;
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Lime",
            (availableItems.length * Lime.SELLING_PRICE));
        k = 0;
        for (int j = 0; j < limeInventory.length; j++) {
          if (limeInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = limeInventory[j];
          }
        }
        limeInventory = updatedInventory;

      } else {
        Lime[] updatedInventory = new Lime[availableItems.length - items
            + numberOfSploitLimes];
        fruitsSold[2] += items;
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Lime",
            (items * Lime.SELLING_PRICE));
        k = 0;
        for (int j = 0; j < limeInventory.length; j++) {
          if (limeInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = limeInventory[j];
          }
        }
        int shiftIndex = numberOfSploitLimes;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = limeInventory[k++];
        }
        limeInventory = updatedInventory;
      }
    }
  }

  private void sellMangoes(int items) throws Exception {
    if (mangoInventory.length == 0) {
      Output.appendToLogFile("FruitNotAvailable: Mangoes");
      throw new Exception("FruitNotAvailable: Mangoes");
    } else {
      int numberOfUnsploitMangoes = 0;
      int numberOfSploitMangoes = 0;
      for (int j = 0; j < mangoInventory.length; j++) {
        if (mangoInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitMangoes++;
        } else {
          numberOfUnsploitMangoes++;
        }
      }
      Mango[] availableItems = new Mango[numberOfUnsploitMangoes];
      int k = 0;
      for (int j = 0; j < mangoInventory.length; j++) {
        if (mangoInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = mangoInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Mango[] updatedInventory = new Mango[numberOfSploitMangoes];
        fruitsSold[3] += availableItems.length;
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Mango",
            (availableItems.length * Mango.SELLING_PRICE));
        k = 0;
        for (int j = 0; j < mangoInventory.length; j++) {
          if (mangoInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = mangoInventory[j];
          }
        }
        mangoInventory = updatedInventory;

      } else {
        Mango[] updatedInventory = new Mango[availableItems.length - items
            + numberOfSploitMangoes];
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Lime",
            (items * Lime.SELLING_PRICE));
        fruitsSold[3] += items;
        k = 0;
        for (int j = 0; j < mangoInventory.length; j++) {
          if (mangoInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = mangoInventory[j];
          }
        }
        int shiftIndex = numberOfSploitMangoes;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = mangoInventory[k++];
        }
        mangoInventory = updatedInventory;
      }
    }
  }

  private void sellWatermelons(int items) throws Exception {
    if (watermelonInventory.length == 0) {
      Output.appendToLogFile("FruitNotAvailable: Watermelons");
      throw new Exception("FruitNotAvailable: Watermelons");
    } else {
      int numberOfUnsploitWatermelons = 0;
      int numberOfSploitWatermelons = 0;
      for (int j = 0; j < watermelonInventory.length; j++) {
        if (watermelonInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitWatermelons++;
        } else {
          numberOfUnsploitWatermelons++;
        }
      }
      Watermelon[] availableItems = new Watermelon[numberOfUnsploitWatermelons];
      int k = 0;
      for (int j = 0; j < watermelonInventory.length; j++) {
        if (watermelonInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = watermelonInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Watermelon[] updatedInventory = new Watermelon[numberOfSploitWatermelons];
        fruitsSold[4] += availableItems.length;
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Watermelon",
            (availableItems.length * Watermelon.SELLING_PRICE));
        k = 0;
        for (int j = 0; j < watermelonInventory.length; j++) {
          if (watermelonInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = watermelonInventory[j];
          }
        }
        watermelonInventory = updatedInventory;

      } else {
        Watermelon[] updatedInventory = new Watermelon[availableItems.length - items
            + numberOfSploitWatermelons];
        fruitsSold[4] += items;
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Watermelon",
            (items * Watermelon.SELLING_PRICE));
        k = 0;
        for (int j = 0; j < watermelonInventory.length; j++) {
          if (watermelonInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = watermelonInventory[j];
          }
        }
        int shiftIndex = numberOfSploitWatermelons;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = watermelonInventory[k++];
        }
        watermelonInventory = updatedInventory;
      }
    }
  }

  private void sellOnions(int items) throws Exception {
    if (onionInventory.length == 0) {
      Output.appendToLogFile("VegetableNotAvailable: Onions");
      throw new Exception("VegetableNotAvailable: Onions");
    } else {
      int numberOfUnsploitOnions = 0;
      int numberOfSploitOnions = 0;
      for (int j = 0; j < onionInventory.length; j++) {
        if (onionInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitOnions++;
        } else {
          numberOfUnsploitOnions++;
        }
      }
      Onion[] availableItems = new Onion[numberOfUnsploitOnions];
      int k = 0;
      for (int j = 0; j < onionInventory.length; j++) {
        if (onionInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = onionInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Onion[] updatedInventory = new Onion[numberOfSploitOnions];
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Onion",
            (availableItems.length * Onion.SELLING_PRICE));
        vegetablesSold[3] += availableItems.length;
        k = 0;
        for (int j = 0; j < onionInventory.length; j++) {
          if (onionInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = onionInventory[j];
          }
        }
        onionInventory = updatedInventory;

      } else {
        Onion[] updatedInventory = new Onion[availableItems.length - items
            + numberOfSploitOnions];
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Onion",
            (items * Onion.SELLING_PRICE));
        vegetablesSold[3] += items;
        k = 0;
        for (int j = 0; j < onionInventory.length; j++) {
          if (onionInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = onionInventory[j];
          }
        }
        int shiftIndex = numberOfSploitOnions;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = onionInventory[k++];
        }
        onionInventory = updatedInventory;
      }
    }
  }

  private void sellParsleys(int items) throws Exception {
    if (parsleyInventory.length == 0) {
      Output.appendToLogFile("VegetableNotAvailable: Parsleys");
      throw new Exception("VegetableNotAvailable: Parsleys");
    } else {
      int numberOfUnsploitParsleys = 0;
      int numberOfSploitParsleys = 0;
      for (int j = 0; j < parsleyInventory.length; j++) {
        if (parsleyInventory[j].getSpoiltValue() <= 0) {
          numberOfSploitParsleys++;
        } else {
          numberOfUnsploitParsleys++;
        }
      }
      Parsley[] availableItems = new Parsley[numberOfUnsploitParsleys];
      int k = 0;
      for (int j = 0; j < parsleyInventory.length; j++) {
        if (parsleyInventory[j].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[k++] = parsleyInventory[j];
        }
      }

      if (items >= availableItems.length) {
        Parsley[] updatedInventory = new Parsley[numberOfSploitParsleys];
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Parsley",
            (availableItems.length * Parsley.SELLING_PRICE));
        vegetablesSold[4] += availableItems.length;
        k = 0;
        for (int j = 0; j < parsleyInventory.length; j++) {
          if (parsleyInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = parsleyInventory[j];
          }
        }
        parsleyInventory = updatedInventory;

      } else {
        Parsley[] updatedInventory = new Parsley[availableItems.length - items
            + numberOfSploitParsleys];
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Parsley",
            (items * Parsley.SELLING_PRICE));
        vegetablesSold[4] += items;
        k = 0;
        for (int j = 0; j < parsleyInventory.length; j++) {
          if (parsleyInventory[j].getSpoiltValue() <= 0) {
            updatedInventory[k++] = parsleyInventory[j];
          }
        }
        int shiftIndex = numberOfSploitParsleys;
        k = 0;
        for (int j = shiftIndex; j < updatedInventory.length; j++) {
          updatedInventory[j] = parsleyInventory[k++];
        }
        parsleyInventory = updatedInventory;
      }
    }
  }

  private void sellGoods() throws Exception {
    if (Customer.willPurchase()) {
      Random rand = new Random();
      int items = 0;
      for (int index = 0; index < customers.length; index++) {
        switch (rand.nextInt(customers.length)) {
          case 0:
            items = customers[index].itemsToBePurchased();
            sellAvocados(items);
            break;
          case 1:
            items = customers[index].itemsToBePurchased();
            sellBananas(items);
            break;
          case 2:
            items = customers[index].itemsToBePurchased();
            sellCarrots(items);
            break;
          case 3:
            items = customers[index].itemsToBePurchased();
            sellCucumbers(items);
            break;
          case 4:
            items = customers[index].itemsToBePurchased();
            sellLettuces(items);
            break;
          case 5:
            items = customers[index].itemsToBePurchased();
            sellLimes(items);
            break;
          case 6:
            items = customers[index].itemsToBePurchased();
            sellMangoes(items);
            break;
          case 7:
            items = customers[index].itemsToBePurchased();
            sellOnions(items);
            break;
          case 8:
            items = customers[index].itemsToBePurchased();
            sellParsleys(items);
            break;
          case 9:
            items = customers[index].itemsToBePurchased();
            sellWatermelons(items);
            break;
          default:
            break;
        }
      }
      if (verbose) {
        int totalFruitsPurchsedByCustomer = 0;
        for (int index = 0; index < fruitsSold.length; index++) {
          totalFruitsPurchsedByCustomer += fruitsSold[index];
        }
        int totalVegetablesPurchsedByCustomer = 0;
        for (int index = 0; index < vegetablesSold.length; index++) {
          totalVegetablesPurchsedByCustomer += vegetablesSold[index];
        }
        Print.verboseTotalAmountCustomerPurchase(totalFruitsPurchsedByCustomer, totalVegetablesPurchsedByCustomer);
      }
    }
  }

  private void removeSpoiltAvocados() {
    int sum = 0;
    for (int i = 0; i < avocadoInventory.length; i++) {
      if (avocadoInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[0] += sum;
    if (verbose) {
      Print.verboseSpoiltFruit("avocado", sum);
    }
    if (sum > 0) {
      Avocado updatedAvocadoInventory[] = new Avocado[avocadoInventory.length - sum];
      int k = 0;
      for (int i = 0; i < avocadoInventory.length; i++) {
        if (avocadoInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedAvocadoInventory[k++] = avocadoInventory[i];
        }
      }
      avocadoInventory = updatedAvocadoInventory;
    }

  }

  private void removeSpoiltBananas() {
    int sum = 0;
    for (int i = 0; i < bananaInventory.length; i++) {
      if (bananaInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[1] += sum;
    if (verbose) {
      Print.verboseSpoiltFruit("banana", sum);
    }
    if (sum > 0) {
      Banana updatedBananaInventory[] = new Banana[bananaInventory.length - sum];
      int k = 0;
      for (int i = 0; i < bananaInventory.length; i++) {
        if (bananaInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedBananaInventory[k++] = bananaInventory[i];
        }
      }
      bananaInventory = updatedBananaInventory;
    }

  }

  private void removeSpoiltCarrots() {
    int sum = 0;
    for (int i = 0; i < carrotInventory.length; i++) {
      if (carrotInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[2] += sum;
    if (verbose) {
      Print.verboseSpoiltVegetable("carrot", sum);
    }
    if (sum > 0) {
      Carrot updatedCarrotsInventory[] = new Carrot[carrotInventory.length - sum];
      int k = 0;
      for (int i = 0; i < carrotInventory.length; i++) {
        if (carrotInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedCarrotsInventory[k++] = carrotInventory[i];
        }
      }
      carrotInventory = updatedCarrotsInventory;
    }
  }

  private void removeSpoiltCucumbers() {
    int sum = 0;
    for (int i = 0; i < cucumberInventory.length; i++) {
      if (cucumberInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[3] += sum;
    if (verbose) {
      Print.verboseSpoiltVegetable("cucumber", sum);
    }
    if (sum > 0) {
      Cucumber updatedCucumbersInventory[] = new Cucumber[cucumberInventory.length - sum];
      int k = 0;
      for (int i = 0; i < cucumberInventory.length; i++) {
        if (cucumberInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedCucumbersInventory[k++] = cucumberInventory[i];
        }
      }
      cucumberInventory = updatedCucumbersInventory;
    }
  }

  private void removeSpoiltLettuces() {
    int sum = 0;
    for (int i = 0; i < lettuceInventory.length; i++) {
      if (lettuceInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[4] += sum;
    if (verbose) {
      Print.verboseSpoiltVegetable("lettuce", sum);
    }
    if (sum > 0) {
      Lettuce updatedLettucesInventory[] = new Lettuce[lettuceInventory.length - sum];
      int k = 0;
      for (int i = 0; i < lettuceInventory.length; i++) {
        if (lettuceInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedLettucesInventory[k++] = lettuceInventory[i];
        }
      }
      lettuceInventory = updatedLettucesInventory;
    }
  }

  private void removeSpoiltLimes() {
    int sum = 0;
    for (int i = 0; i < limeInventory.length; i++) {
      if (limeInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[5] += sum;
    if (verbose) {
      Print.verboseSpoiltFruit("lime", sum);
    }
    if (sum > 0) {
      Lime updatedLimeInventory[] = new Lime[limeInventory.length - sum];
      int k = 0;
      for (int i = 0; i < limeInventory.length; i++) {
        if (limeInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedLimeInventory[k++] = limeInventory[i];
        }
      }
      limeInventory = updatedLimeInventory;
    }

  }

  private void removeSpoiltMangoes() {
    int sum = 0;
    for (int i = 0; i < mangoInventory.length; i++) {
      if (mangoInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[6] += sum;
    if (verbose) {
      Print.verboseSpoiltFruit("mango", sum);
    }
    if (sum > 0) {
      Mango updatedMangoesInventory[] = new Mango[mangoInventory.length - sum];
      int k = 0;
      for (int i = 0; i < mangoInventory.length; i++) {
        if (mangoInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedMangoesInventory[k++] = mangoInventory[i];
        }
      }
      mangoInventory = updatedMangoesInventory;
    }

  }

  private void removeSpoiltOnions() {
    int sum = 0;
    for (int i = 0; i < onionInventory.length; i++) {
      if (onionInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[7] += sum;
    if (verbose) {
      Print.verboseSpoiltVegetable("onion", sum);
    }
    if (sum > 0) {
      Onion updatedOnionsInventory[] = new Onion[onionInventory.length - sum];
      int k = 0;
      for (int i = 0; i < onionInventory.length; i++) {
        if (onionInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedOnionsInventory[k++] = onionInventory[i];
        }
      }
      onionInventory = updatedOnionsInventory;
    }
  }

  private void removeSpoiltParsleys() {
    int sum = 0;
    for (int i = 0; i < parsleyInventory.length; i++) {
      if (parsleyInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[8] += sum;
    if (verbose) {
      Print.verboseSpoiltVegetable("parsley", sum);
    }
    if (sum > 0) {
      Parsley updatedParsleysInventory[] = new Parsley[parsleyInventory.length - sum];
      int k = 0;
      for (int i = 0; i < parsleyInventory.length; i++) {
        if (parsleyInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedParsleysInventory[k++] = parsleyInventory[i];
        }
      }
      parsleyInventory = updatedParsleysInventory;
    }
  }

  private void removeSpoiltWatermelons() {
    int sum = 0;
    for (int i = 0; i < watermelonInventory.length; i++) {
      if (watermelonInventory[i].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    numberOfSpoiltItems[9] += sum;
    if (verbose) {
      Print.verboseSpoiltFruit("wateremelon", sum);
    }
    if (sum > 0) {
      Watermelon updatedWatermelonInventory[] = new Watermelon[watermelonInventory.length - sum];
      int k = 0;
      for (int i = 0; i < watermelonInventory.length; i++) {
        if (watermelonInventory[i].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedWatermelonInventory[k++] = watermelonInventory[i];
        }
      }
      watermelonInventory = updatedWatermelonInventory;
    }
  }

  private void buyGoods() throws Exception {
    if (avocadoInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
      buyAvocados();
    }
    if (bananaInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
      buyBananas();
    }
    if (carrotInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
      buyCarrots();
    }
    if (cucumberInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
      buyCucumbers();
    }
    if (lettuceInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
      buyLettuces();
    }
    if (limeInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
      buyLimes();
    }
    if (mangoInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
      buyMangoes();
    }
    if (onionInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
      buyOnions();
    }
    if (parsleyInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
      buyParsleys();
    }
    if (watermelonInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
      buyWatermelons();
    }
  }

  private void buyAvocados() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (fruitsAreHere.getAvailablity()) {
        int numberOfAvocadosToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - avocadoInventory.length);
        if (cashOnHand >= (numberOfAvocadosToBeBought * Avocado.COST_PRICE)) {
          Avocado[] avocadosBought = fruitsAreHere.sellAvocados(numberOfAvocadosToBeBought);
          cashOnHand -= (avocadosBought.length * Avocado.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Fruits Are Here", "Avocado", avocadosBought.length);
          }
          Output.appendToLogFileAmountOfFruitsPurchase(avocadosBought.length, "Avocado", "Fruits Are Here",
              Avocado.COST_PRICE);
          itemsPurchased[0] += avocadosBought.length;
          Avocado[] updatedAvocadosInventory = new Avocado[avocadoInventory.length
              + avocadosBought.length];
          for (int i = 0; i < avocadoInventory.length; i++) {
            updatedAvocadosInventory[i] = avocadoInventory[i];
          }
          int shiftIndex = avocadoInventory.length;
          for (int i = 0; i < (avocadosBought.length); i++) {
            updatedAvocadosInventory[(i + shiftIndex)] = avocadosBought[i];
          }
          avocadoInventory = updatedAvocadosInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Avocados");
        }
      } else if (!fruitsAreHere.getAvailablity() && (avocadoInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfAvocadosToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - avocadoInventory.length);
          if (cashOnHand >= (numberOfAvocadosToBeBought * Avocado.COST_PRICE)) {
            Avocado[] avocadosBought = allYouCanEat.sellAvocados(numberOfAvocadosToBeBought);
            cashOnHand -= (avocadosBought.length * Avocado.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Avocado", avocadosBought.length);
            }
            Output.appendToLogFileAmountOfFruitsPurchase(avocadosBought.length, "Avocado", "All You Can Eat",
                Avocado.COST_PRICE);
            itemsPurchased[0] += avocadosBought.length;
            Avocado[] updatedAvocadosInventory = new Avocado[avocadoInventory.length
                + avocadosBought.length];
            for (int i = 0; i < avocadoInventory.length; i++) {
              updatedAvocadosInventory[i] = avocadoInventory[i];
            }
            int shiftIndex = avocadoInventory.length;
            for (int i = 0; i < (avocadosBought.length); i++) {
              updatedAvocadosInventory[(i + shiftIndex)] = avocadosBought[i];
            }
            avocadoInventory = updatedAvocadosInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Avocados");
          }
        }
      }
    }
  }

  private void buyBananas() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (fruitsAreHere.getAvailablity()) {
        int numberOfBananasToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - bananaInventory.length);
        if (cashOnHand >= (numberOfBananasToBeBought * Banana.COST_PRICE)) {
          Banana[] bananasBought = fruitsAreHere.sellBananas(numberOfBananasToBeBought);
          cashOnHand -= (bananasBought.length * Banana.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Fruits Are Here", "Banana", bananasBought.length);
          }
          Output.appendToLogFileAmountOfFruitsPurchase(bananasBought.length, "Banana", "Fruits Are Here",
              Banana.COST_PRICE);
          itemsPurchased[1] += bananasBought.length;
          Banana[] updatedBananasInventory = new Banana[bananaInventory.length
              + bananasBought.length];
          for (int i = 0; i < bananaInventory.length; i++) {
            updatedBananasInventory[i] = bananaInventory[i];
          }
          int shiftIndex = bananaInventory.length;
          for (int i = 0; i < (bananasBought.length); i++) {
            updatedBananasInventory[(i + shiftIndex)] = bananasBought[i];
          }
          bananaInventory = updatedBananasInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Bananas");
        }
      } else if (!fruitsAreHere.getAvailablity() && (bananaInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfBananasToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - bananaInventory.length);
          if (cashOnHand >= (numberOfBananasToBeBought * Banana.COST_PRICE)) {
            Banana[] bananasBought = allYouCanEat.sellBananas(numberOfBananasToBeBought);
            cashOnHand -= (bananasBought.length * Banana.COST_PRICE);
            if (verbose) {
            }
            Print.verboseTotalItemsPurchased("All You Can Eat", "Banana", bananasBought.length);
            Output.appendToLogFileAmountOfFruitsPurchase(bananasBought.length, "Banana", "All You Can Eat",
                Banana.COST_PRICE);
            itemsPurchased[1] += bananasBought.length;
            Banana[] updatedBananasInventory = new Banana[bananaInventory.length
                + bananasBought.length];
            for (int i = 0; i < bananaInventory.length; i++) {
              updatedBananasInventory[i] = bananaInventory[i];
            }
            int shiftIndex = bananaInventory.length;
            for (int i = 0; i < (bananasBought.length); i++) {
              updatedBananasInventory[(i + shiftIndex)] = bananasBought[i];
            }
            bananaInventory = updatedBananasInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Bananas");
          }
        }
      }
    }
  }

  private void buyCarrots() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (tastyVegetables.getAvailablity()) {
        int numberOfCarrotsToBeBought = (MAXIMUM_NUMBER_OF_VEGETABLES - carrotInventory.length);
        if (cashOnHand >= (numberOfCarrotsToBeBought * Carrot.COST_PRICE)) {
          Carrot[] carrotsBought = tastyVegetables.sellCarrots(numberOfCarrotsToBeBought);
          cashOnHand -= (carrotsBought.length * Carrot.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Tasty Vegetables", "Carrot", carrotsBought.length);
          }
          Output.appendToLogFileAmountOfVegetablesPurchase(carrotsBought.length, "Carrot", "Tasty Vegetables",
              Carrot.COST_PRICE);
          itemsPurchased[2] += carrotsBought.length;
          Carrot[] updatedCarrotsInventory = new Carrot[carrotInventory.length
              + carrotsBought.length];
          for (int i = 0; i < carrotInventory.length; i++) {
            updatedCarrotsInventory[i] = carrotInventory[i];
          }
          int shiftIndex = carrotInventory.length;
          for (int i = 0; i < (carrotsBought.length); i++) {
            updatedCarrotsInventory[(i + shiftIndex)] = carrotsBought[i];
          }
          carrotInventory = updatedCarrotsInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Carrots");
        }
      } else if (!tastyVegetables.getAvailablity() && (carrotInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfCarrotsToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - carrotInventory.length);
          if (cashOnHand >= (numberOfCarrotsToBeBought * Carrot.COST_PRICE)) {
            Carrot[] carrotsBought = allYouCanEat.sellCarrots(numberOfCarrotsToBeBought);
            cashOnHand -= (carrotsBought.length * Carrot.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Carrot", carrotsBought.length);
            }
            Output.appendToLogFileAmountOfVegetablesPurchase(carrotsBought.length, "Carrot", "All You Can Eat",
                Carrot.COST_PRICE);
            itemsPurchased[2] += carrotsBought.length;
            Carrot[] updatedCarrotsInventory = new Carrot[carrotInventory.length
                + carrotsBought.length];
            for (int i = 0; i < carrotInventory.length; i++) {
              updatedCarrotsInventory[i] = carrotInventory[i];
            }
            int shiftIndex = carrotInventory.length;
            for (int i = 0; i < (carrotsBought.length); i++) {
              updatedCarrotsInventory[(i + shiftIndex)] = carrotsBought[i];
            }
            carrotInventory = updatedCarrotsInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Carrots");
          }
        }
      }
    }
  }

  private void buyCucumbers() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (tastyVegetables.getAvailablity()) {
        int numberOfCucumbersToBeBought = (MAXIMUM_NUMBER_OF_VEGETABLES - cucumberInventory.length);
        if (cashOnHand >= (numberOfCucumbersToBeBought * Cucumber.COST_PRICE)) {
          Cucumber[] cucumbersBought = tastyVegetables.sellCucumbers(numberOfCucumbersToBeBought);
          cashOnHand -= (cucumbersBought.length * Cucumber.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Tasty Vegetables", "Cucumber", cucumbersBought.length);
          }
          Output.appendToLogFileAmountOfVegetablesPurchase(cucumbersBought.length, "Cucumber", "Tasty Vegetables",
              Cucumber.COST_PRICE);
          itemsPurchased[3] += cucumbersBought.length;
          Cucumber[] updatedCucumbersInventory = new Cucumber[cucumberInventory.length
              + cucumbersBought.length];
          for (int i = 0; i < cucumberInventory.length; i++) {
            updatedCucumbersInventory[i] = cucumberInventory[i];
          }
          int shiftIndex = cucumberInventory.length;
          for (int i = 0; i < (cucumbersBought.length); i++) {
            updatedCucumbersInventory[(i + shiftIndex)] = cucumbersBought[i];
          }
          cucumberInventory = updatedCucumbersInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Cucumbers");
        }
      } else if (!tastyVegetables.getAvailablity() && (cucumberInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfCucumbersToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - cucumberInventory.length);
          if (cashOnHand >= (numberOfCucumbersToBeBought * Cucumber.COST_PRICE)) {
            Cucumber[] cucumbersBought = allYouCanEat.sellCucumbers(numberOfCucumbersToBeBought);
            cashOnHand -= (cucumbersBought.length * Cucumber.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Cucumber", cucumbersBought.length);
            }
            Output.appendToLogFileAmountOfVegetablesPurchase(cucumbersBought.length, "Cucumber", "All You Can Eat",
                Cucumber.COST_PRICE);
            itemsPurchased[3] += cucumbersBought.length;
            Cucumber[] updatedCucumbersInventory = new Cucumber[cucumberInventory.length
                + cucumbersBought.length];
            for (int i = 0; i < cucumberInventory.length; i++) {
              updatedCucumbersInventory[i] = cucumberInventory[i];
            }
            int shiftIndex = cucumberInventory.length;
            for (int i = 0; i < (cucumbersBought.length); i++) {
              updatedCucumbersInventory[(i + shiftIndex)] = cucumbersBought[i];
            }
            cucumberInventory = updatedCucumbersInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Cucumbers");
          }
        }
      }
    }
  }

  private void buyLettuces() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (tastyVegetables.getAvailablity()) {
        int numberOfLettucesToBeBought = (MAXIMUM_NUMBER_OF_VEGETABLES - lettuceInventory.length);
        if (cashOnHand >= (numberOfLettucesToBeBought * Lettuce.COST_PRICE)) {
          Lettuce[] lettucesBought = tastyVegetables.sellLettuces(numberOfLettucesToBeBought);
          cashOnHand -= (lettucesBought.length * Lettuce.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Tasty Vegetables", "Lettuce", lettucesBought.length);
          }
          Output.appendToLogFileAmountOfVegetablesPurchase(lettucesBought.length, "Lettuce", "Tasty Vegetables",
              Lettuce.COST_PRICE);
          itemsPurchased[4] += lettucesBought.length;
          Lettuce[] updatedLettucesInventory = new Lettuce[lettuceInventory.length
              + lettucesBought.length];
          for (int i = 0; i < lettuceInventory.length; i++) {
            updatedLettucesInventory[i] = lettuceInventory[i];
          }
          int shiftIndex = lettuceInventory.length;
          for (int i = 0; i < (lettucesBought.length); i++) {
            updatedLettucesInventory[(i + shiftIndex)] = lettucesBought[i];
          }
          lettuceInventory = updatedLettucesInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Lettuces");
        }
      } else if (!tastyVegetables.getAvailablity() && (lettuceInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfLettucesToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - lettuceInventory.length);
          if (cashOnHand >= (numberOfLettucesToBeBought * Lettuce.COST_PRICE)) {
            Lettuce[] lettucesBought = allYouCanEat.sellLettuces(numberOfLettucesToBeBought);
            cashOnHand -= (lettucesBought.length * Lettuce.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Lettuce", lettucesBought.length);
            }
            Output.appendToLogFileAmountOfVegetablesPurchase(lettucesBought.length, "Lettuce", "All You Can Eat",
                Lettuce.COST_PRICE);
            itemsPurchased[4] += lettucesBought.length;
            Lettuce[] updatedLettucesInventory = new Lettuce[lettuceInventory.length
                + lettucesBought.length];
            for (int i = 0; i < lettuceInventory.length; i++) {
              updatedLettucesInventory[i] = lettuceInventory[i];
            }
            int shiftIndex = lettuceInventory.length;
            for (int i = 0; i < (lettucesBought.length); i++) {
              updatedLettucesInventory[(i + shiftIndex)] = lettucesBought[i];
            }
            lettuceInventory = updatedLettucesInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Lettuces");
          }
        }
      }
    }
  }

  private void buyLimes() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (fruitsAreHere.getAvailablity()) {
        int numberOfLimesToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - limeInventory.length);
        if (cashOnHand >= (numberOfLimesToBeBought * Lime.COST_PRICE)) {
          Lime[] limesBought = fruitsAreHere.sellLimes(numberOfLimesToBeBought);
          cashOnHand -= (limesBought.length * Lime.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Fruits Are Here", "Lime", limesBought.length);
          }
          Output.appendToLogFileAmountOfFruitsPurchase(limesBought.length, "Lime", "Fruits Are Here",
              Lime.COST_PRICE);
          itemsPurchased[5] += limesBought.length;
          Lime[] updatedLimesInventory = new Lime[limeInventory.length
              + limesBought.length];
          for (int i = 0; i < limeInventory.length; i++) {
            updatedLimesInventory[i] = limeInventory[i];
          }
          int shiftIndex = limeInventory.length;
          for (int i = 0; i < (limesBought.length); i++) {
            updatedLimesInventory[(i + shiftIndex)] = limesBought[i];
          }
          limeInventory = updatedLimesInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Limes");
        }
      } else if (!fruitsAreHere.getAvailablity() && (limeInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfLimesToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - limeInventory.length);
          if (cashOnHand >= (numberOfLimesToBeBought * Lime.COST_PRICE)) {
            Lime[] limesBought = allYouCanEat.sellLimes(numberOfLimesToBeBought);
            cashOnHand -= (limesBought.length * Lime.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Lime", limesBought.length);
            }
            Output.appendToLogFileAmountOfFruitsPurchase(limesBought.length, "Lime", "All You Can Eat",
                Lime.COST_PRICE);
            itemsPurchased[5] += limesBought.length;
            Lime[] updatedLimesInventory = new Lime[limeInventory.length
                + limesBought.length];
            for (int i = 0; i < limeInventory.length; i++) {
              updatedLimesInventory[i] = limeInventory[i];
            }
            int shiftIndex = limeInventory.length;
            for (int i = 0; i < (limesBought.length); i++) {
              updatedLimesInventory[(i + shiftIndex)] = limesBought[i];
            }
            limeInventory = updatedLimesInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Limes");
          }
        }
      }
    }
  }

  private void buyMangoes() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (fruitsAreHere.getAvailablity()) {
        int numberOfMangoesToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - mangoInventory.length);
        if (cashOnHand >= (numberOfMangoesToBeBought * Mango.COST_PRICE)) {
          Mango[] mangoesBought = fruitsAreHere.sellMangoes(numberOfMangoesToBeBought);
          cashOnHand -= (mangoesBought.length * Mango.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Fruits Are Here", "Mango", mangoesBought.length);
          }
          Output.appendToLogFileAmountOfFruitsPurchase(mangoesBought.length, "Mango", "Fruits Are Here",
              Mango.COST_PRICE);
          itemsPurchased[6] += mangoesBought.length;
          Mango[] updatedMangoesInventory = new Mango[mangoInventory.length
              + mangoesBought.length];
          for (int i = 0; i < mangoInventory.length; i++) {
            updatedMangoesInventory[i] = mangoInventory[i];
          }
          int shiftIndex = mangoInventory.length;
          for (int i = 0; i < (mangoesBought.length); i++) {
            updatedMangoesInventory[(i + shiftIndex)] = mangoesBought[i];
          }
          mangoInventory = updatedMangoesInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Mangoes");
        }
      } else if (!fruitsAreHere.getAvailablity() && (mangoInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfMangoesToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - mangoInventory.length);
          if (cashOnHand >= (numberOfMangoesToBeBought * Mango.COST_PRICE)) {
            Mango[] mangoesBought = allYouCanEat.sellMangoes(numberOfMangoesToBeBought);
            cashOnHand -= (mangoesBought.length * Mango.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Mango", mangoesBought.length);
            }
            Output.appendToLogFileAmountOfFruitsPurchase(mangoesBought.length, "Mango", "All You Can Eat",
                Mango.COST_PRICE);
            itemsPurchased[6] += mangoesBought.length;
            Mango[] updatedMangoesInventory = new Mango[mangoInventory.length
                + mangoesBought.length];
            for (int i = 0; i < mangoInventory.length; i++) {
              updatedMangoesInventory[i] = mangoInventory[i];
            }
            int shiftIndex = mangoInventory.length;
            for (int i = 0; i < (mangoesBought.length); i++) {
              updatedMangoesInventory[(i + shiftIndex)] = mangoesBought[i];
            }
            mangoInventory = updatedMangoesInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Mangoes");
          }
        }
      }
    }
  }

  private void buyOnions() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (tastyVegetables.getAvailablity()) {
        int numberOfOnionsToBeBought = (MAXIMUM_NUMBER_OF_VEGETABLES - onionInventory.length);
        if (cashOnHand >= (numberOfOnionsToBeBought * Onion.COST_PRICE)) {
          Onion[] onionsBought = tastyVegetables.sellOnions(numberOfOnionsToBeBought);
          cashOnHand -= (onionsBought.length * Onion.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Tasty Vegetables", "Lime", onionsBought.length);
          }
          Output.appendToLogFileAmountOfVegetablesPurchase(onionsBought.length, "Onion", "Tasty Vegetables",
              Onion.COST_PRICE);
          itemsPurchased[7] += onionsBought.length;
          Onion[] updatedOnionsInventory = new Onion[onionInventory.length
              + onionsBought.length];
          for (int i = 0; i < onionInventory.length; i++) {
            updatedOnionsInventory[i] = onionInventory[i];
          }
          int shiftIndex = onionInventory.length;
          for (int i = 0; i < (onionsBought.length); i++) {
            updatedOnionsInventory[(i + shiftIndex)] = onionsBought[i];
          }
          onionInventory = updatedOnionsInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Onions");
        }
      } else if (!tastyVegetables.getAvailablity() && (onionInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfOnionsToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - onionInventory.length);
          if (cashOnHand >= (numberOfOnionsToBeBought * Onion.COST_PRICE)) {
            Onion[] onionsBought = allYouCanEat.sellOnions(numberOfOnionsToBeBought);
            cashOnHand -= (onionsBought.length * Onion.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Onions", onionsBought.length);
            }
            Output.appendToLogFileAmountOfVegetablesPurchase(onionsBought.length, "Onion", "All You Can Eat",
                Onion.COST_PRICE);
            itemsPurchased[7] += onionsBought.length;
            Onion[] updatedOnionsInventory = new Onion[onionInventory.length
                + onionsBought.length];
            for (int i = 0; i < onionInventory.length; i++) {
              updatedOnionsInventory[i] = onionInventory[i];
            }
            int shiftIndex = onionInventory.length;
            for (int i = 0; i < (onionsBought.length); i++) {
              updatedOnionsInventory[(i + shiftIndex)] = onionsBought[i];
            }
            onionInventory = updatedOnionsInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Onions");
          }
        }
      }
    }
  }

  private void buyParsleys() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (tastyVegetables.getAvailablity()) {
        int numberOfParsleysToBeBought = (MAXIMUM_NUMBER_OF_VEGETABLES - parsleyInventory.length);
        if (cashOnHand >= (numberOfParsleysToBeBought * Parsley.COST_PRICE)) {
          Parsley[] parsleysBought = tastyVegetables.sellParsleys(numberOfParsleysToBeBought);
          cashOnHand -= (parsleysBought.length * Parsley.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Tasty Vegetables", "Parsley", parsleysBought.length);
          }
          Output.appendToLogFileAmountOfVegetablesPurchase(parsleysBought.length, "Parsley", "Tasty Vegetables",
              Parsley.COST_PRICE);
          itemsPurchased[8] += parsleysBought.length;
          Parsley[] updatedParsleysInventory = new Parsley[parsleyInventory.length
              + parsleysBought.length];
          for (int i = 0; i < parsleyInventory.length; i++) {
            updatedParsleysInventory[i] = parsleyInventory[i];
          }
          int shiftIndex = parsleyInventory.length;
          for (int i = 0; i < (parsleysBought.length); i++) {
            updatedParsleysInventory[(i + shiftIndex)] = parsleysBought[i];
          }
          parsleyInventory = updatedParsleysInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Parsleys");
        }
      } else if (!tastyVegetables.getAvailablity() && (parsleyInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfParsleysToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - parsleyInventory.length);
          if (cashOnHand >= (numberOfParsleysToBeBought * Parsley.COST_PRICE)) {
            Parsley[] parsleysBought = allYouCanEat.sellParsleys(numberOfParsleysToBeBought);
            cashOnHand -= (parsleysBought.length * Parsley.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Parsley", parsleysBought.length);
            }
            Output.appendToLogFileAmountOfVegetablesPurchase(parsleysBought.length, "Parsley", "All You Can Eat",
                Parsley.COST_PRICE);
            itemsPurchased[8] += parsleysBought.length;
            Parsley[] updatedParsleysInventory = new Parsley[parsleyInventory.length
                + parsleysBought.length];
            for (int i = 0; i < parsleyInventory.length; i++) {
              updatedParsleysInventory[i] = parsleyInventory[i];
            }
            int shiftIndex = parsleyInventory.length;
            for (int i = 0; i < (parsleysBought.length); i++) {
              updatedParsleysInventory[(i + shiftIndex)] = parsleysBought[i];
            }
            parsleyInventory = updatedParsleysInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Parsleys");
          }
        }
      }
    }
  }

  private void buyWatermelons() throws Exception {
    if (cashOnHand <= 0) {
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (fruitsAreHere.getAvailablity()) {
        int numberOfWatermelonsToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - watermelonInventory.length);
        if (cashOnHand >= (numberOfWatermelonsToBeBought * Watermelon.COST_PRICE)) {
          Watermelon[] watermelonsBought = fruitsAreHere.sellWatermelons(numberOfWatermelonsToBeBought);
          cashOnHand -= (watermelonsBought.length * Watermelon.COST_PRICE);
          if (verbose) {
            Print.verboseTotalItemsPurchased("Fruits Are Here", "Watermelon", watermelonsBought.length);
          }
          Output.appendToLogFileAmountOfFruitsPurchase(watermelonsBought.length, "Watermelon", "Fruits Are Here",
              Watermelon.COST_PRICE);
          itemsPurchased[9] += watermelonsBought.length;
          Watermelon[] updatedWatermelonsInventory = new Watermelon[watermelonInventory.length
              + watermelonsBought.length];
          for (int i = 0; i < watermelonInventory.length; i++) {
            updatedWatermelonsInventory[i] = watermelonInventory[i];
          }
          int shiftIndex = watermelonInventory.length;
          for (int i = 0; i < (watermelonsBought.length); i++) {
            updatedWatermelonsInventory[(i + shiftIndex)] = watermelonsBought[i];
          }
          watermelonInventory = updatedWatermelonsInventory;
        } else {
          Output.appendToLogFile("NotEnoughFunds: To Buy Parsleys");
        }
      } else if (!fruitsAreHere.getAvailablity() && (watermelonInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfWatermelonsToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - watermelonInventory.length);
          if (cashOnHand >= (numberOfWatermelonsToBeBought * Watermelon.COST_PRICE)) {
            Watermelon[] watermelonsBought = allYouCanEat.sellWatermelons(numberOfWatermelonsToBeBought);
            cashOnHand -= (watermelonsBought.length * Watermelon.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Watermelon", watermelonsBought.length);
            }
            Output.appendToLogFileAmountOfFruitsPurchase(watermelonsBought.length, "Watermelon", "All You Can Eat",
                Watermelon.COST_PRICE);
            itemsPurchased[9] += watermelonsBought.length;
            Watermelon[] updatedWatermelonsInventory = new Watermelon[watermelonInventory.length
                + watermelonsBought.length];
            for (int i = 0; i < watermelonInventory.length; i++) {
              updatedWatermelonsInventory[i] = watermelonInventory[i];
            }
            int shiftIndex = watermelonInventory.length;
            for (int i = 0; i < (watermelonsBought.length); i++) {
              updatedWatermelonsInventory[(i + shiftIndex)] = watermelonsBought[i];
            }
            watermelonInventory = updatedWatermelonsInventory;
          } else {
            Output.appendToLogFile("NotEnoughFunds: To Buy Parsleys");
          }
        }
      }
    }
  }

  private void calculateProfit() {
    double totalPurchases = 0;
    double totalFruitSales = 0;
    double totalVegetableSales = 0;
    double totalCostOfSpolitItems = 0;
    for (int index = 0; index < NUMBER_OF_TYPES_OF_ITEMS; index++) {
      switch (index) {
        case 0:
          totalPurchases += itemsPurchased[index] * Avocado.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Avocado.SELLING_PRICE;
          break;
        case 1:
          totalPurchases += itemsPurchased[index] * Banana.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Banana.SELLING_PRICE;
          break;
        case 2:
          totalPurchases += itemsPurchased[index] * Carrot.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Carrot.SELLING_PRICE;
          break;
        case 3:
          totalPurchases += itemsPurchased[index] * Cucumber.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Cucumber.SELLING_PRICE;
          break;
        case 4:
          totalPurchases += itemsPurchased[index] * Lettuce.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Lettuce.SELLING_PRICE;
          break;
        case 5:
          totalPurchases += itemsPurchased[index] * Lime.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Lime.SELLING_PRICE;
          break;
        case 6:
          totalPurchases += itemsPurchased[index] * Mango.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Mango.SELLING_PRICE;
          break;
        case 7:
          totalPurchases += itemsPurchased[index] * Onion.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Onion.SELLING_PRICE;
          break;
        case 8:
          totalPurchases += itemsPurchased[index] * Parsley.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Parsley.SELLING_PRICE;
          break;
        case 9:
          totalPurchases += itemsPurchased[index] * Watermelon.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Watermelon.SELLING_PRICE;
          break;

        default:
          break;
      }
    }

    for (int index = 0; index < fruitsSold.length; index++) {
      switch (index) {
        case 0:
          totalFruitSales += fruitsSold[index] * Avocado.SELLING_PRICE;
          break;
        case 1:
          totalFruitSales += fruitsSold[index] * Banana.SELLING_PRICE;
          break;
        case 2:
          totalFruitSales += fruitsSold[index] * Lime.SELLING_PRICE;
          break;
        case 3:
          totalFruitSales += fruitsSold[index] * Mango.SELLING_PRICE;
          break;
        case 4:
          totalFruitSales += fruitsSold[index] * Watermelon.SELLING_PRICE;
          break;

        default:
          break;
      }
    }

    for (int index = 0; index < vegetablesSold.length; index++) {
      switch (index) {
        case 0:
          totalVegetableSales += vegetablesSold[index] * Carrot.SELLING_PRICE;
          break;
        case 1:
          totalVegetableSales += vegetablesSold[index] * Cucumber.SELLING_PRICE;
          break;
        case 2:
          totalVegetableSales += vegetablesSold[index] * Lettuce.SELLING_PRICE;
          break;
        case 3:
          totalVegetableSales += vegetablesSold[index] * Onion.SELLING_PRICE;
          break;
        case 4:
          totalVegetableSales += vegetablesSold[index] * Parsley.SELLING_PRICE;
          break;

        default:
          break;
      }
    }

    profit += ((totalFruitSales + totalVegetableSales)
        - (totalPurchases + totalCostOfSpolitItems + lossesCausedbyRandomEvent));
    cashOnHand += profit;
  }

  private void reset() {
    for (int i = 0; i < NUMBER_OF_TYPES_OF_ITEMS; i++) {
      itemsPurchased[i] = 0;
      numberOfSpoiltItems[i] = 0;
    }
    for (int i = 0; i < 5; i++) {
      vegetablesSold[i] = 0;
      fruitsSold[i] = 0;
    }
    lossesCausedbyRandomEvent = 0;
  }

  public void setTotalIterations(int iter) {
    if (iter >= 0) {
      totalIterations = iter;
    }
  }

}