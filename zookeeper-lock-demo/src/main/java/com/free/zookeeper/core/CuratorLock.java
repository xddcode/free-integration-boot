package com.free.zookeeper.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

/**
 * @author songfayuan
 */
@Slf4j
public class CuratorLock implements DistributeLock {

    @Value("${spring.profiles.active}")
    private String profile;

    InterProcessMutex lock;
    public CuratorLock(CuratorFramework client, String monitor) {
        lock=new InterProcessMutex(client, monitor);
    }


    @Override
    public void lock() {
        try {
            lock.acquire();
        } catch (Exception e) {
            log.warn("获取分布式锁异常，errMsg = {}", e);
           // DingDingMsgSendUtils.sendDingDingGroupMsg("【系统消息】" + profile + "环境，获取分布式锁异常，errMsg = "+e);
        }
    }

    @Override
    public void unlock() {
        try {
            lock.release();
        } catch (Exception e) {
            log.warn("释放分布式锁异常，errMsg = {}", e);
            //DingDingMsgSendUtils.sendDingDingGroupMsg("【系统消息】" + profile + "环境，释放分布式锁异常，errMsg = "+e);
        }
    }

    @Override
    public boolean lock(long t, TimeUnit unit) {
        try {
            if (null == unit) {
                unit = TimeUnit.MILLISECONDS;
            }
            return lock.acquire(t, unit);
        } catch (Exception e) {
            log.warn("获取分布式锁（含等待时长）异常，errMsg = {}", e);
            //DingDingMsgSendUtils.sendDingDingGroupMsg("【系统消息】" + profile + "环境，获取分布式锁（含等待时长）异常，errMsg = "+e);
            return false;
        }
    }


}

