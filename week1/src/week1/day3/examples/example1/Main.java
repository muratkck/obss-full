package week1.day3.examples.example1;

import week1.day3.examples.example1.exceptions.EntryNotFoundException;

// Should be revised!
public class Main {
    public static void main(String[] args) {

       DataStore datastore = new InMemoryDataStore(3);

        try {
            datastore.put("Murat", "1234");
            System.out.println("Old value for key 'Murat': " + datastore.get("Murat"));

            datastore.put("Murat", "456345645");
            System.out.println("New value for key 'Murat': " + datastore.get("Murat"));


        } catch (EntryNotFoundException e) {
            System.out.println(e.getMessage());;
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
