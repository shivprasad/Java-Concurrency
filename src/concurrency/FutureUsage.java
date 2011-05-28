package concurrency;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 22, 2009
 * Time: 5:27:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class FutureUsage {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Long> longFuture = executor.submit(new SquareCallableThread(10l));

        int i = 0;

        while (!longFuture.isDone()) {
            if (i == 3) {
                longFuture.cancel(true);
                break;
            }
            System.out.println("Waiting...");
            Thread.sleep(100);
            i++;
        }

        if (longFuture.isDone() && !longFuture.isCancelled()) {
            System.out.println("Done with Result - " + longFuture.get());
        }
        if (longFuture.isCancelled()) {
            System.out.println("Cancelled");
        }
        executor.shutdown();

    }

}
