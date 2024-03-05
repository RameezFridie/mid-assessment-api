# Restful Booking API Automation

This repository was created to display the author's API automation knowledge.

This repository consists of two parts.

- A Postman Collection
- Java automation framework using RestAssured

## Requirements

- Java11(JDK 11 or Higher)


## System under test

The System Under Test(SUT) is *restful-booker*.

*Restful-booker API* is your gateway to diving deeper into the realm of 
API testing and experimenting with various testing tools.Technical API documentation is 
available at https://restful-booker.herokuapp.com/apidoc/index.html.

## Java Automation Framework

### Prerequisites
Use an IDE of your choice. Common choices include IntelliJ IDEA, Eclipse, or Visual Studio Code.
To be able to run the automation the file called ```data.properties``` is required.


### Running

The Java framework uses gradle as the main building tool.

On a Windows operating system, navigate to the root of the project in your file system 
via command line. Then run : ```gradle clean test```

### Reporting

A test report can be generated of the last completed test run. Running command:````gradlew allureReport```` or
````./gradlew allureReport```` respectively, will create the report.
The report will be saved into a generated folder named test at location build\reports\. 
The index.html is the main entry-point to the report which is best viewed in Chrome browser.

## Postman Collection
The Postman collection utilizes environment variables to keep track of all the information used in the collection.
The two files required to use postman can be found in the postman folder at the base of this repository.