public class Avocado extends Fruit {
  final static public double SELLING_PRICE = 16.60;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 13.60;
  //The constant cost price is the cost of buying items from the vendor.

  /**
   * The below method is a constructor for the Avocado class 
   * It calls the parent Fruit class constructor and passes the name Avacado to it
   */
  Avocado() {
    super("Avocdo");
  }
}
