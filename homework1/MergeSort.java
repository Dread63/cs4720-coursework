// Joshua Douglas

package homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort {
    
        public static void main (String args[]) {

        System.out.println("Joshua Douglas");

        ArrayList<Integer> temp = new ArrayList<Integer>();

        // Read file contents into array
        File inputFile = new File("homework1/reverse_50000.txt");

        try (Scanner fileReader = new Scanner(inputFile)) {

            while (fileReader.hasNextLine()) {

                temp.add(fileReader.nextInt());
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("Exeception caught: " + e);
        } 

        // Convert arraylist to array
        int[] array = new int[temp.size()];
        for(int i = 0; i < temp.size(); i++) {
            array[i] = temp.get(i);
        }

        long startingTime = System.nanoTime();
        int[] sortedArray = mergeSort(array);
        long endingTime = System.nanoTime();
        long totalTime = endingTime - startingTime;

        System.out.println("Algorithm took: " + (totalTime / 1_000_000.0) + "ms");
    }

    private static int[] mergeSort(int[] array) {

        int[] sortedArray = new int[array.length];

        // Check if array only has one element
        if (array.length <= 1) {
            
            return array;
        }

        else {

            int len = array.length;
            int middle = len/2;

            int[] left = new int[middle];
            int[] right = new int[len-middle];

            // Fill left and right arrays with their values
            for(int i = 0; i < middle; i++) {

                left[i] = array[i];
            }

            for(int i = middle; i < len; i++) {

                right[i-middle] = array[i];
            }

            left = mergeSort(left);
            right = mergeSort(right);

            // Indexes to track values throughout merge step
            int i=0, j=0, k=0;
            while(i < right.length && j < left.length) {

                if (right[i] <= left[j]) {
                    sortedArray[k++] = right[i++];
                }

                else {
                    sortedArray[k++] = left[j++];
                }
            }

            // Fill sorted array with remaining elements
            while (i < right.length) {
                sortedArray[k++] = right[i++];
            }

            while (j < left.length) {
                sortedArray[k++] = left[j++];
            }
        }

        return sortedArray;
    }
}