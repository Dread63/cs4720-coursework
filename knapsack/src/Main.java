// CS 4720
// Homework #2 - Knapsack implementation
// Student: Joshua Douglas | jdougla8@uccs.edu
// Date: 12/4/25

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    public static final int MAX_PRINTABLE_MATRIX_LEN = 50;

    public static void main(String[] args) {

        // BOOK EXAMPLE
        System.out.println("---BOOK EXAMPLE---");
        int capacity1 = 6;
        int numItems1 = 4;
        int[] values1 = {3,2,4,4};
        int[] sizes1 = {4,3,2,3};

        solveKnapSack(values1, sizes1, capacity1, numItems1);

        // IN CLASS EXAMPLE
        System.out.println("---IN CLASS EXAMPLE---");
        int capacity2 = 7;
        int numItems2 = 5;
        int[] values2 = {5,3,4,10,6};
        int[] sizes2 = {6,2,3,4,1};

        solveKnapSack(values2, sizes2, capacity2, numItems2);

        // LARGE FILE EXAMPLE
        System.out.println("---LARGE FILE EXAMPLE---");
        int capacity3 = 0;
        int numItems3 = 0;
        int[] sizes3 = null;
        int[] values3 = null;

        // Assumed that input file is inside of root directory of project
        File knapSackInfo = new File("problem16.7test-1.txt");

        // Used to catch issues with file reading
        try (Scanner FileReader = new Scanner(knapSackInfo)) {

            // First 2 integers in files will always be the knapsack capacity followed by number of items available to store in the knapsack
            capacity3 = FileReader.nextInt();
            numItems3 = FileReader.nextInt();

            // These two arrays are used to walk through values and populate the 2D array A according
            // to different comparison operations in the for loop
            sizes3 = new int[numItems3];
            values3 = new int[numItems3];

            // Each line following the capacity and number of items has first a values, then its corresponding size
            int index = 0;
            while (FileReader.hasNextInt()) {

                values3[index] = FileReader.nextInt();
                sizes3[index++] = FileReader.nextInt();
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found\n");
        }

        // Call solveKnapSack to run algorithm on ingested data
        solveKnapSack(values3, sizes3, capacity3, numItems3);
    }

    // Complete the 2D array and find the greatest value a knapsack of size C can hold with items of value V and size S
    private static void solveKnapSack(int[] values, int[] sizes, int capacity, int numItems) {

        System.out.println("Values\n" + Arrays.toString(values));
        System.out.println("Sizes\n" + Arrays.toString(sizes));
        System.out.println("Solving Knapsack...\n");

        // 2D array where A[i][c] => max value obtainable using items 1 through i at a knapsack capacity of c
        // A is 1 row and 1 column larger than the number of items and the size because it
        // includes the case where the knapsack size is 0 and therefore zero elements are included => A[0][0]
        int[][] A = new int[numItems + 1][capacity + 1];

        for (int i = 1; i <= numItems; i++) {

            for (int c = 0; c <= capacity; c++) {

                // We begin indexing from i-1 since array starts at 1
                // If the size of the value at (i-1) exceeds c, then we just copy the previous value over because
                // item i-1 is too large to fit in the knapsack so we cannot include it
                if (sizes[i - 1] > c) {

                    // If this block gets entered, we have to set the next entry in the 2d array to that of the previous
                    // entry since the best solution is the same as before without considering this item at A[i-1][c]
                    A[i][c] = A[i - 1][c];
                }

                // If this block is entered, we have found an item with weight that does not exceed the current capacity
                else {

                    // In this scenario, the following two cases determine what's placed into the current cell
                    // 1. The current greatest value found at A[i-1][c] is the best we can do (same as before)
                    // 2. Including item[i-1] gives us a greater total value despite the hit to capacity (better than before)
                    A[i][c] = Math.max(A[i - 1][c], A[i - 1][c - sizes[i - 1]] + values[i - 1]);
                }
            }
        }

        // Backtrack to list selected items that lead to the largest solution
        ArrayList<Integer> selectedValues = new ArrayList<>();
        int c = capacity;

        // This time, start at the bottom right of the 2D array (indicating the largest possible value for the given knapsack capacity)
        for (int z = numItems; z > 0; z--) {

            // If the current value is greater than the value directly above it, we know that we ran into case 2 previously
            // and that the item at [z-1] was INCLUDED in the optimal solution
            if (A[z][c] > A[z-1][c]) {

                // Add the index to the selected values list since we are just listing the position of the values used in the solution
                // rather than their actual numerical value
                selectedValues.add(z);

                // Subtract the capacity of the value that we know was used in the solution to find the others
                c = c - sizes[z-1];
            }
        }

        System.out.println("--- FINAL ARRAY CONTENTS---");
        if (values.length < MAX_PRINTABLE_MATRIX_LEN) {
            print2DArray(A);
        }

        else {
            System.out.println("Matrix too large to print...");
        }

        Collections.reverse(selectedValues); // Reverse so we list the items included in a natural way
        System.out.print("Utilizing items: " + Arrays.toString(selectedValues.toArray()) + " with values [");
        for (int i = 0; i < selectedValues.size(); i++) {
            System.out.print(values[selectedValues.get(i) - 1]);
            if (i < selectedValues.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("--> The maximum optimal value is: " + A[numItems][capacity] + "\n");
    }


    // Used to print final 2D array created by knapsack algorithm
    private static void print2DArray(int[][] A) {

        // Get the length of the largest integer in the matrix to format the printed output uniform
        int arrayLength = A.length;
        int arrayHeight = A[0].length;
        int maxNumLen = String.valueOf(A[arrayLength-1][arrayHeight-1]).length();

        // Formatting output to have columns represent capacity and rows represent items
        System.out.println("+ C A P A C I T Y");
        char[] itemString = {'I', 'T', 'E', 'M', 'S'};
        for (int i = 0; i < A.length; i++) {

            if (i < itemString.length) {
                System.out.print(itemString[i] + " | ");
            }
            else {
                System.out.print("  | ");
            }

            for (int j = 0; j < A[i].length; j++) {
                System.out.printf("%" + maxNumLen + "d | ", A[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }
}