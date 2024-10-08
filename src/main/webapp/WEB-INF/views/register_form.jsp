<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <style type="text/css">
        label {
            display: inline-block;
            width: 200px;
            margin: 5px;
            text-align: left;
        }
        input[type=text], input[type=password], select {
            width: 200px;
        }
        input[type=radio] {
            display: inline-block;
            margin-left: 45px;
        }
        input[type=checkbox] {
            display: inline-block;
            margin-right: 190px;
        }
        button {
            padding: 10px;
            margin: 10px;
        }
    </style>
    <title>User Registration Form</title>
</head>
<body>
<div align="center">
    <h2>User Registration</h2>
    <form:form action="register" method="post" modelAttribute="user">
        <form:label path="name">Full name:</form:label>
        <form:input path="name"/><br/>

        <form:label path="email">E-mail:</form:label>
        <form:input path="email"/><br/>

        <form:label path="password">Password:</form:label>
        <form:password path="password"/><br/>

        <form:label path="accountRole">Role:</form:label>
        <form:select path="accountRole" items="${roleList}" /><br/>

        <form:button>Register</form:button>
    </form:form>
</div>
</body>
</html>
