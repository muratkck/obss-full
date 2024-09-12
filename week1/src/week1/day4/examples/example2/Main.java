package week1.day4.examples.example2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<City> cities = List.of(
                new City("Uşak", 2000),
                new City("İzmir", 5000),
                new City("Ankara", 1000)
        );

        Stream<City> citiesStream = cities.stream();

        Map<String, Integer> result;
        /*
        // What differs with below one?
        /*
           - Streams are single-use, forEach consumes the citiesStream below.
           - This code will work correctly only if citiesStream is not reused afterward.

         */
        /*
        Map<String, Integer> result = new HashMap<>();
        citiesStream.forEach(p -> {
            result.put(p.getName(), p.getPopulation());
        });

        result.forEach((k, v) -> System.out.println(k + ": " + v));
        */

        // This approach is cleaner! We can use additional methods after collect.
        result = citiesStream.collect(Collectors.toMap(
                City::getName, City::getPopulation
        ));

        try{
            citiesStream.forEach(p -> System.out.println(p.getName()));
        }
        catch(IllegalStateException e){
            System.out.println("We cannot use stream second times!");
        }

        result.forEach((k, v) -> System.out.println(k + ": " + v));


    }
}
