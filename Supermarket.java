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
  private int[] fruitsSold;
  // holds the total of each fruit the customer buys per cycle
  private int[] vegetablesSold;
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
    // create a random number generator to generate the inital values for the
    // Supermarket inventory
    Random rand = new Random();
    // initalise the total amount of iteration to the default number (50)
    totalIterations = DEFAULT_TOTAL_ITERATIONS;
    // creates a randomly generated amount of avocados references
    avocadoInventory = new Avocado[rand.nextInt(101)];
    // creates a randomly generated amount of bananas references
    bananaInventory = new Banana[rand.nextInt(101)];
    // creates a randomly generated amount of carrots references
    carrotInventory = new Carrot[rand.nextInt(101)];
    // creates a randomly generated amount of cucumbers references
    cucumberInventory = new Cucumber[rand.nextInt(101)];
    // creates a randomly generated amount of lettuces references
    lettuceInventory = new Lettuce[rand.nextInt(101)];
    // creates a randomly generated amount of limes references
    limeInventory = new Lime[rand.nextInt(101)];
    // creates a randomly generated amount of mangoes references
    mangoInventory = new Mango[rand.nextInt(101)];
    // creates a randomly generated amount of parsleys references
    parsleyInventory = new Parsley[rand.nextInt(101)];
    // creates a randomly generated amount of onions references
    onionInventory = new Onion[rand.nextInt(101)];
    // creates a randomly generated amount of watermelons references
    watermelonInventory = new Watermelon[rand.nextInt(101)];
    // creates 10 customer references
    customers = new Customer[NUMBER_OF_CUSTOMERS];
    // assign each customer a new instance of Customer
    initCustomers();
    // initalise all the items of the Supermarket inventory to have a new instance
    // of each item
    initInventory();
    // creates 5 references of int
    fruitsSold = new int[5];
    // creates 5 references of int
    vegetablesSold = new int[5];
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
              Print.verboseTotalAmountCustomerPurchase(totalFruitsPurchsedByCustomer,
                  totalVegetablesPurchsedByCustomer);
            }
          }
          // catch the FruitNotAvailable and VegetableNotAvailable Exception and prevent
          // the program from terminating
        } catch (Exception e) {
        }
        // remove all spoilt items from the Supermarket inventory
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
        // restock on avocados in the Supermarket
        if (avocadoInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
          buyAvocados();
        } // restock on bananas in the Supermarket
        if (bananaInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
          buyBananas();
        } // restock on carrots in the Supermarket
        if (carrotInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyCarrots();
        } // restock on cucumbers in the Supermarket
        if (cucumberInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyCucumbers();
        } // restock on lettuces in the Supermarket
        if (lettuceInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyLettuces();
        } // restock on limes in the Supermarket
        if (limeInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
          buyLimes();
        } // restock on mangoes in the Supermarket
        if (mangoInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
          buyMangoes();
        } // restock on onions in the Supermarket
        if (onionInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyOnions();
        } // restock on parsleys in the Supermarket
        if (parsleyInventory.length < MAXIMUM_NUMBER_OF_VEGETABLES) {
          buyParsleys();
        } // restock on watermelons in the Supermarket
        if (watermelonInventory.length < MAXIMUM_NUMBER_OF_FRUITS) {
          buyWatermelons();
        }
        // reset the customer's availablity for the next day
        Customer.reset();
        // restock the vendors inventory for the next day
        fruitsAreHere.restock();
        allYouCanEat.restock();
        tastyVegetables.restock();
        // calcuates the profit the Supermarket for each cycle
        calculateProfit();
        // set the fruitsSold, vegetablesSolde, itemsPurchased and numberOfSpoiltItems
        // array back to zero for the next day
        reset();
      }
      // catches the Supermarket Runs Out Of Money Exception to terminate the program
    } catch (Exception e) {
    }
    // display the number of iterations that where carried out
    System.out.println("Total number of cycles performed: " + totalPerformedIterations);
    // display total profit the supermarket made at the end of all the cycles
    System.out.println("Total supermarket profit: $" + profit);
    // display the total profit the vendors made from sale of items to the
    // Supermarket
    System.out.println("Total vendor profit: $"
        + (allYouCanEat.getProfit() + fruitsAreHere.getProfit() + tastyVegetables.getProfit()));
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
   * This function initialises each index in the customers array to a new instance
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
    // loops through the watermelons inventory create a new instance of Watermelon
    for (int index = 0; index < watermelonInventory.length; index++) {
      watermelonInventory[index] = new Watermelon();
    } // loops through the avocados inventory create a new instance of Avocado
    for (int index = 0; index < avocadoInventory.length; index++) {
      avocadoInventory[index] = new Avocado();
    } // loops through the mangoes inventory create a new instance of Mango
    for (int index = 0; index < mangoInventory.length; index++) {
      mangoInventory[index] = new Mango();
    } // loops through the limes inventory create a new instance of Lime
    for (int index = 0; index < limeInventory.length; index++) {
      limeInventory[index] = new Lime();
    } // loops through the bananas inventory create a new instance of Banana
    for (int index = 0; index < bananaInventory.length; index++) {
      bananaInventory[index] = new Banana();
    } // loops through the onions inventory create a new instance of Onion
    for (int index = 0; index < onionInventory.length; index++) {
      onionInventory[index] = new Onion();
    } // loops through the parsleys inventory create a new instance of Parsley
    for (int index = 0; index < parsleyInventory.length; index++) {
      parsleyInventory[index] = new Parsley();
    } // loops through the lettuces inventory create a new instance of Lettuce
    for (int index = 0; index < lettuceInventory.length; index++) {
      lettuceInventory[index] = new Lettuce();
    } // loops through the carrots inventory create a new instance of Carrot
    for (int index = 0; index < carrotInventory.length; index++) {
      carrotInventory[index] = new Carrot();
    } // loops through the cucumbers inventory create a new instance of Cucumber
    for (int index = 0; index < cucumberInventory.length; index++) {
      cucumberInventory[index] = new Cucumber();
    }
  }

  /*
   * This function decreased the spoilt value of each type of item of Fruit or
   * Vegetable. There are no parameters and there are no values to return
   */
  private void decreaseInventorySpoiltValue() {
    // loops through the watermelons inventory decrease the spoilt value of each
    // item
    for (int index = 0; index < watermelonInventory.length; index++) {
      watermelonInventory[index].decreaseSpoiltValue();
    } // loops through the avocados inventory decrease the spoilt value of each item
    for (int index = 0; index < avocadoInventory.length; index++) {
      avocadoInventory[index].decreaseSpoiltValue();
    } // loops through the mangoes inventory decrease the spoilt value of each item
    for (int index = 0; index < mangoInventory.length; index++) {
      mangoInventory[index].decreaseSpoiltValue();
    } // loops through the limes inventory decrease the spoilt value of each item
    for (int index = 0; index < limeInventory.length; index++) {
      limeInventory[index].decreaseSpoiltValue();
    } // loops through the bananas inventory decrease the spoilt value of each item
    for (int index = 0; index < bananaInventory.length; index++) {
      bananaInventory[index].decreaseSpoiltValue();
    } // loops through the onions inventory decrease the spoilt value of each item
    for (int index = 0; index < onionInventory.length; index++) {
      onionInventory[index].decreaseSpoiltValue();
    } // loops through the parsley inventory decrease the spoilt value of each item
    for (int index = 0; index < parsleyInventory.length; index++) {
      parsleyInventory[index].decreaseSpoiltValue();
    } // loops through the lettuce inventory decrease the spoilt value of each item
    for (int index = 0; index < lettuceInventory.length; index++) {
      lettuceInventory[index].decreaseSpoiltValue();
    } // loops through the carrots inventory decrease the spoilt value of each item
    for (int index = 0; index < carrotInventory.length; index++) {
      carrotInventory[index].decreaseSpoiltValue();
    } // loops through the cucumber inventory decrease the spoilt value of each item
    for (int index = 0; index < cucumberInventory.length; index++) {
      cucumberInventory[index].decreaseSpoiltValue();
    }
  }

  /*
   * This function determines a random event and execute that event. The function
   * takes no parameters and returns no values.
   */
  private void randomEvent() {
    // create a random number generator to generate a random event and other random
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
        // loops throught the amount of hours the electricity and spoil one percent the
        // total type of Fruit and Vegetable
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
        spoilFruitsFaster(avocadoInventory, percentage);
        spoilFruitsFaster(bananaInventory, percentage);
        spoilVegetablesFaster(carrotInventory, percentage);
        spoilVegetablesFaster(cucumberInventory, percentage);
        spoilVegetablesFaster(lettuceInventory, percentage);
        spoilFruitsFaster(limeInventory, percentage);
        spoilFruitsFaster(mangoInventory, percentage);
        spoilVegetablesFaster(onionInventory, percentage);
        spoilVegetablesFaster(parsleyInventory, percentage);
        spoilFruitsFaster(watermelonInventory, percentage);
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

  /*
   * This function spoils one percent of the available items of type Fruit to
   * spoil. The function takes an array of type Fruit as a parameter and returns
   * no values.
   */
  private void spoilFruits(Fruit[] inventory) {
    // a counter to determine how many of the item in the inventory is not already
    // spoilt
    int numberOfUnsploitFruits = 0;
    // loops through the inventory of type Fruit and increase the
    // numberOfUnspoiltFruits variable by 1
    for (int index = 0; index < inventory.length; index++) {
      if (inventory[index].getSpoiltValue() > 0) {
        numberOfUnsploitFruits++;
      }
    }
    // calculate one percent of the total amount of unspoilt fruits
    int numberOfFruitsToSpoil = (int) Math.ceil((0.01 * numberOfUnsploitFruits));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out.println("Electricity Goes Off: " + numberOfFruitsToSpoil + "items have spoilt");
    }
    // a counter to determine how many of the Fruit item that have spoilt
    int numberOfSpoiltFruits = 0;
    // loops throught the inventory array
    for (int index = 0; index < inventory.length; index++) {
      // determines if the item can spoil
      if (numberOfSpoiltFruits < numberOfFruitsToSpoil && inventory[index].getSpoiltValue() > 0) {
        // set the spoil value for that particular item to zero
        inventory[index].spoil();
        // increase the amount of Fruit item that has spoilt
        numberOfSpoiltFruits++;
      }
    }
  }

  /*
   * This function spoils one percent of the available items of type Vegetable to
   * spoil. The function takes an array of type Fruit as a parameter and returns
   * no values.
   */
  private void spoilVegetables(Vegetable[] inventory) {
    // a counter to determine how many of the item in the inventory is not already
    // spoilt
    int numberOfUnsploitVegetables = 0;
    // loops through the inventory of type Vegetable and increase the
    // numberOfUnspoiltVegetables variable by 1
    for (int index = 0; index < inventory.length; index++) {
      if (inventory[index].getSpoiltValue() > 0) {
        numberOfUnsploitVegetables++;
      }
    }
    int numberOfVegetablesToSpoil = (int) Math.ceil((0.01 * numberOfUnsploitVegetables));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out.println("Electricity Goes Off: " + numberOfVegetablesToSpoil + "items have spoilt");
    }
    // a counter to determine how many of the Vegetable item that have spoilt
    int numberOfSpoiltVegetables = 0;
    // loops throught the inventory array
    for (int index = 0; index < inventory.length; index++) {
      // determines if the item can spoil
      if (numberOfSpoiltVegetables < numberOfVegetablesToSpoil && inventory[index].getSpoiltValue() > 0) {
        // set the spoil value for that particular item to zero
        inventory[index].spoil();
        // increase the amount of Vegetable item that has spoilt
        numberOfSpoiltVegetables++;
      }
    }
  }

  /*
   * This function spoils a percentage of the available items of type Fruit to
   * spoil. The function takes an array of type Fruit as a parameter and a
   * percentage of type double and returns no values.
   */
  private void spoilFruitsFaster(Fruit[] inventory, double percentage) {
    // determines the an amount base on the amount of items to spoil
    int numberOfFruitsToSpoil = (int) Math.ceil(((percentage / 100.00) * inventory.length));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out.println("Items spoil faster than expected: " + numberOfFruitsToSpoil + " items have spoilt.");
    }
    // a counter to determine how many of the Fruit item that have spoilt
    int numberOfSpoiltFruits = 0;
    // loops throught the inventory array
    for (int index = 0; index < inventory.length; index++) {
      // determines if the item can spoil
      if (numberOfSpoiltFruits < numberOfFruitsToSpoil && inventory[index].getSpoiltValue() > 0) {
        // set the spoil value for that particular item to zero
        inventory[index].spoil();
        // increase the amount of Fruit item that has spoilt
        numberOfSpoiltFruits++;
      }
    }
  }

  /*
   * This function spoils a percentage of the available items of type Vegetable to
   * spoil. The function takes an array of type Vegetable as a parameter and a
   * percentage of type double and returns no values.
   */
  private void spoilVegetablesFaster(Vegetable[] inventory, double percentage) {
    // determines the an amount base on the amount of items to spoil
    int numberOfVegetablesToSpoil = (int) Math.ceil(((percentage / 100.00) * inventory.length));
    if (verbose) {
      // display the random event and the amount of each particular item that was
      // spoilt
      System.out
          .println("Items spoil faster than expected: " + numberOfVegetablesToSpoil + " items have spoilt.");
    }
    // a counter to determine how many of the Vegetable item that have spoilt
    int numberOfSpoiltVegetables = 0;
    // loops throught the inventory array
    for (int index = 0; index < inventory.length; index++) {
      // determines if the item can spoil
      if (numberOfSpoiltVegetables < numberOfVegetablesToSpoil && inventory[index].getSpoiltValue() > 0) {
        // set the spoil value for that particular item to zero
        inventory[index].spoil();
        // increase the amount of Vegetable item that has spoilt
        numberOfSpoiltVegetables++;
      }
    }
  }

  /*
   * This function sells avocados to a customer and decreases the inventory for
   * the avocados inventory and throw a FruitNotAvailable Exception if there are
   * no avocados available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellAvocados(int items) throws Exception {
    if (avocadoInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("FruitNotAvailable: Avocados");
      // throw a FruitNotAvailable: Avocados Exception
      throw new Exception("FruitNotAvailable: Avocados");
    } else {
      // counter for the number of unspoilt and spoilt avocados
      int numberOfUnsploitAvocados = 0;
      int numberOfSploitAvocados = 0;
      // total the the number of unspoilt and spoilt avocados
      for (int index = 0; index < avocadoInventory.length; index++) {
        if (avocadoInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitAvocados++;
        } else {
          numberOfUnsploitAvocados++;
        }
      }
      // create a temporary array to hold the available avocados
      Avocado[] availableItems = new Avocado[numberOfUnsploitAvocados];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      // adds all of the unspoilt avocados to the available array
      for (int index = 0; index < avocadoInventory.length; index++) {
        if (avocadoInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = avocadoInventory[index];
        }
      }
      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt Avocados
        Avocado[] updatedInventory = new Avocado[numberOfSploitAvocados];
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Avocado",
            (availableItems.length * Avocado.SELLING_PRICE));
        // increase the amount of avocados that was sold
        fruitsSold[0] += availableItems.length;
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt Avocados the updated array since the customer bought all
        // the available ones
        for (int index = 0; index < avocadoInventory.length; index++) {
          if (avocadoInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = avocadoInventory[index];
          }
        }
        // reassign the avocados inventory to the updated one
        avocadoInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt Avocados and the unsoilt
        // Avocadoes that are left back over
        Avocado[] updatedInventory = new Avocado[availableItems.length - items
            + numberOfSploitAvocados];
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Avocado",
            (items * Avocado.SELLING_PRICE));
        // increase the amount of avocados that the customer bought
        fruitsSold[0] += items;
        shiftIndex = 0;
        // addes all the spoilt Avocados the updated array
        for (int index = 0; index < avocadoInventory.length; index++) {
          if (avocadoInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = avocadoInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of avocados that are left back from the costomers purchase
        for (int index = numberOfSploitAvocados; index < updatedInventory.length; index++) {
          updatedInventory[index] = avocadoInventory[shiftIndex++];
        }
        // reassign the avocados inventory to the updated one
        avocadoInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells bananas to a customer and decreases the inventory for
   * the bananas inventory and throw a FruitNotAvailable Exception if there are
   * no bananas available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellBananas(int items) throws Exception {
    if (bananaInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("FruitNotAvailable: Bananas");
      // throw a FruitNotAvailable: Bananas Exception
      throw new Exception("FruitNotAvailable: Bananas");
    } else {
      // counter for the number of unspoilt and spoilt bananas
      int numberOfUnsploitBananas = 0;
      int numberOfSploitBananas = 0;
      // total the the number of unspoilt and spoilt bananas
      for (int index = 0; index < bananaInventory.length; index++) {
        if (bananaInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitBananas++;
        } else {
          numberOfUnsploitBananas++;
        }
      }
      // create a temporary array to hold the available bananas
      Banana[] availableItems = new Banana[numberOfUnsploitBananas];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      // adds all of the unspoilt bananas to the available array
      for (int index = 0; index < bananaInventory.length; index++) {
        if (bananaInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = bananaInventory[index];
        }
      }

      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt Bananas
        Banana[] updatedInventory = new Banana[numberOfSploitBananas];
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Banana",
            (availableItems.length * Banana.SELLING_PRICE));
        // increase the amount of bananas that was sold
        fruitsSold[1] += availableItems.length;
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt Bananas the updated array since the customer bought all
        // the available ones
        for (int index = 0; index < bananaInventory.length; index++) {
          if (bananaInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = bananaInventory[index];
          }
        }
        // reassign the bananas inventory to the updated one
        bananaInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt Bananas and the unsoilt
        // Bananas that are left back over
        Banana[] updatedInventory = new Banana[availableItems.length - items
            + numberOfSploitBananas];
        // increase the amount of bananas that the customer bought
        fruitsSold[1] += items;
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Banana",
            (items * Banana.SELLING_PRICE));
        shiftIndex = 0;
        // addes all the spoilt bananas the updated array
        for (int index = 0; index < bananaInventory.length; index++) {
          if (bananaInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = bananaInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of bananas that are left back from the costomers purchase
        for (int index = numberOfSploitBananas; index < updatedInventory.length; index++) {
          updatedInventory[index] = bananaInventory[shiftIndex++];
        }
        // reassign the bananas inventory to the updated one
        bananaInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells avocados to a customer and decreases the inventory for
   * the carrots inventory and throw a VegetableNotAvailable Exception if there
   * are
   * no carrots available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellCarrots(int items) throws Exception {
    if (carrotInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("VegetableNotAvailable: Carrots");
      // throw a VegetableNotAvailable: Carrots Exception
      throw new Exception("VegetableNotAvailable: Carrots");
    } else {
      // counter for the number of unspoilt and spoilt carrots
      int numberOfUnsploitCarrots = 0;
      int numberOfSploitCarrots = 0;
      // total the the number of unspoilt and spoilt carrots
      for (int index = 0; index < carrotInventory.length; index++) {
        if (carrotInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitCarrots++;
        } else {
          numberOfUnsploitCarrots++;
        }
      }
      // create a temporary array to hold the available carrots
      Carrot[] availableItems = new Carrot[numberOfUnsploitCarrots];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      // adds all of the unspoilt carrots to the available array
      for (int index = 0; index < carrotInventory.length; index++) {
        if (carrotInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = carrotInventory[index];
        }
      }
      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt carrots
        Carrot[] updatedInventory = new Carrot[numberOfSploitCarrots];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Carrot",
            (availableItems.length * Carrot.SELLING_PRICE));
        // increase the amount of carrots that was sold
        vegetablesSold[0] += availableItems.length;
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt carrots the updated array since the customer bought all
        // the available ones
        for (int index = 0; index < carrotInventory.length; index++) {
          if (carrotInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = carrotInventory[index];
          }
        }
        // reassign the carrots inventory to the updated one
        carrotInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt carrots and the unsoilt
        // Carrots that are left back over
        Carrot[] updatedInventory = new Carrot[availableItems.length - items
            + numberOfSploitCarrots];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Carrot",
            (items * Carrot.SELLING_PRICE));
        // increase the amount of carrots that the customer bought
        vegetablesSold[0] += items;
        shiftIndex = 0;
        // addes all the spoilt carrots the updated array
        for (int index = 0; index < carrotInventory.length; index++) {
          if (carrotInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = carrotInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of carrots that are left back from the costomers purchase
        for (int index = numberOfSploitCarrots; index < updatedInventory.length; index++) {
          updatedInventory[index] = carrotInventory[shiftIndex++];
        }
        // reassign the carrots inventory to the updated one
        carrotInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells cucumbers to a customer and decreases the inventory for
   * the cucumbers inventory and throw a VegetableNotAvailable Exception if there
   * are
   * no cucumbers available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellCucumbers(int items) throws Exception {
    if (cucumberInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("VegetableNotAvailable: Cucumbers");
      // throw a VegetableNotAvailable: Cucumber Exception
      throw new Exception("VegetableNotAvailable: Cucumbers");
    } else {
      // counter for the number of unspoilt and spoilt cucumbers
      int numberOfUnsploitCucumbers = 0;
      int numberOfSploitCucumbers = 0;
      // total the the number of unspoilt and spoilt cucumbers
      for (int index = 0; index < cucumberInventory.length; index++) {
        if (cucumberInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitCucumbers++;
        } else {
          numberOfUnsploitCucumbers++;
        }
      }
      // create a temporary array to hold the available cucumbers
      Cucumber[] availableItems = new Cucumber[numberOfUnsploitCucumbers];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      // adds all of the unspoilt cucumbers to the available array
      for (int index = 0; index < cucumberInventory.length; index++) {
        if (cucumberInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = cucumberInventory[index];
        }
      }

      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt cucumbers
        Cucumber[] updatedInventory = new Cucumber[numberOfSploitCucumbers];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Cucumber",
            (availableItems.length * Cucumber.SELLING_PRICE));
        // increase the amount of cucumbers that was sold
        vegetablesSold[1] += availableItems.length;
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt cucumbers the updated array since the customer bought
        // all
        // the available ones
        for (int index = 0; index < cucumberInventory.length; index++) {
          if (cucumberInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = cucumberInventory[index];
          }
        }
        // reassign the cucumbers inventory to the updated one
        cucumberInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt cucumbers and the unsoilt
        // Cucumbers that are left back over
        Cucumber[] updatedInventory = new Cucumber[availableItems.length - items
            + numberOfSploitCucumbers];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Cucumber",
            (items * Cucumber.SELLING_PRICE));
        // increase the amount of cucumbers that the customer bought
        vegetablesSold[1] += items;
        shiftIndex = 0;
        // addes all the spoilt cucumbers the updated array
        for (int index = 0; index < cucumberInventory.length; index++) {
          if (cucumberInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = cucumberInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of cucumbers that are left back from the costomers purchase
        for (int index = numberOfSploitCucumbers; index < updatedInventory.length; index++) {
          updatedInventory[index] = cucumberInventory[shiftIndex++];
        }
        // reassign the cucumbers inventory to the updated one
        cucumberInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells lettuces to a customer and decreases the inventory for
   * the lettuces inventory and throw a VegetableNotAvailable Exception if there
   * are
   * no lettuces available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellLettuces(int items) throws Exception {
    if (lettuceInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("VegetableNotAvailable: Lettuces");
      // throw a VegetableNotAvailable: Lettuce Exception
      throw new Exception("VegetableNotAvailable: Lettuces");
    } else {
      // counter for the number of unspoilt and spoilt lettuces
      int numberOfUnsploitLettuces = 0;
      int numberOfSploitLettuces = 0;
      // total the the number of unspoilt and spoilt lettuces
      for (int index = 0; index < lettuceInventory.length; index++) {
        if (lettuceInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitLettuces++;
        } else {
          numberOfUnsploitLettuces++;
        }
      }
      // create a temporary array to hold the available lettuces
      Lettuce[] availableItems = new Lettuce[numberOfUnsploitLettuces];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      for (int index = 0; index < lettuceInventory.length; index++) {
        if (lettuceInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = lettuceInventory[index];
        }
      }

      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt lettuces
        Lettuce[] updatedInventory = new Lettuce[numberOfSploitLettuces];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Lettuce",
            (availableItems.length * Lettuce.SELLING_PRICE));
        // increase the amount of lettuces that was sold
        vegetablesSold[2] += availableItems.length;
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt lettuces the updated array since the customer bought
        // all
        // the available ones
        for (int index = 0; index < lettuceInventory.length; index++) {
          if (lettuceInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = lettuceInventory[index];
          }
        }
        // reassign the lettuces inventory to the updated one
        lettuceInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt lettuces and the unsoilt
        // Lettues that are left back over
        Lettuce[] updatedInventory = new Lettuce[availableItems.length - items
            + numberOfSploitLettuces];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Lettuce",
            (items * Lettuce.SELLING_PRICE));
        // increase the amount of lettuces that the customer bought
        vegetablesSold[2] += items;
        shiftIndex = 0;
        // addes all the spoilt lettuces the updated array
        for (int index = 0; index < lettuceInventory.length; index++) {
          if (lettuceInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = lettuceInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of lettuces that are left back from the costomers purchase
        for (int index = numberOfSploitLettuces; index < updatedInventory.length; index++) {
          updatedInventory[index] = lettuceInventory[shiftIndex++];
        }
        // reassign the lettuces inventory to the updated one
        lettuceInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells limes to a customer and decreases the inventory for
   * the limes inventory and throw a FruitNotAvailable Exception if there are
   * no limes available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellLimes(int items) throws Exception {
    if (limeInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("FruitNotAvailable: Limes");
      // throw a FruitNotAvailable: Lime Exception
      throw new Exception("FruitNotAvailable: Limes");
    } else {
      // counter for the number of unspoilt and spoilt limes
      int numberOfUnsploitLimes = 0;
      int numberOfSploitLimes = 0;
      // total the the number of unspoilt and spoilt limes
      for (int index = 0; index < limeInventory.length; index++) {
        if (limeInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitLimes++;
        } else {
          numberOfUnsploitLimes++;
        }
      }
      // create a temporary array to hold the available limes
      Lime[] availableItems = new Lime[numberOfUnsploitLimes];
      int shiftIndex = 0;
      // counter to shift the index of the array in the for loop
      for (int index = 0; index < limeInventory.length; index++) {
        if (limeInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = limeInventory[index];
        }
      }
      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt limes
        Lime[] updatedInventory = new Lime[numberOfSploitLimes];
        // increase the amount of limes that was sold
        fruitsSold[2] += availableItems.length;
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Lime",
            (availableItems.length * Lime.SELLING_PRICE));
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt limes the updated array since the customer bought
        // all
        // the available ones
        for (int index = 0; index < limeInventory.length; index++) {
          if (limeInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = limeInventory[index];
          }
        }
        // reassign the limes inventory to the updated one
        limeInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt limes and the unsoilt
        // Limes that are left back over
        Lime[] updatedInventory = new Lime[availableItems.length - items
            + numberOfSploitLimes];
        // increase the amount of limes that the customer bought
        fruitsSold[2] += items;
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Lime",
            (items * Lime.SELLING_PRICE));
        shiftIndex = 0;
        // addes all the spoilt limes the updated array
        for (int index = 0; index < limeInventory.length; index++) {
          if (limeInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = limeInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of limes that are left back from the costomers purchase
        for (int index = numberOfSploitLimes; index < updatedInventory.length; index++) {
          updatedInventory[index] = limeInventory[shiftIndex++];
        }
        // reassign the limes inventory to the updated one
        limeInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells mangoes to a customer and decreases the inventory for
   * the mangoes inventory and throw a FruitNotAvailable Exception if there are
   * no mangoes available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellMangoes(int items) throws Exception {
    if (mangoInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("FruitNotAvailable: Mangoes");
      // throw a FruitNotAvailable: Mangoes Exception
      throw new Exception("FruitNotAvailable: Mangoes");
    } else {
      // counter for the number of unspoilt and spoilt mangoes
      int numberOfUnsploitMangoes = 0;
      int numberOfSploitMangoes = 0;
      // total the the number of unspoilt and spoilt mangoes
      for (int index = 0; index < mangoInventory.length; index++) {
        if (mangoInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitMangoes++;
        } else {
          numberOfUnsploitMangoes++;
        }
      }
      // create a temporary array to hold the available mangoes
      Mango[] availableItems = new Mango[numberOfUnsploitMangoes];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      for (int index = 0; index < mangoInventory.length; index++) {
        if (mangoInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = mangoInventory[index];
        }
      }
      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt mangoes
        Mango[] updatedInventory = new Mango[numberOfSploitMangoes];
        // increase the amount of mangoes that was sold
        fruitsSold[3] += availableItems.length;
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Mango",
            (availableItems.length * Mango.SELLING_PRICE));
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt mangoes the updated array since the customer bought
        // all
        // the available ones
        for (int index = 0; index < mangoInventory.length; index++) {
          if (mangoInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = mangoInventory[index];
          }
        }
        // reassign the mango inventory to the updated one
        mangoInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt mangoes and the unsoilt
        // Mangoes that are left back over
        Mango[] updatedInventory = new Mango[availableItems.length - items
            + numberOfSploitMangoes];
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Mango",
            (items * Mango.SELLING_PRICE));
        // increase the amount of mangoes that the customer bought
        fruitsSold[3] += items;
        shiftIndex = 0;
        // addes all the spoilt mangoes the updated array
        for (int index = 0; index < mangoInventory.length; index++) {
          if (mangoInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = mangoInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of mangoes that are left back from the customers purchase
        for (int index = numberOfSploitMangoes; index < updatedInventory.length; index++) {
          updatedInventory[index] = mangoInventory[shiftIndex++];
        }
        // reassign the mango inventory to the updated one
        mangoInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells watermelons to a customer and decreases the inventory for
   * the watermelons inventory and throw a FruitNotAvailable Exception if there
   * are
   * no watermelons available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellWatermelons(int items) throws Exception {
    if (watermelonInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("FruitNotAvailable: Watermelons");
      // throw a FruitNotAvailable: Watermelons Exception
      throw new Exception("FruitNotAvailable: Watermelons");
    } else {
      // counter for the number of unspoilt and spoilt watermelons
      int numberOfUnsploitWatermelons = 0;
      int numberOfSploitWatermelons = 0;
      // total the the number of unspoilt and spoilt watermelons
      for (int index = 0; index < watermelonInventory.length; index++) {
        if (watermelonInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitWatermelons++;
        } else {
          numberOfUnsploitWatermelons++;
        }
      }
      // create a temporary array to hold the available watermelons
      Watermelon[] availableItems = new Watermelon[numberOfUnsploitWatermelons];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      for (int index = 0; index < watermelonInventory.length; index++) {
        if (watermelonInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = watermelonInventory[index];
        }
      }
      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt watermelons
        Watermelon[] updatedInventory = new Watermelon[numberOfSploitWatermelons];
        // increase the amount of watermelons that was sold
        fruitsSold[4] += availableItems.length;
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(availableItems.length, "Watermelon",
            (availableItems.length * Watermelon.SELLING_PRICE));
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt watermelons the updated array since the customer bought
        // all
        // the available ones
        for (int index = 0; index < watermelonInventory.length; index++) {
          if (watermelonInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = watermelonInventory[index];
          }
        }
        // reassign the watermelon inventory to the updated one
        watermelonInventory = updatedInventory;
      } else {
        // create a temporary array to stored the spoilt watermelon and the unsoilt
        // Watermelons that are left back over
        Watermelon[] updatedInventory = new Watermelon[availableItems.length - items
            + numberOfSploitWatermelons];
        // increase the amount of watermelons that the customer bought
        fruitsSold[4] += items;
        // display log message to the log file
        Output.appendCustomerPurchaseFruitsToLogFile(items, "Watermelon",
            (items * Watermelon.SELLING_PRICE));
        shiftIndex = 0;
        // addes all the spoilt watermelons the updated array
        for (int index = 0; index < watermelonInventory.length; index++) {
          if (watermelonInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = watermelonInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of watermelons that are left back from the customers
        // purchase
        for (int index = numberOfSploitWatermelons; index < updatedInventory.length; index++) {
          updatedInventory[index] = watermelonInventory[shiftIndex++];
        } // reassign the watermelon inventory to the updated one
        watermelonInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells onions to a customer and decreases the inventory for
   * the onions inventory and throw a VegetableNotAvailable Exception if there
   * are
   * no onions available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellOnions(int items) throws Exception {
    if (onionInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("VegetableNotAvailable: Onions");
      // throw a VegetableNotAvailable: Onions Exception
      throw new Exception("VegetableNotAvailable: Onions");
    } else {
      // counter for the number of unspoilt and spoilt onions
      int numberOfUnsploitOnions = 0;
      int numberOfSploitOnions = 0;
      // total the the number of unspoilt and spoilt onions
      for (int index = 0; index < onionInventory.length; index++) {
        if (onionInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitOnions++;
        } else {
          numberOfUnsploitOnions++;
        }
      }
      // create a temporary array to hold the available onions
      Onion[] availableItems = new Onion[numberOfUnsploitOnions];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      for (int index = 0; index < onionInventory.length; index++) {
        if (onionInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = onionInventory[index];
        }
      }

      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt onions
        Onion[] updatedInventory = new Onion[numberOfSploitOnions];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Onion",
            (availableItems.length * Onion.SELLING_PRICE));
        // increase the amount of onions that was sold
        vegetablesSold[3] += availableItems.length;
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt onions the updated array since the customer bought
        // all
        // the available ones
        for (int index = 0; index < onionInventory.length; index++) {
          if (onionInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = onionInventory[index];
          }
        }
        // reassign the onions inventory to the updated one
        onionInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt onions and the unsoilt
        // Lettues that are left back over
        Onion[] updatedInventory = new Onion[availableItems.length - items
            + numberOfSploitOnions];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Onion",
            (items * Onion.SELLING_PRICE));
        // increase the amount of onions that the customer bought
        vegetablesSold[3] += items;
        shiftIndex = 0;
        // addes all the spoilt onions the updated array
        for (int index = 0; index < onionInventory.length; index++) {
          if (onionInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = onionInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of onions that are left back from the costomers purchase
        for (int index = numberOfSploitOnions; index < updatedInventory.length; index++) {
          updatedInventory[index] = onionInventory[shiftIndex++];
        }
        // reassign the onions inventory to the updated one
        onionInventory = updatedInventory;
      }
    }
  }

  /*
   * This function sells parsleys to a customer and decreases the inventory for
   * the parsleys inventory and throw a VegetableNotAvailable Exception if there
   * are
   * no parsleys available. There is one parameter item: which is the quanity
   * the customer would like to buy. There are no return values
   */
  private void sellParsleys(int items) throws Exception {
    if (parsleyInventory.length == 0) {
      // append the message to the log file
      Output.appendToLogFile("VegetableNotAvailable: Parsleys");
      // throw a VegetableNotAvailable: Parsleys Exception
      throw new Exception("VegetableNotAvailable: Parsleys");
    } else {
      // counter for the number of unspoilt and spoilt parsleys
      int numberOfUnsploitParsleys = 0;
      int numberOfSploitParsleys = 0;
      // total the the number of unspoilt and spoilt parsleys
      for (int index = 0; index < parsleyInventory.length; index++) {
        if (parsleyInventory[index].getSpoiltValue() <= 0) {
          numberOfSploitParsleys++;
        } else {
          numberOfUnsploitParsleys++;
        }
      }
      // create a temporary array to hold the available parsleys
      Parsley[] availableItems = new Parsley[numberOfUnsploitParsleys];
      // counter to shift the index of the array in the for loop
      int shiftIndex = 0;
      for (int index = 0; index < parsleyInventory.length; index++) {
        if (parsleyInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          availableItems[shiftIndex++] = parsleyInventory[index];
        }
      }

      if (items >= availableItems.length) {
        // create a temporary array to stored the spoilt parsleys
        Parsley[] updatedInventory = new Parsley[numberOfSploitParsleys];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(availableItems.length, "Parsley",
            (availableItems.length * Parsley.SELLING_PRICE));
        // increase the amount of parsleys that was sold
        vegetablesSold[4] += availableItems.length;
        // reset the shift index 0
        shiftIndex = 0;
        // addes all the spoilt parsleys the updated array since the customer bought
        // all
        // the available ones
        for (int index = 0; index < parsleyInventory.length; index++) {
          if (parsleyInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = parsleyInventory[index];
          }
        }
        // reassign the parsleys inventory to the updated one
        parsleyInventory = updatedInventory;

      } else {
        // create a temporary array to stored the spoilt parsleys and the unsoilt
        // Lettues that are left back over
        Parsley[] updatedInventory = new Parsley[availableItems.length - items
            + numberOfSploitParsleys];
        // display log message to the log file
        Output.appendCustomerPurchaseVegetablesToLogFile(items, "Parsley",
            (items * Parsley.SELLING_PRICE));
        // increase the amount of parsleys that the customer bought
        vegetablesSold[4] += items;
        shiftIndex = 0;
        // addes all the spoilt parsleys the updated array
        for (int index = 0; index < parsleyInventory.length; index++) {
          if (parsleyInventory[index].getSpoiltValue() <= 0) {
            updatedInventory[shiftIndex++] = parsleyInventory[index];
          }
        }
        shiftIndex = 0;
        // addes the amount of parsleys that are left back from the costomers purchase
        for (int index = numberOfSploitParsleys; index < updatedInventory.length; index++) {
          updatedInventory[index] = parsleyInventory[shiftIndex++];
        }
        // reassign the parsleys inventory to the updated one
        parsleyInventory = updatedInventory;
      }
    }
  }

  /*
   * This function determines the amount of avocados that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltAvocados() {
    // counter to tally the number of spoilt avocados
    int sum = 0;
    // loops throught the avocados inventory and increase sum by 1
    for (int index = 0; index < avocadoInventory.length; index++) {
      if (avocadoInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt avocados
    numberOfSpoiltItems[0] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltFruit("avocado", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt avocados
      Avocado updatedAvocadoInventory[] = new Avocado[avocadoInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt avocados to the temporary array
      for (int index = 0; index < avocadoInventory.length; index++) {
        if (avocadoInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedAvocadoInventory[shiftIndex++] = avocadoInventory[index];
        }
      }
      // reassign the avocado inventory to the new one
      avocadoInventory = updatedAvocadoInventory;
    }

  }

  /*
   * This function determines the amount of bananas that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltBananas() {
    // counter to tally the number of spoilt bananas
    int sum = 0;
    // loops throught the bananas inventory and increase sum by 1
    for (int index = 0; index < bananaInventory.length; index++) {
      if (bananaInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt bananas
    numberOfSpoiltItems[1] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltFruit("banana", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt bananas
      Banana updatedBananaInventory[] = new Banana[bananaInventory.length - sum];
      int kshiftIndex = 0;
      // added the unspoilt bananas to the temporary array
      for (int index = 0; index < bananaInventory.length; index++) {
        if (bananaInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedBananaInventory[kshiftIndex++] = bananaInventory[index];
        }
      }
      // reassign the banana inventory to the new one
      bananaInventory = updatedBananaInventory;
    }

  }

  /*
   * This function determines the amount of carrots that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltCarrots() {
    // counter to tally the number of spoilt carrots
    int sum = 0;
    // loops throught the carrots inventory and increase sum by 1
    for (int index = 0; index < carrotInventory.length; index++) {
      if (carrotInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt carrots
    numberOfSpoiltItems[2] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltVegetable("carrot", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt carrots
      Carrot updatedCarrotsInventory[] = new Carrot[carrotInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt carrots to the temporary array
      for (int index = 0; index < carrotInventory.length; index++) {
        if (carrotInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedCarrotsInventory[shiftIndex++] = carrotInventory[index];
        }
      }
      // reassign the carrot inventory to the new one
      carrotInventory = updatedCarrotsInventory;
    }
  }

  /*
   * This function determined the amount of cucumbers that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltCucumbers() {
    // counter to tally the number of spoilt cucumbers
    int sum = 0;
    // loops throught the cucumbers inventory and increase sum by 1
    for (int index = 0; index < cucumberInventory.length; index++) {
      if (cucumberInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt cucumbers
    numberOfSpoiltItems[3] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltVegetable("cucumber", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt cucumbers
      Cucumber updatedCucumbersInventory[] = new Cucumber[cucumberInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt cucumbers to the temporary array
      for (int index = 0; index < cucumberInventory.length; index++) {
        if (cucumberInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedCucumbersInventory[shiftIndex++] = cucumberInventory[index];
        }
      }
      // reassign the cucumber inventory to the new one
      cucumberInventory = updatedCucumbersInventory;
    }
  }

  /*
   * This function determined the amount of avocados that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltLettuces() {
    // counter to tally the number of spoilt lettuces
    int sum = 0;
    // loops throught the lettuces inventory and increase sum by 1
    for (int index = 0; index < lettuceInventory.length; index++) {
      if (lettuceInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt lettuces
    numberOfSpoiltItems[4] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltVegetable("lettuce", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt lettuces
      Lettuce updatedLettucesInventory[] = new Lettuce[lettuceInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt lettuces to the temporary array
      for (int index = 0; index < lettuceInventory.length; index++) {
        if (lettuceInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedLettucesInventory[shiftIndex++] = lettuceInventory[index];
        }
      }
      // reassign the lettuce inventory to the new one
      lettuceInventory = updatedLettucesInventory;
    }
  }

  /*
   * This function determined the amount of limes that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltLimes() {
    // counter to tally the number of spoilt limes
    int sum = 0;
    // loops throught the limes inventory and increase sum by 1
    for (int index = 0; index < limeInventory.length; index++) {
      if (limeInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt limes
    numberOfSpoiltItems[5] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltFruit("lime", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt limes
      Lime updatedLimeInventory[] = new Lime[limeInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt limes to the temporary array
      for (int index = 0; index < limeInventory.length; index++) {
        if (limeInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedLimeInventory[shiftIndex++] = limeInventory[index];
        }
      }
      // reassign the lime inventory to the new one
      limeInventory = updatedLimeInventory;
    }

  }

  /*
   * This function determined the amount of mangoes that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltMangoes() {
    // counter to tally the number of spoilt mangoes
    int sum = 0;
    // loops throught the mangoes inventory and increase sum by 1
    for (int index = 0; index < mangoInventory.length; index++) {
      if (mangoInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt mangoes
    numberOfSpoiltItems[6] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltFruit("mango", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt mangoes
      Mango updatedMangoesInventory[] = new Mango[mangoInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt mangoes to the temporary array
      for (int index = 0; index < mangoInventory.length; index++) {
        if (mangoInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedMangoesInventory[shiftIndex++] = mangoInventory[index];
        }
      }
      // reassign the mango inventory to the new one
      mangoInventory = updatedMangoesInventory;
    }
  }

  /*
   * This function determined the amount of onions that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltOnions() {
    // counter to tally the number of spoilt onions
    int sum = 0;
    // loops throught the onions inventory and increase sum by 1
    for (int index = 0; index < onionInventory.length; index++) {
      if (onionInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt onions
    numberOfSpoiltItems[7] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltVegetable("onion", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt onions
      Onion updatedOnionsInventory[] = new Onion[onionInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt onions to the temporary array
      for (int index = 0; index < onionInventory.length; index++) {
        if (onionInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedOnionsInventory[shiftIndex++] = onionInventory[index];
        }
      }
      // reassign the onions inventory to the new one
      onionInventory = updatedOnionsInventory;
    }
  }

  /*
   * This function determined the amount of parsleys that have spoilt and removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltParsleys() {
    // counter to tally the number of spoilt parsley
    int sum = 0;
    for (int index = 0; index < parsleyInventory.length; index++) {
      if (parsleyInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt parsley
    numberOfSpoiltItems[8] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltVegetable("parsley", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt parsley
      Parsley updatedParsleysInventory[] = new Parsley[parsleyInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt parsley to the temporary array
      for (int index = 0; index < parsleyInventory.length; index++) {
        if (parsleyInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedParsleysInventory[shiftIndex++] = parsleyInventory[index];
        }
      }
      // reassign the parsley inventory to the new one
      parsleyInventory = updatedParsleysInventory;
    }
  }

  /*
   * This function determined the amount of watermelons that have spoilt and
   * removes
   * them from the inventory.There are no parameters. There are no return values
   */
  private void removeSpoiltWatermelons() {
    // counter to tally the number of spoilt watermelons
    int sum = 0;
    // increase the amount of spoilt watermelons
    for (int index = 0; index < watermelonInventory.length; index++) {
      if (watermelonInventory[index].getSpoiltValue() <= 0) {
        ++sum;
      }
    }
    // increase the amount of spoilt watermelons
    numberOfSpoiltItems[9] += sum;
    if (verbose) {
      // display the message verbosely
      Print.verboseSpoiltFruit("wateremelon", sum);
    }
    if (sum > 0) {
      // create a temporary array to hold the unspoilt watermelons
      Watermelon updatedWatermelonInventory[] = new Watermelon[watermelonInventory.length - sum];
      int shiftIndex = 0;
      // added the unspoilt watermelons to the temporary array
      for (int index = 0; index < watermelonInventory.length; index++) {
        if (watermelonInventory[index].getSpoiltValue() <= 0) {
          continue;
        } else {
          updatedWatermelonInventory[shiftIndex++] = watermelonInventory[index];
        }
      }
      // reassign the watermelon inventory to the new one
      watermelonInventory = updatedWatermelonInventory;
    }
  }

  /*
   * This function determines the amount of avocados the Supermarket can be and
   * throw a Supermarket Runs Out Of Money if the Supermarket trys to buy if there
   * is no money left.
   * The function no parameters and returns no value.
   */
  private void buyAvocados() throws Exception {
    if (cashOnHand <= 0) {
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
      throw new Exception("Supermarket Runs Out Of Money");
    } else {
      if (fruitsAreHere.getAvailablity()) {
        // determines the amount of avocados to be bought
        int numberOfAvocadosToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - avocadoInventory.length);
        // determines if the Supermarket can order avocados
        if (cashOnHand >= (numberOfAvocadosToBeBought * Avocado.COST_PRICE)) {
          // receive the avocados from the vendor
          Avocado[] avocadosBought = fruitsAreHere.sellAvocados(numberOfAvocadosToBeBought);
          // pay for the avocados
          cashOnHand -= (avocadosBought.length * Avocado.COST_PRICE);
          if (verbose) {
            // display the purchase verbosely
            Print.verboseTotalItemsPurchased("Fruits Are Here", "Avocado", avocadosBought.length);
          }
          // append the message to the log file
          Output.appendToLogFileAmountOfFruitsPurchase(avocadosBought.length, "Avocado", "Fruits Are Here",
              Avocado.COST_PRICE);
          // increase the number of avocados purchased
          itemsPurchased[0] += avocadosBought.length;
          // temporary variable to increase inventory
          Avocado[] updatedAvocadosInventory = new Avocado[avocadoInventory.length
              + avocadosBought.length];
          // add the odd inventory to the array
          for (int index = 0; index < avocadoInventory.length; index++) {
            updatedAvocadosInventory[index] = avocadoInventory[index];
          }
          // the the new inventory to the array
          int shiftIndex = avocadoInventory.length;
          for (int index = 0; index < (avocadosBought.length); index++) {
            updatedAvocadosInventory[(index + shiftIndex)] = avocadosBought[index];
          }
          // update the avocado inventory
          avocadoInventory = updatedAvocadosInventory;
        } else {
          // append the message to the log file
          Output.appendToLogFile("NotEnoughFunds: To Buy Avocados");
        }
      } else if (!fruitsAreHere.getAvailablity() && (avocadoInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          // determines the amount of avocados to be bought
          int numberOfAvocadosToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - avocadoInventory.length);
          if (cashOnHand >= (numberOfAvocadosToBeBought * Avocado.COST_PRICE)) {
            Avocado[] avocadosBought = allYouCanEat.sellAvocados(numberOfAvocadosToBeBought);
            // pay for the avocados
            cashOnHand -= (avocadosBought.length * Avocado.COST_PRICE);
            if (verbose) {
              // display the purchase verbosely
              Print.verboseTotalItemsPurchased("All You Can Eat", "Avocado", avocadosBought.length);
            }
            // append the message to the log file
            Output.appendToLogFileAmountOfFruitsPurchase(avocadosBought.length, "Avocado", "All You Can Eat",
                Avocado.COST_PRICE);
            // increase the number of avocados purchased
            itemsPurchased[0] += avocadosBought.length;
            // temporary variable to increase inventory
            Avocado[] updatedAvocadosInventory = new Avocado[avocadoInventory.length
                + avocadosBought.length];
            // add the odd inventory to the array
            for (int index = 0; index < avocadoInventory.length; index++) {
              updatedAvocadosInventory[index] = avocadoInventory[index];
            }
            int shiftIndex = avocadoInventory.length;
            // the the new inventory to the array
            for (int index = 0; index < (avocadosBought.length); index++) {
              updatedAvocadosInventory[(index + shiftIndex)] = avocadosBought[index];
            }
            // update the avocado inventory
            avocadoInventory = updatedAvocadosInventory;
          } else {
            // append the message to the log file
            Output.appendToLogFile("NotEnoughFunds: To Buy Avocados");
          }
        }
      }
    }
  }

  private void buyBananas() throws Exception {
    if (cashOnHand <= 0) {
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
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
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
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
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
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
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
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
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
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
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
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
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
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
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
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
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfParsleysToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - parsleyInventory.length);
          if (cashOnHand >= (numberOfParsleysToBeBought * Parsley.COST_PRICE)) {
            Parsley[] parsleysBought = allYouCanEat.sellParsleys(numberOfParsleysToBeBought);
            cashOnHand -= (parsleysBought.length * Parsley.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Parsley", parsleysBought.length);
            }
            // append the message to the log file
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
            // append the message to the log file
            Output.appendToLogFile("NotEnoughFunds: To Buy Parsleys");
          }
        }
      }
    }
  }

  private void buyWatermelons() throws Exception {
    if (cashOnHand <= 0) {
      // append the message to the log file
      Output.appendToLogFile("Supermarket Runs Out Of Money");
      // throw a Supermarket Runs Out Of Money
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
          // append the message to the log file
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
          // append the message to the log file
          Output.appendToLogFile("NotEnoughFunds: To Buy Parsleys");
        }
      } else if (!fruitsAreHere.getAvailablity() && (watermelonInventory.length < MAXIMUM_NUMBER_OF_FRUITS)
          && allYouCanEat.getAvailablity()) {
        if (cashOnHand <= 0) {
          // append the message to the log file
          Output.appendToLogFile("Supermarket Runs Out Of Money");
          // throw a Supermarket Runs Out Of Money
          throw new Exception("Supermarket Runs Out Of Money");
        } else {
          int numberOfWatermelonsToBeBought = (MAXIMUM_NUMBER_OF_FRUITS - watermelonInventory.length);
          if (cashOnHand >= (numberOfWatermelonsToBeBought * Watermelon.COST_PRICE)) {
            Watermelon[] watermelonsBought = allYouCanEat.sellWatermelons(numberOfWatermelonsToBeBought);
            cashOnHand -= (watermelonsBought.length * Watermelon.COST_PRICE);
            if (verbose) {
              Print.verboseTotalItemsPurchased("All You Can Eat", "Watermelon", watermelonsBought.length);
            }
            // append the message to the log file
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
            // append the message to the log file
            Output.appendToLogFile("NotEnoughFunds: To Buy Parsleys");
          }
        }
      }
    }
  }

  /*
   * This function calculates the profit for the Supermarket per cycle. There are
   * no parameters and no return values
   */
  private void calculateProfit() {
    double totalPurchases = 0;
    double totalFruitSales = 0;
    double totalVegetableSales = 0;
    double totalCostOfSpolitItems = 0;
    // loops through both arrays and calculate the total losses
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
    // loops through array and calculate the total fruits purchases
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
    // loops through array and calculate the total fruits purchases
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

    // addes the profit to the profit field
    profit += ((totalFruitSales + totalVegetableSales)
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
      vegetablesSold[index] = 0;
      fruitsSold[index] = 0;
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