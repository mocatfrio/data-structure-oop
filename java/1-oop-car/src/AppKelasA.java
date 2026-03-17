public class AppKelasA {
  public static void main(String[] args) {

    ElectricCar myCar = new ElectricCar("Honda", "Brio", "Kuning", 0);

    System.out.println(myCar.getSpeed());
    myCar.startEngine();
    myCar.payToll(100000);
    myCar.newNotif("ayo ngeprint oli hehe");
    myCar.estimate(100, 10);
  }
}

abstract class Car {
  protected String brand;
  protected String type;
  protected String color;
  private int speed;
  protected CashPayment cashPayment = new CashPayment();
  protected OliHabisNotification oliHabis = new OliHabisNotification();
  protected TimeEstimate timeEstimate = new TimeEstimate();

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

  public void payToll(int number) {
    cashPayment.payToll(number);
  }

  public void newNotif(String notif) {
    oliHabis.sendNotification(notif);
  }

  public void estimate(int distance, int speed) {
    timeEstimate.timeEstimate(distance, speed);
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

interface Notification {
  void sendNotification(String message);
}

class OliHabisNotification implements Notification {
  @Override
  public void sendNotification(String message) {
    System.out.println("Notifikasi Mobil: " + message);
  }
}

class BengkelTime implements Notification {
  @Override
  public void sendNotification(String message) {
    System.out.println("Waktu Service" + message);
  }
}

interface Navigation {
  void timeEstimate(int distance, int speed);
}

class TimeEstimate implements Navigation {
  @Override
  public void timeEstimate(int distance, int speed) {
    System.out.println("Waktu tersisa hingga tujuan: " + distance / speed);
  }
}