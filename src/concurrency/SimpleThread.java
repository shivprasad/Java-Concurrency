package concurrency;

import concurrency.executors.StopWatch;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 21, 2009
 * Time: 2:19:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleThread {

    public static int thisCounterIsNotThreadSafe = 0;

    public static void main(String[] args) {

//        Thread threadUsingRunnable = new Thread(new Runnable() {
//            public void run() {
//                SimpleThread.printMessage("Hello There");
//            }
//        }, "RunnableThread");
//
//        threadUsingRunnable.start();


//        new ExtendedThread("ExtendedThread").start();

//        UsingCounterThreads();
        UsingReadWriteLockCounterThreads();
//        UsingReentrantLockCounterThreads();
    }

    private static void UsingReadWriteLockCounterThreads() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        thisCounterIsNotThreadSafe = 0;
        long totalTime = executeReadWriteCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReadWriteCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReadWriteCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReadWriteCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReadWriteCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReadWriteCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        printMessage("Average Execution Time - " + totalTime / 6);

    }

    private static void UsingReentrantLockCounterThreads() {
        Lock lock = new ReentrantLock();
        thisCounterIsNotThreadSafe = 0;
        long totalTime = executeReentrantCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReentrantCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReentrantCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReentrantCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReentrantCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeReentrantCounters(lock);
        thisCounterIsNotThreadSafe = 0;
        printMessage("Average Execution Time - " + totalTime / 6);

    }

    private static void UsingCounterThreads() {
        thisCounterIsNotThreadSafe = 0;
        long totalTime = executeCounters();
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeCounters();
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeCounters();
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeCounters();
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeCounters();
        thisCounterIsNotThreadSafe = 0;
        totalTime += executeCounters();
        thisCounterIsNotThreadSafe = 0;
        printMessage("Average Execution Time - " + totalTime / 6);
    }

    private static long executeCounters() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 50; i++) {
            new Thread(new CounterThread(), "Thread " + i).start();
        }
        stopWatch.stop();
        printMessage("Total Time required for 50 CounterThreads - " + stopWatch.getElapsedTime() + " mili-seconds.");
        return stopWatch.getElapsedTime();
    }

    private static long executeReadWriteCounters(ReadWriteLock lock) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 50; i++) {
            new Thread(new ReadWriteLockCounterThread(lock), "Thread " + i).start();
        }
        stopWatch.stop();
        printMessage("Total Time required for 50 ReadWriteLockCounterThread - " + stopWatch.getElapsedTime() + " mili-seconds.");
        return stopWatch.getElapsedTime();
    }

    private static long executeReentrantCounters(Lock lock) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 50; i++) {
            new Thread(new ReentrantLockCounterThread(lock), "Thread " + i).start();
        }
        stopWatch.stop();
        printMessage("Total Time required for 50 ReentrantLockCounterThread - " + stopWatch.getElapsedTime() + " mili-seconds.");
        return stopWatch.getElapsedTime();
    }

    public static void printMessage(String message) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), message);
    }


    public synchronized static void incrementSynchronizedCounter() {
        SimpleThread.thisCounterIsNotThreadSafe += 1;
    }

    public static void incrementCounter() {
        SimpleThread.thisCounterIsNotThreadSafe += 1;
    }

}
