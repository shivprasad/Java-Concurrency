package concurrency.executors;

import concurrency.SquareRunnableThread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 22, 2009
 * Time: 6:45:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorDemo {

    public static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) throws InterruptedException {
        UsingCachedThreadPool();
        UsingFixedThreadPool();
    }

    private static void UsingCachedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i =0;i<100;i++){
            Thread.sleep(Math.abs(new Random().nextInt()*200%200));
            executorService.submit(new SquareRunnableThread(i));
        }
        stopWatch.stop();
        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
        for (String threadName : concurrentHashMap.keySet()) {
            System.out.printf(" %s is getting reused %s times\n",threadName,concurrentHashMap.get(threadName));
        }
        System.out.println("Total time required UsingCachedThreadPool "+stopWatch.getElapsedTime() + " mili-seconds.");
        concurrentHashMap.clear();
    }

    private static void UsingFixedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i =0;i<100;i++){
            Thread.sleep(Math.abs(new Random().nextInt()*200%200));
            executorService.submit(new SquareRunnableThread(i));
        }
        stopWatch.stop();
        executorService.shutdown();
        executorService.awaitTermination(20, TimeUnit.SECONDS);
        for (String threadName : concurrentHashMap.keySet()) {
            System.out.printf(" %s is getting reused %s times\n",threadName,concurrentHashMap.get(threadName));
        }
        System.out.println("Total time required UsingFixedThreadPool "+stopWatch.getElapsedTime() + " mili-seconds.");
        concurrentHashMap.clear();

    }

}
