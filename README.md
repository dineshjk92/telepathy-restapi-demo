# RESTFUL API FOR FIND-BEST-PLAN PROBLEM

This project has been implemented with RESTful API service using spring-boot framework

## Project Tree 

* ├───src
* │   ├───main
* │   │   ├───java
* │   │   │   └───org
* │   │   │       └───example
* │   │   │           └───telepathyrestdemo
* │   │   │               ├───controller
* │   │   │               ├───model
* │   │   │               └───service
* │   │   └───resources
* │   └───test
* │       └───java
* │           └───org
* │               └───example
* │                   └───telepathyrestdemo

## Inside "src" directory,
* Model: Pojo class for Plan
* Controller: the class that processes each web API 
* Service\FindBestPlan: the logic behind find the best(cheap) plan

## Inside "test" directory,
* APISanityTest: which tests the Get/Post/BestPlan endpoints

## How to start the microservice
To start the spring-boot tomcat service

`mvn spring-boot:run`

## How to build with tests

Start the service by running the above spring-boot command. To build along with the tests, run the below command from the project root directory

`mvn clean install -U dependency:copy-dependencies`

## How to build without tests

Start the service by running the above spring-boot command. To build along with the tests, run the below command from the project root directory

`mvn clean install -U dependency:copy-dependencies -DskipTests`


