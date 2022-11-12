public class VegetableVendor extends Vendors{
  private int stockAvailable[];
  stockAvailable = new int[5];
  private double price;
  price = new double[5];
  private double totalCharge, total;
  *items vegetable;

  VegetableVendor(){
      for (int i = 0; i < 5; i++){
          stockAvailable[i] = 0;
      }
      for (int i = 0; i < 5; i++){
          price[i] = 0.0;
      }
      totalCharge = 0.0;
      total = 0.0;
      vegetable = new items();
  }
  void setPrice(){
      for (int i = 0; i < 5; i++){
      price[i] = //call vege items class
      } 
  }
  void setStock(){
      for (int i = 0; i < 5; i++){
          stockAvailable[i] = vegetable.//items;
          }
  }
  void setStock(int type){
      stockAvailable[type] = vegetable.//items;
  }
  double sellStock(int request int type){
      if (request == 0) && (type >= 5){
          System.out.println("Please input a request amount and a valid type");
      }
      else{
          if (request < stockAvailable[type]){
              stockAvailable[type] -= request;
          }
          else if (request = stockAvailable[type]){
              stockAvailable[type]-= request;
              System.out.println("Please note that there are no Vegetables remaining");
          }
          else if (request > stockAvailable[type]){
              request = stockAvailable[type];
              stockAvailable[type] = 0;
              System.out.println("Please note that there are no vegetables remaining. Amount granted is, " +request);
              setStock(type);
          }
      }
      totalCharge = request * price;
      total += totalCharge;
      return totalCharge;
  }
  int getStock(){
      System.out.println("The number of vegetables available is; " +stockAvailable);
      return stockAvailable;
  }
}