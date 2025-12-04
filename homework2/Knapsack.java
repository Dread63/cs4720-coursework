// Joshua Douglas

package homework2;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Knapsack {

    public static void main (String args[]) throws FileNotFoundException {

        int knapSackSize = 0;
        int numItems = 0;

        int[] sizes = null;
        int[] values = null;

        File knapSackInfo = new File("problem16.7test-1.txt");

        // Used to catch issues with file reading
        try (Scanner FileReader = new Scanner(knapSackInfo)) {

            // First 2 integers in files will always be the knapsack capacity followed by number of items available to store in the knapsack
            knapSackSize = FileReader.nextInt();
            numItems = FileReader.nextInt();

            // A is 1 row and 1 column larger than the number of items and the size because it
            // includes the case where the knapsack size is 0 and zero elements are included => A[0][0]
            A = new int[numItems+1][knapSackSize+1];
            
            // These two arrays are used to walk through values and populate the 2D array A according
            // to different comparison operations in the for loop
            sizes = new int[numItems];
            values = new int[numItems];
            
            // Each line following the capacity and number of items has first a values, then its cooresponding size
            int index = 0;
            while (FileReader.hasNextInt()) {

                values[index] = FileReader.nextInt();
                sizes[index++] = FileReader.nextInt();
            }
        }

        

        System.out.println("Maximum value: " + A[numItems][knapSackSize]);
    }

    private int solveKnapsack(int[] values, int[] sizes, int capacity) {

        // 2D array where A[i][c] => max value obtainable using items 1 through i at a knapsack capacity of c
        int[][] A = null;

        for (int i = 1; i <= numItems; i++) {

            for (int c = 0; c <= knapSackSize; c++) {

                if (sizes[i - 1] > c) {
                    A[i][c] = A[i - 1][c];
                }

                else {
                    A[i][c] = Math.max(A[i - 1][c], A[i - 1][c - sizes[i - 1]] + values[i - 1]);
                }
            }
        }

        return 0;
    }
}