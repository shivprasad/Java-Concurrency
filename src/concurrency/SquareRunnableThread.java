package concurrency;

import static concurrency.executors.ExecutorDemo.concurrentHashMap;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 22, 2009
 * Time: 5:25:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class SquareRunnableThread implements Runnable {
    private int number;

    public SquareRunnableThread(int number) {
        this.number = number;
    }

    public void run() {
        try {
            Thread.sleep(Math.abs(new Random().nextInt() * 500 % 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            String threadName = Thread.currentThread().getName();
            if (concurrentHashMap.containsKey(threadName)) {
                Integer count = concurrentHashMap.get(threadName);
                concurrentHashMap.put(threadName, count + 1);
            } else
                concurrentHashMap.put(threadName, 1);
        //System.out.printf("[%s] The square is - %s\n", Thread.currentThread().getName(), number * number);
    }
}