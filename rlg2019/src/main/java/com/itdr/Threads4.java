package com.itdr;

public class Threads4 {
    public static void main(String[] args) {
        new Threads4().go();
        Thread t = new Thread();

    }

    public void go() {
        Runnable r = new Runnable() {
            public void run() {
                System.out.print("foo");
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
}