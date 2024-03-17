package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Philosopher(i).start();
        }


    }


}