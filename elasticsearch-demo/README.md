## 简介
该demo整合es7以及canal 实现es与mysql的全量数据同步和增量数据同步，并且实现es的增删改查等接口。

## 测试环境
* windows 10
* elasticsearch 7
* canal 1.1.5
* mysql 5.7
## 核心技术栈
* springboot 2.3.8
* elasticsearch 7.3.0
* canal 1.1.4
* mybatis-plus 3.3.0

## 准备工作-中间件配置篇

> 项目依赖lombok插件，运行项目前，idea先安装lombok插件。

 **该demo依赖es7、canal、canal-adapter三个中间件服务，请先安装准备好：**

- [elasticsearch安装与部署](https://blog.csdn.net/jixieguang/article/details/110621561)
- [canal-deployer安装与部署](https://blog.csdn.net/jixieguang/article/details/110621561)
- [canal-adapter安装与部署](https://blog.csdn.net/jixieguang/article/details/110621561)
- [elasticsearch安装与部署](https://blog.csdn.net/jixieguang/article/details/110621561)


#### 一.elasticsearch
[官网](https://www.elastic.co/cn/downloads/elasticsearch)下载elasticsearch-7.11.1 -选择windows版本

**修改es安装包下config/elasticsearch.yml配置文件中cluster.name的值：**
```properties
cluster.name: elasticsearch
```

**或者自定义修改，在项目配置文件application.yml中自定义配置：**

```properties
#es配置
elasticsearch:
  clusterNodes: 127.0.0.1:9200
  clusterName: elasticsearch
```
> 总之双方的cluster name保持相同的值就ok  
> 到此elasticsearch配置完成，先不急，继续往下

#### 二.mysql

> mysql需要开启binlog模式，因为canal同步是通过监听mysql的binlog

1. 开启mysql的binlog
找到my.ini（我是mysql5.7，有的低版本好像是my.cnf），添加以下配置：

```
[mysqld]
log-bin=mysql-bin  #开启binlog
binlog-format=ROW  #选择row模式
server-id=1        #配置mysql replaction需要定义，不能和canal的slaveId重复
```
添加完成后重新启动mysql服务，测试是否开启成功：
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/153739_8f7d8482_4951941.png "1617176241(1).png")

2. 为canal新增mysql用户，mysql命令台一次执行以下命令：

```
CREATE USER canal IDENTIFIED BY 'canal';  
GRANT SELECT, SHOW VIEW, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ; 需要具有SHOW VIEW 权限
FLUSH PRIVILEGES;
```
已经有的账户可直接通过grant

3.查询用户是否授权

`select u.User,u.Host,u.Repl_slave_priv,u.Repl_client_priv from mysql.user u`

![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/155234_25fbb76a_4951941.png "1617177136(1).png")

> 到此mysql配置完成，先不急，继续往下

#### 三.canal

1.下载安装[canal-deployer](https://github.com/alibaba/canal/releases/tag/canal-1.1.5-alpha-2)
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/152154_67e1204b_4951941.png "1617175292(1).png")
2.解压到指定文件夹，并修改conf\example\instance.properties配置文件
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/154421_d211bd99_4951941.png "1617176638(1).png")
> 到此canal配置完成，先不急，继续往下

#### 三.canal-adapter 
adapter是canal的客户端适配器，主要实现增量同步和全量同步的服务，目前Adapter具备以下基本能力：

- 对接上游消息，包括kafka、rocketmq、canal-server
- 实现mysql数据的增量同步
- 实现mysql数据的全量同步
- 下游写入支持mysql、es、hbase

1.下载adapter
- [下载地址](https://github.com/alibaba/canal/releases/tag/canal-1.1.5-alpha-2)

2.解压到指定文件夹，找到conf/application.yml进行配置（照着我底下的复制就行，注意一点：cluster.name的值和上方配置es的cluster.name保持一致）

```
server:
  port: 8081
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    default-property-inclusion: non_null

canal.conf:
  mode: tcp #tcp kafka rocketMQ rabbitMQ
  flatMessage: true
  zookeeperHosts:
  syncBatchSize: 1000
  retries: 0
  timeout:
  accessKey:
  secretKey:
  consumerProperties:
    # canal tcp consumer
    canal.tcp.server.host: 127.0.0.1:11111
    canal.tcp.zookeeper.hosts:
    canal.tcp.batch.size: 500
    canal.tcp.username:
    canal.tcp.password:
    # kafka consumer
    kafka.bootstrap.servers: 127.0.0.1:9092
    kafka.enable.auto.commit: false
    kafka.auto.commit.interval.ms: 1000
    kafka.auto.offset.reset: latest
    kafka.request.timeout.ms: 40000
    kafka.session.timeout.ms: 30000
    kafka.isolation.level: read_committed
    kafka.max.poll.records: 1000
    # rocketMQ consumer
    rocketmq.namespace:
    rocketmq.namesrv.addr: 127.0.0.1:9876
    rocketmq.batch.size: 1000
    rocketmq.enable.message.trace: false
    rocketmq.customized.trace.topic:
    rocketmq.access.channel:
    rocketmq.subscribe.filter:
    # rabbitMQ consumer
    rabbitmq.host:
    rabbitmq.virtual.host:
    rabbitmq.username:
    rabbitmq.password:
    rabbitmq.resource.ownerId:

  srcDataSources:
    defaultDS:
      url: jdbc:mysql://127.0.0.1:3306/es_demo?useUnicode=true
      username: root
      password: root
  canalAdapters:
  - instance: example
    groups:
    - groupId: g1
      outerAdapters:
      - name: logger
      - name: es7
        hosts: 127.0.0.1:9200
        properties:
          mode: rest
          cluster.name: elasticsearch
```

> 简单演示我们用的是tcp模式同步es7，根据不同的配置还可以通过kafka rocketMQ rabbitMQ等消息机制，具体可以自行查阅资料

3.在conf/es7目录下新建es的同步配置文件es_article.yml，并添加内容
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/165355_677d3cd8_4951941.png "1617180646(1).png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/165420_74921527_4951941.png "1617180694(1).png")

> 基本环境准备完毕

## 准备工作-项目篇

- 1.导入项目后修改application.yml配置文件里的数据库连接配置和elasticsearch配置（不懂得可以不改，用我默认的） 
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/170405_1380d441_4951941.png "1617181419(1).png") 
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/170423_1f684394_4951941.png "1617181423(1).png")

- 2.导入数据库sql文件，创建一个名为es_demo的数据库，导入位于项目sql/文件夹下的es_article.sql文件，生成项目测试表。
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/170451_01d18145_4951941.png "1617181477(1).png")

## 启动各个中间件和项目
- 1.启动es   
windows双击es安装目录下/bin/elasticsearch.bat 启动es服务，启动成功后访问http:127.0.0.1:9200，如果打印出版本信息则启动成功
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/170647_42d82908_4951941.png "1617181589(1).png")
- 2.启动canal
windows双击canal安装目录下/bin/startup.bat 启动canal服务，窗口显示如下则启动成功：
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/170805_c91f6a48_4951941.png "1617181668(1).png")

- 3.启动canal-adapter
windows双击canal-adapter安装目录下/bin/startup.bat 启动canal-adapter服务，窗口显示如下则启动成功：
![输入图片说明](https://images.gitee.com/uploads/images/2021/0331/170950_1b033d66_4951941.png "1617181774(1).png")
&nbsp;
## 说明
**本demo封装utils/EsUtil.java工具类，用于索引等操作**


&nbsp;
**本demo摸拟文章的插入与搜索，并且提供以下测试接口**

1. 创建索引，并添加数据  
http://localhost:8800/es/save 
2. 查询全部数据  
http://localhost:8800/es/findAll    
3. 根据作者字段条件查询索引  
http://localhost:8800/es/findByAuthor?text=
4. 多字段匹配  
http://localhost:8800/es/findMultiMatchQuery?text=  
5. 多条件检索   
http://localhost:8800/es/findByConditions?text=
6. 删除索引库  
http://localhost:8800/es/delIndel

**es初次启动后，必须先调用save方法初始化索引库，直接查询会报错**
