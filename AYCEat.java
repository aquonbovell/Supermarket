public class AYCEat extends Vendors{
  private int stockAvailable[][];
  stockAvailable = new int[2][5];
  private double price[][];
  price = new double[2][5];
  private double totalCharge, total;
  *items good;

  AYCEat(){
      for (int j = 0; j < 2; j++){
          for (int i = 0; i < 5; i++){
              stockAvailable[j][i] = 0;
          }
      }
      for(j = 0; j < 2; j++){
          for (int i = 0; i < 5; i++){
              price[j][i] = 0.0;
          }
      }
      totalCharge = 0.0;
      total = 0.0;
      good = new items();
  }
  void setPrice(){
      for(j = 0; j < 2; j++){
          for (int i = 0; i < 5; i++){
              price[j][i] = //call fruit items class
          } 
      }
  }
  void setStock(){
      for(j = 0; j < 5; j++){
          for (int i = 0; i < 5; i++){
              stockAvailable[j][i] = fruit.//items;
          }   
      }
  }
  void setStock(int type, int kind){
      stockAvailable[type] = fruit.//items;
  }
  double sellStock(int request int type, int kind){
      if (request == 0) && (type >= 5){
          System.out.println("Please input a request amount and a valid type");
      }
      else{
          if (request < stockAvailable[type][kind]){
              stockAvailable[type][kind] -= request;
          }
          else if (request = stockAvailable[type][kind]){
              stockAvailable[type][kind]-= request;
              System.out.println("Please note that there are no fruits remaining");
          }
          else if (request > stockAvailable[type][kind]){
              request = stockAvailable[type][kind];
              stockAvailable[type][kind] = 0;
              System.out.println("Please note that there are no fruits remaining. Amount granted is, " +request);
              setStock(type, kind);
          }
      }
      totalCharge = request * price[type][kind];
      total += totalCharge;
      return totalCharge;
  }
  int getStock(int type, int kind){
      System.out.println("The number of that item available is; " +stockAvailable[type][kind]);
      return stockAvailable[type][kind];
  }
}