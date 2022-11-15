import java.util.Scanner;

public class Test {
  private static boolean isVerbose;
  private static String[] commandsPassed;

  static {
    isVerbose = false;
  }

  public static void main(String[] args) {
    commandsPassed = new String[args.length];
    Supermarket market = new Supermarket();
    Scanner scan = new Scanner(System.in);

    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("iter")) {
        commandsPassed[0] = args[i];
        if (i < args.length - 1) {
          i++;
          try {
            int iter = Integer.parseInt(args[i]);
            commandsPassed[1] = new String(args[i]);
            // set total simulation iterations
            market.setTotalIterations(iter);
          } catch (NumberFormatException e) {
            System.out.println("Invalid iter command, total number of iterations is missing");
          }
        }
      }
      if (args[i].equalsIgnoreCase("verbose")) {
        commandsPassed[args.length - 1] = "verbose";
        isVerbose = true;
        break;
      }
    }

    if (isVerbose) {
      char selection;
      System.out.println("Supermarket Simulator");
      System.out.println("=====================");
      System.out.println("A. Start the Simulation");
      System.out.println("B. Display the Log");
      System.out.println("C. Exit");
      System.out.print("Enter your selection: ");
      selection = scan.next().toUpperCase().charAt(0);
      while (Character.getNumericValue(selection) >= 13 ||
          Character.getNumericValue(selection) <= 9) {
        System.out.println("Invalid selection, the options are as follows");
        System.out.println("A. Start the Simulation");
        System.out.println("B. Display the Log");
        System.out.println("C. Exit");
        System.out.print("Enter your selection: ");
        selection = scan.next().toUpperCase().charAt(0);
      }
      switch (selection) {

        case 'A':
          System.out.println("Starting simulation");
          System.out.println("Commands passed are:");
          for (int i = 0; i < commandsPassed.length; i++) {
            System.out.println(commandsPassed[i]);
          }
          // market.run();
          System.out.println("Simulation has ended");
          break;
        case 'B':
          System.out.println("Displaying Log");
          break;
        case 'C':
          System.out.println("Exiting");
          break;
        default:
          System.out.println("Invalid selection");
          break;
      }
      scan.close();

    } else {
      char selection;
      System.out.println("Supermarket Simulator");
      System.out.println("=====================");
      System.out.println("A. Start the Simulation");
      System.out.println("B. Display the Log");
      System.out.println("C. Exit");
      System.out.print("Enter your selection: ");
      selection = scan.next().toUpperCase().charAt(0);
      while (Character.getNumericValue(selection) >= 13 ||
          Character.getNumericValue(selection) <= 9) {
        System.out.println("Invalid selection, the options are as follows");
        System.out.println("A. Start the Simulation");
        System.out.println("B. Display the Log");
        System.out.println("C. Exit");
        System.out.print("Enter your selection: ");
        selection = scan.next().toUpperCase().charAt(0);
      }
      switch (selection) {

        case 'A':
          System.out.println("Starting simulation");
          if (commandsPassed.length > 0) {
            System.out.println("Commands passed are:");
            for (int i = 0; i < commandsPassed.length; i++) {
              System.out.println(commandsPassed[i]);
            }
          } else {
            System.out.println("No commands are passed");
          }
          market.run();
          System.out.println("Simulation has ended");
          break;
        case 'B':
          System.out.println("Displaying Log");
          break;
        case 'C':
          System.out.println("Exiting");
          System.exit(0);
          break;
        default:
          System.out.println("Invalid selection");
          break;
      }
    }

    scan.close();
  }
}
