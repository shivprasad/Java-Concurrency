package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 21, 2009
 * Time: 4:35:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignalingUsingShared {

    private static class Signal {
        private boolean isReady = false;


        public synchronized boolean isReady() {
            return isReady;
        }

        public synchronized void setReady(boolean ready) {
            isReady = ready;
        }
    }

    public static void main(String[] args) {

        final Signal signal = new SignalingUsingShared.Signal();

        Thread thread1 = new Thread(){

            public void run() {
                while(!signal.isReady()){
                    try {
                        Thread.sleep(1000);
                        System.out.println("Thread 1 is waiting...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                System.out.println("Thread 1 is ready...");
            }
        };

        Thread thread2 = new Thread(){

            public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.println("Thread 2 is now ready...");
                signal.setReady(true);
            }
        };

        thread1.start();
        thread2.start();

    }





}
