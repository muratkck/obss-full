package org.example.exercises.otherExercises.sync;

public class SyncClass {

    Object obj = new Object();

    public void method1(){
        // blocks just obj used synchronized methods!
        synchronized(obj){
            sleep(3);
            System.out.println("method1 invoked!");
        }
    }

    // blocks the whole synchronized methods!
    public synchronized void method2(){
        sleep(5);
        System.out.println("method2 invoked!");
    }
    // method2 equals below:
    public void method2Same(){
        synchronized (this){
            sleep(5);
            System.out.println("method2 invoked!");
        }
    }

    public void method3(){
        synchronized (obj){
            System.out.println("method3 invoked!");
        }
    }

    public static void sleep(int seconds){
        int milliseconds = seconds * 1000;
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
