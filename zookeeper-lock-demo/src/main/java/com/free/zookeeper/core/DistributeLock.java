package com.free.zookeeper.core;

import java.util.concurrent.TimeUnit;

/**
 * 不需要实现lock接口中的多数功能，加锁与解锁足够，但是一定要保证可重入性
 *
 * @author dinghao
 * @date 2021/3/8
 */
public interface DistributeLock {

    public void lock();

    public void unlock();

    /**
     * @param t    等待时长
     * @param unit 单位,默认MILLISECONDS
     * @return
     */
    boolean lock(long t, TimeUnit unit);

}
