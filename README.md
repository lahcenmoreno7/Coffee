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
   ![screenshot](images/coffee-de.png)

 - API
   ![screenshot](images/amountPaid.png)
   ![screenshot](images/amountOwed.png)






