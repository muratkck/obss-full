package week1.day3.examples.example1Bonus.dataStore;

public interface Datastore {
    void put(String key, String value);
    void put(String key, String value, int ttl);
    String get(String key);
}
