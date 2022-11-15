import java.io.File;
import java.util.Scanner;

/**
 * The names of the programmers are: Aquon Bovell and Dwayne Archer
 * The SimulationMain class contains one method called main and is the entry
 * point to the entire Supermarket Simulation and two fields.
 * The SimulationMain class's functionality is to execute the run method of the
 * Supermarket class in two modes: verbose and nonverbose.
 * The default mode is non-verbose, and the "verbose" command must be used at
 * the command line to activate verbose mode It also requires a "iter" command,
 * which must be followed by an iteration number. A "log" command and a file
 * path are also required to define where the log file will be saved.
 */
public class SimulationMain {
  // determines if to run the verbose functionality or not to
  // the default is false
  private static boolean isVerbose;
  // holds commands that are passed in a at the command line
  private static String[] commandsPassed;

  static {
    // initalise all the static field of SimulationMain
    commandsPassed = new String[0];
    isVerbose = false;
  }

  public static void main(String[] args) {
    // creates an instance of the Supermarket Class
    Supermarket market = new Supermarket();
    // creates an instance of the Scanner class
    Scanner scan = new Scanner(System.in);

    // loops through the arguments passed in at the command line
    for (int i = 0; i < args.length; i++) {
      // check for if the "iter" command is passed in
      if (args[i].equalsIgnoreCase("iter")) {
        commandsPassed = new String[2];
        // save the "iter" command to commandsPassed
        commandsPassed[0] = args[i];
        if (i < args.length - 1) {
          i++;
          try {
            // try to parse the number of iterations
            int iter = Integer.parseInt(args[i]);
            commandsPassed[1] = new String(args[i]);
            // set total simulation iterations
            market.setTotalIterations(iter);
            // catch the NumberFormatException Exception
          } catch (NumberFormatException e) {
            // Output the error message
            System.out.println("Invalid iter command, total number of iterations is missing");
          }
        }
      }
      // check for if the "verbose" command is passed in
      if (args[i].equalsIgnoreCase("verbose")) {
        // check if there are commands already in the commandsPassed field
        if (commandsPassed.length > 0) {
          // temporary variable to hold the commands that are already saved and the
          // "verbose" command
          String[] updatedCommandsPassed = new String[commandsPassed.length + 1];
          // addes all the commands that are already in the commandsPassed field to the
          // updatedCommandsPassed variable
          for (int index = 0; index < commandsPassed.length; index++) {
            updatedCommandsPassed[index] = commandsPassed[index];
          }
          // append the "verbose" command to the updatedCommandsPassed variable
          updatedCommandsPassed[args.length - 1] = args[i];
          // updates the commandsPassed field to hold the previous commands and the
          // "verbose" command
          commandsPassed = updatedCommandsPassed;
        } else {
          // if no commands have been added increase the commandsPassed field by 1
          commandsPassed = new String[1];
          // append the "verbose" command to the commandsPassed field
          commandsPassed[0] = args[i];
        }
        // set the isVerbose field to true to turn on the verbose functionality
        isVerbose = true;
      }
      // check for if the "log" command is passed in
      if (args[i].equalsIgnoreCase("log")) {
        // check if there is a file path to be passed in
        if (i < args.length - 1) {
          i++;
          // save the file path to the file location field in the Output class
          Output.setFileLocation(args[i]);
          // check if there are commands already in the commandsPassed field
          if (commandsPassed.length > 0) {
            // temporary variable to hold the commands that are already saved "log" command
            // and the file path
            String[] updatedCommandsPassed = new String[commandsPassed.length + 2];
            // addes all the commands that are already in the commandsPassed field to the
            // updatedCommandsPassed variable
            for (int index = 0; index < commandsPassed.length; index++) {
              updatedCommandsPassed[index] = commandsPassed[index];
            }
            // append the "log" command and the file path to the uupdatedCommandsPassed
            // variable
            updatedCommandsPassed[updatedCommandsPassed.length - 2] = "log";
            updatedCommandsPassed[updatedCommandsPassed.length - 1] = args[i];
            // updates the commandsPassed field to hold the previous commands and the
            // "log" command and the file path
            commandsPassed = updatedCommandsPassed;
          } else {
            // if no commands have been added increase the commandsPassed field by 2
            commandsPassed = new String[2];
            // append the "log" command to the commandsPassed field
            commandsPassed[0] = "log";
            // append the "file path to the commandsPassed field
            commandsPassed[1] = args[i];
          }
        } else {
          // if no commands have been added increase the commandsPassed field by 1
          commandsPassed = new String[1];
          // append the "log" command to the commandsPassed field
          commandsPassed[0] = args[i];
        }
      }
    }

    // holds the user selection for the menu
    char selection;
    System.out.println("Supermarket Simulator");
    System.out.println("=====================");
    System.out.println("A. Start the Simulation");
    System.out.println("B. Display the Log");
    System.out.println("C. Exit");
    System.out.print("Enter your selection: ");

    // reads in the first character of the first word that is inputted at the
    // command line
    selection = scan.next().toUpperCase().charAt(0);

    // checks if the user selection is invalid and reprompt the following messages
    // until the user enters a valid selection
    while (Character.getNumericValue(selection) >= 13 ||
        Character.getNumericValue(selection) <= 9) {
      System.out.println("Invalid selection, the options are as follows: ");
      System.out.println("A. Start the Simulation");
      System.out.println("B. Display the Log");
      System.out.println("C. Exit");
      System.out.print("Enter your selection: ");
      // reads in the first character of the first word that is inputted at the
      // command line
      selection = scan.next().toUpperCase().charAt(0);
    }

    // close the scan object
    scan.close();
    // checks with selection the user made
    switch (selection) {

      case 'A':
        System.out.println("Starting simulation");
        // if there are no commands display the following message
        if (commandsPassed.length == 0) {
          System.out.println("No commands are passed");
        } else {
          // if there are commands each command will be listed
          System.out.println("Commands passed are:");
          for (int i = 0; i < commandsPassed.length; i++) {
            System.out.println(commandsPassed[i]);
          }
        }
        // checks if the isVerbose is turned on then the runVerbose in market will run
        // in the verbose mode
        if (isVerbose)
          market.runVerbose();
        // checks if the isVerbose is turned off then the run will run in it's default
        // mode i.e verbose off
        else
          market.run();
        System.out.println("Simulation has ended");
        break;
      case 'B':
        System.out.println("Displaying Log");
        try {
          // creates a instance of fileInput from the file path from the Output class
          Scanner fileInput = new Scanner(new File(new String(Output.getFileLocation())));
          // a variable to hold each line of the log file
          String logLine;
          // loops through all the line the log file
          while (fileInput.hasNextLine()) {
            logLine = fileInput.nextLine();
            // displays each line that was read into the logLine variable
            System.out.println(logLine);
          }
          // close the instance of the fileInput to prevent any IO Exception
          fileInput.close();
          // catches any Exception that is thrown and displays message indicated below
        } catch (Exception e) {
          System.out.println("File could not be access.");

        }
        break;
      case 'C':
        // Exits the main function
        System.out.println("Exiting");
        break;
      default:
        // Displays an invalid message if the selection is invalid
        System.out.println("Invalid selection");
        break;
    }
  }
}
