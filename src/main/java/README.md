#springbootdemo-version003:
1. 项目使用JPA连接h2数据库。
2. 项目访问端口是80端口

#springbootdemo-version003p:
1. 在原来的基础上，将日志配置文件logback.xml中的loghome属性节点由固定路径改为了从vm变量中读取。
	如果正确配置这个vm变量，日志将被数据到指定路径下的文件中，而且是按天输出。
	如果没有正确配置这个vm变量，不会引起任何错误。原来输出的控制台的信息也不受影响，只是日志没有输出到文件。
	实例：在vm参数中设置-Dloghome=d:\，这样就将日志保存到了d盘下。

#springbootdemo-version005:
1. 增加了单元测试。对service、controller进行单元测试。
2. 增加了eclipse文件夹和其中的.launch文件。

#springbootdemo-version006:
这个版本修改了数据源的类，使用HikariCP替换spring boot自带的数据源。  
1. pom.xml中增加HikariCP。
2. 修改配置文件中spring.datasource的配置。
3. 在增加DataSourceConfig文件，来启用HikariDataSource数据源。

#springbootdemo-version007:
1. 修改controller增加完善的注解，控制请求方式。
2. 增加swagger。
3. 替换日志框架为log4j2

#springbootdemo-version008:
1. 添加config包下的LogAOP类，该类使用spring aop技术，对所有controller中的方法进行日志打印。


#springbootdemo-version009:
1. 修改视图引擎为velocity。添加velocity配置类com.ms.springbootdemo_version009.config.VelocityConfig。
2. 应用bootstrap，作为css样式。






