import java.util.Random;

/*This class details the fruit vendor and its behaviour and operations.
*Kenez Horne*/
public class FruitsAreHere extends Vendor {
  private Watermelon[] watermelonInventory;//This field stores the number of watermelon object that the Vendor possesses.
  private Mango[] mangoInventory;// This method stores the number of mango objects that the vendor possesses.
  private Banana[] bananaInventory;// This method stores the number of banana objects that the vendor possesses.
  private Lime[] limeInventory;//This field stores the number of Lime object that the vendor possesses.
  private Avocado[] avocadoInventory;// This field stores the number of Avocado that the vendor possesses.
  private int totalWatermelons, totalMangoes, totalBananas, totalLimes, totalAvocado; // These fields store the total number of the diiferent items that were sold by the vendor.
  private double watermelonProfit, mangoProfit, bananaProfit, limeProfit, avocadoProfit, totalProfit;// These fields store the profit gained from the sale of the various items.
  /*FruitsAreHere constructor which assigns each field to an initial value.
  This method neither accepts parameters nor returns any values.*/
  FruitsAreHere() {
    super("Fruits Are Here");
    Random rand = new Random();
    watermelonInventory = new Watermelon[rand.nextInt(91) + 10];
    avocadoInventory = new Avocado[rand.nextInt(91) + 10];
    limeInventory = new Lime[rand.nextInt(91) + 10];
    bananaInventory = new Banana[rand.nextInt(91) + 10];
    mangoInventory = new Mango[rand.nextInt(91) + 10];
    initArray(watermelonInventory);
    initArray(avocadoInventory);
    initArray(limeInventory);
    initArray(bananaInventory);
    initArray(mangoInventory);
    setAvailable();
    totalWatermelons = 0;
    totalMangoes = 0;
    totalBananas = 0;
    totalLimes = 0;
    totalAvocado = 0;
    watermelonProfit = 0.0;
    mangoProfit = 0.0;
    bananaProfit = 0.0;
    limeProfit = 0.0;
    avocadoProfit = 0.0;
    totalProfit = 0.0;
  }
  /*This method initializes each instance of the items to an object of its type
  This method accepts an array as parameters and returns no values */
  private void initArray(Fruit[] inventory) {
    if (inventory instanceof Watermelon[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Watermelon();
      }
    } else if (inventory instanceof Avocado[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Avocado();
      }
    } else if (inventory instanceof Mango[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Mango();
      }
    } else if (inventory instanceof Lime[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Lime();
      }
    } else if (inventory instanceof Banana[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Banana();
      }
    }
  }
  /*This method accepts a purchases request from the supermarket which it processes and returns the amount requested once available
  *this method accepts and integer and returns an array of objects.*/
  public Watermelon[] sellWatermelons(int request) {
    int limit = 0;
    Watermelon[] sellWatermelon;
    if (request >= watermelonInventory.length) {
      sellWatermelon = new Watermelon[watermelonInventory.length];
      for (int counter = 0; counter < watermelonInventory.length; counter++) {
        sellWatermelon[counter] = watermelonInventory[(watermelonInventory.length - 1) - counter];
      }
      limit = request - watermelonInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      watermelonInventory = new Watermelon[0];
    } else {
      sellWatermelon = new Watermelon[request];
      limit = watermelonInventory.length - request;
      Watermelon[] updatedWatermelonInventory = new Watermelon[watermelonInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellWatermelon[counter] = watermelonInventory[(watermelonInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedWatermelonInventory[counter] = watermelonInventory[counter];
      }
      watermelonInventory = updatedWatermelonInventory;
    }
    totalWatermelons += sellWatermelon.length;
    return sellWatermelon;
  }
  /*This method accepts a purchases request for Avocado, processes it and returns the request amount once it doesnot exceed the available inventory
  *This method accepts an integer and returns an array of type Avocado*/
  public Avocado[] sellAvocados(int request) {
    int limit = 0;
    Avocado[] sellAvocado;
    if (request >= avocadoInventory.length) {
      sellAvocado = new Avocado[avocadoInventory.length];
      for (int counter = 0; counter < avocadoInventory.length; counter++) {
        sellAvocado[counter] = avocadoInventory[(avocadoInventory.length - 1) - counter];
      }
      limit = request - avocadoInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      avocadoInventory = new Avocado[0];
    } else {
      sellAvocado = new Avocado[request];
      limit = avocadoInventory.length - request;
      Avocado[] updatedAvocadoInventory = new Avocado[avocadoInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellAvocado[counter] = avocadoInventory[(avocadoInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedAvocadoInventory[counter] = avocadoInventory[counter];
      }
      avocadoInventory = updatedAvocadoInventory;
    }
    totalAvocado += sellAvocado.length;
    return sellAvocado;
  }
  /*This method accepts a purchases request for Limes, processes it and returns the request amount once it doesnot exceed the available inventory
  *This method accepts an integer and returns an array of type Lime.*/
  public Lime[] sellLimes(int request) {
    int limit = 0;
    Lime[] sellLime;
    if (request >= limeInventory.length) {
      sellLime = new Lime[limeInventory.length];
      for (int counter = 0; counter < limeInventory.length; counter++) {
        sellLime[counter] = limeInventory[(limeInventory.length - 1) - counter];
      }
      limit = request - limeInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      limeInventory = new Lime[0];
    } else {
      sellLime = new Lime[request];
      limit = limeInventory.length - request;
      Lime[] updatedLimeInventory = new Lime[limeInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellLime[counter] = limeInventory[(limeInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedLimeInventory[counter] = limeInventory[counter];
      }
      limeInventory = updatedLimeInventory;
    }
    totalLimes += sellLime.length;
    return sellLime;
  }
  /*This method accepts a purchases request for Banana, processes it and returns the request amount once it doesnot exceed the available inventory
  *This method accepts an integer and returns an array of type Banana*/
  public Banana[] sellBananas(int request) {
    int limit = 0;
    Banana[] sellBanana;
    if (request >= bananaInventory.length) {
      sellBanana = new Banana[bananaInventory.length];
      for (int counter = 0; counter < bananaInventory.length; counter++) {
        sellBanana[counter] = bananaInventory[(bananaInventory.length - 1) - counter];
      }
      limit = request - bananaInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      bananaInventory = new Banana[0];
    } else {
      sellBanana = new Banana[request];
      limit = bananaInventory.length - request;
      Banana[] updatedBananaInventory = new Banana[bananaInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellBanana[counter] = bananaInventory[(bananaInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedBananaInventory[counter] = bananaInventory[counter];
      }
      bananaInventory = updatedBananaInventory;
    }
    totalBananas += sellBanana.length;
    return sellBanana;
  }
  /*This method accepts a purchases request for Mango, processes it and returns the request amount once it doesnot exceed the available inventory
  *This method accepts an integer and returns an array of type Mango*/
  public Mango[] sellMangoes(int request) {
    int limit = 0;
    Mango[] sellMango;
    if (request >= mangoInventory.length) {
      sellMango = new Mango[mangoInventory.length];
      for (int counter = 0; counter < mangoInventory.length; counter++) {
        sellMango[counter] = mangoInventory[(mangoInventory.length - 1) - counter];
      }
      limit = request - mangoInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      mangoInventory = new Mango[0];
    } else {
      sellMango = new Mango[request];
      limit = mangoInventory.length - request;
      Mango[] updatedMangoInventory = new Mango[mangoInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellMango[counter] = mangoInventory[(mangoInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedMangoInventory[counter] = mangoInventory[counter];
      }
      mangoInventory = updatedMangoInventory;
    }
    totalMangoes += sellMango.length;
    return sellMango;
  }
  /*This method retocks the items that vendor has available for sale. An calls for the vendor availablity to change accordingly
  *This method neither accepts parameters nor returns a value.*/
  public void restock() {
    Random rand = new Random();
    watermelonInventory = new Watermelon[rand.nextInt(91) + 10];
    avocadoInventory = new Avocado[rand.nextInt(91) + 10];
    limeInventory = new Lime[rand.nextInt(91) + 10];
    bananaInventory = new Banana[rand.nextInt(91) + 10];
    mangoInventory = new Mango[rand.nextInt(91) + 10];
    initArray(watermelonInventory);
    initArray(avocadoInventory);
    initArray(limeInventory);
    initArray(bananaInventory);
    initArray(mangoInventory);
    setAvailable();
  }
  /*This Method Calculates the inidividual items and overall profits of the vendor
  *This method Neither accepts parameters nor returns and values.*/
  public void calculateProfit() {
    watermelonProfit = totalWatermelons * Watermelon.COST_PRICE;
    totalProfit += watermelonProfit;
    bananaProfit = totalBananas * Banana.COST_PRICE;
    totalProfit += bananaProfit;
    avocadoProfit = totalAvocado * Avocado.COST_PRICE;
    totalProfit += avocadoProfit;
    limeProfit = totalLimes * Lime.COST_PRICE;
    totalProfit += limeProfit;
    mangoProfit = totalMangoes * Mango.COST_PRICE;
    totalProfit += mangoProfit;
  }
  /*This method tells the value of the overall profits of the vendor
  *This method does not accept parameters but returns a double*/
  public double getProfit() {
    calculateProfit();
    return totalProfit;
  }
}
