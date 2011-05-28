package concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
* User: shiv
* Date: Dec 22, 2009
* Time: 4:27:41 AM
* To change this template use File | Settings | File Templates.
*/
class ReentrantLockCounterThread implements Runnable {

Lock lock;

    ReentrantLockCounterThread(Lock lock) {
        this.lock = lock;
    }

    public void run() {
    while (SimpleThread.thisCounterIsNotThreadSafe < 90000000) {
        lock.lock();
        int counterValue = SimpleThread.thisCounterIsNotThreadSafe;
        if (counterValue != SimpleThread.thisCounterIsNotThreadSafe)
            SimpleThread.printMessage("Values don't match - Expected[" + counterValue + "] but Actual[" + SimpleThread.thisCounterIsNotThreadSafe + "]");
        SimpleThread.incrementCounter();
        lock.unlock();
    }

}
}