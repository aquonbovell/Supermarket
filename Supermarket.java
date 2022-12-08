import java.util.ArrayList;
import java.util.Iterator;
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
  // holds the losses that the random event caused
  private int lossesCausedbyRandomEvent;
  // defines the starting amount that the Supermarket initially has on hand
  private double cashOnHand = 4640_000;
  // holds the total of each fruit the customer buys per cycle
  private int[] itemsSold;
  // holds the total of each item the Supermarket buys per cycle
  private int[] itemsPurchased;
  // holds the total of each item that has spoilt per cycle
  private int[] numberOfSpoiltItems;
  // holds the customers that the Supermarket has
  private Customer[] customers;
  // creates a fruits are here reference
  private FruitsAreHere fruitsAreHere;
  // creates a tasty vegetables reference
  private TastyVegetables tastyVegetables;
  // creates a all You can eat reference
  private AllYouCanEat allYouCanEat;
  // hold all the avocados the Supermarket has at any time
  private ArrayList<Fruit> avocadosInventory;
  private ArrayList<Fruit> bananasInventory;
  private ArrayList<Vegetable> carrotsInventory;
  private ArrayList<Vegetable> cucumbersInventory;
  private ArrayList<Vegetable> lettucesInventory;
  private ArrayList<Fruit> limesInventory;
  private ArrayList<Fruit> mangoesInventory;
  private ArrayList<Vegetable> onionsInventory;
  private ArrayList<Vegetable> parsleysInventory;
  private ArrayList<Fruit> watermelonsInventory;

  /*
   * Constructor - This function takes no parameters since it is the default
   * constructor for the Supermarket Class. The constructor therefore creates
   * randomly generated amounts for each item the Supermarket has availalbe and
   * initalises those arrays to create new items. Also, the customers created with
   * a lenght of 10 since we assumed that 10 customers come in to the Supermarket
   * to buy items and initialise each slot to a new instance of Customer and
   * creates both fruitsSold and vegetablesSold to have a lenght of 5 since there
   * are 5 types of Fruit and Vegetable and create an instance of each different
   * type of vendor and creates both itemsPurchased and numberOfSpoiltItems to
   * have lenght of 10 since there 10 items in total.
   * 
   */
  Supermarket() {
    // initalise the total amount of iteration to the default number (50)
    totalIterations = DEFAULT_TOTAL_ITERATIONS;
    // creates 10 customer references
    customers = new Customer[NUMBER_OF_CUSTOMERS];
    // assign each customer a new instance of Customer
    avocadosInventory = new ArrayList<>();
    bananasInventory = new ArrayList<>();
    limesInventory = new ArrayList<>();
    mangoesInventory = new ArrayList<>();
    watermelonsInventory = new ArrayList<>();
    carrotsInventory = new ArrayList<>();
    lettucesInventory = new ArrayList<>();
    cucumbersInventory = new ArrayList<>();
    parsleysInventory = new ArrayList<>();
    onionsInventory = new ArrayList<>();
    initCustomers();
    // initalise all the items of the Supermarket inventory to have a new instance
    // of each item
    initInventory();
    // creates 5 references of int
    itemsSold = new int[NUMBER_OF_TYPES_OF_ITEMS];
    // creates 10 references of int
    itemsPurchased = new int[NUMBER_OF_TYPES_OF_ITEMS];
    // creates 10 references of int
    numberOfSpoiltItems = new int[NUMBER_OF_TYPES_OF_ITEMS];

    // initalise fruitsAreHere to have a new instance of FruitsAreHere
    fruitsAreHere = new FruitsAreHere();
    // initalise tastyVegetables to have a new instance of TastyVegetables
    tastyVegetables = new TastyVegetables();
    // initalise allYouCanEat to have a new instance of AllYouCanEat
    allYouCanEat = new AllYouCanEat();
  }

  /*
   * The run method the entry to the start of the actual Supermarket Simulation
   * and runs the total amount of iterations specified by the totalIterations
   * field the display the total amount of cycles that were carried out, the
   * profit the Supermarket made and the profit the vendors made in total. The
   * function takes no parameters and returns no vaule. It is public so the
   * SimulationMain class can call it.
   */
  public void run() {
    // initalise the carried out amount to 0
    totalPerformedIterations = 0;
    // creates a log file with a title
    Output.createLogFile();
    try {
      // loops through the total amount of iterations specified by the totalIterations
      // field
      for (int i = 0; i < totalIterations; i++) {
        // output "Day" along with current cycle number and offsetted by 1
        Output.appendToLogFile(new String("Day :" + (1 + i)));
        // increase the total amount of cycles carried out
        ++totalPerformedIterations;
        // check if the random event can occur and execute a random event every ten
        // cycles
        if ((i + 1) % 10 == 0 && i != 0) {
          // determines random event
          randomEvent();
        }
        // decrement spoilt value for all items
        decreaseInventorySpoiltValue();
        try {
          // sell items to customers
          if (Customer.willPurchase()) {
            Random rand = new Random();
            int items = 0;
            for (int index = 0; index < customers.length; index++) {
              switch (rand.nextInt(customers.length)) {
                case 0:
                  items = customers[index].itemsToBePurchased();
                  sellFruits(items, avocadosInventory);
                  break;
                case 1:
                  items = customers[index].itemsToBePurchased();
                  sellFruits(items, bananasInventory);
                  break;
                case 2:
                  items = customers[index].itemsToBePurchased();
                  sellFruits(items, limesInventory);
                  break;
                case 3:
                  items = customers[index].itemsToBePurchased();
                  sellFruits(items, mangoesInventory);
                  break;
                case 4:
                  items = customers[index].itemsToBePurchased();
                  sellFruits(items, watermelonsInventory);
                  break;
                case 5:
                  items = customers[index].itemsToBePurchased();
                  sellVegetables(items, carrotsInventory);
                  break;
                case 6:
                  items = customers[index].itemsToBePurchased();
                  sellVegetables(items, cucumbersInventory);
                  break;
                case 7:
                  items = customers[index].itemsToBePurchased();
                  sellVegetables(items, lettucesInventory);
                  break;
                case 8:
                  items = customers[index].itemsToBePurchased();
                  sellVegetables(items, onionsInventory);
                  break;
                case 9:
                  items = customers[index].itemsToBePurchased();
                  sellVegetables(items, parsleysInventory);
                  break;
                default:
                  break;
              }
            }
            if (verbose) {
              int totalFruitsPurchsedByCustomer = 0;
              for (int index = 0; index < itemsSold.length; index++) {
                totalFruitsPurchsedByCustomer += itemsSold[index];
              }
              int totalVegetablesPurchsedByCustomer = 0;
              Print.verboseTotalAmountCustomerPurchase(totalFruitsPurchsedByCustomer,
                  totalVegetablesPurchsedByCustomer);
            }

            // catch the FruitNotAvailable and VegetableNotAvailable Exception and
            // prevent
            // the program from terminating
          }
        } catch (Exception e) {
        }
        // remove all spoilt items from the Supermarket inventory
        removeSpoiltFruits(avocadosInventory);
        removeSpoiltFruits(bananasInventory);
        removeSpoiltFruits(limesInventory);
        removeSpoiltFruits(mangoesInventory);
        removeSpoiltFruits(watermelonsInventory);
        removeSpoiltVegetables(carrotsInventory);
        removeSpoiltVegetables(cucumbersInventory);
        removeSpoiltVegetables(lettucesInventory);
        removeSpoiltVegetables(onionsInventory);
        removeSpoiltVegetables(parsleysInventory);
        // restock on avocados in the Supermarket
        if (avocadosInventory.size() < MAXIMUM_NUMBER_OF_FRUITS) {
          buyFruits(avocadosInventory, FruitType.Avocado);
        } // restock on bananas in the Supermarket
        if (bananasInventory.size() < MAXIMUM_NUMBER_OF_FRUITS) {
          buyFruits(bananasInventory, FruitType.Banana);
        } // restock on carrots in the Supermarket
        if (carrotsInventory.size() < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyVegetables(carrotsInventory, VegetableType.Carrot);
        } // restock on cucumbers in the Supermarket
        if (cucumbersInventory.size() < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyVegetables(cucumbersInventory, VegetableType.Cucumber);
        } // restock on lettuces in the Supermarket
        if (lettucesInventory.size() < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyVegetables(lettucesInventory, VegetableType.Lettuce);
        } // restock on limes in the Supermarket
        if (limesInventory.size() < MAXIMUM_NUMBER_OF_FRUITS) {
          buyFruits(limesInventory, FruitType.Lime);
        } // restock on mangoes in the Supermarket
        if (mangoesInventory.size() < MAXIMUM_NUMBER_OF_FRUITS) {
          buyFruits(mangoesInventory, FruitType.Mango);
        } // restock on onions in the Supermarket
        if (onionsInventory.size() < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyVegetables(onionsInventory, VegetableType.Onion);
        } // restock on parsleys in the Supermarket
        if (parsleysInventory.size() < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyVegetables(parsleysInventory, VegetableType.Parsley);
        } // restock on watermelons in the Supermarket
        if (watermelonsInventory.size() < MAXIMUM_NUMBER_OF_FRUITS) {
          buyFruits(watermelonsInventory, FruitType.Watermelon);
        }
        // reset the customer's availablity for the next day
        Customer.reset();
        // restock the vendors inventory for the next day
        fruitsAreHere.restock();
        allYouCanEat.restock();
        tastyVegetables.restock();
        // calcuates the profit the Supermarket for each cycle
        calculateProfit();
        // set the fruitsSold, vegetablesSolde, itemsPurchased and
        // numberOfSpoiltItems
        // array back to zero for the next day
        reset();
      }
      // catches the Supermarket Runs Out Of Money Exception to terminate the
      // program
    } catch (Exception e) {
    }
    // display the number of iterations that where carried out
    System.out.println("Total number of cycles performed: " +
        totalPerformedIterations);
    // display total profit the supermarket made at the end of all the cycles
    System.out.printf("Total supermarket profit: $%.2f", profit);
    // display the total profit the vendors made from sale of items to the
    // Supermarket
    System.out.println();
    System.out.printf("Total vendor profit: $%.2f", (allYouCanEat.getProfit() + fruitsAreHere.getProfit() +
        tastyVegetables.getProfit()));
    System.out.println();
  }

  /*
   * This function turns on the verbose mode for the run method. It take no
   * parameters and have to return values.
   */
  public void turnOnVerbose() {
    // set the verbose field to true since the default mode is false
    verbose = true;
  }

  /*
   * This function initialises each index in the customers array to a new
   * instance
   * of Customer. There are no parameters and return no values.
   */
  private void initCustomers() {
    // loops through the customers array
    for (int index = 0; index < customers.length; index++) {
      // create a new instance of Customer and added it to the customers array
      customers[index] = new Customer();
    }
  }

  /*
   * This function initialise a type of item to have a new instance of each type
   * of Fruit or Vegetable. There are no parameters and there are no values to
   * return
   */
  private void initInventory() {
    Random rand = new Random();
    int numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      avocadosInventory.add(new Avocado());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      bananasInventory.add(new Banana());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      limesInventory.add(new Lime());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      mangoesInventory.add(new Mango());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      watermelonsInventory.add(new Watermelon());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      carrotsInventory.add(new Carrot());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      cucumbersInventory.add(new Cucumber());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      lettucesInventory.add(new Lettuce());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      onionsInventory.add(new Onion());
    }
    numberOfItems = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfItems; counter++) {
      parsleysInventory.add(new Parsley());
    }
  }

  /*
   * This function decreased the spoilt value of each type of item of Fruit or
   * Vegetable. There are no parameters and there are no values to return
   */
  private void decreaseInventorySpoiltValue() {
    avocadosInventory.forEach((avocado) -> avocado.decreaseSpoiltValue());
    bananasInventory.forEach((banana) -> banana.decreaseSpoiltValue());
    carrotsInventory.forEach((carrot) -> carrot.decreaseSpoiltValue());
    cucumbersInventory.forEach((cucumber) -> cucumber.decreaseSpoiltValue());
    lettucesInventory.forEach((lettuce) -> lettuce.decreaseSpoiltValue());
    limesInventory.forEach((lime) -> lime.decreaseSpoiltValue());
    onionsInventory.forEach((onion) -> onion.decreaseSpoiltValue());
    watermelonsInventory.forEach((watermelon) -> watermelon.decreaseSpoiltValue());
    watermelonsInventory.forEach((watermelon) -> watermelon.decreaseSpoiltValue());
  }

  /*
   * This function determines a random event and execute that event. The
   * function
   * takes no parameters and returns no values.
   */
  private void randomEvent() {
    // create a random number generator to generate a random event and other
    // random
    // numbers that the random event uses
    Random rand = new Random();
    switch (rand.nextInt(5)) {
      case 0:
        // added the random event to the log file
        Output.appendToLogFile("Fridge Breaks Down");
        // generate a cost that the Supermarket must pay
        int cost = rand.nextInt(101);
        // addes the cost to the field lossesCausedbyRandomEvent
        lossesCausedbyRandomEvent += cost;
        // if the verbose mode is on display the random even name and the cost
        if (verbose) {
          System.out.println("Fridge Breaks Down: Supermarket pays $" + cost + ".00");
        }
        break;
      case 1:
        // added the random event to the log file
        Output.appendToLogFile("Electricity Goes Off");
        // generate the amount of hours the Supermarket goes off
        int numberOfHours = rand.nextInt(5) + 1;
        // loops throught the amount of hours the electricity and spoil one percent
        // the
        // total type of Fruit and Vegetable
        for (int i = 0; i < numberOfHours; i++) {
          spoilFruits(avocadosInventory);
          spoilFruits(bananasInventory);
          spoilVegetables(carrotsInventory);
          spoilVegetables(cucumbersInventory);
          spoilVegetables(lettucesInventory);
          spoilFruits(limesInventory);
          spoilFruits(mangoesInventory);
          spoilVegetables(onionsInventory);
          spoilVegetables(parsleysInventory);
          spoilFruits(watermelonsInventory);
        }
        break;
      case 2:
        // generate a randon number to select any of the three vendor
        int vendorSelection = rand.nextInt(3);
        if (vendorSelection == 0) {
          // set the field isAvailable of fruitsAreHere to false
          fruitsAreHere.isNotAvailable();
          // append the message to the log file
          Output.appendToLogFile("Fruits Are Here Does Not Deliver The Goods");
          if (verbose) {
            // display the message verbosely to the command line
            System.out.println("Vendor does not deliver the goods: Fruits Are Here");
          }
        } else if (vendorSelection == 1) {
          // set the field isAvailable of tastyVegetables to false
          tastyVegetables.isNotAvailable();
          // append the message to the log file
          Output.appendToLogFile("Tasty Vegetables Does Not Deliver The Goods");
          if (verbose) {
            // display the message verbosely to the command line
            System.out.println("Vendor does not deliver the goods: Tasty Vegetables");
          }
        } else if (vendorSelection == 2) {
          // set the field isAvailable of allYouCanEat to false
          allYouCanEat.isNotAvailable();
          // append the message to the log file
          Output.appendToLogFile("All You Can Eat Does Not Deliver The Goods");
          if (verbose) {
            // display the message verbosely to the command line
            System.out.println("Vendor does not deliver the goods: All You Can Eat");
          }
        }
        break;
      case 3:
        // append the message to the log file
        Output.appendToLogFile("Items spoil faster than expected");
        // generate a random percentage to spoil the items faster
        double percentage = (rand.nextDouble(9) + 1);
        spoilFruitsFaster(avocadosInventory, percentage);
        spoilFruitsFaster(bananasInventory, percentage);
        spoilVegetablesFaster(carrotsInventory, percentage);
        spoilVegetablesFaster(cucumbersInventory, percentage);
        spoilVegetablesFaster(lettucesInventory, percentage);
        spoilFruitsFaster(limesInventory, percentage);
        spoilFruitsFaster(mangoesInventory, percentage);
        spoilVegetablesFaster(onionsInventory, percentage);
        spoilVegetablesFaster(parsleysInventory, percentage);
        spoilFruitsFaster(watermelonsInventory, percentage);
        break;
      case 4:
        // set the willPurchase field of the Customers class to false
        Customer.willNotPurchase();
        // append the message to the log file
        Output.appendToLogFile("No purchases are made: Customers do not buy anything from the supermarket.");
        if (verbose) {
          // display the message verbosely to the command line
          System.out.println("No purchases are made: Customers do not buy anything from the supermarket.");
        }
        break;
      default:
        break;
    }
  }

  private void spoilVegetables(ArrayList<Vegetable> inventory) {
    int numberOfUnsploitItems = (int) inventory.size();
    // calculate one percent of the total amount of unspoilt fruits
    int numberOfItemsToSpoil = (int) Math.ceil((0.01 * numberOfUnsploitItems));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out.println("Electricity Goes Off: " + numberOfItemsToSpoil + "items have spoilt");
    }
    inventory.forEach((item) -> {
      int numberOfSpoiltItems = 0;
      if (numberOfSpoiltItems < numberOfItemsToSpoil && item.getSpoiltValue() > 0) {
        item.spoil();
        numberOfSpoiltItems++;
      }
    });
  }

  /*
   * This function spoils one percent of the available items of type Fruit to
   * spoil. The function takes an array of type Fruit as a parameter and returns
   * no values.
   */
  private void spoilFruits(ArrayList<Fruit> inventory) {
    int numberOfUnsploitItems = (int) inventory.size();
    // calculate one percent of the total amount of unspoilt fruits
    int numberOfItemsToSpoil = (int) Math.ceil((0.01 * numberOfUnsploitItems));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out.println("Electricity Goes Off: " + numberOfItemsToSpoil + "items have spoilt");
    }
    inventory.forEach((item) -> {
      int numberOfSpoiltItems = 0;
      if (numberOfSpoiltItems < numberOfItemsToSpoil && item.getSpoiltValue() > 0) {
        item.spoil();
        numberOfSpoiltItems++;
      }
    });
  }

  /*
   * This function spoils a percentage of the available items of type Fruit to
   * spoil. The function takes an array of type Fruit as a parameter and a
   * percentage of type double and returns no values.
   */
  private void spoilFruitsFaster(ArrayList<Fruit> inventory, double percentage) {
    // determines the an amount base on the amount of items to spoil
    int numberOfFruitsToSpoil = (int) Math.ceil(((percentage / 100.00) *
        inventory.size()));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out.println("Items spoil faster than expected: " +
          numberOfFruitsToSpoil + " items have spoilt.");
    }
    // a counter to determine how many of the Fruit item that have spoilt
    inventory.forEach((item) -> {
      int numberOfSpoiltFruits = 0;
      if (numberOfSpoiltFruits < numberOfFruitsToSpoil && item.getSpoiltValue() > 0) {
        item.spoil();
        numberOfSpoiltFruits++;
      }
    });
  }

  /*
   * This function spoils a percentage of the available items of type Fruit to
   * spoil. The function takes an array of type Fruit as a parameter and a
   * percentage of type double and returns no values.
   */
  private void spoilVegetablesFaster(ArrayList<Vegetable> inventory, double percentage) {
    // determines the an amount base on the amount of items to spoil
    int numberOfVegetablesToSpoil = (int) Math.ceil(((percentage / 100.00) *
        inventory.size()));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out.println("Items spoil faster than expected: " +
          numberOfVegetablesToSpoil + " items have spoilt.");
    }
    // a counter to determine how many of the Vegetable item that have spoilt
    inventory.forEach((item) -> {
      int numberOfSpoiltVegetables = 0;
      if (numberOfSpoiltVegetables < numberOfVegetablesToSpoil && item.getSpoiltValue() > 0) {
        item.spoil();
        numberOfSpoiltVegetables++;
      }
    });
  }

  /*
   * This function sells avocados to a customer and decreases the inventory for
   * the avocados inventory and throw a FruitNotAvailable Exception if there are
   * no avocados available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellFruits(int amount, ArrayList<Fruit> inventory) {
    // counter for the number of unspoilt and spoilt fruits
    int numberOfUnsploitItems = (int) inventory.stream().filter((item) -> item.getSpoiltValue() > 0).count();
    Iterator<Fruit> itr = inventory.iterator();
    // Holds true till there is single element
    // remaining in the object
    if (numberOfUnsploitItems > amount) {
      while (itr.hasNext()) {

        // Remove elements smaller than 10 using
        // Iterator.remove()
        Fruit item = itr.next();
        if (item.getSpoiltValue() > 0)
          itr.remove();
      }
    } else {
      inventory.removeAll(inventory);
    }
  }

  /*
   * This function sells avocados to a customer and decreases the inventory for
   * the avocados inventory and throw a FruitNotAvailable Exception if there are
   * no avocados available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellVegetables(int amount, ArrayList<Vegetable> inventory) {
    // counter for the number of unspoilt and spoilt fruits
    int numberOfUnsploitItems = (int) inventory.stream().filter((item) -> item.getSpoiltValue() > 0).count();
    Iterator<Vegetable> itr = inventory.iterator();
    // Holds true till there is single element
    // remaining in the object
    if (numberOfUnsploitItems > amount) {
      while (itr.hasNext()) {

        // Remove elements smaller than 10 using
        // Iterator.remove()
        Vegetable item = itr.next();
        if (item.getSpoiltValue() > 0)
          itr.remove();
      }
    } else {
      inventory.removeAll(inventory);
    }
  }

  /*
   * This function determines the amount of avocados that have spoilt and
   * removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltFruits(ArrayList<Fruit> inventory) {
    Iterator<Fruit> itr = inventory.iterator();
    // Holds true till there is single element
    // remaining in the object
    while (itr.hasNext()) {
      // Remove elements smaller than 10 using
      // Iterator.remove()
      Fruit item = itr.next();
      if (item.getSpoiltValue() < 0)
        itr.remove();
    }
    // // display the message verbosely
    // Print.verboseSpoiltFruit("lime", sum);
    // }
  }

  private void removeSpoiltVegetables(ArrayList<Vegetable> inventory) {
    Iterator<Vegetable> itr = inventory.iterator();
    // Holds true till there is single element
    // remaining in the object
    while (itr.hasNext()) {
      // Remove elements smaller than 10 using
      // Iterator.remove()
      Vegetable item = itr.next();
      if (item.getSpoiltValue() < 0)
        itr.remove();
    }
    // // display the message verbosely
    // Print.verboseSpoiltFruit("lime", sum);
    // }
  }

  /*
   * This function determines the amount of avocados the Supermarket can be and
   * throw a Supermarket Runs Out Of Money if the Supermarket trys to buy if
   * there
   * is no money left.
   * The function no parameters and returns no value.
   */
  private void buyFruits(ArrayList<Fruit> inventory, FruitType type) throws Exception {
    if (cashOnHand <= 0) {
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (fruitsAreHere.getAvailablity()) {
        int numberOfItemsToBeBought = (MAXIMUM_NUMBER_OF_FRUITS -
            inventory.size());
        double cost = 0;
        switch (type) {
          case Avocado:
            cost = Avocado.COST_PRICE;
          case Banana:
            cost = Banana.COST_PRICE;
          case Lime:
            cost = Lime.COST_PRICE;
          case Mango:
            cost = Mango.COST_PRICE;
          case Watermelon:
            cost = Watermelon.COST_PRICE;
          default:
            break;
        }
        if (cashOnHand >= (numberOfItemsToBeBought * cost)) {
          ArrayList<Fruit> itemsBought = fruitsAreHere.orderFruits(numberOfItemsToBeBought, type);
          switch (type) {
            case Avocado:
              itemsBought.forEach((item) -> avocadosInventory.add(item));
              cashOnHand -= (itemsBought.size() * Avocado.COST_PRICE);
              itemsPurchased[0] += itemsBought.size();
            case Banana:
              itemsBought.forEach((item) -> bananasInventory.add(item));
              cashOnHand -= (itemsBought.size() * Banana.COST_PRICE);
              itemsPurchased[1] += itemsBought.size();
            case Lime:
              itemsBought.forEach((item) -> limesInventory.add(item));
              cashOnHand -= (itemsBought.size() * Lime.COST_PRICE);
              itemsPurchased[2] += itemsBought.size();
            case Mango:
              itemsBought.forEach((item) -> mangoesInventory.add(item));
              cashOnHand -= (itemsBought.size() * Mango.COST_PRICE);
              itemsPurchased[3] += itemsBought.size();
            case Watermelon:
              itemsBought.forEach((item) -> watermelonsInventory.add(item));
              cashOnHand -= (itemsBought.size() * Watermelon.COST_PRICE);
              itemsPurchased[4] += itemsBought.size();
            default:
              break;
          }
          if (verbose) {
            // display the purchase verbosely
            Print.verboseTotalItemsPurchased("Fruits Are Here", type,
                itemsBought.size());
          }
          // append the message to the log file
          Output.appendToLogFileAmountOfFruitsPurchase(itemsBought.size(), type, "Fruits Are Here",
              cost);
          Output.appendToLogFile("NotEnoughFunds: To Buy Avocados");
        }
      } else if (!fruitsAreHere.getAvailablity() && (inventory.size() < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        int numberOfItemsToBeBought = (MAXIMUM_NUMBER_OF_FRUITS -
            inventory.size());
        double cost = 0;
        switch (type) {
          case Avocado:
            cost = Avocado.COST_PRICE;
          case Banana:
            cost = Banana.COST_PRICE;
          case Lime:
            cost = Lime.COST_PRICE;
          case Mango:
            cost = Mango.COST_PRICE;
          case Watermelon:
            cost = Watermelon.COST_PRICE;
          default:
            break;
        }
        if (cashOnHand >= (numberOfItemsToBeBought * cost)) {
          ArrayList<Fruit> itemsBought = fruitsAreHere.orderFruits(numberOfItemsToBeBought, type);
          switch (type) {
            case Avocado:
              itemsBought.forEach((item) -> avocadosInventory.add(item));
              cashOnHand -= (itemsBought.size() * Avocado.COST_PRICE);
              itemsPurchased[0] += itemsBought.size();
            case Banana:
              itemsBought.forEach((item) -> bananasInventory.add(item));
              cashOnHand -= (itemsBought.size() * Banana.COST_PRICE);
              itemsPurchased[1] += itemsBought.size();
            case Lime:
              itemsBought.forEach((item) -> limesInventory.add(item));
              cashOnHand -= (itemsBought.size() * Lime.COST_PRICE);
              itemsPurchased[2] += itemsBought.size();
            case Mango:
              itemsBought.forEach((item) -> mangoesInventory.add(item));
              cashOnHand -= (itemsBought.size() * Mango.COST_PRICE);
              itemsPurchased[3] += itemsBought.size();
            case Watermelon:
              itemsBought.forEach((item) -> watermelonsInventory.add(item));
              cashOnHand -= (itemsBought.size() * Watermelon.COST_PRICE);
              itemsPurchased[4] += itemsBought.size();
            default:
              break;
          }
        }
      }
    }
  }

  private void buyVegetables(ArrayList<Vegetable> inventory, VegetableType type) throws Exception {
    if (cashOnHand <= 0) {
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (tastyVegetables.getAvailablity()) {
        int numberOfItemsToBeBought = (MAXIMUM_NUMBER_OF_VEGETABLES -
            inventory.size());
        double cost = 0;
        switch (type) {
          case Carrot:
            cost = Carrot.COST_PRICE;
          case Cucumber:
            cost = Cucumber.COST_PRICE;
          case Lettuce:
            cost = Lettuce.COST_PRICE;
          case Onion:
            cost = Onion.COST_PRICE;
          case Parsley:
            cost = Parsley.COST_PRICE;
          default:
            break;
        }
        if (cashOnHand >= (numberOfItemsToBeBought * cost)) {
          ArrayList<Vegetable> itemsBought = tastyVegetables.orderVegetables(numberOfItemsToBeBought, type);
          switch (type) {
            case Carrot:
              itemsBought.forEach((item) -> carrotsInventory.add(item));
              cashOnHand -= (itemsBought.size() * Carrot.COST_PRICE);
              itemsPurchased[5] += itemsBought.size();
            case Cucumber:
              itemsBought.forEach((item) -> cucumbersInventory.add(item));
              cashOnHand -= (itemsBought.size() * Cucumber.COST_PRICE);
              itemsPurchased[6] += itemsBought.size();
            case Lettuce:
              itemsBought.forEach((item) -> lettucesInventory.add(item));
              cashOnHand -= (itemsBought.size() * Lettuce.COST_PRICE);
              itemsPurchased[7] += itemsBought.size();
            case Onion:
              itemsBought.forEach((item) -> onionsInventory.add(item));
              cashOnHand -= (itemsBought.size() * Onion.COST_PRICE);
              itemsPurchased[8] += itemsBought.size();
            case Parsley:
              itemsBought.forEach((item) -> parsleysInventory.add(item));
              cashOnHand -= (itemsBought.size() * Parsley.COST_PRICE);
              itemsPurchased[9] += itemsBought.size();
            default:
              break;
          }
          if (verbose) {
            // display the purchase verbosely
            Print.verboseTotalItemsPurchased("Vegetables Are Here", type,
                itemsBought.size());
          }
          // append the message to the log file
          Output.appendToLogFileAmountOfVegetablesPurchase(itemsBought.size(), type, "Vegetables Are Here",
              cost);
          Output.appendToLogFile("NotEnoughFunds: To Buy Avocados");
        }
      } else if (!tastyVegetables.getAvailablity() && (inventory.size() < MAXIMUM_NUMBER_OF_VEGETABLES)
          && allYouCanEat.getAvailablity()) {
        int numberOfItemsToBeBought = (MAXIMUM_NUMBER_OF_VEGETABLES -
            inventory.size());
        double cost = 0;
        switch (type) {
          case Carrot:
            cost = Carrot.COST_PRICE;
          case Cucumber:
            cost = Cucumber.COST_PRICE;
          case Lettuce:
            cost = Lettuce.COST_PRICE;
          case Onion:
            cost = Onion.COST_PRICE;
          case Parsley:
            cost = Parsley.COST_PRICE;
          default:
            break;
        }
        if (cashOnHand >= (numberOfItemsToBeBought * cost)) {
          ArrayList<Vegetable> itemsBought = tastyVegetables.orderVegetables(numberOfItemsToBeBought, type);
          switch (type) {
            case Carrot:
              itemsBought.forEach((item) -> carrotsInventory.add(item));
              cashOnHand -= (itemsBought.size() * Carrot.COST_PRICE);
              itemsPurchased[5] += itemsBought.size();
            case Cucumber:
              itemsBought.forEach((item) -> cucumbersInventory.add(item));
              cashOnHand -= (itemsBought.size() * Cucumber.COST_PRICE);
              itemsPurchased[6] += itemsBought.size();
            case Lettuce:
              itemsBought.forEach((item) -> lettucesInventory.add(item));
              cashOnHand -= (itemsBought.size() * Lettuce.COST_PRICE);
              itemsPurchased[7] += itemsBought.size();
            case Onion:
              itemsBought.forEach((item) -> onionsInventory.add(item));
              cashOnHand -= (itemsBought.size() * Onion.COST_PRICE);
              itemsPurchased[8] += itemsBought.size();
            case Parsley:
              itemsBought.forEach((item) -> parsleysInventory.add(item));
              cashOnHand -= (itemsBought.size() * Parsley.COST_PRICE);
              itemsPurchased[9] += itemsBought.size();
            default:
              break;
          }
        }
      }
    }
  }

  /*
   * This function calculates the profit for the Supermarket per cycle. There
   * are
   * no parameters and no return values
   */
  private void calculateProfit() {
    double totalPurchases = 0;
    double totalSales = 0;
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
          totalPurchases += itemsPurchased[index] * Lime.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Lime.SELLING_PRICE;
          break;
        case 3:
          totalPurchases += itemsPurchased[index] * Mango.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Mango.SELLING_PRICE;
          break;
        case 4:
          totalPurchases += itemsPurchased[index] * Watermelon.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] *
              Watermelon.SELLING_PRICE;
          break;
        case 5:
          totalPurchases += itemsPurchased[index] * Carrot.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Carrot.SELLING_PRICE;
          break;
        case 6:
          totalPurchases += itemsPurchased[index] * Cucumber.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] *
              Cucumber.SELLING_PRICE;
          break;
        case 7:
          totalPurchases += itemsPurchased[index] * Lettuce.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Lettuce.SELLING_PRICE;
          break;

        case 8:
          totalPurchases += itemsPurchased[index] * Onion.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Onion.SELLING_PRICE;
          break;
        case 9:
          totalPurchases += itemsPurchased[index] * Parsley.COST_PRICE;
          totalCostOfSpolitItems += numberOfSpoiltItems[index] * Parsley.SELLING_PRICE;
          break;

        default:
          break;
      }
    }
    // loops through array and calculate the total fruits purchases
    for (int index = 0; index < itemsSold.length; index++) {
      switch (index) {
        case 0:
          totalSales += itemsSold[index] * Avocado.SELLING_PRICE;
          break;
        case 1:
          totalSales += itemsSold[index] * Banana.SELLING_PRICE;
          break;
        case 2:
          totalSales += itemsSold[index] * Lime.SELLING_PRICE;
          break;
        case 3:
          totalSales += itemsSold[index] * Mango.SELLING_PRICE;
          break;
        case 4:
          totalSales += itemsSold[index] * Watermelon.SELLING_PRICE;
          break;
        case 5:
          totalSales += itemsSold[index] * Carrot.SELLING_PRICE;
          break;
        case 6:
          totalSales += itemsSold[index] * Cucumber.SELLING_PRICE;
          break;
        case 7:
          totalSales += itemsSold[index] * Lettuce.SELLING_PRICE;
          break;
        case 8:
          totalSales += itemsSold[index] * Onion.SELLING_PRICE;
          break;
        case 9:
          totalSales += itemsSold[index] * Parsley.SELLING_PRICE;
          break;
        default:
          break;
      }
    }

    // addes the profit to the profit field
    profit += ((totalSales)
        - (totalPurchases + totalCostOfSpolitItems + lossesCausedbyRandomEvent));
    // added to cashOnHand
    cashOnHand += profit;
  }

  /*
   * This function reset the counter fields of the Supermarket.
   * There are no parameters and no return values
   */
  private void reset() {
    for (int index = 0; index < NUMBER_OF_TYPES_OF_ITEMS; index++) {
      itemsPurchased[index] = 0;
      numberOfSpoiltItems[index] = 0;
    }
    for (int index = 0; index < 5; index++) {
      itemsSold[index] = 0;
      itemsPurchased[index] = 0;
    }
    lossesCausedbyRandomEvent = 0;
  }

  /*
   * The function set the total amount of iteration when passed in at tha command
   * line. There are no parameters and no return values
   */
  public void setTotalIterations(int iter) {
    // set the total iterations if it is positive
    if (iter >= 0) {
      totalIterations = iter;
    }
  }
}