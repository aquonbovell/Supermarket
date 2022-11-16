import java.util.Random;

/*
 * Programmers' name: Aquon Bovell
 * This class have some methods and one fields
 * The functionality of this class is to represent a single customer buying from the supermarket and has functionality to not buy items which is determined by a random event.
 * The types of items and amounts are randomly generated
 */
public class Customer {
  // to determine whether the customer buys items in the Supermarket
  public static boolean willPurchase;

  static {
    willPurchase = true;
  }

  // The function returns random number of items to be purchased. There are no
  // parameters and returns an integer
  public int itemsToBePurchased() {
    Random rand = new Random();
    return rand.nextInt(21) + 1;
  }

  // The function returns random number of the type of item to be purchased. There
  // are no parameters and returns an integer
  public int typeOfItem() {
    Random rand = new Random();
    return rand.nextInt(4);
  }

  // The function returns the valuse of the willPurchase. There are no parameters
  // and returns a boolean
  public static boolean willPurchase() {
    return willPurchase;
  }

  // The function sets the willPurchase to fasle for the random event. There are
  // no parameters and no return types
  public static void willNotPurchase() {
    willPurchase = false;
  }

  // The function resets the willPurchase to true. There are no parameters and no
  // return types
  public static void reset() {
    willPurchase = true;
  }
}
