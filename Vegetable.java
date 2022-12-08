import java.util.Random;

public class Vegetable extends Item {
  private String nameOfVegetable;
  private int spoiltValue;

  Vegetable(String name) {
    Random rand = new Random();
    nameOfVegetable = name;
    spoiltValue = rand.nextInt(6) + 5;
  }

  final public String getName() {
    return this.nameOfVegetable;
  }

  final public int getSpoiltValue() {
    return this.spoiltValue;
  }

  final public void spoil() {
    spoiltValue = 0;
  }

  final public void decreaseSpoiltValue() {
    --spoiltValue;
  }
}
