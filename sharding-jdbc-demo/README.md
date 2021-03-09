## 说明
**本demo实现springboot集成sharding-jdbc完成分库分表**
&nbsp;
## 测试环境
* springboot 2.3.8
* mybaits plus 3.1
* mysql 5.7

&nbsp;
## 1.创建本demo测试数据库
**导入doc下的两个sql文件创建测试数据库shard_one,shard_two**  

## 2.修改数据源
**修改application.yml中数据源为你自己的数据库**  

```properties
spring:
  shardingsphere:
    datasource:
      #创建两个数据源
      names: ds-master-0,ds-master-1
      #数据源1
      ds-master-0:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/shard_one?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai
        username: root
        password: root
      #数据源2
      ds-master-1:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/shard_two?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai
        username: root
        password: root
```
**修改为你做自己的url，username和password，注意：mysql8.0的driver-class-name需要配置时区**

## 3.测试
**本demo提供以下测试接口,可以在postman进行接口测试，观测数据是否正确**

1. 添加数据，由于配置了分库分表的规则，我摸拟了5条数据，根据规则会添加到不同的库和不同的表  
POST http://localhost:8801/user 
2. 查询全部数据，返回两个库全部组合起来的完整数据  
GET http://localhost:8801/user    
3. 根据用户id查询数据   
GET http://localhost:8801/user/{id}
4. 删除用户信息  
DELETE http://localhost:8801/user/{id}

## 4.扩展
**分库分表规则可以自定义扩展，也可以添加多个数据源，配置文件直接配即可**
```properties
   #分库分表的规则
   sharding:
      tables:
        t_user:
          actual-data-nodes: ds-master-$->{0..1}.t_user$->{0..1}
          table-strategy:
            inline:
              sharding-column: id
              algorithm-expression: t_user$->{id % 2}
          key-generator:
            column: id
            type: SNOWFLAKE
      default-database-strategy:
        inline:
          sharding-column: id
          algorithm-expression: ds-master-$->{id % 2}
      props:
        #是否打印sql
        sql:
          show: true
```
