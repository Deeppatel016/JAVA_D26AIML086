package lab09;

class SeatBooking {
    int seatsLeft = 5;
    int booked = 0;

    void book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            booked++;
            System.out.println("Seat booked");
        }
    }

    synchronized void safeBook() {
        if (seatsLeft > 0) {
            seatsLeft--;
            booked++;
            System.out.println("Seat booked");
        }
    }
}

public class SeatBookingRace {
    public static void main(String[] args) throws InterruptedException {
        SeatBooking s = new SeatBooking();
        Thread[] threads = new Thread[10];

        
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> s.book());
            threads[i].start();
        }

        for (Thread t : threads)
            t.join();

        System.out.println("Without synchronization: " + s.booked);

        
        s.seatsLeft = 5;
        s.booked = 0;

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> s.safeBook());
            threads[i].start();
        }

        for (Thread t : threads)
            t.join();

        System.out.println("With synchronization: " + s.booked);
    }
}