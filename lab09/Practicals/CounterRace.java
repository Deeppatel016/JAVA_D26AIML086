package lab09;

class Counter {
    int count = 0;

    void increment() {
        count++;
    }

    synchronized void safeIncrement() {
        count++;
    }
}

public class CounterRace {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++)
                    c.increment();
            });
            threads[i].start();
        }

        for (Thread t : threads)
            t.join();

        System.out.println("Without synchronization: " + c.count);


        c.count = 0;

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++)
                    c.safeIncrement();
            });
            threads[i].start();
        }

        for (Thread t : threads)
            t.join();

        System.out.println("With synchronization: " + c.count);
    }
}