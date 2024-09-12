package week1.day3.examples.example2Bonus.fileDataStore;



import week1.day3.examples.example2Bonus.exceptions.EntryNotFoundException;
import week1.day3.examples.example2Bonus.exceptions.EntryOutOfDateException;
import week1.day3.examples.example2Bonus.entities.Entry;

import java.io.*;

public class FileDataStore implements Datastore{

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

    @Override
    public void put(String key, String value, int ttl) throws IOException {
        entry = new Entry(key, value, ttl);
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
        if(entry.isExpired()){
            throw new EntryOutOfDateException("Entry is expired!");
        }

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
