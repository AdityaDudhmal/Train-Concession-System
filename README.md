# **<u>Train Concession System<u>**

## <u>Description<u>

**Train Concession System** is a terminal based project especially made for colleges where their students can get _Travelling Concession_ on their _Season Passes_.

As per your role in this project, you can access any one of login metioned below:

* Admin Login
* Student Login

## <u>Key Features<u>

Below is the list of features that each login provides:

**1. Admin Login**

  > * Retrieve form data from database
  > * Approve application (If information is correct)
  > * Generate concession letter and send it to user (If form is approved)
  > * Send Grievance (If information is not valid)

  > Form data of student gets deleted from database once it is processed by admin


**2. Student Login**

  > * Create new account (Store information in database)
  > * Apply for application (Form gets added in Queue after submitting it)
  > * Submit application 



## <u>Results<u>

You can view the output results of this project by clicking on the Google-drive link given below:
> https://drive.google.com/drive/folders/1onbI4armLuRy4p53OSjA6VFfVTy3M4kF?usp=sharing


## <u>Project Setup<u>

* First install Java on your computer (JDK).
* You can use any java IDE such as IntelliJ, Eclipse, VS Code etc.
* You need to have one database server such as MySQL, MariaDB, Oracle etc.
* Install `.jar` file of your database in order to use JDBC. (Do check version of `.jar` files)
* Clone this repository and connect your `.jar` file with programme.
* Run **Test_TrainConcessionSystem.java** to run project.


## <u>Concepts Involved<u>

* **Data Structures**
  > * Queue
  > * String

* **Object Oriented Programming** (OOP)
* **Java Database Connectivity** (JDBC)
* **MariaDB**

## <u>Progress<u>

* Admin Progress
  - [x] Creating Admin Login
  - [x] Verifying authorized admin with help of _Security Key_
  - [x] Checking application forms one by one by retrieving data from database
  - [x] Sending _Concession Letter_ to applicant if form is valid
  - [x] Sending grievances if found any  

* Student Login
  - [x] Creating Student Login
  - [x] Creating new account and storing the data in database
  - [x] Applying for concession scheme
  - [x] Submit the form    

## <u>Application<u>

This project can be used by college students to get concession on their travelling `Local Train` passes.


## <u>Eligible Students<u>

Students from various courses such as `Diploma, B.Tech, M.Tech, PhD` can apply for scheme


## <u>Future Scope<u>

New features can be added such as: 

* Students being able to upate their personal data
* Adding valid time period for generated letter by admin


















