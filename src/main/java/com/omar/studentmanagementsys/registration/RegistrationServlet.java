package com.omar.studentmanagementsys.registration;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "registration", value = "/register")
public class RegistrationServlet extends HttpServlet {
    private String message;

    public void init() {
        System.out.println("The SignUp page has been successfully loaded.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("pass");
        String umobile = request.getParameter("contact");
        RequestDispatcher dispatcher = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smsDB", "postgres", "admin123");
           PreparedStatement ps = conn.prepareStatement("INSERT INTO users(uname, upwd, uemail, umobile) values(?,?,?,?) ");
           ps.setString(1,uname);
           ps.setString(2, upwd);
           ps.setString(3,uemail);
           ps.setString(4, umobile);

           int rowCount = ps.executeUpdate();
            dispatcher = request.getRequestDispatcher("/registration.jsp");
           if(rowCount > 0){
               request.setAttribute("status", "success");
           }else{
              request.setAttribute("status", "failed");
           }
//           Thread.sleep(10000);
           dispatcher.forward(request, response);
            Thread.sleep(5000);
           response.sendRedirect("/registration.jsp");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

//        response.setContentType("text/html");
//
//        out.print("<h1>Working!!</h1>");
//        try {
//            //test
//            System.out.println("hehe2");
//            // Register PostgreSQL Driver
//            Class.forName("org.postgresql.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smsDB", "postgres", "admin123");
//
//            System.out.println("hehe3");
//
//            out.println("<html><body>");
//            out.println("<h1> Hello its working finally!</h1>");
//            out.println("</body></html>");
//        }
//        catch (ClassNotFoundException e) {
//            System.out.println("Unable to load Driver class");
//           e.printStackTrace();
//            // can directly print the stack trace
//            System.exit(1);
//        } catch (SQLException e2) {
//            throw new RuntimeException(e2);
//        }
//
    }

    public void destroy() {
    }
}


/*
 Signup
 - by filling the sign-up form and clicking "Sign-up" button user information has to be stored to a database table
 - there would be a "already signed up? - Login here!" Hyperlink to redirect to the login page
 - if any mandatory form field is left blank "Signup not successful, please fill-up all the fields

 Login
 - upon entering email and password, servlet will check if the user is already in the database or not
 - if the user is found, will be redirected to the SMS (CRUD operation page)
 - if the user is not found, will be redirected to the Signup page

 SMS page
 - there will be a list of users shown and add at the last add button and, with each user update and delete button
 - to edit the page information will be present
 - there would be a logout button to exit the sms page and redirect to the homepage/Signup page

 GET
 PUT
 POST
 PATCH
 DELETE
*/
