import java.util.ArrayList;
import java.util.Random;

//This class describes a vendor which sells vegetables - Kenez Horne 
public class TastyVegetables extends Vendor {
  private ArrayList<Vegetable> carrotsInventory; // This field stores the number of carrot items available
  private ArrayList<Vegetable> lettucesInventory;// This field stores the number of Lettuce items available
  private ArrayList<Vegetable> cucumbersInventory;// This field stores the number of Cucumber items available
  private ArrayList<Vegetable> parsleysInventory;// This field stores the number Parsley items available
  private ArrayList<Vegetable> onionsInventory;// This field stores the number of Onion items available

  /*
   * TastyVegetables is the class constructor and it initializes all of the fields
   * of the class to a starting value
   * This method neither accepts parameters nor returns values.
   */
  TastyVegetables() {
    super("Tasty Vegetables");
    carrotsInventory = new ArrayList<>();
    lettucesInventory = new ArrayList<>();
    cucumbersInventory = new ArrayList<>();
    parsleysInventory = new ArrayList<>();
    onionsInventory = new ArrayList<>();
    initArray();
  }

  /*
   * This method fills all of the items available spaces with an instance of that
   * item.
   * This method accepts an array but returns no value
   */
  private void initArray() {
    Random rand = new Random();
    int numberOfVegetables = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfVegetables; counter++) {
      carrotsInventory.add(new Carrot());
    }
    numberOfVegetables = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfVegetables; counter++) {
      cucumbersInventory.add(new Cucumber());
    }
    numberOfVegetables = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfVegetables; counter++) {
      lettucesInventory.add(new Lettuce());
    }
    numberOfVegetables = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfVegetables; counter++) {
      onionsInventory.add(new Onion());
    }
    numberOfVegetables = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfVegetables; counter++) {
      parsleysInventory.add(new Parsley());
    }
  }

  private void restockInventory() {
    restockVegetables(carrotsInventory, VegetableType.Carrot);
    restockVegetables(cucumbersInventory, VegetableType.Cucumber);
    restockVegetables(lettucesInventory, VegetableType.Lettuce);
    restockVegetables(onionsInventory, VegetableType.Onion);
    restockVegetables(parsleysInventory, VegetableType.Parsley);
  }

  /*
   * This method restocks the vendors inventory of items.
   * After restocking the availabilty of vendor changes.
   */
  public void restock() {
    setAvailable();
    restockInventory();
  }

  public ArrayList<Vegetable> orderVegetables(int amount, VegetableType type) {
    switch (type) {
      case Carrot:
        return orderVegetables(amount, carrotsInventory);
      case Cucumber:
        return orderVegetables(amount, cucumbersInventory);
      case Lettuce:
        return orderVegetables(amount, lettucesInventory);
      case Onion:
        return orderVegetables(amount, onionsInventory);
      case Parsley:
        return orderVegetables(amount, parsleysInventory);
      default:
        return null;
    }
  }
}
