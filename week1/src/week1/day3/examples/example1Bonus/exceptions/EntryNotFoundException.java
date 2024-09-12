package week1.day3.examples.example1Bonus.exceptions;

public class EntryNotFoundException extends RuntimeException{
    public EntryNotFoundException(String message) {
        super(message);
    }
}
