public class Banana extends Fruit {
  final static public double SELLING_PRICE = 15.60;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 13.30;
  //The constant cost price is the cost of buying items from the vendor.

  /**
   * The below method is a constructor for the Banana class 
   * It calls the parent Fruit class constructor and passes the name Banana to it
   */
  Banana() {
    super("Banana");
  }
}