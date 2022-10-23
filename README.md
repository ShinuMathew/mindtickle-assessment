# MT API test automation framework

## PRE-REQUISITES
* Java jdk-11
* Node 12+ (For allure reports)
* Eclipse or IntelliJ IDE

## SETUP

* Clone repo
* Import into the IDE of your preference and build the project.
* Setup lombok plugin in your IDE. You can install the plugin from market place in eclipse and Plugins tab in IntelliJ.
* Install allure node package using npm - `sudo npm i -g allure-commandline`.
* Run install-hooks.bash to install pre-commit hook for unit testing `sh install-hooks.sh`.
* Run `mvn clean install -DskipTests=true` to install dependencies
* To run test ` mvn test -DsuiteXmlFile=service-test.xml`

## FRAMEWORK SPECIFICATION

* Core framework is built over an http client library `RestAssured`, a library commonly used for writing tests for Restful API's.
* `RequestBuilder` provides a wrapper to create request in a simplified manner. RequestBuilder uses `Builder pattern`.
* Request and response constructions are done using object serialisation and deserialisation. We are using `Jackson` library for this purposes.
* The object POJO classes all implement builder pattern at runtime using `Lombok`.
* To run unit tests : `mvn test -DsuiteXmlFile=unit-test.xml`