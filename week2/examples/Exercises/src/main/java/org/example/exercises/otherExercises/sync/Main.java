package org.example.exercises.otherExercises.sync;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        SyncClass syncClass = new SyncClass();
        Thread thread1 = new Thread(() -> syncClass.method1());
        Thread thread2 = new Thread(() -> syncClass.method2());
        Thread thread3 = new Thread(() -> syncClass.method3());

        thread2.start();
        Thread.sleep(1000);
        thread1.start();
        thread3.start();



    }
}
