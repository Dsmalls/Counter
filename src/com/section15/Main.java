package com.section15;

public class Main {

    public static void main(String[] args) {

        // setting the name to start the thread
        Countdown countdown = new Countdown();


        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class Countdown{

    // instance variable...previously used a local variable in the for loop of (int j)
    private int j;

    public void doCountdown(){ // counts down from 10-1
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1": // name of the thread
                color = ThreadColor.ANSI_RED;
                break;

            case "Thread 2": // name of thread
                color = ThreadColor.ANSI_PURPLE;
                break;

            default:
                color = ThreadColor.ANSI_BLUE;
        }

        // synchronized block
        // added the synchronized that allows the thread to run without any breaks or jumping to the next thread
        synchronized (this){
            for (j = 10; j>0; j--) {
                System.out.println(color + Thread.currentThread().getName() + ": j =" + j);
            }
        }
    }
}

// second class
class CountdownThread extends Thread{
    private Countdown threadCountdown;

    CountdownThread(Countdown countdown){
        threadCountdown = countdown;
    }

    public void run(){
        threadCountdown.doCountdown();
    }
}
