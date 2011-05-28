package concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

/**
 * Created by IntelliJ IDEA.
* User: shiv
* Date: Dec 22, 2009
* Time: 4:27:41 AM
* To change this template use File | Settings | File Templates.
*/
class ReadWriteLockCounterThread implements Runnable {

    ReadWriteLock lock;
    private Lock readLock;
    private Lock writeLock;

    ReadWriteLockCounterThread(ReadWriteLock lock) {
        this.lock = lock;
    }

    public void run() {
        while (SimpleThread.thisCounterIsNotThreadSafe < 90000000) {
            readLock = lock.readLock();
            readLock.lock();
            int counterValue = SimpleThread.thisCounterIsNotThreadSafe;
            if (counterValue != SimpleThread.thisCounterIsNotThreadSafe)
                SimpleThread.printMessage("Values don't match - Expected[" + counterValue + "] but Actual[" + SimpleThread.thisCounterIsNotThreadSafe + "]");
            writeLock = lock.writeLock();
            writeLock.lock();
            SimpleThread.incrementCounter();
            writeLock.unlock();
            readLock.unlock();
        }

    }
}
