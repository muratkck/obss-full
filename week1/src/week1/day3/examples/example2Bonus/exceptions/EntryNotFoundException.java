package week1.day3.examples.example2Bonus.exceptions;

public class EntryNotFoundException extends RuntimeException{
    public EntryNotFoundException(String message) {
        super(message);
    }
}
