package week1.day3.examples.example2;


import week1.day3.examples.example2.DataStore.DataStore;
import week1.day3.examples.example2.DataStore.FileDataStore;
import week1.day3.examples.example2.exceptions.EntryNotFoundException;
import week1.day3.examples.example2.exceptions.EntryOutOfDateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String folderPath = "/home/muratkucuk/Desktop/Workshop/OBSS/murat.kucuk/src/week1/day3/examples/example2/Files";
        DataStore dataStore = new FileDataStore(folderPath);

        try {
            dataStore.put("Murat", "1234");
            System.out.println("Old value for key 'Murat': " + dataStore.get("Murat"));

            dataStore.put("Murat", "456345645");
            System.out.println("New value for key 'Murat': " + dataStore.get("Murat"));

        } catch (EntryNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(EntryOutOfDateException e){
            System.out.println(e.getMessage());
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("***** Not exist key error *****");
        try {
            dataStore.get("notExistedKey");
        }
        catch (EntryNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}