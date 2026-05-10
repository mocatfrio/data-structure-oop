
public class HashTableNoOOP {
    static int[] table = new int[10];
    static boolean[] occupied = new boolean[10];
    
    public static void insert(int val) {
        int idx = val % 10;
        int orig = idx;
        while(occupied[idx]) {
            idx = (idx + 1) % 10;
            if (idx == orig) return; // Full
        }
        table[idx] = val;
        occupied[idx] = true;
    }
    
    public static void display() {
        for(int i=0; i<10; i++) {
            if(occupied[i]) System.out.println(i + " -> " + table[i]);
        }
    }
    
    public static void main(String[] args) {
        insert(15); insert(25); insert(35); display();
    }
}
