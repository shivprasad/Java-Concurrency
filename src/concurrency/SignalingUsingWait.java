package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: shiv
 * Date: Dec 21, 2009
 * Time: 4:35:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignalingUsingWait {


    public static void main(String[] args) {

        final Object signal = new Object();

        Thread thread1 = new Thread(){

            public void run() {
                synchronized (signal){
                    try {
                        System.out.println("Thread 1 is waiting...");
                        signal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                System.out.println("Thread 1 is ready...");
            }
        };
        Thread thread2 = new Thread(){

            public void run() {
                synchronized (signal){
                    try {
                        System.out.println("Thread 2 is waiting...");
                        signal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                System.out.println("Thread 2 is ready...");
            }
        };
        Thread thread3 = new Thread(){

            public void run() {
                synchronized (signal){
                    try {
                        System.out.println("Thread 3 is waiting...");
                        signal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                System.out.println("Thread 3 is ready...");
            }
        };
        Thread thread4 = new Thread(){

            public void run() {
                synchronized (signal){
                    try {
                        System.out.println("Thread 4 is waiting...");
                        signal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                System.out.println("Thread 4 is ready...");
            }
        };

        Thread thread5 = new Thread(){

            public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.println("Thread 5 is now ready...");
                synchronized (signal)
                {
                    signal.notifyAll();
                }
            }
        };

        thread2.start();
        thread1.start();
        thread3.start();
        thread4.start();
        thread5.start();

    }





}