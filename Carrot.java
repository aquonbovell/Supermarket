public class Carrot extends Vegetable {
  final public static double SELLING_PRICE = 4.00;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 2.50;
  //The constant cost price is the cost of buying items from the vendor.

  /**
   * The below method is a constructor for the Carrot class 
   * It calls the parent Vegetable class constructor and passes the name Carrot to it
   */
  Carrot() {
    super("Carrot");
  }
}
