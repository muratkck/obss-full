package week1.day3.examples.example1Bonus.dataStore;


import week1.day3.examples.example1Bonus.exceptions.CapacityFullException;
import week1.day3.examples.example1Bonus.exceptions.EntryNotFoundException;
import week1.day3.examples.example1Bonus.exceptions.EntryOutOfDateException;

public class InMemoryDataStore implements Datastore{

    private Entry[] store;
    private int entryCount = 0;

    public InMemoryDataStore(int size) {
        this.store = new Entry[size];
    }

    @Override
    public void put(String key, String value) {
        put(new Entry(key, value));
    }

    @Override
    public void put(String key, String value, int ttl) {
        put(new Entry(key, value, ttl));
    }

    @Override
    public String get(String key) {

        int index = findItemIndex(key);

        if(index == -1){
            throw new EntryNotFoundException("Entry not found");
        }

        if (store[index].isExpired()){
            throw new EntryOutOfDateException("Entry is expired!");
        }
        return store[index].getValue();
    }

    private void put(Entry entry) {
        int index = findItemIndex(entry.getKey());
        if(index != -1){
            store[index] = entry;
            return;
        }
        if (entryCount == store.length) {
            throw new CapacityFullException("Capacity is full! Item could not be added to the store");
        }
        store[entryCount++] = entry;
    }

    private int findItemIndex(String key) {
        for (int i = 0; i < entryCount; i++) {
            if (store[i].getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }
}
