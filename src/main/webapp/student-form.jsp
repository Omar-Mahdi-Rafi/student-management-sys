<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 10/3/2022
  Time: 6:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>Student Management System</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark"
       style="background-color: blue">
    <div>
      <a href="https://localhost:8080/" class="navbar-brand"> Student Management System </a>
    </div>

    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/list"
             class="nav-link">Students</a></li>
    </ul>
  </nav>
</header>
<br>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${student != null}">
      <form action="update" method="post">
        </c:if>
        <c:if test="${student == null}">
        <form action="insert" method="post">
          </c:if>

          <caption>
            <h2>
              <c:if test="${student != null}">
                Edit Student
              </c:if>
              <c:if test="${student == null}">
                Add New Student
              </c:if>
            </h2>
          </caption>

          <c:if test="${student != null}">
            <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
          </c:if>

          <fieldset class="form-group">
            <label>Student Name</label> <input type="text" value="<c:out value='${student.name}' />" class="form-control" name="name" required="required">
          </fieldset>

            <fieldset class="form-group">
              <label>Marks</label> <input type="text" value="<c:out value='${student.marks}' />" class="form-control" name="marks" required="required">
            </fieldset>
            <fieldset class="form-group">
              <label>Gender</label> <input type="text" value="<c:out value='${student.gender}' />" class="form-control" name="gender" required="required">
            </fieldset>
            <fieldset class="form-group">
              <label>Date of Birth</label> <input type="text" value="<c:out value='${student.date_of_birth}' />" class="form-control" name="date_of_birth" required="required">
            </fieldset>

          <fieldset class="form-group">
            <label>email</label> <input type="text" value="<c:out value='${student.email}' />" class="form-control" name="email" required="required">
          </fieldset>


          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
</div>
</body>
</html>
