package org.example;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private final int id;
    private int idLeftFork;
    private int idRightFork;
    private Semaphore permissionToTake;
    private Semaphore[] forks;

    public Philosopher(int id, Semaphore permissionToTake, Semaphore[] forks) {
        this.id = id;
        idLeftFork = ((id % 5) + 1) - 1;
        idRightFork = id - 1;
        this.permissionToTake = permissionToTake;
        this.forks = forks;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 10; i++) {
            System.out.println("Phylosopher " + id + " thinking " + i + " times");

            try {

                permissionToTake.acquire();

                forks[idLeftFork].acquire();
                System.out.println("Phylosopher " + id + " took left fork with id: " + idLeftFork);
                forks[idRightFork].acquire();
                System.out.println("Phylosopher " + id + " took right fork with id: " + idRightFork);

                System.out.println("Phylosopher " + id + " eating " + i + " times");

                forks[idRightFork].release();
                System.out.println("Phylosopher " + id + " put right fork");
                forks[idLeftFork].release();
                System.out.println("Phylosopher " + id + " put left fork");

                permissionToTake.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
