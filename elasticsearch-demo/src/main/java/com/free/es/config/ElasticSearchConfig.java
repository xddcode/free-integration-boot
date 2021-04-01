package com.free.es.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * es7.x配置类
 *
 * @author dinghao
 * @date 2021/3/9
 */
@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.address}")
    private String address;

    /**
     *  连接超时时间
     */
    @Value("${elasticsearch.connect-timeout}")
    private int connectTimeOut = 1000;

    /**
     * 连接超时时间
     */
    @Value("${elasticsearch.socket-timeout}")
    private int socketTimeOut = 30000;

    /**
     * 获取连接的超时时间
     */
    @Value("${elasticsearch.connection-request-timeout}")
    private int connectionRequestTimeOut = 500;

    /**
     * 最大连接数
     */
    @Value("${elasticsearch.max-connect-num}")
    private int maxConnectNum = 100;

    /**
     * 最大路由连接数
     */
    @Value("${elasticsearch.max-connect-per-route}")
    private int maxConnectPerRoute = 100;

    @Bean
    RestHighLevelClient restHighLevelClient() {
        ArrayList<HttpHost> hostList = new ArrayList<>();
        String[] addrss = address.split(",");
        for(String addr : addrss){
            String[] arr = addr.split(":");
            hostList.add(new HttpHost(arr[0], Integer.parseInt(arr[1]), "http"));
        }

        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                requestConfigBuilder.setSocketTimeout(socketTimeOut);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return requestConfigBuilder;
            }
        });
        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.setMaxConnTotal(maxConnectNum);
                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpClientBuilder;
            }
        });

        return new RestHighLevelClient(builder);
    }

}
