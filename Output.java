import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * The programmers' names are: Aquon Bovell
 * The Output class has six methods and one field for the location of the log
 * file to be saved. The default destination is "log.txt" and is saved in the
 * same directory as the program.
 * The functionality of the Output class is to write a specific action to a
 * unique format that can be easily read from the log file, and the format can
 * be easily changed for customer purchases, the amount of each item purchased
 * from a specific vendor, random events that have occurred, and exceptions that
 * are thrown.
 */
public class Output {

  // holds the file path of where the file will be sored the default is "log.txt"
  // in the same directory as the program. It is a character array to allow the
  // modification when the user wants to change the default location
  static private char[] fileLocation = "log.txt".toCharArray();

  /**
   * The purpose of this function is to allow the user to specify a different
   * location for where the log file is to be saved since the default is "log.txt"
   * in the same directory as the program. This function takes one parameter and
   * that is a String of where the file path will be stored. There is no return
   * type. The function is static to allow SimulationMain class to set the
   * location of the log file from the commmand line. whenever and does not
   * require and instance of the Output class.
   */
  public static void setFileLocation(String location) {
    // set the new location of the log file
    fileLocation = location.toCharArray();
  }

  /**
   * The purpose of this function is to return the saved file location of the log
   * file of the Supermarket Simulation, where the log can be displayed to the
   * screen when the user selects option 'B' to display the log. This function
   * takes no parameters and simply returns the location of the log file in a
   * character array. The function is static to allow SimulationMain to determine
   * where to go to read the log file whenever and does not require and instance
   * of the Output class.
   */
  public static char[] getFileLocation() {
    // returns the location of the log file
    return fileLocation;
  }

  /**
   * The purpose of this function is to try to create a new log file at the
   * location of the fileLocation field with the header "Supermarket Simulation",
   * so the output from the iterations will have a title. The function takes no
   * parameters, and return nothing. The function is static to allow Supermarket
   * to create the log file whenever and does not require and instance of the
   * Output class.
   */
  static void createLogFile() {
    try {
      // creates in instance of the fileWriter to write at the location of the
      // location specified by fileLocation
      FileWriter writer = new FileWriter(new String(fileLocation));
      // write over the content of the file with the message below
      writer.write("Supermarket Simulation");
      // closes the fileWriter object
      writer.close();
      // catch the IOExecption and display the following message
    } catch (IOException e) {
      System.out.println("Could not create log file.");
    }
  }

  /**
   * The purpose of this function is to try to append a general format of what the
   * customer purchased to the log file at the location of the fileLocation field.
   * The parameters are the amount: which is the quantity of the type of Fruit
   * that the customer purchase and is a integer, the name: which is the name of
   * the type of Fruit that the customer purchases and is a String and cost: which
   * is the total price of the type of Fruit that the customer purchased and is a
   * double and return nothing. The function is static to allow Supermarket to
   * append the log file whenever and does not require and instance of the Output
   * class.
   */
  static void appendCustomerPurchaseFruitsToLogFile(int amount, String name, double cost) {
    try {
      // create a decimal format for the cost
      DecimalFormat decimalFormat = new DecimalFormat("#.00");
      // creates in instance of the fileWriter to write at the location of the
      // location specified by fileLocation to append output
      FileWriter writer = new FileWriter(new String(fileLocation), true);
      // append the message below to the log file
      writer.append("\n(Fruit) " + amount + " " + name + "s were sold and received $"
          + decimalFormat.format(cost));
      // closes the fileWriter object
      writer.close();
      // catch the IOExecption and display the following message
    } catch (IOException e) {
      System.out.println("Could not write to log file.");
    }
  }

  /**
   * The purpose of this function is to try to append a general format of what the
   * customer purchased to the log file at the location of the fileLocation field.
   * The parameters are the amount: which is the quantity of the type of Vegetable
   * that the customer purchased and is a integer, the name: which is the name of
   * the type of Vegetable that the customer purchased and is a String and cost:
   * which is the total price of the type of Vegetable that the customer purchased
   * and is a double and return nothing. The function is static to allow
   * Supermarket to append the log file whenever and does not require and instance
   * of the Output class.
   */
  static void appendCustomerPurchaseVegetablesToLogFile(int amount, String name, double cost) {
    try {
      // create a decimal format for the cost
      DecimalFormat decimalFormat = new DecimalFormat("#.00");
      // creates in instance of the fileWriter to write at the location of the
      // location specified by fileLocation to append output
      FileWriter writer = new FileWriter(new String(fileLocation), true);
      // append the message below to the log file
      writer.append("\n(Vegetable) " + amount + " " + name + "s were sold and received $"
          + decimalFormat.format(cost));
      // closes the fileWriter object
      writer.close();
      // catch the IOExecption and display the following message
    } catch (IOException e) {
      System.out.println("Could not write to log file.");
    }
  }

  /**
   * The purpose of this function is to try to append a general format how much of
   * the type of Fruit was bought from a particular vendor to the log file at the
   * location of the fileLocation field. The parameters are the amount: which is
   * the quantity of the type of Fruit that the supermarket purchased
   * and is a integer, the name: which is the name of the type of Fruit that the
   * Supermarket purchased and is a String, vendorName: which is the name of the
   * Vendor the Supermarket purchased from and is a String and unitCost: which is
   * the unit price of the type of Fruit that the Supermarket bought and is a
   * double and return nothing. The function is static to allow Supermarket to
   * append the log file whenever and does not require and instance of the Output
   * class.
   */
  static void appendToLogFileAmountOfFruitsPurchase(int amount, String name, String vendorName, double unitCost) {
    try {
      // create a decimal format for the unitCost and totalPrice
      DecimalFormat decimalFormat = new DecimalFormat("#.00");
      // creates in instance of the fileWriter to write at the location of the
      // location specified by fileLocation to append output
      FileWriter writer = new FileWriter(new String(fileLocation), true);
      // append the message below to the log file
      writer.append("\n(Fruit) " + amount + " " + name + "s were purchased from " + vendorName + " at $"
          + decimalFormat.format(unitCost)
          + ". Total Cost: $"
          + decimalFormat.format(amount * unitCost));
      // closes the fileWriter object
      writer.close();
      // catch the IOExecption and display the following message
    } catch (IOException e) {
      System.out.println("Could not write log file.");
    }
  }

  /**
   * The purpose of this function is to try to append a general format how much of
   * the type of Fruit was bought from a particular vendor to the log file at the
   * location of the fileLocation field. The parameters are the amount: which is
   * the quantity of the type of Vegetable that the supermarket purchased
   * and is a integer, the name: which is the name of the type of Vegetable that
   * the Supermarket purchased and is a String, vendorName: which is the name of
   * the Vendor the Supermarket purchased from and is a String and unitCost: which
   * is the unit price of the type of Vegetable that the Supermarket bought and
   * is a double and return nothing. The function is static to allow Supermarket
   * to append the log file whenever and does not require and instance of the
   * Output class.
   */
  static void appendToLogFileAmountOfVegetablesPurchase(int amount, String name, String vendorName, double unitCost) {
    try {
      // create a decimal format for the unitCost and totalPrice
      DecimalFormat decimalFormat = new DecimalFormat("#.00");
      // creates in instance of the fileWriter to write at the location of the
      // location specified by fileLocation to append output
      FileWriter writer = new FileWriter(new String(fileLocation), true);
      // append the message below to the log file
      writer.append("\n(Vegetable) " + amount + " " + name + "s were purchased from " + vendorName + " at $"
          + decimalFormat.format(unitCost)
          + ". Total Cost: $"
          + decimalFormat.format(amount * unitCost));
      // closes the fileWriter object
      writer.close();
      // catch the IOExecption and display the following message
    } catch (IOException e) {
      System.out.println("Could not write log file.");
    }
  }

  /**
   * The purpose of this function is to try to append a string text to the log
   * file at the location of the fileLocation field. The parameter is logText:
   * which is the text that is to be appended to the log file. The return nothing.
   * The function is static to allow Supermarket to append the log file whenever
   * and does not require and instance of the Output class.
   */
  public static void appendToLogFile(String logText) {
    try {
      // creates in instance of the fileWriter to write at the location of the
      // location specified by fileLocation to append output
      FileWriter writer = new FileWriter(new String(fileLocation), true);
      // append the message below to the log file
      writer.append("\n" + logText);
      // closes the fileWriter object
      writer.close();
      // catch the IOExecption and display the following message
    } catch (IOException e) {
      System.out.println("Could not write to log file.");
    }
  }
}