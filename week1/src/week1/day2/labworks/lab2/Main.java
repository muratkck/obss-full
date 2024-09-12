package week1.day2.labworks.lab2;

import week1.day2.labworks.lab2.entities.Bus;
import week1.day2.labworks.lab2.entities.Destination;
import week1.day2.labworks.lab2.entities.Passenger;

public class Main {

    public static void main(String[] args) {
        Bus bus1 = new Bus(Destination.ISTANBUL);

        bus1.insertPassenger(new Passenger("Murat", Destination.ISTANBUL));
        bus1.insertPassenger(new Passenger("Ahmet", Destination.ISTANBUL));
        bus1.insertPassenger(new Passenger("Veli", Destination.ADANA));
        bus1.insertPassenger(new Passenger("Hüseyin", Destination.ISTANBUL));

    }
}
