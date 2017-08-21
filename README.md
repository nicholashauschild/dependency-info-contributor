# dependency-info-contributor

[![Build Status](https://img.shields.io/travis/nicholashauschild/dependency-info-contributor/master.svg?style=flat-square)](https://travis-ci.org/nicholashauschild/dependency-info-contributor)
[![Bintray](https://img.shields.io/bintray/v/nicholashauschild/maven/dependency-info-contributor.svg?style=flat-square)](https://bintray.com/nicholashauschild/maven/dependency-info-contributor/_latestVersion)

> Library with class that can be used to add gradle dependency details to a SpringBoot `info` actuator endpoint,
based on file in classpath.

This library utilizes Spring Boot AutoConfiguration to conditionally register a
Spring Boot InfoContributor (specifically, [`DependencyInfoContributor`](https://github.com/nicholashauschild/dependency-info-contributor/blob/master/src/main/java/com/github/nicholashauschild/boot/actuate/info/DependencyInfoContributor.java)), when the following conditions are met:
  - A resource by the name of `dependency-info.properties` exists on the classpath (resource name is configurable)
  - The Spring Environment is not configured to exclude this bean.
  - There is no bean of type `DependencyInfoContributor` already within Spring's `ApplicationContext`

#### File format
For each dependency, there exists a line in the file of the format:
dependency.[some_identifier]=[group]/[name]/[version]

```
dependency.jetty-servlets=org.eclipse.jetty/jetty-servlets/9.3.18.v20170406
dependency.javax.servlet-api=javax.servlet/javax.servlet-api/3.1.0
dependency.jetty-server=org.eclipse.jetty/jetty-server/9.3.18.v20170406
dependency.classmate=com.fasterxml/classmate/1.3.3
...
```

Please note that at this time, [some_identifier] is not used, and can actually be any text.

Also note, this file can be generated at build time vai the 

#### Generating the dependency-info file
This is best handled by utilizing the [gradle-dependency-info-plugin](https://github.com/nicholashauschild/gradle-dependency-info-plugin).

#### Configuration via Spring Environment
Where to look for the file that contains the dependency information, and the default value:
```
spring.info.dependencies.resource=classpath:dependency-info.properties
```

To enable/disable the dependencies info contributor, and the default value:
```
management.info.dependencies.enabled=true
```