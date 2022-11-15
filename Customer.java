import java.util.Random;

public class Customer {
  public static boolean willPurchase;

  static {
    willPurchase = true;
  }

  Customer() {
  }

  public int itemsToBePurchased() {
    Random rand = new Random();
    return rand.nextInt(21) + 1;
  }

  public int typeOfItem() {
    Random rand = new Random();
    return rand.nextInt(4);
  }

  public static boolean willPurchase() {
    return willPurchase;
  }

  public static void willNotPurchase() {
    willPurchase = false;
  }

  public static void reset() {
    willPurchase = true;
  }
}
