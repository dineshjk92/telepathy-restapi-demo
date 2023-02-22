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

The service will be up and running in "[http://locallost:8081/cloudservice](http://locallost:8081/cloudservice)"

## API Endpoints

### Add a plan

POST: http://localhost:8081/cloudservice

RequestBody: 
```json
{
"planName":"Plan3",
"planPrice":"50",
"planFeatures": [
      "database"
    ]
}
```

### Get a plan detail

GET: http://localhost:8081/cloudservice/{planName}

Param:planName

### Get all plan details

GET: http://localhost:8081/cloudservice/plans

### Find Best plan

POST: http://localhost:8081/cloudservice/findbestplan

RequestBody:
```json
[
  "plan1",
  "plan2",
  "plan3"...
]
```

### Update a plan

PUT: http://localhost:8081/cloudservice

RequestBody:
```json
{
"planName":"Plan3",
"planPrice":"50",
"planFeatures": [
      "database"
    ]
}
```

### Delete a plan

DELETE: http://localhost:8081/cloudservice/{planName}

Param:planName


Swagger UI - API Documentation

Swagger UI with API documentation can be accessed via http://localhost:8081/swagger-ui/index.html


## How to build with tests

Prerequisite:Start the service by running the above spring-boot command. 

To build along with the tests, run the below command from the project root directory

`mvn clean install -U dependency:copy-dependencies`

## How to build without tests

Prerequisite:Start the service by running the above spring-boot command.

To build without tests, run the below command from the project root directory

`mvn clean install -U dependency:copy-dependencies -DskipTests`

## Docker image

The docker image for this RESTFul API service is available at https://hub.docker.com/r/dineshjk92/telepathy-rest-demo-0.0.1

Docker image name: dineshjk92/telepathy-rest-demo-0.0.1


