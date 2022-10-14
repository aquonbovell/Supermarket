import java.util.Scanner;

public class Test{
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String selection;
    System.out.println("Supermarket Simulator");
    System.out.println("=====================");
    System.out.println("A. Start the Simulation");
    System.out.println("B. Display log");
    System.out.println("C. Exit");
    System.out.print("Enter your selection: ");
    selection = scan.nextLine();
    scan.close();
    System.out.println("You have selected "+selection);
  }
}