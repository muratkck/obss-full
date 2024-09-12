package week1.day3.examples.example2Bonus.fileDataStore;

import java.io.IOException;

public interface Datastore {
    void put(String key, String value) throws IOException;
    void put(String key, String value, int ttl) throws IOException;
    String get(String key) throws IOException, ClassNotFoundException;
}
