import java.util.Scanner;

public class SimulationMain {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Supermarket market = new Supermarket();

		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("iter")) {
				if (i < args.length - 1) {
					i++;
					try {
						int iter = Integer.parseInt(args[i]);
						System.out.println(iter);
						// set total simulation iterations
					} catch (NumberFormatException e) {
						System.out.println("Invalid iter command, total number of iterations is missing");
					}

				}
			}
		}
		char selection;

		System.out.println("Supermarket Simulator");
		System.out.println("=====================");
		System.out.println("A. Start the Simulation");
		System.out.println("B. Display the Log");
		System.out.println("C. Exit");
		System.out.print("Enter your selection: ");

		selection = scan.next().toUpperCase().charAt(0);
		while (Character.getNumericValue(selection) >= 13 || Character.getNumericValue(selection) <= 9) {
			System.out.println("Invalid selection");
			System.out.println("A. Start the Simulation");
			System.out.println("B. Display the Log");
			System.out.println("C. Exit");
			System.out.print("Enter your selection: ");
			selection = scan.next().toUpperCase().charAt(0);
		}

		switch (selection) {

			case 'A':
				System.out.println("starting simulation");
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
		// market.run();
		// Display the menu
		// Call this method to run the simulastor; market.run();
	}
}