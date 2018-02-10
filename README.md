# Blog System

Simple Blog System made for the Softuni Java EE Course.

### Prerequisites
- `MySql Server`
- `Java EE Server(Wildfly)`
- `Maven`

### Setup

- `Provision the Database`: Star MySql and execute 'provisioning/full_db_provisioning.sql' to create the initial 'blog' database and execute the queries in full_db_provisioning.sql file
- `Configure Wildfly`: Create 'mysql' folder inside 'wildfly/modules/system/layers/base/'
- `Configure Wildfly`: Create 'driver' folder inside 'wildfly/modules/system/layers/base/mysql/'
- `Configure Wildfly`:  Add JDBC driver to Wildfly. Download [Mysql JDBC Driver](https://dev.mysql.com/downloads/connector/j) and copy the jar file into 'wildfly/modules/system/layers/base/mysql/
- `Configure WildFly`: Create module.xml configuration file in with the following commands:
```
C:\User\wildfly-10.1.0.Final\bin>jboss-cli.bat
You are disconnected at the moment. Type 'connect' to connect to the server or 'help' for the list of supported commands.
[disconnected /] connect
[standalone@localhost:9990 /]  module add --name=com.mysql.driver  --dependencies=javax.api,javax.transaction.api --resources=/PATH/TO/mysql-connector-java.jar  
[standalone@localhost:9990 /] :reload  
{  
    "outcome" => "success",  
    "result" => undefined  
}  
```
replace 'mysql-connector-java.jar' with the filename of the JDBC jar file.

- `Configure Wildfly`: Add your driver in Wildfly configuration in 'wildfly/standalone/configuration/standalone.xml' add inside the already existing 'drivers' tag the following datasource:
```  
<driver name="mysql" module="com.mysql.driver">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                    </driver>
		    
```

- `Configure Wildfly`: Add your database as Datasource in Wildfly configuration in 'wildfly/standalone/configuration/standalone.xml' add inside the already existing 'datasources' tag the following datasource:
```
<datasource jta="true" jndi-name="java:/SoftUniDS" pool-name="SoftUniDS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/blog?characterEncoding=utf8</connection-url>
                    <driver>mysql</driver>
                    <pool>
                        <min-pool-size>5</min-pool-size>
                        <max-pool-size>30</max-pool-size>
                        <prefill>true</prefill>
                    </pool>
                    <security>
                        <user-name>root</user-name>
                    </security>
                </datasource>
```
If you do not use the default mysql credentials edit the 'security' tag with the proper credentials

- `install required dependencies`: In the directory "provisioning/lib/" execute:
```
mvn install:install-file -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar -Dfile=jta-1.0.1B.jar
```
- `Provision the Blog`: In the parent directory run 'mvn install' to provision the application using Maven.
- `Add the Project to WildFly`: Copy the created 'SoftUniJEE/target/SoftUniJEE-0.0.1-SNAPSHOT.ear' into wildfly/standalone/deployments/
- `Run Wildfly`: execute '/wildfly/bin/standalone.sh' script
- `Browse the App`: 'http://localhost:8080/' 

Run with docker:
- `install required dependencies`: In the directory "provisioning/lib/" execute:
```
mvn install:install-file -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar -Dfile=jta-1.0.1B.jar
```
- `Provision the Blog`: In the parent directory run 'mvn install' to provision the application using Maven.
- `Setup the ear`: Copy the created 'SoftUniJEE/target/SoftUniJEE-0.0.1-SNAPSHOT.ear' into "provisioning/docker/customization"
- `Build docker image`: execute "docker build . -t blog/wildfly" in "provisioning/docker/" directory
- `Start Application`: execute "docker-compose up -d" in "provisioning/docker/" directory
- `Browse the App`: 'http://localhost:8080/' 


![alt tag](http://puu.sh/tO8aT/c15dbe7b1b.png)
![alt tag](http://puu.sh/tO8kD/78b74a4722.png)
![alt tag](http://puu.sh/tO8uc/57ce97fdec.png)
![alt tag](https://puu.sh/tO8sH/726e49ee52.png)


## Built With
/opt/apache-maven-3.3.9
Java version: 1.8.0_65, vendor: Oracle Corporation

* [Wildfly](http://wildfly.org/downloads/) - The Java EE application server used(Wildfly 10.1.0 with Java 1.8)
* [Maven](https://maven.apache.org/) - Dependency Management(Maven-3.3.9)
* [MySql](https://www.mysql.com/) - The database used (MySql  Ver 14.14 Distrib 5.6.30)
* [Hibernate](http://hibernate.org/) - Java persistence framework for powerful object relational mapping and query databases using HQL and SQL(version in maven dependencies)
* [JSF](https://en.wikipedia.org/wiki/JavaServer_Faces) - Java specification for building component-based user interfaces for web applications(JSF 2)
* [PrimeFaces](http://www.primefaces.org/) - User interface (UI) component library for JavaServer Faces (JSF) based applications.(version in maven dependencies)

## License

[![CC0](https://licensebuttons.net/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

