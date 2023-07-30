package com.advait.interviewprep.multithreading;

/**
To run one thread after another
 */
public class Sample1 implements Runnable{

    private static volatile int num = 1;

    @Override
    public void run() {

        if (Thread.currentThread().getName().equals("oneToTen")) {
            while (num < 10) {
                System.out.println(num);
                num = num + 1;
            }
        } else {
            while (num < 19) {
                System.out.println(num);
                num = num + 1;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        Sample1 sample1 = new Sample1();

        Thread t1 = new Thread(sample1);
        t1.setName("oneToTen");
        t1.start();

        Thread t2 = new Thread(sample1);
        t1.join();
        t2.start();
    }
}
