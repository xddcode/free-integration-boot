package com.free.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义es配置资源类，可在配置文件中自定义各项配置，如果不配置则走默认值
 *
 * @author dinghao
 * @date 2021/3/9
 */
@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class ElasticsearchProperties {

    /**
     * 请求协议
     */
    private String schema = "http";

    /**
     * 集群名称
     */
    private String clusterName = "elasticsearch";

    /**
     * 集群节点
     */
    private List<String> clusterNodes = new ArrayList<>();

    /**
     * 连接超时时间(毫秒)
     */
    private Integer connectTimeout = 1000;

    /**
     * socket 超时时间
     */
    private Integer socketTimeout = 30000;

    /**
     * 连接请求超时时间
     */
    private Integer connectionRequestTimeout = 500;

    /**
     * 每个路由的最大连接数量
     */
    private Integer maxConnectPerRoute = 10;

    /**
     * 最大连接总数量
     */
    private Integer maxConnectTotal = 30;


    /**
     * 索引配置信息
     */
    private Index index = new Index();

    /**
     * 认证账户
     */
    private Account account = new Account();


    /**
     * 索引配置信息
     */
    @Data
    public static class Index {

        /**
         * 分片数量
         */
        private Integer numberOfShards = 3;

        /**
         * 副本数量
         */
        private Integer numberOfReplicas = 0;

    }

    /**
     * 认证账户
     */
    @Data
    public static class Account {

        /**
         * 认证用户
         */
        private String username;

        /**
         * 认证密码
         */
        private String password;

    }

}
