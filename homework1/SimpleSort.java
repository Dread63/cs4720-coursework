// Joshua Douglas

package homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class SimpleSort {
    
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


        long startingTime = System.nanoTime();
        int[] sortedArray = selectionSort(array);
        long endingTime = System.nanoTime();
        long totalTime = endingTime - startingTime;

        System.out.println("Algorithm took: " + (totalTime / 1_000_000.0) + "ms");
    }

    private static int[] selectionSort(int[] array) {


        for (int i = 0; i < array.length; i++) {

            for (int j = i+1; j < array.length; j++) {

                if (array[i] > array[j]) {

                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }

        return array;
    }
}
