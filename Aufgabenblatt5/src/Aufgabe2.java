import java.util.Arrays;

/*
    Aufgabe 2) Zweidimensionale Arrays - Sortieren und Filtern
*/
public class Aufgabe2 {

    private static double[][] genMeanFilter(int n) {
        if (n % 2 == 0 || n < 0) return null;

        double mean = 1.0 / (n * n);
        double[][] result = new double[n][n];

        for (double[] row : result) Arrays.fill(row, mean);

        return result;
    }

    private static double[][] applyFilter(double[][] workArray, double[][] filterArray) {
        int ww = workArray[0].length;       // workArray width
        int wh = workArray.length;          // workArray height
        int fw = filterArray[0].length;     // filterArray width
        int fh = filterArray.length;        // filterArray height

        double[][] result = new double[wh][ww];

        for (int wr = fh / 2; wr < wh - fh / 2; wr++) {         // workArray row
            for (int wc = fw / 2; wc < ww - fw / 2; wc++) {     // workArray column
                double mean = 0;

                for (int fr = 0; fr < fh; fr++) {               // filterArray row
                    for (int fc = 0; fc < fw; fc++) {           // filterArray column
                        mean += workArray[wr - fh / 2 + fr][wc - fw / 2 + fc] * filterArray[fr][fc];
                    }
                }

                result[wr][wc] = mean;
            }
        }

        return result;
    }

    private static void print(double[][] workArray) {
        if (workArray != null) {
            for (int y = 0; y < workArray.length; y++) {
                for (int x = 0; x < workArray[y].length; x++) {
                    System.out.printf("%.2f", workArray[y][x]);
                    System.out.print("\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] myResultArray;

        double[][] myFilter1 = genMeanFilter(3);
        print(myFilter1);
        double[][] myFilter2 = genMeanFilter(5);
        print(myFilter2);

        double[][] myArray1 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};
        print(myArray1);

        myResultArray = applyFilter(myArray1, myFilter1);
        print(myResultArray);
        myResultArray = applyFilter(myArray1, myFilter2);
        print(myResultArray);

        double[][] myArray2 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        print(myArray2);

        double[][] myFilter3 = {{0, 1, 0}, {0, 0, 0}, {0, 0, 0}};
        print(myFilter3);

        myResultArray = applyFilter(myArray2, myFilter3);
        print(myResultArray);
    }
}
