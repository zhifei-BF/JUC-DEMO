package com.liquor.juc.demo.utils;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 同步计数器
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <= 7; i ++ ){
            new Thread(()->{
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + countDownLatch.toString());
            },String.valueOf(i)).start();
        }
    }
}
