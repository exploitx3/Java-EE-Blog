# Java-EE-JSF---Blog-


# Blog System

Simple Blog System made for the Softuni Java EE Course.

### Prerequisites

MySql Server
Java EE Server(Wildfly)
Maven

### Setup

Add JDBC driver to Wildfly and configure your database as a Datasource.

Available variables:

- `Start the MySql server and create the Databse`: run 'provisioning/full_db_provisioning.sql' query to create a the initial 'blog' database
- `Configure Wildfly - Create 'mysql' folder`: Create 'mysql' folder inside 'wildfly/modules/system/layers/base/'
- `Configure Wildfly - Create 'driver' folder`: Create 'driver' folder inside 'wildfly/modules/system/layers/base/mysql/'
- `Configure Wildfly - Add JDBC driver to Wildfly`: Download [Mysql JDBC Driver](https://dev.mysql.com/downloads/connector/j) and copy the jar file into 'wildfly/modules/system/layers/base/mysql/
- `Create module.xml configuration file`: Create 'wildfly/modules/system/layers/base/mysql/module.xml' file with the following content:
`<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="com.mysql.driver">
	<resources>
		<resource-root path="mysql-connector-java.jar"/>
	</resources>
	<dependencies>
		<module name="javax.api"/>
		<module name="javax.transaction.api"/>
	</dependencies>
</module>`
replace 'mysql-connector-jave.jar' with the filename of the JDBC jar file in the same folder.

- `Configure Wildfly - Add your driver in Wildfly configuration`: in 'wildfly/standalone/configuration/standalone.xml' add inside the already existing <drivers> tag the following datasource:
`  <driver name="mysql" module="com.mysql.driver">
                        <driver-class>com.mysql.jdbc.Driver</driver-class>
                    </driver>
`

- `Add your database as Datasource in Wildfly configuration`: in 'wildfly/standalone/configuration/standalone.xml' add inside the already existing <datasources> tag the following datasource:
`<datasource jta="true" jndi-name="java:/SoftUniDS" pool-name="SoftUniDS" enabled="true" use-java-context="true">
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
`
If you do not use the default mysql credentials edit the '<security>' tag with the proper credentials

- `height`: Height of the specified size, eg. `1000`
- `crop`: Outputs `-cropped` when the crop option is true
- `date`: The current date (Y-M-d), eg. 2015-05-18
- `time`: The current time (h-m-s), eg. 21-15-11

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

