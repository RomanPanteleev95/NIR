import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Graph {
    private int size;
    private int[][] graph;
    boolean[] used;
    ArrayList<int[]> permutations = new ArrayList<int[]>();

    public Graph(int[][] graph) throws FileNotFoundException {
        this.graph = graph;
        this.size = graph.length;
        used = new boolean[graph.length];
        int[] firstPermutation = new int[size];
        for (int i = 0; i < size; i++){
            firstPermutation[i] = i;
        }
        generateAllPermutations(0, size, firstPermutation);
    }

    public void writeGraph() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getChromoNumber() {
        int resultChromo = Integer.MAX_VALUE;
        for (int i = 0; i < graph.length; i++){
            ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
            used = new boolean[graph.length];
            dfs(i, vertexList);
            int chromoNumber = -1;
            for (Vertex vertex : vertexList){
                if (vertex.getColor() > chromoNumber){
                    chromoNumber = vertex.getColor();
                }
            }
            if (chromoNumber < resultChromo){
                resultChromo = chromoNumber;
            }
        }


        return resultChromo;
    }

    public int getChromoNumberLongTime() throws FileNotFoundException {
//        PrintWriter logOut = new PrintWriter(new FileOutputStream("logPermutation.txt"));
        int resultChromo = Integer.MAX_VALUE;
        for (int i = 0; i < permutations.size(); i++) {
            int[] currentPermutation = permutations.get(i);
            ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
            vertexList.add(new Vertex(currentPermutation[0], 1));
            for (int j = 1; j < currentPermutation.length; j++){
                List<Integer> colorsForAdjancesVertices = new ArrayList<Integer>();
                for (int k = 0; k < vertexList.size(); k++){
                    if (graph[vertexList.get(k).getId()][currentPermutation[j]] == 1){
                        colorsForAdjancesVertices.add(vertexList.get(k).getColor());
                    }
                }
                Collections.sort(colorsForAdjancesVertices);
                int currentVertexColor = 1;
                for (Integer color : colorsForAdjancesVertices){
                    if (color == currentVertexColor){
                        currentVertexColor++;
                    }
                }
                vertexList.add(new Vertex(currentPermutation[j], currentVertexColor));
            }
            int chromoNumber = -1;
            for (Vertex vertex : vertexList) {
                if (vertex.getColor() > chromoNumber) {
                    chromoNumber = vertex.getColor();
                }
            }
            if (chromoNumber < resultChromo) {
                resultChromo = chromoNumber;
            }

//            logOut.println(Arrays.toString(currentPermutation));
//            for (Vertex vertex : vertexList){
//                logOut.println(vertex);
//            }
//            logOut.println("Chromo" + chromoNumber);

//            for (int j = 0; j < currentPermutation.length; j++) {
//                ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
//                used = new boolean[graph.length];
//                dfs(currentPermutation[j], vertexList);
//                int chromoNumber = -1;
//                for (Vertex vertex : vertexList) {
//                    if (vertex.getColor() > chromoNumber) {
//                        chromoNumber = vertex.getColor();
//                    }
//                }
//                if (chromoNumber < resultChromo) {
//                    resultChromo = chromoNumber;
//                }
//            }
        }
//        logOut.close();
        return resultChromo;
    }

    public void dfs(int pos, ArrayList<Vertex> vertexList) {
        used[pos] = true;
        List<Integer> colorsForAdjancesVertices = new ArrayList<Integer>();
        for (int i = 0; i < vertexList.size(); i++){
            if (graph[vertexList.get(i).getId()][pos] == 1){
                colorsForAdjancesVertices.add(vertexList.get(i).getColor());
            }
        }
        Collections.sort(colorsForAdjancesVertices);
        int currentVertexColor = 1;
        for (Integer color : colorsForAdjancesVertices){
            if (color == currentVertexColor){
                currentVertexColor++;
            }
        }
        vertexList.add(new Vertex(pos, currentVertexColor));
        for (int i = 0; i < size; i++){
                if (graph[pos][i] == 1 && used[i] == false){
                    dfs(i, vertexList);
            }
        }
    }

    boolean isCompleteGraph(){
        int cntZero = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (graph[i][j] == 0){
                    cntZero++;
                }
            }
        }
        return cntZero == size;
    }

    private void generateAllPermutations(int k, int n, int[] arr) {
        if (k == n) {
            permutations.add((int[]) arr.clone());
        } else {
            for (int j = k; j < arr.length; j++) {
                swap(arr, k, j);
                generateAllPermutations(k + 1, n, arr);
                swap(arr, k, j);
            }
        }

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

