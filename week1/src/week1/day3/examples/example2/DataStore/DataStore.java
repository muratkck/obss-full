package week1.day3.examples.example2.DataStore;

import java.io.IOException;

public interface DataStore {
    void put(String key, String value) throws IOException;
    String get(String key) throws ClassNotFoundException, IOException;
}
