package com.omar.studentmanagementsys.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.omar.studentmanagementsys.bean.Student;


/**
 * StudentDAO.java This DAO class provides CRUD database operations for the
 * students table in the smsDB database.
 */
public class StudentDAO {

    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students" + "  (name, marks, gender, date_of_birth, email) VALUES "
            + " (?, ?, ?, ?, ?);";

    private static final String SELECT_STUDENT_BY_ID = "SELECT id, name, marks, gender, date_of_birth, email FROM students WHERE id=?;";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students;";
    private static final String DELETE_STUDENTS_SQL = "DELETE FROM students WHERE id = ?;";
    private static final String UPDATE_STUDENTS_SQL = "UPDATE students SET name = ?, marks= ?, gender =?, date_of_birth=?, email=? WHERE id = ?;";

    public StudentDAO() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smsDB", "postgres", "admin123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //Add or Insert student in database
    public void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENTS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getMarks());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getDate_of_birth());
            ps.setString(5, student.getEmail());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    //Selecting particular student by "id"
    public Student selectStudent(int id) {
        Student student = null;
        //Establishing a Connection
        try (Connection connection = getConnection();
             //Create a statement using connection object
             PreparedStatement ps = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            ps.setInt(1, id);
            System.out.println(ps);
            //Execute the query or update query
            ResultSet rs = ps.executeQuery();

            //Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String marks = rs.getString("marks");
                String gender = rs.getString("gender");
                String date_of_birth = rs.getString("date_of_birth");
                String email = rs.getString("email");
//
               student = new Student(id, name, marks, gender, date_of_birth, email);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    //Selecting all the users for Read operation
    public List<Student> selectAllStudents() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Student> students = new ArrayList<>();
        // Establishing a Connection with DataBase
        try (Connection connection = getConnection();

             //Create a statement using connection object
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            System.out.println(ps);
            //Execute the query or update query
            ResultSet rs = ps.executeQuery();

            //Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String marks = rs.getString("marks");
                String gender = rs.getString("gender");
                String date_of_birth = rs.getString("date_of_birth");
                String email = rs.getString("email");
                students.add(new Student(id, name, marks, gender, date_of_birth, email));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    //Deleting particular student entry by 'id' from student table
    public boolean deleteStudent(int id) throws SQLException {
        //Boolean value to confirm deletion
        boolean rowDeleted;
        //Establishing a Connection with DataBase
        try (Connection connection = getConnection();
             //Creating prepareStatement using connection object to run database query
             PreparedStatement ps = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    //Updating (Editing student information)
    public boolean updateStudent(Student student) throws SQLException {
        // boolean value to store 'true' or 'false' value to confirm update
        boolean rowUpdated;
        //Establishing a Connection with DataBase
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENTS_SQL);) {
            System.out.println("updated student:"+ps);
            //fetching student details from DataBase using getter method
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getMarks());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getDate_of_birth());
            ps.setString(6, student.getEmail());

            //Storing whether update is successful or not
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}