<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="/style.css">
<title>Classification</title>
</head>
<body>
	<form:form action="/classify/${image.id}" method="put" modelAttribute="newClassification">
	<p>
	<form:input type="hidden" path="id"/>
	<p>
 		<form:errors path="image"/>
        <form:label path="image">Image</form:label>
        <form:input path="image"/>
    </p>
    <p>
        <form:label path="classification">Classification</form:label>
        <form:errors path="classification"/>
        <form:input path="classification"/>
    </p>
	<button class="btn btn-primary">Submit</button>
</form:form>
</body>
</html>