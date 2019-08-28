package com.wp771910012.concurrency.theory;

/**
 * @Description
 * @Author wangpeng
 * @Date 2019-07-25 17:32
 */
public class NoVisibility {

    private static boolean ready = false;

    private static int number = 0;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
