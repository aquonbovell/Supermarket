public class Vendor {
  String name;
  private boolean isAvailable;

  Vendor(String name) {
    this.name = name;
    isAvailable = true;
  }

  public void isNotAvailable() {
    isAvailable = false;
  }

  protected void setAvailable() {
    isAvailable = true;
  }

  public boolean getAvailablity() {
    return isAvailable;
  }

  public String getName() {
    return name;
  }

}