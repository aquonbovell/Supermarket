public class Cucumber extends Vegetable {
  final public static double SELLING_PRICE = 3.10;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 1.50;
  //The constant cost price is the cost of buying items from the vendor.

  /**
   * The below method is a constructor for the Cucumber class 
   * It calls the parent Vegetable class constructor and passes the name Cucumber to it
   */
  Cucumber() {
    super("Cucumber");
  }
}
