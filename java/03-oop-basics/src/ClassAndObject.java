class Car {
    String brand;
    String model;
    int year;
    String color;

    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public void displayInfo() {
        System.out.println(brand + " " + model + " (" + year + ") - " + color);
    }

    public void start() {
        System.out.println(brand + " " + model + " is starting...");
    }

    public void stop() {
        System.out.println(brand + " " + model + " has stopped.");
    }
}

public class ClassAndObject {
    public static void main(String[] args) {
        System.out.println("=== CLASS DAN OBJECT ===\n");

        Car car1 = new Car("Toyota", "Camry", 2022, "Silver");
        Car car2 = new Car("Honda", "Civic", 2023, "Black");
        Car car3 = new Car("BMW", "X5", 2021, "White");

        car1.displayInfo();
        car2.displayInfo();
        car3.displayInfo();

        System.out.println();
        car1.start();
        car1.stop();
    }
}
