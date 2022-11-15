public class Print {
  public static void verboseSpoiltFruit(String name, int amount) {
    System.out.println("Spoilt: Fruit (" + name + "): " + amount);
  }

  public static void verboseSpoiltVegetable(String name, int amount) {
    System.out.println("Spoilt: Vegetable (" + name + "): " + amount);
  }

  public static void verboseTotalAmountCustomerPurchase(int fruitsAmount, int vegetableAmount) {
    System.out.println("Total customer purchases: Fruits (" + fruitsAmount + "), Vegetables (" + vegetableAmount + ")");
  }

  public static void verboseTotalItemsPurchased(String vendor, String type, int amount) {
    System.out.println(vendor + ": " + type + " (" + amount + ")");
  }
}
