public class SimulationMain
{
	public static void main(String args[]){
		Supermarket market = new Supermarket();
		
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("iter")) {
				if(i < args.length - 1){
					i++;
					try{
						int iter = Integer.parseInt(args[i]);
						System.out.println(iter);
						// set total simulation iterations
					}
					catch(NumberFormatException e) {
						System.out.println("Invalid iter command, total number of iterations is missing");
					}
					
				}
			}
		}
		market.run();
		// Display the menu
		// Call this method to run the simulastor; market.run();
	}
}