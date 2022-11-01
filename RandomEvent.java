import java.util.Random;

final public class RandomEvent {
  final public void run(){
    Random randomNumber = new Random();
    switch(randomNumber.nextInt(5)){
      case 0:
        fridgeBreakDown();
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

  private void noPurchases() {
    System.out.println("noPurchases");
  }

  private void spoilItemsFaster() {
    System.out.println("spoilItemsFaster");
  }

  private void noGoodsDelivered() {
    System.out.println("noGoodsDelivered");
  }

  private void electricityGoesOff() {
    System.out.println("electricityGoesOff");
  }

  private void fridgeBreakDown() {
    System.out.println("fridgeBreakDown");
  }
}
