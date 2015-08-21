package com.mem.proj.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import wang.mouge.rest.consumer.IRest;
import wang.mouge.rest.consumer.RestFactory;

/**
 * @author cuizhennan<cuizhennan@mouge.wang>
 * @since Aug 21, 2015
 */
public class MemTest
{
    public static void main(String[] args)
    {
        {
            // 获取当前程序运行时对象
            Runtime run = Runtime.getRuntime();
            // 调用垃圾回收机制，以减少内存误差
            run.gc();
            // 获取当前JVM的空闲内存
            long freeMemory = run.freeMemory();
            // 系统当前时间
            long timePro = System.currentTimeMillis();
            ExecutorService service = Executors.newFixedThreadPool(1000);
            // 独立创建并执行1000个线程
            for (int i = 0; i < 1000; i++)
            {
                service.execute(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        IRest rest = RestFactory.getConsumer();
                        System.out.println(rest.get("http://localhost:8080/cet/query/333230313930313531313039303130/王雨纯/1"));
                    }
                });
            }

            System.out.println("独立创建并执行1000个线程所需要占用的内存大小: " + (freeMemory - run.freeMemory()));
            System.out.println("独立创建并运行1000个线程需要的时间为: " + (System.currentTimeMillis() - timePro));
        }
    }
}
