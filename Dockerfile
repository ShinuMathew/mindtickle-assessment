# FROM maven:3.6.3-jdk-11
# Using the base image from snapshot
FROM mtapitest/jdk11-mvn3.6.3-mtapitest
WORKDIR /home/mtapitest
COPY src /home/mtapitest/src
COPY config /home/mtapitest/config
COPY scripts /home/mtapitest/scripts
COPY pom.xml /home/mtapitest/pom.xml
COPY app-test.xml /home/mtapitest/app-test.xml
COPY app-dd-test.xml /home/mtapitest/app-dd-test.xml
COPY unit-test.xml /home/mtapitest/unit-test.xml
COPY build.sh /home/mtapitest/build.sh
# RUN mvn clean install -DskipTests=true
ENTRYPOINT mvn clean test -DsuiteXmlFile=unit-test.xml