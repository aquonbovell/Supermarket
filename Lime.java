public class Lime extends Fruit {
  final static public double SELLING_PRICE = 38.6;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final static public double COST_PRICE = 25.6;
  //The constant cost price is the cost of buying items from the vendor.
  
   /**
   * The below method is a constructor for the Lime class 
   * It calls the parent Fruit class constructor and passes the name Lime to it
   */
  Lime() {
    super("Lime");
  }
}