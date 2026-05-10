
public class GraphNoOOP {
    static int[][] adjMatrix = new int[10][10];
    static int V = 5;
    
    public static void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }
    public static void display() {
        for(int i=0; i<V; i++) {
            System.out.print(i + " -> ");
            for(int j=0; j<V; j++) {
                if(adjMatrix[i][j] == 1) System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        addEdge(0, 1); addEdge(0, 4); addEdge(1, 2); display();
    }
}
