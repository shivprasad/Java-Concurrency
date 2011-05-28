package concurrency.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 22, 2009
 * Time: 8:57:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class Consumer implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        try {
            while (true) {
                Integer number = blockingQueue.take();
                System.out.printf("[%s] Square of %s\n", Thread.currentThread().getName(), number * number);
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
