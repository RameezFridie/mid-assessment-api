# Restful Booking API Automation
## Mid SDET Assessment Section 3

This repository showcases the author's expertise in API automation and consists of two main parts:

1. **Postman Collection**
2. **Java Automation Framework using RestAssured**

## Requirements

- Java 11 (JDK 11 or Higher)
- Browser

## System Under Test (SUT)

The System Under Test (SUT) for this project is [restful-booker](https://restful-booker.herokuapp.com/). Technical API documentation is available [here](https://restful-booker.herokuapp.com/apidoc/index.html).

## Installation

1. Clone the repository: `git clone https://github.com/RameezFridie/mid-assessment-api`
2. Navigate to the project root: `cd your-repo`

## Java Automation Framework

### Prerequisites

- Choose an IDE for development (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code).
- Ensure the presence of a `data.properties` file to run the automation successfully.
- `data.properties` file should have the data required for the automation to run.

### Other Libraries

- [**Hamcrest**](https://hamcrest.org/JavaHamcrest/tutorial) for Matchers
- [**Project Lombok**](https://projectlombok.org/) to get auto-generated Getter and Setter methods.
- [**TestNG**](https://testng.org/doc/) to get annotations and assertion methods in order to execute the tests.
- [**RestAssured**](https://rest-assured.io/) to run the api automation tests

### Running

The Java framework utilizes Gradle as the main building tool. To run the tests:

- On a Windows operating system, navigate to the project root in your command line. 
- One could also right-click on the **tests** package and click **run tests**
- Run the command: `gradle clean test`
- On Mac machine you can run: `./gradlew clean test`

### Project Structure

This project follows a certain structure, where:

- The tests are found under the **tests** package.
- The classes that provide test data are found under the **common** package.
- The **CustomConfig.java** class for configuring the `data.properties` is found under the overall web folder.

### Reporting

Generate a test report for the last completed run:

- Run the command: `gradlew allureReport` (or `./gradlew allureReport` on Unix).
- The report will be available in the `test-report` folder. Open `index.html` in a Chrome browser for the best viewing experience.
- This report can be opened on a browser as a server using `gradlew allureServe` or `./gradlew allureServe`.
## Postman Collection

The Postman collection employs environment variables to track all information. Find the required files in the
`postman-collection` folder at the project's root.

## Examples/Sample Usage

### Java Automation Framework
```bash
# Example command to run tests on Windows
gradle clean test

# Example command to run tests on Mac
./gradlew clean test