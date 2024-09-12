package week1.day3.examples.example1Bonus;


import week1.day3.examples.example1Bonus.dataStore.Datastore;
import week1.day3.examples.example1Bonus.dataStore.InMemoryDataStore;
import week1.day3.examples.example1Bonus.exceptions.EntryNotFoundException;
import week1.day3.examples.example1Bonus.exceptions.EntryOutOfDateException;

public class DataStoreApp {
    public static void main(String[] args) {

        Datastore datastore = new InMemoryDataStore(3);

        try {
            datastore.put("Murat", "1111");
            System.out.println("Old value for key 'Murat': " + datastore.get("Murat"));

            datastore.put("Murat", "222222222");
            System.out.println("New value for key 'Murat': " + datastore.get("Murat"));


            datastore.put("Murat-expired", "123456789", 2);
            Thread.sleep(3000);

            System.out.println("***** Expired entity error *****");
            datastore.get("Murat-expired");
        }
        catch (EntryNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(EntryOutOfDateException e){
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }

        System.out.println("***** Not exist key error *****");
        try {
            datastore.get("notExistedKey");
        }
        catch (EntryNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
