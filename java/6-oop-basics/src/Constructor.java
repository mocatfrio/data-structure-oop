class Person {
    private String name;
    private int age;
    private String address;

    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.address = "Unknown";
        System.out.println("Default constructor called");
    }

    public Person(String name) {
        this.name = name;
        this.age = 0;
        this.address = "Unknown";
        System.out.println("Constructor with name called");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.address = "Unknown";
        System.out.println("Constructor with name and age called");
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        System.out.println("Full constructor called");
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age + ", Address: " + address);
    }
}

public class Constructor {
    public static void main(String[] args) {
        System.out.println("=== CONSTRUCTOR OVERLOADING ===\n");

        Person p1 = new Person();
        p1.display();

        System.out.println();
        Person p2 = new Person("Alice");
        p2.display();

        System.out.println();
        Person p3 = new Person("Bob", 25);
        p3.display();

        System.out.println();
        Person p4 = new Person("Charlie", 30, "Jakarta");
        p4.display();
    }
}
