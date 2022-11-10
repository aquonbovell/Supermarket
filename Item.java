import java.util.Random;

public class Item {
  private int spoiltValue;
  private int numberOfItems;
  private double priceOfItems;

  Item() {
    setSpoiltValue(getRandomNumberForSpoiltValue());
    setNumberOfItems(getRandomNumberOfItems());
    // setPriceOfItems();
  }

  public void setSpoiltValue(int spoiltVal) {
    spoiltValue = spoiltVal;
  }

  public int getSpoiltValue() {
    return spoiltValue;
  }

  public int getRandomNumberForSpoiltValue() {
    Random rand = new Random();
    return (rand.nextInt((10 - 5) + 1) + 5);
  }

  public void setNumberOfItems(int num) {
    numberOfItems = num;
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public int getRandomNumberOfItems() {
    Random rand = new Random();
    return (rand.nextInt((100 - 10) + 1) + 10);
  }

  private void setPriceOfItems(double price) {
    priceOfItems = price;
  }

  private double getPriceOfItems() {
    return priceOfItems;
  }

}
