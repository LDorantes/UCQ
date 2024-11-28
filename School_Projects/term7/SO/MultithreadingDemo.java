class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread " + name + " running iteration " + i);
            try {
                Thread.sleep(1000); // Simulates work with a 1-second pause
            } catch (InterruptedException e) {
                System.out.println("Thread " + name + " was interrupted.");
            }
        }
        System.out.println("Thread " + name + " finished.");
    }
}

public class MultithreadingDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task("A"));
        Thread thread2 = new Thread(new Task("B"));
        Thread thread3 = new Thread(new Task("C"));

        System.out.println("Starting threads...");
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted.");
        }

        System.out.println("All threads have finished.");
    }
}
