package week1.day3.examples.example2Bonus;


import week1.day3.examples.example2Bonus.exceptions.EntryNotFoundException;
import week1.day3.examples.example2Bonus.exceptions.EntryOutOfDateException;
import week1.day3.examples.example2Bonus.fileDataStore.Datastore;
import week1.day3.examples.example2Bonus.fileDataStore.FileDataStore;

import java.io.IOException;

public class FileDataStoreApp {
    public static void main(String[] args) {

        String filePath = "/home/muratkucuk/Desktop/challenge3-new/src/com/challenge3/fileDataStoreApp/Files";

        Datastore fileDataStore = new FileDataStore(filePath);

        try {
            fileDataStore.put("Murat", "1234");
            System.out.println("Old value for key 'Murat': " + fileDataStore.get("Murat"));

            fileDataStore.put("Murat", "456345645");
            System.out.println("New value for key 'Murat': " + fileDataStore.get("Murat"));


            fileDataStore.put("Murat-expired", "456345645", 2);
            Thread.sleep(3000);

            System.out.println("***** Expired entity error *****");
            fileDataStore.get("Murat-expired");


        } catch (EntryNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(EntryOutOfDateException e){
            System.out.println(e.getMessage());
        }
        catch (IOException | InterruptedException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("***** Not exist key error *****");
        try {
            fileDataStore.get("notExistedKey");
        }
        catch (EntryNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
