/**
 * The programmers' names are: Aquon Bovell
 * The Print class has four void static methods and no fields for the display of
 * the verbose mode is the SimulationMain and Supermarket classes.
 * The output for a specific type of action can be generalized in four ways:
 * 1) The total amount of each type of Fruit that are spoilt every cycle
 * 2) The total amount of each type of Vegetable that are spoilt every cycle
 * 3) The total amount of Fruits and Vegetables the customer buys every cycle
 * 4) The name of the vendor in which the items were purchased from, the type of
 * Fruit or Vegetable and the amount that was purchased.
 */
public class Print {
  /**
   * The purpose of this function is to output a general format of the amount of
   * each tpye of Fruit that was spoilt every cycle of the iterations. The
   * parameters are the name: which is the name of the Fruit that was spoilt and
   * the type is a String, the amount: which is the amount of each type of Fruit
   * that was spoilt and the type is an integer. There is no values returned. The
   * function is static to allow SimulationMain class to display the message
   * without an instance of Print.
   */
  public static void verboseSpoiltFruit(String name, int amount) {
    // display the spoilt fruits system message
    System.out.println("Spoilt: Fruit (" + name + "): " + amount);
  }

  /**
   * The purpose of this function is to output a general format of the amount of
   * each tpye of Vegetable that was spoilt every cycle of the iterations. The
   * parameters are the name: which is the name of the Vegetable that was spoilt
   * and the type is a String, the amount: which is the amount of each type of
   * Vegetable that was spoilt and the type is an integer. There is no values
   * returned. The function is static to allow SimulationMain class to display the
   * message without an instance of Print.
   */
  public static void verboseSpoiltVegetable(String name, int amount) {
    // display the spoilt vegetables system message
    System.out.println("Spoilt: Vegetable (" + name + "): " + amount);
  }

  /**
   * The purpose of this function is to output a general format of the total
   * amount of Vegetables and Friuts that the customers bought at the end of the
   * purchasing of items from the Supermarket class. The parameters are the
   * fruitsAmount: which is the total amount of the Fruits that where sold to the
   * customers and the type is an integer vegetablesAmount which is the total
   * amount of the Vegetables that where sold to the customers and the type is an
   * integer. There is no values returned. The function is static to allow
   * SimulationMain class to display the message without an instance of Print.
   */
  public static void verboseTotalAmountCustomerPurchase(int fruitsAmount, int vegetablesAmount) {
    // display the total amount of fruits and vegetables that the customer buys in
    // totality
    System.out
        .println("Total customer purchases: Fruits (" + fruitsAmount + "), Vegetables (" + vegetablesAmount + ")");
  }

  /**
   * The purpose of this function is to output a general format of the name of the
   * vendor in which the items were purchased from, the type of Fruit or Vegetable
   * and the amount that was purchased amount of Vegetables and Friuts that the
   * Supermarket bought. The parameters are vendor: which is the name of the
   * vendor that the Supermarket bought from and the type is String, type: which
   * is the type of Fruit or Vegetable that the Supermarket bought from the vendor
   * and the type is String, amount: which is the total amount of Fruits or
   * Vegetables that the Supermarket bought from the vendor and the type is
   * integer. There is no values returned. The function is static to allow
   * SimulationMain class to display the message without an instance of Print.
   */
  public static void verboseTotalItemsPurchased(String vendor, String type, int amount) {
    // display the name of the vendor, the type of the Fruit or Vegetable that was
    // purchased and total amount of fruits or vegetables that the was purchased
    // from the vendor
    System.out.println(vendor + ": " + type + " (" + amount + ")");
  }
}
