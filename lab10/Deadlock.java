package lab10;

class Deadlock {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked lock1");

                synchronized (lock2) {
                    System.out.println("Thread 1 locked lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked lock2");

                synchronized (lock1) {
                    System.out.println("Thread 2 locked lock1");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join(1000);
        t2.join(1000);

        System.out.println("Deadlock example completed (threads may be stuck).");

    
        Thread t3 = new Thread(() -> {
            synchronized (lock1) {
                synchronized (lock2) {
                    System.out.println("Thread 3 completed safely");
                }
            }
        });

        Thread t4 = new Thread(() -> {
            synchronized (lock1) {
                synchronized (lock2) {
                    System.out.println("Thread 4 completed safely");
                }
            }
        });

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("Fixed example completed.");
    }
}