import java.util.Arrays;
import java.util.List;

public class MultiParadigmDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Rara", "Budi", "Eka", "Pratiwi", "Agung");

        System.out.println("=== Pendekatan Imperatif ===");
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i).toUpperCase());
        }

        System.out.println("\n=== Pendekatan OOP (enhanced for) ===");
        for (String name : names) {
            System.out.println(name.toUpperCase());
        }

        System.out.println("\n=== Pendekatan Fungsional (Stream) ===");
        names.stream()
             .map(String::toUpperCase)
             .forEach(System.out::println);

        System.out.println("\n=== Filter nama dengan huruf vokal ===");
        names.stream()
             .filter(name -> "AEIOUaeiou".indexOf(name.charAt(0)) != -1)
             .map(String::toUpperCase)
             .forEach(System.out::println);
    }
}
