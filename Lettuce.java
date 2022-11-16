public class Lettuce extends Vegetable {
  final public static double SELLING_PRICE = 6.40;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 4.50;
  //The constant cost price is the cost of buying items from the vendor.

  /**
   * The below method is a constructor for the Lettuce class 
   * It calls the parent Vegetable class constructor and passes the name Lettuce to it
   */
  Lettuce() {
    super("Lettuce");
  }
}