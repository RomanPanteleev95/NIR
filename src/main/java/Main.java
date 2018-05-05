import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by pante on 05.05.2018.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main().run();
    }

    void run() throws FileNotFoundException {
        PrintWriter reportOut = new PrintWriter(new FileOutputStream("graph_report.txt"));
        Scanner systemScan = new Scanner(System.in);
        String graphFileName = systemScan.nextLine();
        Scanner scan = new Scanner(new FileInputStream(graphFileName));

//        Scanner scanner = new Scanner(new FileInputStream("graph.txt"));
//        int testSize = scanner.nextInt();
//        int[][] test = new int[testSize][testSize];
//        for (int i = 0; i < testSize; i++){
//            for (int j = 0; j < testSize; j++){
//                test[i][j] = scanner.nextInt();
//            }
//        }
//
//        Graph graph = new Graph(test);
//        int testAnswer = graph.getChromoNumberLongTime();
//        System.out.println(testAnswer);
        int idx = 1;
        while (scan.hasNext()){
            System.out.println(idx++);
            String g = scan.nextLine();
            int[][] currentGraph = Utils.parseGraph(g);
            Graph graph = new Graph(currentGraph);
            int chromo = graph.getChromoNumberLongTime();
            reportOut.println(g);
            for (int i = 0; i < currentGraph.length; i++){
                for (int j = 0; j < currentGraph.length; j++){
                    reportOut.print(currentGraph[i][j] + " ");
                }
                reportOut.println();
            }
            reportOut.println("Хромотическое число = " + chromo);
        }

        reportOut.close();

    }
}
