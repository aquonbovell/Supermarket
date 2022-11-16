public class Parsley extends Vegetable {
  final public static double SELLING_PRICE = 3.60;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 1.80;
  //The constant cost price is the cost of buying items from the vendor.

  /**
   * The below method is a constructor for the Parsley class 
   * It calls the parent Vegetable class constructor and passes the name Parsley to it
   */
  Parsley() {
    super("Parsley");
  }
}