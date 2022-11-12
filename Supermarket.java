import java.util.Random;

public class Supermarket {
	public final int DEFAULT_TOTAL_ITERATIONS = 50;
	private int totalIterations;
	public Fruits[] fruitsInventorty;
  private int lossCausedByRandomEvent;


	public Supermarket() {
		totalIterations = DEFAULT_TOTAL_ITERATIONS;
		fruitsInventorty = new Fruits[5];
		initArray(fruitsInventorty);
		lossCausedByRandomEvent = 0 ;
	}
	private void initArray(Fruits array[]){
		for (int i = 0; i < array.length; i++) {
			array[i] = new Fruits();
		}
	}

	public void setTotalIterations(int totalIterations) {
		this.totalIterations = totalIterations;
	}

	public int getTotalIterations() {
		return totalIterations;
	}

	public void run() {
		for (int i = 0; i < totalIterations; i++) {
			// perform simulation actions.
			if ((i + 1) % 10 == 0 && i != 0) {
				randomEvent();
			}
			// decrement spoilt values
		}
	}

	private void randomEvent() {
			Random randomNumber = new Random();
			switch(randomNumber.nextInt(5)){
				case 0:
					lossCausedByRandomEvent += fridgeBreakDown();
					System.out.println(lossCausedByRandomEvent);
					break;
				case 1:
					electricityGoesOff();
					break;
				case 2:
					noGoodsDelivered();
					break;
				case 3:
					spoilItemsFaster();
					break;
				case 4:
					noPurchases();
					break;
				default:
					break;
			}
		}
	
		private int fridgeBreakDown() {
			Random randomNumber = new Random();
			System.out.println("fridgeBreakDown");
			return randomNumber.nextInt(101);
		}
	
		private void electricityGoesOff() {
			Random randomNumber = new Random();
			System.out.println("Electricity went off for "+(randomNumber.nextInt(5) + 1)+"hrs");
		}
		
		private void noGoodsDelivered() {
			Random randomNumber = new Random();
			System.out.println("noGoodsDelivered by"+randomNumber.nextInt(3));
		}
	
		private void spoilItemsFaster() {
			Random randomNumber = new Random();
			System.out.println("spoilItemsFaster by"+randomNumber.nextInt(11)+"%");
		}
	
		private void noPurchases() {
			System.out.println("noPurchases");
			// System.out.println(0);
		}

	private void sellGoods(){
		// execute customers buying goods
	}
}
