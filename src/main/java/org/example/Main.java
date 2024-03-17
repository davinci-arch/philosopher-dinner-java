package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore[] forks = new Semaphore[5];
        Semaphore permissionToTake = new Semaphore(4);
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Semaphore(1);
        }
        for (int i = 1; i <= 5; i++) {
            new Philosopher(i, permissionToTake, forks).start();
        }


    }


}