public class Watermelon extends Fruit {
  final public static double SELLING_PRICE = 5.60;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 3.30;
  //The constant cost price is the cost of buying items from the vendor.

   /**
   * The below method is a constructor for the Watermelon class 
   * It calls the parent Fruit class constructor and passes the name Watermelon to it
   */
  Watermelon() {
    super("Watermelon");
  }
}