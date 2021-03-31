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

## 准备工作

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
找到my.ini（我是mysql5.7，有的低版本好像是my.cnf），添加已下配置：

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

2.解压到指定文件夹，并进行配置

&nbsp;
## 启动es
**windows双击bin/elasticsearch.bat 启动es服务，启动成功后访问http:127.0.0.1:9200，如果打印出版本信息则启动成功**

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
