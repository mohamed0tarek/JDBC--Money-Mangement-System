![Language](https://img.shields.io/badge/language-Java%20-blue.svg)
![Language](https://img.shields.io/badge/language-SQL%20-red.svg)

# Money Mangement System [JDBC] 🏛💵


# Overview
This project is a console application that connected to a datebase to represent a simple money management system. The API used to connect with the database here is **JDBC** of course with respect of transactions rules.<br>

**`NOTE`** ***I build the same project using JPA. If you want to check it [here](https://github.com/mohamed0tarek/JPA--Money-Management-system/).***<br>

<p align="center">
  <img height=140  src="https://images.idgesg.net/images/article/2022/05/what-is-jdbc-fig1-100927559-large.jpg?auto=webp&quality=85,70">
</p>
<br>

**JDBC** stands for Java Database Connectivity. JDBC is a Java API to connect and execute the query with the database.


# Project parts
**1. Client** <br>
Opertaions that client can do :
   - Inquires about account information
   - Transfer money to another account
   - Deposit money
   - Withdraw
  

**2. Admin** <br>
Operations that admin can do :
   - Add Client
   - Delete Client
   - List all Clients in the system 
   - Show all Clients operations History
   - Show Operations History of specific client
   
**3. Database** <br>
 - Client table
 - History table
 
 **4. Connection to Database** <br>
  - Singletone class that take care of all connections between the application and the database

# Tools
* JAVA CORE
* Maven
* XML
* MYSQL
* SQL

# How works
when you run the application, There are processes you will go through : <br>
**1. Authentication** <br>
First you have to sign in with id and password either you were a client or an admin.

**2. Do Operations** <br>
List of operations will appear. And user can choose what he want to execute. of course clients and admins have different list of tasks they can do as I refered above.

**3. Transactions rules** <br>
All Transactions rules have been taken care of. Before commiting any transaction there are conditions to check. if all these transactions have been taken place successfully. If so, transaction commited. If not, transaction rollback.

