package week1.day2.labworks.lab2.entities;

public class Bus {
    public static final int capacity = 2;

    private Destination destination;
    private Passenger[] passengers;
    private static int passengerCount = 0;
    
    public Bus(Destination destination) {
        this.destination = destination;
        this.passengers = new Passenger[capacity];
    }

    public void insertPassenger(Passenger passenger){

        // TRY two if not nested: passengerCount >= capacity

        if (passenger.getDestination() != this.destination){
            System.out.println("Destinations do not match");
            return;
        }
        if (passengerCount >= capacity){
            System.out.println("Bus is full");
            return;
        }
        passengers[passengerCount++] = passenger;
        System.out.println("Passenger " + passenger.getName() + " inserted");

        /*
        if (passengerCount < capacity){
            if (passenger.getDestination() == (this.destination)){
                passengers[passengerCount++] = passenger;
                System.out.println("Passenger inserted.");
            }
            else{
                System.out.println("Destinations do not match!");
            }
        }
        else{
            System.out.println("Bus is full, cannot add more passengers.");
        }
        */
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }
}
