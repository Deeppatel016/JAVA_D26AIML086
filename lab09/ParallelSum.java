package lab09;

class ParallelSum {
    static long total = 0;

    static synchronized void add(long value) {
        total += value;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 1;

        int n = 4;
        Thread[] threads = new Thread[n];

        total = 0;
        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            int from = i * arr.length / n;
            int to = (i + 1) * arr.length / n;

            threads[i] = new Thread(() -> {
                for (int j = from; j < to; j++)
                    total += arr[j];
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();
        System.out.println("Without synchronization: " + total);
        System.out.println("Time: " + (System.nanoTime() - start) + " ns");

        total = 0;
        start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            int from = i * arr.length / n;
            int to = (i + 1) * arr.length / n;

            threads[i] = new Thread(() -> {
                for (int j = from; j < to; j++)
                    add(arr[j]);
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();
        System.out.println("With synchronization: " + total);
        System.out.println("Time: " + (System.nanoTime() - start) + " ns");

        total = 0;
        long[] sums = new long[n];
        start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            final int k = i;
            int from = i * arr.length / n;
            int to = (i + 1) * arr.length / n;

            threads[i] = new Thread(() -> {
                for (int j = from; j < to; j++)
                    sums[k] += arr[j];
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();
        for (long sum : sums) total += sum;

        System.out.println("Using local totals: " + total);
        System.out.println("Time: " + (System.nanoTime() - start) + " ns");
    }
}