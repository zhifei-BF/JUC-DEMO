package com.liquor.juc.demo.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semphore 信号量、
 * 多个线程抢多个资源
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //第二个参数不写，默认为非公平锁，效率更高。
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for(int i = 1; i <= 6; i++){//模拟6部汽车
            new Thread(()-> {
                try {
                    semaphore.acquire();//抢占
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位！");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后，离开车位！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放停车位
                }
            },String.valueOf(i)).start();
        }

    }
}
