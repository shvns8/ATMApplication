# Spring Boot "ATM" Application

This is a Maven Spring Boot application that can be used to perform the following functionalities:
  - create Accounts
  - cash deposits
  - cash withdrawls
  - balance enquiry

## How to Build and Run 

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

1. Clone the repository
2. Make sure you have JDK 1.8
3. You can build the project and run the tests by running mvn clean install
4. Once successfully built, you can run the service by one of these two methods:


  

## Assumptions: 

 1. The application uses the in-memory H2 database. 

 2. The application considers the following attributes for the account resource –  

      - Account Number (accNumber) 

      - Account Holder First Name (accholderFirstName) 

      - Account Holder Last Name (accholderLastName) 

      - Account Holder contact number (contactNumber) 

      - Account Holder email address (email) 

      - Account active status (accStatus) 

      - Account Creation date time (accCreationDate) 

      - Account Balance (balance) 

3. The application performs all operations related to cash deposit, cash withdrawl and balance enquiry taking into account only first 2 places of decimal in the    balance 

4. The application assumes that the user need not give creation date in the json body while creating the account. The application itself assigns the current date time when the account is created. 

5. The application takes the currency as INR.


## REST API’s available in ATM application 

1. Endpoint: /accounts/add – to create an account in the atm application 

   Method Verb: POST 

   Headers: Content-Type: application/json 

   Body: It contains the json in the following format 

            { 

            “accNumber”:120, 

            “accholderFirstName”:”Raj”, 

            “accholderLastName”:”Singh”, 

            “email”:raj123@gmail.com, 

            “contactNumber”:”9009778612” 

            “accStatus”:”Active”, 

            “balance”:200.12 

            } 

  

    Output: 200 OK – if the account is successfully created 

    400 Bad Request – if the account creation was not successful 

  

2. Endpoint: /transact/deposit – to deposit the cash into the account 

   Method Verb: PUT 

   Request Params:  

        accnumber – account number 

        amount – amount to be deposited into the account 

    Output: 200 OK – if the amount was successfully deposited into the account 

      400 Bad request – if the amount could not be deposited into the account 

  

3. Endpoint: /transact/withdraw – to withdraw the cash into the account 

    Method Verb: PUT 

    Request Params:  

        accnumber – account number 

        amount – amount to be deposited into the account 

    Output: 200 OK – if the amount was successfully withdrawn from the account 

      400 Bad request – if the amount could not be withdrawn from the account 

  

4. Endpoint: /transact/getbalance/{accnumber} – to deposit the cash into the account 

   Method Verb: GET 

   PathVariable:  

        accnumber – account number 

   Output: 200 OK alongwith the balance in the body of the response – if the balance enquiry was       successful 

   400 Bad request – if the balance enquiry failed 
