package week1.day4.examples.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Integer> originalArray  = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            originalArray.add(i);
        }

        Stream<Integer> streamPrime = originalArray.stream();
        List<Integer> newArray = streamPrime.map(number -> {
            if (number <= 1) {
                return -1;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return -1;
                }
            }
            return number;
        })
        .filter(number -> number != -1)
        .toList();

        for (Integer number : newArray) {
            System.out.println(number);
        }
    }


}
