FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY library-service/pom.xml /tmp/
COPY library-service/generated /tmp/generated/
WORKDIR /tmp
RUN mvn package

FROM tomcat:9.0.14-jre8-alpine
RUN rm -rf /usr/local/tomcat/webapps/*

COPY ./library-web/target/library-web-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/library-web-1.0-SNAPSHOT.war
CMD ["catalina.sh","run"]