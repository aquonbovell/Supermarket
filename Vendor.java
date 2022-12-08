import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/*This class provides a template the various types of vendors that provide items to the supermarket.
*Kenez Horne*/
public class Vendor {
  String name; // This field stores the name of the vendor.
  private boolean isAvailable; // This field stores the value of the vendors availability to provide items.
  private int[] totalItems; // These fields store the total
  private double profit;
  /*
   * Constructor method which initializes the fields of the class
   * This method returns no value but accepts a value to initialize the name of
   * the class
   */

  Vendor(String name) {
    this.name = name;
    isAvailable = true;
    profit = 0;
    totalItems = new int[10];
    for (int index = 0; index < totalItems.length; index++) {
      totalItems[index] = 0;
    }
  }

  /*
   * This method sets the availablilty of the vendor to false when the vendor can
   * not deliver goods
   * This method accepts no parameters and returns no values
   */
  public void isNotAvailable() {
    isAvailable = false;
  }

  /*
   * This method sets the availabilty of the vendor to true whenever the vendor is
   * able to deliver goods again
   * This method accepts no parameters nor returns a value
   */
  protected void setAvailable() {
    isAvailable = true;
  }

  /*
   * This method returns the vendors availabilty status when called
   * This method returns the Vendor's availability status
   */
  public boolean getAvailablity() {
    return isAvailable;
  }

  /*
   * This method returns the name of the vendor when needed
   * This method accepts not parameters
   */
  public String getName() {
    return name;
  }

  protected void restockFruits(ArrayList<Fruit> inventory, FruitType type) {
    Random rand = new Random();
    int numberOfFruits = rand.nextInt(91) + 10;
    numberOfFruits = (int) inventory.size() > numberOfFruits ? 0
        : numberOfFruits - (int) inventory.size();
    for (int counter = 0; counter < numberOfFruits; counter++) {
      switch (type) {
        case Avocado:
          inventory.add(new Avocado());
          break;
        case Banana:
          inventory.add(new Banana());
          break;
        case Lime:
          inventory.add(new Lime());
          break;
        case Mango:
          inventory.add(new Mango());
          break;
        case Watermelon:
          inventory.add(new Watermelon());
          break;
        default:
          break;
      }
    }
  }

  protected ArrayList<Fruit> orderFruits(int amount, ArrayList<Fruit> inventory) {
    ArrayList<Fruit> sellFruits = new ArrayList<>();
    if (amount > (int) inventory.size()) {
      Iterator<Fruit> itr = inventory.iterator();
      while (itr.hasNext()) {
        Fruit x = itr.next();
        sellFruits.add(x);
        itr.remove();
      }
    } else {
      int counter = 0;
      Iterator<Fruit> itr = inventory.iterator();
      while (itr.hasNext() && counter < amount) {
        Fruit x = itr.next();
        sellFruits.add(x);
        itr.remove();
        ++counter;
      }
    }
    switch (sellFruits.toString().substring(1, sellFruits.toString().indexOf('@'))) {
      case "Avocado":
        totalItems[0] += sellFruits.size();
        break;
      case "Banana":
        totalItems[1] += sellFruits.size();
        break;
      case "Lime":
        totalItems[2] += sellFruits.size();
        break;
      case "Mango":
        totalItems[3] += sellFruits.size();
        break;
      case "Watermelon":
        totalItems[4] += sellFruits.size();
        break;
      default:
        break;
    }
    return sellFruits;
  }

  protected void restockVegetables(ArrayList<Vegetable> inventory, VegetableType type) {
    Random rand = new Random();
    int numberOfVegetables = rand.nextInt(91) + 10;
    numberOfVegetables = (int) inventory.size() > numberOfVegetables ? 0
        : numberOfVegetables - (int) inventory.size();
    for (int counter = 0; counter < numberOfVegetables; counter++) {
      switch (type) {
        case Carrot:
          inventory.add(new Carrot());
          break;
        case Cucumber:
          inventory.add(new Cucumber());
          break;
        case Lettuce:
          inventory.add(new Lettuce());
          break;
        case Onion:
          inventory.add(new Onion());
          break;
        case Parsley:
          inventory.add(new Parsley());
          break;
        default:
          break;
      }
    }
  }

  protected ArrayList<Vegetable> orderVegetables(int amount, ArrayList<Vegetable> inventory) {
    ArrayList<Vegetable> sellVegetables = new ArrayList<>();
    if (amount > (int) inventory.size()) {
      Iterator<Vegetable> itr = inventory.iterator();
      while (itr.hasNext()) {
        Vegetable x = itr.next();
        sellVegetables.add(x);
        itr.remove();
      }
    } else {
      int counter = 0;
      Iterator<Vegetable> itr = inventory.iterator();
      while (itr.hasNext() && counter < amount) {
        Vegetable x = itr.next();
        sellVegetables.add(x);
        itr.remove();
        ++counter;
      }
    }
    switch (sellVegetables.toString().substring(1, sellVegetables.toString().indexOf('@'))) {
      case "Carrot":
        totalItems[5] += sellVegetables.size();
        break;
      case "Cucumber":
        totalItems[6] += sellVegetables.size();
        break;
      case "Lettuce":
        totalItems[7] += sellVegetables.size();
        break;
      case "Onion":
        totalItems[8] += sellVegetables.size();
        break;
      case "Parsley":
        totalItems[9] += sellVegetables.size();
        break;
      default:
        break;
    }
    return sellVegetables;
  }

  private void calculateProfit() {
    for (int index = 0; index < totalItems.length; index++) {
      switch (index) {
        case 0:
          profit += totalItems[index] * Avocado.COST_PRICE;
          break;
        case 1:
          profit += totalItems[index] * Banana.COST_PRICE;
          break;
        case 2:
          profit += totalItems[index] * Lime.COST_PRICE;
          break;
        case 3:
          profit += totalItems[index] * Mango.COST_PRICE;
          break;
        case 4:
          profit += totalItems[index] * Watermelon.COST_PRICE;
          break;
        case 5:
          profit += totalItems[index] * Carrot.COST_PRICE;
          break;
        case 6:
          profit += totalItems[index] * Cucumber.COST_PRICE;
          break;
        case 7:
          profit += totalItems[index] * Lettuce.COST_PRICE;
          break;
        case 8:
          profit += totalItems[index] * Onion.COST_PRICE;
          break;
        case 9:
          profit += totalItems[index] * Parsley.COST_PRICE;
          break;
        default:
          break;
      }
    }
  }

  public double getProfit() {
    calculateProfit();
    return profit;
  }
}
