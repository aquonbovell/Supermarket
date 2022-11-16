public class Mango extends Fruit {
  final static public double SELLING_PRICE = 4.10;
  //The constant selling price is the price that the supermarket sells its items to 
  //the customer
  final public static double COST_PRICE = 2.90;
  //The constant cost price is the cost of buying items from the vendor.

   /**
   * The below method is a constructor for the Mango class 
   * It calls the parent Fruit class constructor and passes the name Mango to it
   */
  public Mango() {
    super("Mango");
  }
}