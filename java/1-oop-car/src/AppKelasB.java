interface Navigation {
  void goToWhere(String dest);
}

interface Notification {
  void maintenanceWarn(String tuing);
}

public class AppKelasB {
  public static void main(String[] args) {

    ElectricCar myCar = new ElectricCar("Honda", "Brio", "Kuning", 0);

    System.out.println(myCar.getSpeed());
    myCar.startEngine();

    myCar.payToll(1000);
    myCar.payTollQRIS(1000);

    HybridCar hybridCar = new HybridCar("Hybrid", "Hybrid", "White", 200);

    hybridCar.payToll(1500);
    hybridCar.goToWhere("J-one");
    hybridCar.maintenanceWarn("whatsapp");
  }
}

abstract class Car {
  protected String brand;
  protected String type;
  protected String color;
  private int speed;
  protected CashPayment cashPayment = new CashPayment();
  protected FuncNavigation funcNavigation = new FuncNavigation();
  protected FuncNotification funcNotification = new FuncNotification();

  // constructor 
  public Car(String brand, String type, String color, int speed) {
    this.brand = brand;
    this.type = type;
    this.color = color;
    this.speed = speed;
  }

  // method abstract
  abstract void startEngine();

  // setter & getter 
  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speedNew) {
    this.speed = speedNew;
  }

  public void payToll(int amount) {
    cashPayment.payToll(amount);
  }

  public void goToWhere(String dest) {
    funcNavigation.goToWhere(dest);
  }

  public void maintenanceWarn(String tuing) {
    funcNotification.maintenanceWarn(tuing);
  }

}

class ElectricCar extends Car {
  protected QRISPayment qrisPayment = new QRISPayment();

  // constructor 
  public ElectricCar(String brand, String type, String color, int speed) {
    super(brand, type, color, speed);
  }

  @Override
  void startEngine() {
    System.out.println("Ini electric car");
  }

  public void payTollQRIS(int amount) {
    qrisPayment.payToll(amount);
  }
}

class HybridCar extends Car {
  // constructor
  public HybridCar(String brand, String type, String color, int speed) {
    super(brand, type, color, speed);
  }

  @Override
  void startEngine() {
    System.out.println("Ini electric car");
  }
}

interface TollPayment {
  void payToll(int amount);
}

class CashPayment implements TollPayment {
  @Override
  public void payToll(int amount) {
    System.out.println("Pembayaran cash: " + amount);
  }
}

class QRISPayment implements TollPayment {
  @Override
  public void payToll(int amount) {
    System.out.println("Pembayaran QRIS: " + amount);
  }
}

class FuncNavigation implements Navigation {
  @Override
  public void goToWhere(String dest) {
    System.out.println("Saya pergi ke :" + dest);
  }
}

class FuncNotification implements Notification {
  @Override
  public void maintenanceWarn(String tuing) {
    System.out.println("Ayo Makrab lagi" + tuing);
    /*if (speed > 150) {
      System.out.println("kurangi kecepatan");
    }*/
  }
}