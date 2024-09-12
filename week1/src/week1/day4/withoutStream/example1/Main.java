package week1.day4.withoutStream.example1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> myIntegerList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 13);

        List<Integer> result = filterList(myIntegerList, (number) -> {
            if (number <= 1)
                return false;

            // Check from 2 to sqrt(n)
            for (int i = 2; i <= Math.sqrt(number); i++)
                if (number % i == 0)
                    return false;

            return true;
        });

        for (Integer i : result) {
            System.out.println(i);
        }

        System.out.println("*********************************");

        List<Integer> resultMapping = mapList(result, (n) -> n * 2);

        for (Integer i : resultMapping) {
            System.out.println(i);
        }
    }

    private static List<Integer> filterList(List<Integer> myIntegerList, FilterRule filterRule) {
        List<Integer> result = new ArrayList<>();

        for (Integer i : myIntegerList) {
            if(filterRule.satisfiesCondition(i)){
                result.add(i);
            }
        }
        return result;
    }

    private static List<Integer> mapList(List<Integer> myIntegerList, MapRule mapper) {
        List<Integer> result = new ArrayList<>();

        for (Integer i : myIntegerList) {
            result.add(mapper.map(i));
        }
        return result;
    }
}
