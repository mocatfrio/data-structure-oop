interface Drawable {
    void draw();
}

interface Resizable {
    void resize(double factor);
}

interface Printable {
    void print();
}

class Square implements Drawable, Resizable, Printable {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Drawing square with side: " + side);
    }

    @Override
    public void resize(double factor) {
        side *= factor;
        System.out.println("Resized square to side: " + side);
    }

    @Override
    public void print() {
        System.out.println("Square [side=" + side + ", area=" + (side * side) + "]");
    }
}

class Oval implements Drawable {
    private double radiusX;
    private double radiusY;

    public Oval(double radiusX, double radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw() {
        System.out.println("Drawing oval with radiusX: " + radiusX + ", radiusY: " + radiusY);
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== INTERFACE ===\n");

        Square square = new Square(5);
        square.draw();
        square.print();
        square.resize(2);
        square.print();

        System.out.println();

        Oval oval = new Oval(3, 5);
        oval.draw();

        System.out.println("\n=== POLYMORPHISM WITH INTERFACE ===");
        Drawable[] drawables = {new Square(3), new Oval(2, 4)};
        for (Drawable d : drawables) {
            d.draw();
        }
    }
}
