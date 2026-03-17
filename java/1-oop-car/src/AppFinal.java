abstract class Car {
  protected String brand;
  protected String type;
  protected String color;
  private int speed;
  protected TollPayment tollPayment;

  public Car() {
  }

  public Car(String brand, String type, String color, int speed, TollPayment tollPayment) {
    this.brand = brand;
    this.type = type;
    this.color = color;
    this.speed = speed;
    this.tollPayment = tollPayment;
  }

  public abstract void startEngine();

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public void payToll(int amount) {
    if (tollPayment != null) {
      tollPayment.payToll(amount);
    } else {
      System.out.println("Metode pembayaran tol belum tersedia.");
    }
  }

  public void showCarInfo() {
    System.out.println("Brand : " + brand);
    System.out.println("Type  : " + type);
    System.out.println("Color : " + color);
    System.out.println("Speed : " + speed + " km/h");
  }
}

interface TollPayment {
  void payToll(int amount);
}

class CashPayment implements TollPayment {
  @Override
  public void payToll(int amount) {
    System.out.println("Pembayaran tol tunai sebesar Rp" + amount + " berhasil.");
  }
}

class ElectricCar extends Car {
  public ElectricCar(String brand, String type, String color, int speed, TollPayment tollPayment) {
    super(brand, type, color, speed, tollPayment);
  }

  @Override
  public void startEngine() {
    System.out.println("Mobil listrik dinyalakan tanpa suara.");
  }
}

public class AppFinal {
  public static void main(String[] args) {
    TollPayment payment = new CashPayment();

    ElectricCar car = new ElectricCar(
        "Tesla",
        "Model 3",
        "Merah",
        120,
        payment);

    System.out.println("=== DATA MOBIL ===");
    car.showCarInfo();

    System.out.println("\n=== MENYALAKAN MOBIL ===");
    car.startEngine();

    System.out.println("\n=== PEMBAYARAN TOL ===");
    car.payToll(15000);

    System.out.println("\n=== UBAH KECEPATAN ===");
    car.setSpeed(140);
    System.out.println("Kecepatan sekarang: " + car.getSpeed() + " km/h");
  }
}