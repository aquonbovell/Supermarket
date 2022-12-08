import java.util.Random;

public class Fruit extends Item{
  private String nameOfFruit;
  private int spoiltValue;

  public Fruit(String name) {
    Random rand = new Random();
    nameOfFruit = name;
    spoiltValue = rand.nextInt(6) + 5;
  }

  final public String getName() {
    return this.nameOfFruit;
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