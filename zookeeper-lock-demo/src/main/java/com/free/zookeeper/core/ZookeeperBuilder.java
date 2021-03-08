package com.free.zookeeper.core;

import com.free.zookeeper.properties.ZookeeperProperties;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.util.StringUtils;

public class ZookeeperBuilder {

    public static CuratorFramework buildCuratorFramework(ZookeeperProperties p) throws Exception {

        if (StringUtils.isEmpty(p.getUri())) {
            return null;
        }

        int sleepTimeMs = p.getBaseSleepTimeMs() == null ? 1000 : p.getBaseSleepTimeMs();
        int tries = p.getMaxRetries() == null ? 3 : p.getMaxRetries();

        int sessionTimeoutMs = p.getSessionTimeoutMs() == null ? 5000 : p.getSessionTimeoutMs();
        int connectionTimeoutMs = p.getConnectionTimeoutMs() == null ? 5000 : p.getConnectionTimeoutMs();


        RetryPolicy retryPolicy = new ExponentialBackoffRetry(sleepTimeMs, tries);
        CuratorFramework client = CuratorFrameworkFactory.newClient(p.getUri(), sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
        client.start();
        return client;
    }
}
