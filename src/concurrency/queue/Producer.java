package concurrency.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 22, 2009
 * Time: 8:56:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Producer implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        try {
            for(int i=0;i<10;i++){
                blockingQueue.put(i);
                System.out.printf("[%s] Produced %s\n",Thread.currentThread().getName(),i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
