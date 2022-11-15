import java.util.Random;

public class TastyVegetables extends Vendor {
  private Carrot[] carrotInventory;
  private Lettuce[] lettuceInventory;
  private Cucumber[] cucumberInventory;
  private Parsley[] parsleyInventory;
  private Onion[] onionInventory;
  private int totalCarrots, totalLettuces, totalCucumbers, totalParsleys, totalOnions; 
  private double carrotProfit, lettuceProfit, cucumberProfit, parsleyProfit, onionProfit, totalProfit;


  TastyVegetables() {
    super("Tasty Vegetables");
    Random rand = new Random();
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
    setAvailable();
    totalCarrots = 0; 
    totalLettuces = 0; 
    totalCucumbers = 0; 
    totalParsleys = 0;
    totalOnions = 0;
    carrotProfit = 0.0; 
    lettuceProfit = 0.0;
    cucumberProfit = 0.0;
    parsleyProfit = 0.0;
    totalProfit = 0.0;
  }

  private void initArray(Vegetable[] inventory) {
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


  public void restock() {
    Random rand = new Random();
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
    setAvailable();
  }
  public void calculateProfit(){
    carrotProfit = totalCarrots * Carrot.COST_PRICE;
    totalProfit += carrotProfit;
    lettuceProfit = totalLettuces * Lettuce.COST_PRICE;
    totalProfit += lettuceProfit;
    cucumberProfit = totalCucumbers * Cucumber.COST_PRICE;
    totalProfit+= cucumberProfit;
    parsleyProfit = totalParsleys * Parsley.COST_PRICE;
    totalProfit += parsleyProfit;
    onionProfit = totalOnions * Onion.COST_PRICE;
    totalProfit += onionProfit;
  }
  public double getProfit(){
    calculateProfit();
    return totalProfit;
  }
}