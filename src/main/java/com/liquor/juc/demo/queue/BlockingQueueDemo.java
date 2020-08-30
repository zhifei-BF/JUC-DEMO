package com.liquor.juc.demo.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列： 先进先出
 * 阻塞队列  有三种
 *
 * 当需要阻塞一个线程或者唤醒该线程，我们可以通过阻塞队列去自动阻塞/唤醒，不需要我们手动去操作。
 *
 * 当阻塞队列是空时，往队列中获取元素的操作将会被阻塞。
 * 当阻塞队列为满时，往队列中添加元素的操作将会被阻塞。
 *
 * ArrayBlockingQueue:
 * LinkedBlockingQueue:
 * SynchronousQueue:
 */
public class BlockingQueueDemo {
    /**
     * 脾气火爆型，一言不合就抛出异常
     */
    @Test
    public void test1(){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

//        会抛出异常 java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("x"));

//        检查队列空不空，以及队首元素是谁
        System.out.println(blockingQueue.element()); //a
//        先进先出：依次是a、b、c
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

//        会抛出java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());
    }

    /**
     *特殊值型
     */
    @Test
    public void test2(){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
//        直接返回false
        System.out.println(blockingQueue.offer("d"));

//        检查队首的那个元素
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

//        直接返回null
        System.out.println(blockingQueue.poll());
    }

    /**
     * 阻塞型
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("==============");
        //会一直等着，直到队列前面的被消费。
//        blockingQueue.put("d");
        //没有检查的方法
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();

        //一定要拿到才ok。
        blockingQueue.take();
    }

    /**
     * 超时型
     * @throws InterruptedException
     */
    @Test
    public void  test4() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        //没有检查的方法
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));

    }
}
