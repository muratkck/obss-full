package week1.day4.withoutStream.example2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        
        List<City> cities = List.of(
                new City("Uşak", 2000),
                new City("İzmir", 5000),
                new City("Ankara", 1000)
        );

        Stream<City> citiesStream = cities.stream();

        Map<String, Integer> result = mapList(cities, (cityName) -> "The population of the city " + cityName + ": ");

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

    private static Map<String, Integer> mapList(List<City> cities, MapRule mapper) {
        Map<String, Integer> result = new HashMap<>();
        for (City city : cities) {
            result.put(mapper.map(city.getName()), city.getPopulation());
        }
        return result;
    }
}
