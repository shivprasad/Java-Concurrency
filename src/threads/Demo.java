package threads;

import sun.misc.Unsafe;

import java.lang.ref.*;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * User: shiv
 * Date: 23/04/2011
 * Time: 17:54
 */
public class Demo implements Cloneable {

    public final int value;
    SoftReference<Integer> softInt = new SoftReference<Integer>(10);
    WeakReference<Integer> weakInt = new WeakReference<Integer>(10);


//    public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
//        Demo demo = new Demo();
//        Demo demo1 = new Demo();
//        PhantomReference<Demo> phantomInt = new PhantomReference<Demo>(demo1);
//
//        Class<?> aClass = Class.forName("threads.Demo");
//        Field declaredField = aClass.getDeclaredField("value");
//        declaredField.setAccessible(true);
//        System.out.println(demo.value);
//        declaredField.setInt(demo, 11);
//        System.out.println(demo.value);
//    }

    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue();
        Object data = new Object(){
            @Override
            public String toString() {
                return "Data";
            }
        };
        Object object = new Object(){
            @Override
            public String toString() {
                return "Referenced Object";
            }
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Finalize");
            }
        };
        HashMap map = new HashMap();
        Reference reference = new PhantomReference(object,queue);
        map.put(reference,data);

        System.out.println("Map  - "+ map);
        System.out.println("Reference Queue  - "+ queue.toString());
        System.out.println("Reference - "+ reference.get());
        System.out.println("Reference isEnqueued - "+ reference.isEnqueued());
        System.gc();
        System.out.println("Map  - " + map);
        System.out.println("Reference Queue  - "+ queue.toString());
        System.out.println("Reference - "+ reference.get());
        System.out.println("Reference isEnqueued - " + reference.isEnqueued());

        object=null;
        data=null;

        System.gc();
        System.out.println("Map  - "+ map);
        System.out.println("Reference Queue  - "+ queue.toString());
        System.out.println("Reference - "+ reference.get());
        System.out.println("Reference isEnqueued - "+ reference.isEnqueued());

    }


    public Demo(){
              value = 10;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
