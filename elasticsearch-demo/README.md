## **elasticsearch详细的安装原理和部署细节请查看**
[elasticsearch安装与部署](https://blog.csdn.net/jixieguang/article/details/110621561)

&nbsp;
## 测试环境
* elasticsearch 7.x

&nbsp;
## elasticsearch的配置如下
**修改es安装包下config/elasticsearch.yml，与ElasticsearchProperties配置类中一致**
```properties
cluster.name: elasticsearch
```

**或者自定义修改，在配置文件中自定义配置：**
```properties

#es配置
elasticsearch:
  clusterNodes: 127.0.0.1:9200
  clusterName: elasticsearch
```

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
