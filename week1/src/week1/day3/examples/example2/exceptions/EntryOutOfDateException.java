package week1.day3.examples.example2.exceptions;

public class EntryOutOfDateException extends RuntimeException {
    public EntryOutOfDateException(String message) {
        super(message);
    }
}
