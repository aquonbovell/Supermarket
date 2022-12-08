import java.util.ArrayList;
import java.util.Random;

/*This class details the fruit vendor and its behaviour and operations.
*Kenez Horne*/
public class FruitsAreHere extends Vendor {
  private ArrayList<Fruit> avocadosInventory;// This field stores the number of Avocado that the vendor possesses.
  private ArrayList<Fruit> bananasInventory;// This method stores the number of banana objects that the vendor
                                            // possesses.
  private ArrayList<Fruit> limesInventory;// This field stores the number of Lime object that the vendor possesses.
  private ArrayList<Fruit> mangoesInventory;// This method stores the number of mango objects that the vendor possesses.
  private ArrayList<Fruit> watermelonsInventory;// This field stores the number of watermelon object that the Vendor
                                                // possesses.
  /*
   * FruitsAreHere constructor which assigns each field to an initial value.
   * This method neither accepts parameters nor returns any values.
   */

  FruitsAreHere() {
    super("Fruits Are Here");
    avocadosInventory = new ArrayList<>();
    bananasInventory = new ArrayList<>();
    limesInventory = new ArrayList<>();
    mangoesInventory = new ArrayList<>();
    watermelonsInventory = new ArrayList<>();
    initArray();
  }

  /*
   * This method initializes each instance of the items to an object of its type
   * This method accepts an array as parameters and returns no values
   */
  private void initArray() {
    Random rand = new Random();
    int numberOfFruits = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfFruits; counter++) {
      avocadosInventory.add(new Avocado());
    }
    numberOfFruits = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfFruits; counter++) {
      bananasInventory.add(new Banana());
    }
    numberOfFruits = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfFruits; counter++) {
      limesInventory.add(new Lime());
    }
    numberOfFruits = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfFruits; counter++) {
      mangoesInventory.add(new Mango());
    }
    numberOfFruits = rand.nextInt(91) + 10;
    for (int counter = 0; counter < numberOfFruits; counter++) {
      watermelonsInventory.add(new Watermelon());
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

  /*
   * This method retocks the items that vendor has available for sale. An calls
   * for the vendor availablity to change accordingly
   * This method neither accepts parameters nor returns a value.
   */
  public void restock() {
    restockArray();
    setAvailable();
  }

  private void restockArray() {
    restockFruits(avocadosInventory, FruitType.Avocado);
    restockFruits(bananasInventory, FruitType.Banana);
    restockFruits(limesInventory, FruitType.Lime);
    restockFruits(mangoesInventory, FruitType.Mango);
    restockFruits(watermelonsInventory, FruitType.Watermelon);
  }
}
