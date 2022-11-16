/*This class provides a template the various types of vendors that provide items to the supermarket.
*Kenez Horne*/
public class Vendor {
  String name; // This field stores the name of the vendor.
  private boolean isAvailable; // This field stores the value of the vendors availability to provide items.
  /*Constructor method which initializes the fields of the class
  *This method returns no value but accepts a value to initialize the name of the class*/
  Vendor(String name){
    this.name = name;
    isAvailable = true;
  }
  /*This method sets the availablilty of the vendor to false when the vendor can not deliver goods
  *This method accepts no parameters and returns no values*/ 
  public void isNotAvailable() {
    isAvailable = false;
  }
  /*This method sets the availabilty of the vendor to true whenever the vendor is able to deliver goods again
  *This method accepts no parameters nor returns a value*/
  protected void setAvailable() {
    isAvailable = true;
  }
  /*This method returns the vendors availabilty status when called
  * This method returns the Vendor's availability status*/
  public boolean getAvailablity() {
    return isAvailable;
  }
  /*This method returns the name of the vendor when needed
  *This method accepts not parameters*/
  public String getName() {
    return name;
  }

}
