package syncronization.using.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MovieReservationSystem {
    private int availableSeats;
    private final Lock reservationLock;

    public MovieReservationSystem(int totalSeats) {
        //initialises lock and the total seats available for reservation
        this.availableSeats = totalSeats;
        this.reservationLock = new ReentrantLock();
    }


    public boolean reserveSeats(int numSeats) {

        try {
            if(reservationLock.tryLock() && availableSeats >= numSeats) {
                availableSeats -= numSeats;
                return true;
            }
        } finally {
            reservationLock.unlock();
        }
        return false;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

}