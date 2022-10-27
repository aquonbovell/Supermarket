

public class Supermarket {
	private int totalIterations;
	public final int DEFAULT_TOTAL_ITERATIONS = 50;

	public Supermarket() {
		totalIterations = DEFAULT_TOTAL_ITERATIONS;

	}

	public void run() {
		for (int i = 0; i < totalIterations; i++) {
			// perform simulation actions.
			System.out.println("iteration");
		}
	}

}