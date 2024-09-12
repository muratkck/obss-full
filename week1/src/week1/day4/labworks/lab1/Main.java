package week1.day4.labworks.lab1;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        String text = "Merhabİ1 Merhaba1 asdf. Merhaba asdf Merhaba merhaba";

        String[] words = text.replace(".", "").toLowerCase(Locale.forLanguageTag("tr-TR")).split(" ");

        Map<String, Integer> frequencyMap = new TreeMap<>();

        for (String word : words) {
            if(!frequencyMap.containsKey(word)){
                frequencyMap.put(word, 1);
            }
            else {
                frequencyMap.put(word, frequencyMap.get(word) + 1);
            }
        }


        for (String word : frequencyMap.keySet()) {
            System.out.println(word + ": " + frequencyMap.get(word));
        }
    }
}
