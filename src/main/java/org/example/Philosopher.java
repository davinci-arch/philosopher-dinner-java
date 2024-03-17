package org.example;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private final int id;
    private int idLeftFork;
    private int idRightFork;
    private Semaphore[] forks;

    public Philosopher(int id) {
        this.id = id;
        idLeftFork = id;
        idRightFork = id % 5;
        forks = getForks();
    }

    private static Semaphore[] getForks() {
        Semaphore[] forks = new Semaphore[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }

        return forks;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Phylosopher " + id + " thinking " + i + " times");

            try {
                forks[idLeftFork].acquire();
                System.out.println("Phylosopher " + id + " took left fork");

                forks[idRightFork].acquire();
                System.out.println("Phylosopher " + id + " took right fork");


                System.out.println("Phylosopher " + id + " eating " + i + " times");

                forks[idRightFork].release();
                System.out.println("Phylosopher " + id + " put right fork");

                forks[idLeftFork].release();
                System.out.println("Phylosopher " + id + " put left fork");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
