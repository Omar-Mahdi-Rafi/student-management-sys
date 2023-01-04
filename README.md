# Student Management System
## This is a student management system being developed with Java servlets, JSP, JSTL, JDBC and PostgreSQL with basic registration features like Signup, authentication features like Login/Logout and CRUD operation.

```
Download and extract the project package as a .zip from the repository. 

Open with IntelliJ IDE with Tomcat latest version installed locally and maven integrated in IntelliJ.

Run Tomcat server to get started with using the SMS WebApp.
```

## Architecture and Design Patterns
- MVC Architecture has been used. 
- DAO pattern has been used to separate model and controllers. The StudentServlet.java is the controller and the StudentDAO is the Model here. 
- The DAO has been implemented to separate Logic operations from database connectors for convenient to future upgrade.
- Views has been done using JSP and JSTL to use tags for efficient Java logic implementation in JSP files. Separate views have been created for each feature.
- The Ui has been made better with the usage of simple Bootstrap and Css.

## Problems yet to be solved - 
- The JSTL library doesn't work in the index.jsp and student-form.jsp due to some environment setup error. Though the .jar dependencies are integrated in the Tomcat web library. 
  This causes an error while starting the project despite the right implementation of server side logics.
