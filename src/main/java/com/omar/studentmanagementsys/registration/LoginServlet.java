package com.omar.studentmanagementsys.registration;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "validation", value = "/login")
public class LoginServlet extends HttpServlet {
    public void init() {
        System.out.println("The Login page has been successfully loaded.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smsDB", "postgres", "admin123");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE uemail=? AND upwd=?");
            ps.setString(1, uemail);
            ps.setString(2, upwd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                session.setAttribute("email", rs.getString("uemail"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void destroy() {

    }
    }

