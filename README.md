
## Tech Stack: 
- Java 17
- Spring Boot 3.1.12
- Open API specification: Swagger 2.1.0
- H2 Database
- Swagger URL: http://localhost:8080/swagger-ui/index.html
- H2 Console URL: http://localhost:8080/h2-console/


## Customer Controller
#### H2 Database Page
- Sample SQL Query: SELECT * FROM CUSTOMER;;
- List all customer
![github](customer-register.png)

#### Swagger UI Page
![github](customer-register2.png)
![github](customer-register3.png)


## Payment Controller
#### H2 Database Page
- Sample SQL Query: SELECT * FROM PAYMENT WHERE CUSTOMER_ID = 1;
- List all payments by customer number
![github](payment-by-customer.png)

#### Swagger UI Page
![github](payment-by-customer2.png)
![github](payment-by-customer3.png)


