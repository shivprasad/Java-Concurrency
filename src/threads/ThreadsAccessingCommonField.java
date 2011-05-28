package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * User: shiv
 * Date: 18/04/2011
 * Time: 19:27
 */
public class ThreadsAccessingCommonField implements Runnable {

    private List sharedCollection = new ArrayList();

    public static void main(String[] args) throws InterruptedException {

        ThreadsAccessingCommonField threadsAccessingCommonField = new ThreadsAccessingCommonField();

        Thread thread1 = new Thread(threadsAccessingCommonField, "Thread 1");
        thread1.start();
        Thread thread2 = new Thread(threadsAccessingCommonField, "Thread 2");
        thread2.start();

        thread2.join();
        System.out.println("\nSize of Collection - "+threadsAccessingCommonField.getSize());

    }

    private int getSize() {
        return sharedCollection.size();
    }

    public void run() {
        int i=5;
        while(--i>0){
            try {
                Random random = new Random();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (sharedCollection){
                System.out.printf("\n%s Thread - Size of Shared Collection before adding element is %d",Thread.currentThread().getName(),sharedCollection.size());
                sharedCollection.add("Element");
                System.out.printf("\n%s Thread - Size of Shared Collection after adding element is %d",Thread.currentThread().getName(),sharedCollection.size());
            }
        }

    }
}
