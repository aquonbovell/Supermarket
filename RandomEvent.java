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

  private void fridgeBreakDown() {
    Random randomNumber = new Random();
    System.out.println("fridgeBreakDown");
    System.out.println(randomNumber.nextInt(101));
  }

  private void electricityGoesOff() {
    Random randomNumber = new Random();
    System.out.println("electricityGoesOff");
    System.out.println(randomNumber.nextInt(5) + 1);
  }
  
  private void noGoodsDelivered() {
    Random randomNumber = new Random();
    System.out.println("noGoodsDelivered");
    System.out.println(randomNumber.nextInt(3));
  }

  private void spoilItemsFaster() {
    Random randomNumber = new Random();
    System.out.println("spoilItemsFaster");
    System.out.println(randomNumber.nextInt(11));
  }

  private void noPurchases() {
    System.out.println("noPurchases");
    System.out.println(0);
  }
}
