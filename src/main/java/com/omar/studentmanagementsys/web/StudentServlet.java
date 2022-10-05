package com.omar.studentmanagementsys.web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.omar.studentmanagementsys.dao.StudentDAO;
import com.omar.studentmanagementsys.bean.Student;

/**
    Servlet implementation class StudentServlet.java
    This servlet acts as a page controller for the application, handling all
    requests from the user.
*/
@WebServlet("/")
public class StudentServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
        System.out.println("The Student Servlet has been successfully loaded.");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    try {
                        insertStudent(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "/delete":
                    try {
                        deleteStudent(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/edit":
                    try {
                        showEditForm(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "/update":
                    try {
                        updateStudent(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    try {
                        listStudent(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Student> listStudent = null;
        try {
            listStudent = studentDAO.selectAllStudents();
            request.setAttribute("listStudent", listStudent);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // New form for new entry
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    //Edit form for existing entry
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent;
        try {
            existingStudent = studentDAO.selectStudent(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
            request.setAttribute("student", existingStudent);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Insert new student entry
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String marks = request.getParameter("marks");
        String gender = request.getParameter("gender");
        String date_of_birth = request.getParameter("date_of_birth");
        String email = request.getParameter("email");
        Student newStudent = new Student(name, marks, gender, date_of_birth, email);
        studentDAO.insertStudent(newStudent);
        response.sendRedirect("list");
    }

    //Update Student information
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String marks = request.getParameter("marks");
        String gender = request.getParameter("gender");
        String date_of_birth = request.getParameter("date_of_birth");
        String email = request.getParameter("email");

        Student book = new Student(id, name, marks, gender, date_of_birth, email);
        System.out.println(book);
        try {
            studentDAO.updateStudent(book);
        }catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("list");
    }

    //Deleting student entry
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try{
            studentDAO.deleteStudent(id);

        } catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("list");
    }

}


