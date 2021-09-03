# RegistraionManagement
# **8.12**

整合SSM 框架 和mysql数据库；

可以web hello 和 数据库查询；

# **8.13**

写了一堆mapper和一个service

# **8.14**

测试

queryDoctorByPartAndDate

**错误**** 1**：

java.lang.IllegalArgumentException: Result Maps collection does not contain value for com.xf.registration.pojo.Patient

at org.apache.ibatis.session.Configuration$StrictMap.get(Configuration.java:1031)

...

参考博客：[https://blog.csdn.net/sayyy/article/details/83115590](https://blog.csdn.net/sayyy/article/details/83115590)

解决方法：select标签中

resultMap=&quot;com.xf.registration.pojo.Patient&quot;

改为

resultType=&quot;com.xf.registration.pojo.Patient&quot;

**错误**** 2**：

org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: Parameter &#39;doctorId&#39; not found. Available parameters are [arg1, arg0, param1, param2]

...

解决方法：传入参数前面加上@Param注解；输入参数多于一个时要加上；

@Select(&quot;select \* from doc\_schedule where doctor\_Id = #{id} and DATE = #{date};&quot;) Schedule selectSchedule(@Param(&quot;id&quot;) int doctorId,@Param(&quot;date&quot;) Date date);

**错误**** 3**：

时间解析错误导致查不出结果

预期的时间：

List\&lt;Doctor\&gt; list = doctorService.queryDoctorByPartAndDate(&quot;00010001&quot;,new Date(2018,1,22));

日志中发现错误解析的时间：

[com.xf.registration.dao.ScheduleMapper.selectSchedule]-==\&gt; Preparing: select \* from doc\_schedule where doctor\_Id = ? and DATE = ?;

[com.xf.registration.dao.ScheduleMapper.selectSchedule]-==\&gt; Parameters: 1(Integer), 3918-02-22(Date)

[com.xf.registration.dao.ScheduleMapper.selectSchedule]-\&lt;== Total: 0

解决：

![](RackMultipart20210903-4-1oksolu_html_b073034b20db176f.png)

java.sql.date构造函数中年份是1900年的绝对值；月份从0开始；

所以改用java.util.date

SimpleDateFormat ft = new SimpleDateFormat(&quot;yyyy-MM-dd&quot;); Date date = ft.parse(&quot;2018-1-22&quot;); // Date date = new Date(2018-1900,1-1,22); logger.info(date.toString()); List\&lt;Doctor\&gt; list = doctorService.queryDoctorByPartAndDate(&quot;00010001&quot;,date);

加上jdbcType=DATE，例如#{onlineDateStart,jdbcType=DATE}

博客：[https://www.jianshu.com/p/46c3635f2268](https://www.jianshu.com/p/46c3635f2268)

配置redis数据库

**错误**** 1**：

org.springframework.beans.factory.BeanCreationException: Error creating bean with name &#39;jedisConnectionFactory&#39;: Lookup method resolution failed; nested exception is java.lang.IllegalStateException: Failed to introspect Class [org.springframework.data.redis.connection.jedis.JedisConnectionFactory] from ClassLoader [sun.misc.Launcher$AppClassLoader@18b4aac2]

解决：maven引入包的版本要跟[博客](https://juejin.cn/post/6844904030917902343)[1](https://juejin.cn/post/6844904030917902343)[博客](https://programmer.group/mybatis-uses-redis-instead-of-secondary-caching.html)2中一致

\&lt;!-- 1、java连接Redis的jar包，也就是使用jedis --\&gt; \&lt;dependency\&gt; \&lt;groupId\&gt;redis.clients\&lt;/groupId\&gt; \&lt;artifactId\&gt;jedis\&lt;/artifactId\&gt; \&lt;version\&gt;2.4.2\&lt;/version\&gt; \&lt;/dependency\&gt; \&lt;!-- 2、spring整合Redis的jar包 --\&gt; \&lt;!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-redis --\&gt; \&lt;dependency\&gt; \&lt;groupId\&gt;org.springframework.data\&lt;/groupId\&gt; \&lt;artifactId\&gt;spring-data-redis\&lt;/artifactId\&gt; \&lt;version\&gt;1.4.2.RELEASE\&lt;/version\&gt; \&lt;/dependency\&gt; \&lt;!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api --\&gt; \&lt;dependency\&gt; \&lt;groupId\&gt;org.slf4j\&lt;/groupId\&gt; \&lt;artifactId\&gt;slf4j-api\&lt;/artifactId\&gt; \&lt;version\&gt;2.0.0-alpha2\&lt;/version\&gt; \&lt;/dependency\&gt; \&lt;!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12 --\&gt; \&lt;dependency\&gt; \&lt;groupId\&gt;org.slf4j\&lt;/groupId\&gt; \&lt;artifactId\&gt;slf4j-log4j12\&lt;/artifactId\&gt; \&lt;version\&gt;2.0.0-alpha1\&lt;/version\&gt; \&lt;scope\&gt;test\&lt;/scope\&gt; \&lt;/dependency\&gt; \&lt;!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-redis --\&gt; \&lt;dependency\&gt; \&lt;groupId\&gt;org.mybatis.caches\&lt;/groupId\&gt; \&lt;artifactId\&gt;mybatis-redis\&lt;/artifactId\&gt; \&lt;version\&gt;1.0.0-beta2\&lt;/version\&gt; \&lt;/dependency\&gt;

**错误**** 2**：int 不能转为object

set(key,value) key和value都用String;

其他配置：

spring\_dao.xml:

\&lt;!-- 1、配置连接池信息 --\&gt; \&lt;bean id=&quot;jedisPoolConfig&quot; class=&quot;redis.clients.jedis.JedisPoolConfig&quot;\&gt; \&lt;!-- 最大连接数--\&gt; \&lt;property name=&quot;maxTotal&quot; value=&quot;50&quot;\&gt;\&lt;/property\&gt; \&lt;property name=&quot;maxIdle&quot; value=&quot;5&quot;\&gt;\&lt;/property\&gt; \&lt;!-- 这里只是Spring整合Redis测试，配两个做个样子就得了--\&gt; \&lt;/bean\&gt; \&lt;!--2、spring整合Jedis（Redis） 也就是配置连接工厂JedisConnectionFactory--\&gt; \&lt;bean id=&quot;jedisConnectionFactory&quot; class=&quot;org.springframework.data.redis.connection.jedis.JedisConnectionFactory&quot;\&gt; \&lt;!--需要自定义（指定）一些工厂属性配置信息--\&gt; \&lt;!-- 指定服务器地址--\&gt; \&lt;property name=&quot;hostName&quot; value=&quot;116.56.138.144&quot;\&gt;\&lt;/property\&gt; \&lt;!-- 指定服务端口号--\&gt; \&lt;property name=&quot;port&quot; value=&quot;6379&quot;\&gt;\&lt;/property\&gt; \&lt;!-- 指定密码（Redis3之前可以不指定，之后都要）--\&gt; \&lt;!-- \&lt;property name=&quot;password&quot; value=&quot;&quot;\&gt;\&lt;/property\&gt;--\&gt; \&lt;!-- 自定义连接池配置：再把第一步配置好的连接池信息通过属性注入进来 如果不自定义会采用默认的连接池配置，工厂中有属性new JedisPoolConfig--\&gt; \&lt;property name=&quot;poolConfig&quot; ref=&quot;jedisPoolConfig&quot;\&gt;\&lt;/property\&gt; \&lt;/bean\&gt; \&lt;!-- 3、配置RedisTemplate模板 把第二步配置好的连接工厂JedisConnectionFactory通过属性注入到RedisTemplate模板中--\&gt; \&lt;bean id=&quot;redisTemplate&quot; class=&quot;org.springframework.data.redis.core.RedisTemplate&quot;\&gt; \&lt;property name=&quot;connectionFactory&quot; ref=&quot;jedisConnectionFactory&quot;\&gt;\&lt;/property\&gt; \&lt;!--如下配置是为了之后的key和value的序列化操作，暂时不配制也是OK的--\&gt; \&lt;property name=&quot;keySerializer&quot;\&gt; \&lt;bean class=&quot;org.springframework.data.redis.serializer.StringRedisSerializer&quot;\&gt;\&lt;/bean\&gt; \&lt;/property\&gt; \&lt;property name=&quot;valueSerializer&quot;\&gt; \&lt;bean class=&quot;org.springframework.data.redis.serializer.StringRedisSerializer&quot;\&gt;\&lt;/bean\&gt; \&lt;/property\&gt; \&lt;/bean\&gt;

测试redis功能实现类：

public void register(int doctorId, Date date) { String str = doctorId+&quot;\_available\_register&quot;; redisUtil.set(str,&quot;10&quot;); logger.info(redisUtil.get(str)); }

结果：

[com.xf.registration.service.DoctorServiceImpl]-10

数据库中也有

127.0.0.1:6379\&gt; get 1\_available\_register

&quot;10&quot;

注意点：其他包比如util工具类包下的类如果用了@Resource @Compent等要在.xml配置文件中加上包扫描，不然找不到；

# **8.15**

查询医生及其挂号资源使用redis做缓存；

挂号方法：增加挂号记录，redis中可挂号数减一；

排诊方法：增加排诊计划；

**错误** ：使用Jedis将java app的数据写入redis中时，本应是数字类型，结果存为字符串，导致无法使用自减操作；

org.springframework.dao.InvalidDataAccessApiUsageException: ERR hash value is not a float; nested exception is redis.clients.jedis.exceptions.JedisDataException: ERR hash value is not a float

![](RackMultipart20210903-4-1oksolu_html_ab3a0a6e4002ce25.png)

解决：[原理博客](https://blog.csdn.net/way_king/article/details/78729232)、[方法博客](https://blog.csdn.net/m0_46086429/article/details/107999180?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0.control&amp;spm=1001.2101.3001.4242) 大概因为hash push进去的时候使用jackson封装key和value，自增的时候没有反序列化；解决方法是设置hash key和value以string的方式序列化；

spring\_dao.xml中加入：

\&lt;property name=&quot;hashKeySerializer&quot;\&gt; \&lt;bean class=&quot;org.springframework.data.redis.serializer.StringRedisSerializer&quot;/\&gt; \&lt;/property\&gt; \&lt;property name=&quot;hashValueSerializer&quot;\&gt; \&lt;bean class=&quot;org.springframework.data.redis.serializer.StringRedisSerializer&quot;/\&gt; \&lt;/property\&gt;

注意：service中操作数据库的某些方法需考虑使用事务。比如插入一条挂号记录的同时应将redis的可挂号数量减一，这是一个事务，否则将导致出错；

# **8.16**

完善Model层的基本功能与实现并测试；

错误：

使用java.util.date去查Mysql查不出来数据；

解决：

字段后面加上

,jdbcType=DATE

不然会解析为时间戳，即后面跟有时间；

# **8.20**

写一些controller与前台交互；

写restful风格时，方法参数前面要加上

@PathVariable(&quot;name&quot;)

打包war然后部署到docker

IDEA build-atiface, .ar包在target目录下；

运行docker

sudo docker run -d -p 9090:8080 -v ~/warPackage/:/usr/local/tomcat/webapps --name mytomcat tomcat

拷贝

scp RegistrationManagement-1.0-SNAPSHOT.war jiang@116.56.138.144:~/

重命名：

mv RegistrationManagement-1.0-SNAPSHOT.war registration.war

# **8.25**

calendar日历类中月份范围[0,11]注意变化；

实现了获取整个月份某个医生的挂号和排诊信息，并缓存到redis；
