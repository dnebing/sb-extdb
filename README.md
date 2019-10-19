# Service Builder - External Database
An example of a Service Builder project for Liferay 7.1 Community Edition/DXP that uses an external data source.

This project is a fork of the [sb-extdb](https://github.com/dnebing/sb-extdb) project realized by [David H Nebinger](https://liferay.dev/web/guest/profile/-/user/user.26526). You can see the blog post for more info: [Liferay 7 - Service Builder and External Databases](https://liferay.dev/blogs/-/blogs/liferay-7-service-builder-and-external-databases)

This fork has been updated to support Liferay 7.1. For more information, I invite you to read this documentation. [Connecting Service Builder to External Databases](https://portal.liferay.dev/docs/7-1/tutorials/-/knowledge_base/t/connecting-service-builder-to-external-databases#step-2-create-a-spring-bean-that-points-to-the-data-source)



# 1. Quick Start

The external entity to which we want to access from Liferay has the following structure.



| Attribute                    | Primary | Type     | Description                                                  |
| ---------------------------- | ------- | -------- | ------------------------------------------------------------ |
| uuid                         | x       | String   | Universal Unique Identifier of the Liferay User              |
| Screen Name                  |         | String   | The Liferay user Screen Name                                 |
| System Name                  |         | String   | IP Address, Hostname, Virtual Host or FQDN of the Liferay instance |
| Last Login                   |         | Datetime | We'll track the date of last login                           |
| Total Login                  |         | Long     | We'll track the total number of individual logins for the user |
| Shortest Time Between Logins |         | Long     | And we'll also track the shortest time between logins        |
| Longest Time Between Logins  |         | Long     | Let's also track the longest time between logins             |



The SQL code below shows the creation of the ExtDB_UserLogin table on the external db, in this case SQL Server.

```mssql
create table ExtDB_UserLogin
(
    uuid_ VARCHAR(75) not null primary key,
    screenName VARCHAR(75) null,
    systemName VARCHAR(75) null,
    lastLogin DATETIME null,
    totalLogins BIGINT,
    longestTimeBetweenLogins BIGINT,
    shortestTimeBetweenLogins BIGINT
);
```



Following the configuration of the PostgreSQL database used by Liferay, while the external database is SQL Server.

```properties
    #
    # PostgreSQL
    #
    jdbc.default.driverClassName=org.postgresql.Driver
    jdbc.default.url=jdbc:postgresql://localhost:5432/lportal_713_ce_ga4_develop
    jdbc.default.username=liferay
    jdbc.default.password=liferay

    #
    # SQL Server 2017 as External DB
    #
    jdbc.ext.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
    jdbc.ext.url=jdbc:sqlserver://localhost;databaseName=lportal_dev_external_db
    jdbc.ext.username=liferay
    jdbc.ext.password=lportal@DEV@72
```



Refer to the [liferay-portal-database-all-in-one-support](https://github.com/amusarra/liferay-portal-database-all-in-one-support) project to add SQL Server support to Liferay. The instructions below show the clone of the project, build and deploy on your Liferay instance 7.1



```bash
$ git clone https://github.com/amusarra/sb-extdb
$ cd sb-extdb
$ ./gradlew clean deploy -Pauto.deploy.dir=$LIFERAY_HOME/deploy
```



Once the three modules have been deployed, if you try to log in to Liferay, the login action will be stored on the external database.



```shell
g! lb SB
START LEVEL 20
   ID|State      |Level|Name
 1014|Active     |   10|SB External DB API (1.0.0)|1.0.0
 1015|Active     |   10|SB External DB Post Login Hook (1.0.0)|1.0.0
 1016|Active     |   10|SB External DB Service (1.0.0)|1.0.0
```



The figure below shows the data entered in the external table from the post login hook.

![select_data_on_external_table](docs/images/select_data_on_external_table.png)