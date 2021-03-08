package com.free.zookeeper.controller;

import com.free.zookeeper.core.DistributeLock;
import com.free.zookeeper.core.DistributeLockFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController implements DisposableBean {

    private ExecutorService executor = Executors.newFixedThreadPool(10);  //newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。

    // @Autowired
    //private MongoTemplate mongoTemplate;

    @Autowired
    private DistributeLockFactory lockFactory;

    private int Numbers = 400;

    public int getNumbers() {
        return Numbers;
    }

    public void setNumbers(int numbers) {
        Numbers = numbers;
    }


    /**
     * 单个线程异步执行任务
     *
     * @return
     */
    @GetMapping("/test")
    public void test() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (getNumbers() > 0) {
                    DistributeLock lock = lockFactory.newLock("demo-test");
                    try {
                        lock.lock();
                        log.info(Thread.currentThread().getName() + "获得锁...");
                        if (getNumbers() > 0) {
                            log.info(Thread.currentThread().getName() + "【正在执行.....】，当前剩余票数为：{}", getNumbers());
                            Numbers--;
                            try {
                                Thread.sleep(10);
                            } catch (Exception e) {
                                log.error("errMsg = {}", e);
                            }
                        }
                    } finally {
                        log.info(Thread.currentThread().getName() + "释放锁...");
                        lock.unlock();
                    }
                }
            }
        });
        //return Response.successResponse("请求成功");
    }

    /**
     * 分布式锁测试 输出结果200 正确的
     */
    @GetMapping("/lock")
    public void test3() {
        //模拟200个请求
        for (int i = 1; i <= 200; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    DistributeLock lock = lockFactory.newLock("test3");
                    try {
                        lock.lock();
                        log.info(Thread.currentThread().getName() + "获得锁...");
                        if (getNumbers() > 0) {
                            Numbers--;
                            log.info(Thread.currentThread().getName() + "正在售票，剩余：" + getNumbers());
                        } else {
                            log.info("沒有余票了：{}", getNumbers());
                        }
                    } finally {
                        lock.unlock();
                        log.info(Thread.currentThread().getName() + "释放锁...");
                        log.info("**********************************************************************************");
                        log.info("**********************************************************************************");
                    }

                }

            });
        }
    }

    /**
     * 不加分布式锁测试 输出结果201 错误的
     */
    @GetMapping("/noLock")
    public void test4() {
        //模拟200个请求
        for (int i = 1; i <= 200; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (getNumbers() > 0) {
                            Numbers--;
                            log.info(Thread.currentThread().getName() + "正在售票，剩余：" + getNumbers());
                        } else {
                            log.info("沒有余票了：{}", getNumbers());
                        }
                    } finally {
                        log.info("**********************************************************************************");
                    }

                }

            });
        }
    }

    @Override
    public void destroy() throws Exception {
        executor.shutdown();
    }

}

