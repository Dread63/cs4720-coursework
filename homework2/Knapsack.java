// Joshua Douglas

package homework2;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Knapsack {

    public static void main (String args[]) throws FileNotFoundException {

        int knapSackSize = 0;
        int numItems = 0;
        int[][] A = null;
        int[] sizes = null;
        int[] values = null;

        File knapSackInfo = new File("problem16.7test-1.txt");

        try (Scanner FileReader = new Scanner(knapSackInfo)) {

            knapSackSize = FileReader.nextInt();
            numItems = FileReader.nextInt();

            A = new int[numItems+1][knapSackSize+1];

            sizes = new int[numItems];
            values = new int[numItems];
            int index = 0;

            while (FileReader.hasNextInt()) {

                values[index] = FileReader.nextInt();
                sizes[index++] = FileReader.nextInt();
            }
        }

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

        System.out.println("Maximum value: " + A[numItems][knapSackSize]);
    }
}