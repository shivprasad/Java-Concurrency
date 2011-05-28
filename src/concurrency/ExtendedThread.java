package concurrency;

/**
 * Created by IntelliJ IDEA.
* User: shiv
* Date: Dec 22, 2009
* Time: 4:28:16 AM
* To change this template use File | Settings | File Templates.
*/
class ExtendedThread extends Thread {
    public ExtendedThread(String name) {
        super(name);
    }

    public void run() {
        SimpleThread.printMessage("Hello There");
    }
}
