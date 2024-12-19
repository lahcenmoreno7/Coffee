# Coffee 

## Description
An API to calculate amounts paid and owed for coffee orders.

## Endpoints
1. `payments/amount-paid/{user}` - Calculate amounts paid by users.
2. `payments/amount-owed` - Calculate amounts owed by users.

## Running the Application
1. Clone the repository https://github.com/lahcenmoreno7/Coffee.git
2. Run `mvn spring-boot:run`.
3. Access API at `http://localhost:8080`.

## Tests
Run `mvn test` to execute the test cases.
 - CoffeeServiceTest
 - CoffeeControllerTest


## Technology Stack 
 - Java 17
 - Spring Web
 - Spring Boot DevTools
 - Spring Boot Validation
 - Spring Data JPA
 - H2 Database (or any in-memory database for testing)
 - Lombok
 - JUnit
 - Logging
 - maven
 - Git

## Postman screen

 - Coffee Databae
   ![alt text](https://github.com/lahcenmoreno7/Coffee/blob/main/src/main/resources/images/coffe-db.png)

 - API
   ![alt text](https://github.com/lahcenmoreno7/Coffee/blob/main/src/main/resources/images/amountPaid.png)
   ![alt text](https://github.com/lahcenmoreno7/Coffee/blob/main/src/main/resources/images/amountOwed.png)






