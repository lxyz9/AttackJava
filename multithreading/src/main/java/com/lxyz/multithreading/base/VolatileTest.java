package com.lxyz.multithreading.base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lbf
 * @date 2021/3/29
 */
public class VolatileTest {


    public static volatile int race = 0;
    public static final int THREAD_COUNT = 20;

   /* private static AtomicInteger race = new AtomicInteger(0);*/

    public static void increase() {
        race++;
//        race.incrementAndGet();
    }

    public static volatile boolean shutdown;

    public static void shutdown() {
        shutdown = true;
    }




    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            while (!shutdown) {
                System.out.println("doing something ...");
            }
        });

        Thread t2 = new Thread(() -> {
            shutdown();
        });


        ReentrantLock lock = new ReentrantLock();
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {

            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    lock.lock();
                    increase();
                    lock.unlock();
                }
            });

            threads[i].start();

        }
//        System.out.println(ClassLayout.parseInstance(race).toPrintable());
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
//        System.out.println(ClassLayout.parseInstance(race).toPrintable());
        System.out.println("total num is " + race);

//        t1.start();
//        Thread.sleep(2000);
//        t2.start();

    }



}
