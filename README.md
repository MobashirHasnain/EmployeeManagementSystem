# Employee Management System

A Java-based Employee Management System using MySQL and JDBC to manage employee records.

## Features

- Add Employee
- View Employees
- Search Employee
- Update Employee
- Delete Employee

## Technologies Used

- Java
- MySQL
- JDBC
- Git
- GitHub

## Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE employee_db;
```

Select the database:

```sql
USE employee_db;
```

Create the employees table:

```sql
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(100),
    salary DOUBLE
);
```

## How to Run

1. Install Java and MySQL.
2. Create the `employee_db` database and `employees` table in MySQL.
3. Set the `DB_PASSWORD` environment variable with your MySQL password.
4. Make sure the MySQL Connector/J JAR is inside the `lib` folder.
5. Compile and run the project.

```bash
javac -cp ".;lib/mysql-connector-j-26.7.0.jar" *.java
java -cp ".;lib/mysql-connector-j-26.7.0.jar" Main
```

## Project Structure

```text
EmployeeManagementSystem/
├── DBConnection.java
├── Employee.java
├── Main.java
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── .gitignore
└── README.md
```

## Security

The MySQL password is stored using the `DB_PASSWORD` environment variable.

The `.env` file is excluded from Git using `.gitignore`.

## Author

Mobashir Hasnain
