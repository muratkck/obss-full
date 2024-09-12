package week1.day3.examples.example1;

import week1.day3.examples.example1.exceptions.CapacityFullException;
import week1.day3.examples.example1.exceptions.EntryNotFoundException;
import week1.day3.examples.example1.exceptions.NullKeyException;

public class InMemoryDataStore implements DataStore {

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
    public String get(String key) {

        if (key == null){
            throw new NullKeyException("Given key is null!");
        }

        int index = findItemIndex(key);

        if(index == -1){
            throw new EntryNotFoundException("Entry not found");
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
