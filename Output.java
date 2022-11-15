import java.io.FileWriter;
import java.io.IOException;

public class Output {

  static void createLogFile() {
    try {
      FileWriter writer = new FileWriter("log.txt");
      writer.write("Supermarket Simulation");
      writer.close();
    } catch (IOException e) {
      System.out.println("Could not create log file.");
    }
  }

  static void appendCustomerPurchaseFruitsToLogFile(int amount, String name, double cost) {
    try {
      FileWriter writer = new FileWriter("log.txt", true);
      writer.append("\n(Fruit) " + amount + " " + name + "s were sold and received $"
          + cost);
      writer.close();
    } catch (IOException e) {
      System.out.println("Could not write to log file.");
    }
  }

  static void appendCustomerPurchaseVegetablesToLogFile(int amount, String name, double cost) {
    try {
      FileWriter writer = new FileWriter("log.txt", true);
      writer.append("\n(Vegetable) " + amount + " " + name + "s were sold and received $"
          + cost);
      writer.close();
    } catch (IOException e) {
      System.out.println("Could not write to log file.");
    }
  }

  static void appendToLogFileAmountOfFruitsPurchase(int amount, String name, String vendorName, double unitCost,
      double totalPrice) {
    try {
      FileWriter writer = new FileWriter("log.txt", true);
      writer.append("\n(Fruit) " + amount + " " + name + "s were purchased from " + vendorName + " at $"
          + unitCost
          + ". Total Cost: $"
          + (amount * unitCost));
      writer.close();
    } catch (IOException e) {
      System.out.println("Could not write log file.");
    }
  }

  static void appendToLogFileAmountOfVegetablesPurchase(int amount, String name, String vendorName, double unitCost,
      double totalPrice) {
    try {
      FileWriter writer = new FileWriter("log.txt", true);
      writer.append("\n(Vegetable) " + amount + " " + name + "s were purchased from " + vendorName + " at $"
          + unitCost
          + ". Total Cost: $"
          + (amount * unitCost));
      writer.close();
    } catch (IOException e) {
      System.out.println("Could not write log file.");
    }
  }

  public static void appendToLogFile(String logText) {
    try {
      FileWriter writer = new FileWriter("log.txt", true);
      writer.append("\n" + logText);
      writer.close();
    } catch (IOException e) {
      System.out.println("Could not write to log file.");
    }
  }
}