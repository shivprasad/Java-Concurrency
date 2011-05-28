package concurrency.queue;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 22, 2009
 * Time: 9:05:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

//         BlockingQueue queue = new ArrayBlockingQueue(5);
        BlockingQueue queue = new SynchronousQueue();

        new Thread(new Producer(queue),"Producer").start();
        new Thread(new Consumer(queue),"Consumer 1").start();
        new Thread(new Consumer(queue),"Consumer 2").start();

    }
}
