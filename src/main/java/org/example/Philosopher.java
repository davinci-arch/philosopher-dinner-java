package org.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {

    private final int id;
    private int idLeftFork;
    private int idRightFork;
    private Semaphore waitForFork;
    private Semaphore permissionDenied;
    private Semaphore[] forks;

    public Philosopher(int id, Semaphore waitForFork, Semaphore permissionDenied, Semaphore[] forks) {
        this.id = id;
        idLeftFork = ((id % 5) + 1) - 1;
        idRightFork = id - 1;
        this.waitForFork = waitForFork;
        this.permissionDenied = permissionDenied;
        this.forks = forks;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 10; i++) {
            System.out.println("Phylosopher " + id + " thinking " + i + " times");

            try {
                while (true) {
                    permissionDenied.acquire();
                    if (forks[idLeftFork].availablePermits() == 1 && forks[idRightFork].availablePermits() == 1) {
                        forks[idLeftFork].acquire();
                        System.out.println("Phylosopher " + id + " took left fork with id: " + idLeftFork);
                        forks[idRightFork].acquire();
                        System.out.println("Phylosopher " + id + " took right fork with id: " + idRightFork);
                        break;
                    } else {
                        permissionDenied.release();
                        waitForFork.acquire();
                    }
                }
                permissionDenied.release();

                System.out.println("Phylosopher " + id + " eating " + i + " times");

                forks[idRightFork].release();
                System.out.println("Phylosopher " + id + " put right fork");
                forks[idLeftFork].release();
                System.out.println("Phylosopher " + id + " put left fork");

                waitForFork.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
