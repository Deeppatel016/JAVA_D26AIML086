package lab10;

class Buffer {
    int item;
    boolean available = false;

    synchronized void produce(int value) throws InterruptedException {
        while (available)
            wait();

        item = value;
        available = true;
        System.out.println("Produced: " + value);
        notifyAll();
    }

    synchronized int consume() throws InterruptedException {
        while (!available)
            wait();

        int value = item;
        available = false;
        System.out.println("Consumed: " + value);
        notifyAll();
        return value;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Buffer b = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    b.produce(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    b.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items processed.");
    }
}