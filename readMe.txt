Registry Pattern��
�����ʵ�ڣ�https://redbeardtechnologies.wordpress.com/category/software-design-patterns/registry-pattern/
�����Щԭ����ܣ�http://securesoftwaredev.com/2009/03/01/the-registry-pattern/

ConcurrentHashMapʵ��ԭ��
��������map����hashcode�ֳɺܶ�segment�����൱��hashͰ����Ȼ���ÿ��Ͱ����ͬ��������������ͬ��segment��
���������⣬��ʵ�������ͬһ��hashͰ������ݻ�����ͬ����ͨ�������Ĵ����ÿ����������ͬ��Ч�ʸ���16��,Hashtable��ʵ
����ÿ��������ͬ��


oracle��jar����maven����û��������װ�������أ�http://stackoverflow.com/questions/9898499/oracle-jdbc-ojdbc6-jar-as-a-maven-dependency
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=<Path where the jar is, example downloads>/ojdbc6.jar -DgeneratePom=true
    <dependency>
        <groupId>com.oracle</groupId>
        <artifactId>ojdbc6</artifactId>
        <version>11.2.0.3</version>
    </dependency>
    
    
AbstractBeanFactory�б��������������е�BeanPostProcessor
DataSourceTransactionObject ������� ����ConnectionHolder Ȼ�����Connection
DataSourceTransactionManager �������� doBegin