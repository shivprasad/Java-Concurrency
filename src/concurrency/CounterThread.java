package concurrency;

/**
 * Created by IntelliJ IDEA.
* User: shiv
* Date: Dec 22, 2009
* Time: 4:28:04 AM
* To change this template use File | Settings | File Templates.
*/
class CounterThread implements Runnable {

    public void run() {
        while (SimpleThread.thisCounterIsNotThreadSafe < 90000000) {
            synchronized (CounterThread.class) {
                int counterValue = SimpleThread.thisCounterIsNotThreadSafe;
                if (counterValue != SimpleThread.thisCounterIsNotThreadSafe)
                    SimpleThread.printMessage("Values don't match - Expected[" + counterValue + "] but Actual[" + SimpleThread.thisCounterIsNotThreadSafe + "]");
                SimpleThread.incrementCounter();
            }
        }

    }
}
