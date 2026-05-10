public class StringDemo {
    public static void main(String[] args) {
        System.out.println("=== STRING OPERATIONS ===\n");

        String str1 = "Hello";
        String str2 = "World";
        String str3 = new String("Hello");

        System.out.println("Concatenation: " + str1 + " " + str2);
        System.out.println("Length: " + str1.length());
        System.out.println("Uppercase: " + str1.toUpperCase());
        System.out.println("Lowercase: " + str1.toLowerCase());
        System.out.println("Char at 0: " + str1.charAt(0));
        System.out.println("Substring(1,3): " + str1.substring(1, 3));

        System.out.println("\n=== STRING COMPARISON ===");
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str1.equals(str3): " + str1.equals(str3));

        System.out.println("\n=== STRINGBUILDER ===");
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        sb.append("!");
        System.out.println(sb.toString());
        sb.reverse();
        System.out.println("Reversed: " + sb.toString());

        System.out.println("\n=== STRING SPLIT ===");
        String sentence = "Java is awesome";
        String[] words = sentence.split(" ");
        for (String word : words) {
            System.out.println(word);
        }
    }
}
