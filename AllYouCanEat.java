import java.util.ArrayList;
import java.util.Random;

//This class describes a vendor which sells vegetables - Kenez Horne 
public class AllYouCanEat extends Vendor {
  private ArrayList<Vegetable> carrotsInventory;// This field stores the number of carrot items available
  private ArrayList<Vegetable> lettucesInventory;// This field stores the number of Lettuce items available
  private ArrayList<Vegetable> cucumbersInventory;// This field stores the number of Cucumber items available
  private ArrayList<Vegetable> parsleysInventory;// This field stores the number Parsley items available
  private ArrayList<Vegetable> onionsInventory;// This field stores the number of Onion items available
  private ArrayList<Fruit> avocadosInventory;// This field stores the number of Avocado that the vendor possesses.
  private ArrayList<Fruit> bananasInventory;// This method stores the number of banana objects that the vendor
                                            // possesses.
  private ArrayList<Fruit> limesInventory;// This field stores the number of Lime object that the vendor possesses.
  private ArrayList<Fruit> mangoesInventory;// This method stores the number of mango objects that the vendor possesses.
  private ArrayList<Fruit> watermelonsInventory;// This field stores the number of watermelon object that the Vendor
                                                // possesses.

  /*
   * TastyVegetables is the class constructor and it initializes all of the fields
   * of the class to a starting value
   * This method neither accepts parameters nor returns values.
   */
  AllYouCanEat() {
    super("All You Can Eat");
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
    initArray();
  }

  /*
   * This method fills all of the items available spaces with an instance of that
   * item.
   * This method accepts an array but returns no value
   */
  private void initArray() {
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

  public ArrayList<Fruit> orderFruits(int amount, FruitType type) {
    switch (type) {
      case Avocado:
        return orderFruits(amount, avocadosInventory);
      case Banana:
        return orderFruits(amount, bananasInventory);
      case Lime:
        return orderFruits(amount, limesInventory);
      case Mango:
        return orderFruits(amount, mangoesInventory);
      case Watermelon:
        return orderFruits(amount, watermelonsInventory);
      default:
        return null;
    }
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

  private void restockInventory() {
    restockFruits(avocadosInventory, FruitType.Avocado);
    restockFruits(bananasInventory, FruitType.Banana);
    restockFruits(limesInventory, FruitType.Lime);
    restockFruits(mangoesInventory, FruitType.Mango);
    restockFruits(watermelonsInventory, FruitType.Watermelon);
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
}
