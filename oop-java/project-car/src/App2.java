public class App2 {
  public static void main(String[] args) {

    ElectricCar myCar = new ElectricCar("Honda", "Brio", "Kuning", 0);

    System.out.println(myCar.getSpeed());
    myCar.startEngine();
    myCar.payToll(100000);
  }
}

abstract class Car {
  protected String brand;
  protected String type;
  protected String color;
  private int speed;
  protected CashPayment cashPayment = new CashPayment();

  // constructor 
  public Car(String brand, String type, String color, int speeds) {
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

  public void payToll(int number) {
    cashPayment.payToll(number);
  }

}

class ElectricCar extends Car {
  // constructor 
  public ElectricCar(String brand, String type, String color, int speed) {
    super(brand, type, color, speed);
  }

  @Override
  void startEngine() {
    System.out.println("Ini electric car");
  }
}


interface TollPayment {
  void payToll(int number);
}

class CashPayment implements TollPayment {
  @Override
  public void payToll(int number) {
    System.out.println("Pembayaran Cash sejumlah: " + number);
  }
}