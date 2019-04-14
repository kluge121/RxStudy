package util;

public class Log {


    public static void i(Object obj) {
        long time = System.currentTimeMillis() - CommonUtils.startTime;
        System.out.println(Thread.currentThread().toString() + " // " + time + ": " + "value = " + obj);
    }

    public static void d(Object obj) {
        long time = System.currentTimeMillis() - CommonUtils.startTime;
        System.out.println(Thread.currentThread().toString() + " // " + time + " debug : " + "value = " + obj);
    }
}
