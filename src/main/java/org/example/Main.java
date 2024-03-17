package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore[] forks = new Semaphore[5];
        Semaphore waitForFork = new Semaphore(0);
        Semaphore permissionDenied = new Semaphore(1);
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Semaphore(1);
        }
        for (int i = 1; i <= 5; i++) {
            new Philosopher(i, waitForFork, permissionDenied, forks).start();
        }


    }


}