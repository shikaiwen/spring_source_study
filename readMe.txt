Registry Pattern：
这个简单实在：https://redbeardtechnologies.wordpress.com/category/software-design-patterns/registry-pattern/
这个有些原理介绍：http://securesoftwaredev.com/2009/03/01/the-registry-pattern/

ConcurrentHashMap实现原理
将这个大的map根据hashcode分成很多segment（就相当于hash桶），然后对每个桶进行同步，这样操作不同的segment就
不会有问题，其实如果操作同一个hash桶里的数据还是有同步，通过这样的处理比每个方法进行同步效率高了16倍,Hashtable其实
就是每个方法都同步


oracle的jar包在maven库里没有这样安装，先下载，http://stackoverflow.com/questions/9898499/oracle-jdbc-ojdbc6-jar-as-a-maven-dependency
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=<Path where the jar is, example downloads>/ojdbc6.jar -DgeneratePom=true
    <dependency>
        <groupId>com.oracle</groupId>
        <artifactId>ojdbc6</artifactId>
        <version>11.2.0.3</version>
    </dependency>
    
    
AbstractBeanFactory中保存了容器中所有的BeanPostProcessor
DataSourceTransactionObject 事务对象 包含ConnectionHolder 然后包含Connection
DataSourceTransactionManager 开启事务 doBegin