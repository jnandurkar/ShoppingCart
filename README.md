# Shopping Cart Application

# Problem statement 
Epic Context
A big retail brand outlet, on the eve of Christmas, we want to offer attractive seasonal discounts to our
customers to boost our retail sales.
Story#1
As a retail outlet’s salesman, I should be able to calculate the customer's shopping cart bill after considering
applicable discount rates that are based on the purchase amount and customer type so that our retail outlet
can maximize sales volume & value.
Discount Slabs for various purchase amounts for the regular and premium customers are as follows:
Customer Type: Regular 
Purchase Amount Slab Discount % 
$0 - $ 5,000 = Nil 
$ 5,000 - $10,000 10% 
$10,000 & above 20% 

Customer Type: Premium
Purchase Amount Slab Discount %
$ 0 - $ 4,000 10%
$ 4,000 - $ 8,000 15%
$ 8,000 - $ 12,000 20%
$ 12,000 & above 30%

Note:
Total discount would be the sum of discount calculated for each slab.
E.g. For purchase of $15,000 by a regular customer would entitle total discount $1500 which is sum of
discount $500 for 2nd slab [10% of $(10000-5000)] & $1000 discount for 3rd slab [20% of $(15000-10000)].
Story Test-cases
Here are some of the test cases and corresponding input / output expectations:
Customer Type (input): Regular Customer Type (input): Premium
Purchase Amount
(input)

Bill Amount
(output)

Purchase Amount(input)  Bill Amount(output)


$ 5,000 $ 5,000 
$ 10,000 $ 9,500 
$ 15,000 $ 13,500 


$ 4,000 $ 3,600
$ 8,000 $ 7,000
$ 12,000 $ 10,200

$20,000 $15,800

## Features
- REST API
- JWT authentication
- Swagger-ui documentation

## Technology Stacks
**Backend**
  - Java 8
  - Spring Boot 1.4.7
  - Spring Security
  - JWT Authentication
  - Spring Data JPA
  - h2 database
  - Maven

# REST endpoints
http://localhost:8090/swagger-ui.html#/ 
*to check bill
GET /bills
GET /bill/{id}
#(port used- 8090)


## authentication
username-admin	
password - admin


# How to run the application locally?

Pre-requisites to run application are Java, git and maven.
*  Apache Maven 3.5.0 
*  Java version: 1.8

# How to build & run?
1. Open commandline terminal
2. Go to the Project base location
3. Build the jar using maven=> mvn package  
4. Go to target folder => cd target
5. Run following command to start the server on port 8090=> java -jar ShoppingCartApp-0.0.1-SNAPSHOT.jar (For other port number, Please change port number in application.properties file)
Optionally, one can configure port using commandline parameter => --server.port=8080
6. Access and invoke APIs using url => http://localhost:8090/swagger-ui.html
7. For authentication credential, Use 'admin' as user id and password.

This application developed using H2 database and hence does not persist data after application restarts. 

