# Blog System

Simple Blog System made for the Softuni Java EE Course.

### Prerequisites
- `MySql Server`
- `Java EE Server(Wildfly)`
- `Maven`

### Setup

- `Start the MySql server and create the Databse`: run 'provisioning/full_db_provisioning.sql' query to create the initial 'blog' database
- `Configure Wildfly - Create 'mysql' folder`: Create 'mysql' folder inside 'wildfly/modules/system/layers/base/'
- `Configure Wildfly - Create 'driver' folder`: Create 'driver' folder inside 'wildfly/modules/system/layers/base/mysql/'
- `Configure Wildfly - Add JDBC driver to Wildfly`: Download [Mysql JDBC Driver](https://dev.mysql.com/downloads/connector/j) and copy the jar file into 'wildfly/modules/system/layers/base/mysql/
- `Create module.xml configuration file`: Create 'wildfly/modules/system/layers/base/mysql/module.xml' file with the following content:
```
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql.driver">
	<resources>
		<resource-root path="mysql-connector-java.jar"/>
	</resources>
	<dependencies>
		<module name="javax.api"/>
		<module name="javax.transaction.api"/>
	</dependencies>
</module>
```
replace 'mysql-connector-jave.jar' with the filename of the JDBC jar file in the same folder.

- `Configure Wildfly - Add your driver in Wildfly configuration`: in 'wildfly/standalone/configuration/standalone.xml' add inside the already existing <drivers> tag the following datasource:
```  
<driver name="mysql" module="com.mysql.driver">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                    </driver>
		    
```

- `Add your database as Datasource in Wildfly configuration`: in 'wildfly/standalone/configuration/standalone.xml' add inside the already existing <datasources> tag the following datasource:
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
If you do not use the default mysql credentials edit the '<security>' tag with the proper credentials

- `Run Maven`: In the parent directory run 'mvn install' to provision the application
- `Copy the generated EAR into Wildfly`: copy the created 'SoftUniJEE/target/SoftUniJEE-0.0.1-SNAPSHOT.ear' into wildfly/standalone/deployments/
- `Run Wildfly`: execute '/wildfly/bin/standalone.sh' script
- `Browse the App`: 'http://localhost:8080/' 



## Built With

* [Wildfly 10.1.0](http://wildfly.org/downloads/) - The Java EE application server used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySql](https://www.mysql.com/) - The database used
* [Hibernate](http://hibernate.org/) - Java persistence framework for powerful object relational mapping and query databases using HQL and SQL
* [JSF 2](https://en.wikipedia.org/wiki/JavaServer_Faces) - Java specification for building component-based user interfaces for web applications
* [PrimeFaces](http://www.primefaces.org/) - User interface (UI) component library for JavaServer Faces (JSF) based applications.

## License

[![CC0](https://licensebuttons.net/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

To the extent possible under law, [Matias Singers](http://mts.io) has waived all copyright and related or neighboring rights to this work.


