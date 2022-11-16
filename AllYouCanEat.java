import java.util.Random;

// This class decribes the functionality and state of the AllYouCanEat vendor. It extends from the Vendor class - Kenez Horne
public class AllYouCanEat extends Vendor {
  private Watermelon[] watermelonInventory;//This feild stores the total watermelon items that are available 
  private Mango[] mangoInventory;//This field store the total mango items that are available.
  private Banana[] bananaInventory;//this feild stores the total banana items available.
  private Lime[] limeInventory;//This field stores total lime items available.
  private Avocado[] avocadoInventory;//This field stores the total avocado fields available.
  private Carrot[] carrotInventory;//This field stores the total carrot items available.
  private Lettuce[] lettuceInventory;//This field stores the total lettuce items available.
  private Cucumber[] cucumberInventory;//This field stores the total Cucumber items available.
  private Parsley[] parsleyInventory;// This field stores the total Parsley items available.
  private Onion[] onionInventory;//This field stores the total Onion items available.
  private int totalWatermelons, totalMangoes, totalBananas, totalLimes, totalAvocado; // These fields store the total of each fruit item sold by the vendor.
  private double watermelonProfit, mangoProfit, bananaProfit, limeProfit, avocadoProfit;//These fields store the total profit and individual profit of the fruit items.
  private int totalCarrots, totalLettuces, totalCucumbers, totalParsleys, totalOnions;// These fields store the total of each vegetables item sold by the vendor.
  private double carrotProfit, lettuceProfit, cucumberProfit, parsleyProfit, onionProfit, totalProfit; //These fields store the total profit and individual profit of the vegetables items.
  
  /*AllYouCanEat is the constructor of the class and it sets the fields of the class to an initial value.
  this method neither accepts parameters nor returns a value.*/
  AllYouCanEat() {
    super("All You Can Eat");
    Random rand = new Random();
    watermelonInventory = new Watermelon[rand.nextInt(91) + 10];
    avocadoInventory = new Avocado[rand.nextInt(91) + 10];
    limeInventory = new Lime[rand.nextInt(91) + 10];
    bananaInventory = new Banana[rand.nextInt(91) + 10];
    mangoInventory = new Mango[rand.nextInt(91) + 10];
    carrotInventory = new Carrot[rand.nextInt(91) + 10];
    lettuceInventory = new Lettuce[rand.nextInt(91) + 10];
    cucumberInventory = new Cucumber[rand.nextInt(91) + 10];
    parsleyInventory = new Parsley[rand.nextInt(91) + 10];
    onionInventory = new Onion[rand.nextInt(91) + 10];
    initArray(carrotInventory);
    initArray(lettuceInventory);
    initArray(cucumberInventory);
    initArray(parsleyInventory);
    initArray(onionInventory);
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
    totalCarrots = 0;
    totalLettuces = 0;
    totalCucumbers = 0;
    totalParsleys = 0;
    totalOnions = 0;
    carrotProfit = 0.0;
    lettuceProfit = 0.0;
    bananaProfit = 0.0;
    cucumberProfit = 0.0;
    parsleyProfit = 0.0;
    totalProfit = 0;
  }
   /*This method creates an array of obects for each fruit items' inventory.
  *This method accepts an array but returns no value.*/
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
  /*This method creates an array of obects for each vegetable items' inventory.
  *This method accepts an array but returns no value.*/
  public void initArray(Vegetable[] inventory) {
    if (inventory instanceof Carrot[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Carrot();
      }
    } else if (inventory instanceof Lettuce[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Lettuce();
      }
    } else if (inventory instanceof Cucumber[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Cucumber();
      }
    } else if (inventory instanceof Parsley[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Parsley();
      }
    } else if (inventory instanceof Onion[]) {
      for (int counter = 0; counter < inventory.length; counter++) {
        inventory[counter] = new Onion();
      }
    }
  }
  /*This method is responsible for providing Watermelons to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Watermelon*/
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
  /*This method is responsible for providing Avocados to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Avocado*/
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
  /*This method is responsible for providing Limes to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Lime*/
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
  /*This method is responsible for providing Bananas to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Banana*/
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
  /*This method is responsible for providing Mangoes to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Mango.*/
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
  /*This method is responsible for providing Carrots to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Carrots*/
  public Carrot[] sellCarrots(int request) {
    int limit = 0;
    Carrot[] sellCarrot;
    if (request >= carrotInventory.length) {
      sellCarrot = new Carrot[carrotInventory.length];
      for (int counter = 0; counter < carrotInventory.length; counter++) {
        sellCarrot[counter] = carrotInventory[(carrotInventory.length - 1) - counter];
      }
      limit = request - carrotInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      carrotInventory = new Carrot[0];
    } else {
      sellCarrot = new Carrot[request];
      limit = carrotInventory.length - request;
      Carrot[] updatedCarrotInventory = new Carrot[carrotInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellCarrot[counter] = carrotInventory[(carrotInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedCarrotInventory[counter] = carrotInventory[counter];
      }
      carrotInventory = updatedCarrotInventory;
    }
    totalCarrots += sellCarrot.length;
    return sellCarrot;
  }
  /*This method is responsible for providing Lettuces to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Lettuce*/
  public Lettuce[] sellLettuces(int request) {
    int limit = 0;
    Lettuce[] sellLettuce;
    if (request >= lettuceInventory.length) {
      sellLettuce = new Lettuce[lettuceInventory.length];
      for (int counter = 0; counter < lettuceInventory.length; counter++) {
        sellLettuce[counter] = lettuceInventory[(lettuceInventory.length - 1) - counter];
      }
      limit = request - lettuceInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      lettuceInventory = new Lettuce[0];
    } else {
      sellLettuce = new Lettuce[request];
      limit = lettuceInventory.length - request;
      Lettuce[] updatedLettuceInventory = new Lettuce[lettuceInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellLettuce[counter] = lettuceInventory[(lettuceInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedLettuceInventory[counter] = lettuceInventory[counter];
      }
      lettuceInventory = updatedLettuceInventory;
    }
    totalLettuces += sellLettuce.length;
    return sellLettuce;
  }
  /*This method is responsible for providing Cucumbers to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Cucumber*/
  public Cucumber[] sellCucumbers(int request) {
    int limit = 0;
    Cucumber[] sellCucumber;
    if (request >= cucumberInventory.length) {
      sellCucumber = new Cucumber[cucumberInventory.length];
      for (int counter = 0; counter < cucumberInventory.length; counter++) {
        sellCucumber[counter] = cucumberInventory[(cucumberInventory.length - 1) - counter];
      }
      limit = request - cucumberInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      cucumberInventory = new Cucumber[0];
    } else {
      sellCucumber = new Cucumber[request];
      limit = cucumberInventory.length - request;
      Cucumber[] updatedCucumberInventory = new Cucumber[cucumberInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellCucumber[counter] = cucumberInventory[(cucumberInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedCucumberInventory[counter] = cucumberInventory[counter];
      }
      cucumberInventory = updatedCucumberInventory;
    }
    totalCucumbers += sellCucumber.length;
    return sellCucumber;
  }
  /*This method is responsible for providing Parsleys to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Parsley*/
  public Parsley[] sellParsleys(int request) {
    int limit = 0;
    Parsley[] sellParsley;
    if (request >= parsleyInventory.length) {
      sellParsley = new Parsley[parsleyInventory.length];
      for (int counter = 0; counter < parsleyInventory.length; counter++) {
        sellParsley[counter] = parsleyInventory[(parsleyInventory.length - 1) - counter];
      }
      limit = request - parsleyInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      parsleyInventory = new Parsley[0];
    } else {
      sellParsley = new Parsley[request];
      limit = parsleyInventory.length - request;
      Parsley[] updatedParsleyInventory = new Parsley[parsleyInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellParsley[counter] = parsleyInventory[(parsleyInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedParsleyInventory[counter] = parsleyInventory[counter];
      }
      parsleyInventory = updatedParsleyInventory;
    }
    totalParsleys += sellParsley.length;
    return sellParsley;
  }
  /*This method is responsible for providing Onions to the vendors customers. it accepts their request, processes it and returns the request amount once its not more than
  * what is available of provide what can be sold. 
  *This method accepts an integer and returns and array of type Onion*/
  public Onion[] sellOnions(int request) {
    int limit = 0;
    Onion[] sellOnion;
    if (request >= onionInventory.length) {
      sellOnion = new Onion[onionInventory.length];
      for (int counter = 0; counter < onionInventory.length; counter++) {
        sellOnion[counter] = onionInventory[(onionInventory.length - 1) - counter];
      }
      limit = request - onionInventory.length;
      // System.out.println("Please note that we could not supply, " + limit);
      onionInventory = new Onion[0];
    } else {
      sellOnion = new Onion[request];
      limit = onionInventory.length - request;
      Onion[] updatedOnionInventory = new Onion[onionInventory.length - request];
      for (int counter = 0; counter < request; counter++) {
        sellOnion[counter] = onionInventory[(onionInventory.length - 1) - counter];
      }
      for (int counter = 0; counter < limit; counter++) {
        updatedOnionInventory[counter] = onionInventory[counter];
      }
      onionInventory = updatedOnionInventory;
    }
    totalOnions += sellOnion.length;
    return sellOnion;
  }
  /*This method is responsible for restocking the items of the vendor. 
  *This method neither accepts parameters nor returns a value.*/
  public void restock() {
    Random rand = new Random();
    watermelonInventory = new Watermelon[rand.nextInt(91) + 10];
    avocadoInventory = new Avocado[rand.nextInt(91) + 10];
    limeInventory = new Lime[rand.nextInt(91) + 10];
    bananaInventory = new Banana[rand.nextInt(91) + 10];
    mangoInventory = new Mango[rand.nextInt(91) + 10];
    carrotInventory = new Carrot[rand.nextInt(91) + 10];
    lettuceInventory = new Lettuce[rand.nextInt(91) + 10];
    cucumberInventory = new Cucumber[rand.nextInt(91) + 10];
    parsleyInventory = new Parsley[rand.nextInt(91) + 10];
    onionInventory = new Onion[rand.nextInt(91) + 10];
    initArray(carrotInventory);
    initArray(lettuceInventory);
    initArray(cucumberInventory);
    initArray(parsleyInventory);
    initArray(onionInventory);
    initArray(watermelonInventory);
    initArray(avocadoInventory);
    initArray(limeInventory);
    initArray(bananaInventory);
    initArray(mangoInventory);
    setAvailable();
  }
  /*This method calculates the total individual items and overall profits of the vendor.
  This method neithr accepts parameters nor returns a value.*/
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
    carrotProfit = totalCarrots * Carrot.COST_PRICE;
    totalProfit += carrotProfit;
    lettuceProfit = totalLettuces * Lettuce.COST_PRICE;
    totalProfit += lettuceProfit;
    cucumberProfit = totalCucumbers * Cucumber.COST_PRICE;
    totalProfit += cucumberProfit;
    parsleyProfit = totalParsleys * Parsley.COST_PRICE;
    totalProfit += parsleyProfit;
    onionProfit = totalOnions * Onion.COST_PRICE;
    totalProfit += onionProfit;
  }
  /*This method returns the overall profit of the vendor. 
  * This method accepts no parameters but returns a double.*/
  public double getProfit() {
    calculateProfit();
    return totalProfit;
  }
}
