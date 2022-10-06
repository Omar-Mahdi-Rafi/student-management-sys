package com.omar.studentmanagementsys.web;

import jakarta.json.Json;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.ws.rs.core.Application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/dbjson")
public class Servlet extends HttpServlet {



        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String queryJson = null;
        public void init(ServletConfig config) throws ServletException{
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smsDB", "postgres", "admin123");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        queryJson = "SELECT row_to_json(students) FROM students;";

        try{
            ps = connection.prepareStatement(queryJson);
            rs = ps.executeQuery();
           while(rs.next()) {
               out.println( rs.getString(1) );
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}


