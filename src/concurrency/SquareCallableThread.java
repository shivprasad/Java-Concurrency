package concurrency;

import java.util.concurrent.Callable;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 22, 2009
 * Time: 5:25:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class SquareCallableThread implements Callable<Long> {
    private Long number;

    public SquareCallableThread(Long number) {
        this.number = number;
    }

    public Long call() throws Exception {
        Thread.sleep(Math.abs(new Random().nextInt()*1000%1000));
        return number*number;
    }
}
