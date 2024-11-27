# Bank Account Management System (Java Console Application)

## **Project Description**
The Bank Account Management System is a Java-based console application that allows users to manage bank accounts without a graphical interface. The system supports basic banking functionalities like account creation, login, balance checking, deposit, withdrawal, and transaction history. The project is implemented using Object-Oriented Programming (OOP) principles and stores data in `.txt` files instead of a database.

---

## **Features**
1. **Account Creation**  
   - Create a savings account with a name, account number, and password.
   
2. **Account Login**  
   - Authenticate users with their account number and password.
   
3. **Check Balance**  
   - View the current balance of the account.

4. **Deposit Money**  
   - Add funds to the account balance.

5. **Withdraw Money**  
   - Deduct funds from the account balance (ensuring sufficient funds are available).

6. **Transaction History**  
   - View the history of deposits and withdrawals for an account.

7. **Exit**  
   - Safely exit the application.

---

## **Project Architecture**
This project uses **Object-Oriented Programming (OOP)** principles:
1. **Abstraction:**  
   - Key functionalities are abstracted into methods and classes for better modularity.
   
2. **Encapsulation:**  
   - Sensitive data (e.g., account balance and password) is protected using private variables and public getter/setter methods.
   
3. **Inheritance:**  
   - A `BankAccount` superclass is extended by a `SavingsAccount` subclass for specific account types.
   
4. **Polymorphism:**  
   - Methods like `displayAccountDetails()` can be overridden in different account types to provide specific implementations.

---

## **File Storage**
The system uses `.txt` files to store data persistently:
- **`accounts.txt`**: Stores serialized account information.
- **`transactions.txt`**: Stores transaction history for all accounts.

---

## **Technologies Used**
- **Programming Language:** Java
- **Tools and Libraries:**
  - `java.util.*` for utilities like `Scanner`, `HashMap`, and `Date`.
  - `java.io.*` for file handling (reading and writing `.txt` files).

---

## **Setup and Usage**
### **Prerequisites**
- Java Development Kit (JDK) installed on your system.

### **Steps to Run the Project**
1. Clone or download the project files.
2. Compile the Java files:
   ```bash
   javac Main.java
