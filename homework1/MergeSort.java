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
        File inputFile = new File("homework1/problem3.5test.txt");

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

        int[] sortedArray = mergeSort(array);

        for (int i = 0; i < sortedArray.length; i++) {
            System.out.println(sortedArray[i]);
        }
    }

    private static int[] mergeSort(int[] array) {

        int[] sortedArray = {0};


        return sortedArray;
    }
}