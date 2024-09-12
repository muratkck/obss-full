package week1.day1.examples.example4a;

import java.util.Arrays;

public class Example4a {
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        int left = 0;
        int right = array.length - 1;

        System.out.println("Original Array: " + Arrays.toString(array));

        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
        System.out.println("Reversed Array: " + Arrays.toString(array));

    }
}
