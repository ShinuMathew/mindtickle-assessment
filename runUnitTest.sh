#!/usr/bin/sh
 
echo Running Unit tests
mvn clean test -Dsurefire.suiteXmlFiles=unit-test.xml