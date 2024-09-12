package week1.day3.examples.example1.exceptions;

public class CapacityFullException extends RuntimeException{
    public CapacityFullException(String message) {
        super(message);
    }
}
