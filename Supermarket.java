

public class Supermarket {
	private int totalIterations;
	public final int DEFAULT_TOTAL_ITERATIONS = 50;
	private RandomEvent randomEvent;

	public Supermarket() {
		totalIterations = DEFAULT_TOTAL_ITERATIONS;
		randomEvent = new RandomEvent();
	}

	public void setTotalIterations(int totalIterations) {
		this.totalIterations = totalIterations;
	}

	public int getTotalIterations() {
		return totalIterations;
	}

	public void run()
	{
		for(int i = 0; i < totalIterations; i++){
			// perform simulation actions.
			if((i+1)%10 == 0 && i!= 0){

				randomEvent.run();
			}
			// execute cycles from totalIterations
				// in each cycle determine and execute random event
				// use dynamic binding to change random 
		}
	}

}