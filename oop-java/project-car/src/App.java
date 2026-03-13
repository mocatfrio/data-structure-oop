public class App {
    public static void main(String[] args) throws Exception {

        // Instansiasi 
        Car myCar = new Car();

        Car myHonda = new Car("Honda", "Brio", "Kuning", 1500);

        System.out.println(myCar.getBrand());
        System.out.println(myHonda.getBrand());

        myCar.setBrand("Toyota");
        System.out.println(myCar.getBrand());
    }
}

class Car {
    // atribut
    private String brand;
    private String type;
    private String color;
    private int speed;

    // constructor 
    public Car() {
        this.brand = "Default";
    }

    public Car(String brand, String type, String color, int speed) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.speed = speed;
    }

    // method 
    // setter & getter
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brandNew) {
        this.brand = brandNew;
    }
    
}