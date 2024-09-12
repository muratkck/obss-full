package week1.day3.examples.example2.DataStore;

import week1.day3.examples.example2.entities.Entry;
import week1.day3.examples.example2.exceptions.EntryNotFoundException;

import java.io.*;

public class FileDataStore implements DataStore {

    Entry entry;
    String filePath;

    public FileDataStore(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void put(String key, String value) throws IOException {

        entry = new Entry(key, value);
        String filePath = setFilePath(key);
        put(entry, filePath);
    }

    private void put (Entry entry, String filePath) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(entry);
        }
    }

    @Override
    public String get(String key) throws IOException, ClassNotFoundException {
        File file = new File(setFilePath(key));
        if(!file.exists()) {
            throw new EntryNotFoundException("Entry is not exist!");
        }
        Entry entry = readFromFile(setFilePath(key));
        return entry.getValue();
    }

    private Entry readFromFile(String s) throws IOException, ClassNotFoundException {

        Entry entry;
        try(FileInputStream fileInputStream = new FileInputStream(s);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){

            entry = (Entry) objectInputStream.readObject();
        }
        return entry;
    }

    public String setFilePath(String key) {
        return filePath + "/" + key + ".txt";
    }
}
