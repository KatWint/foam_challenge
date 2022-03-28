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
<link rel="stylesheet" href="style.css">
<title>Home</title>
</head>
<body>
<a href="/logout">Logout</a>
<h1>Click below for available images by category or to view all</h1>
<p>To view all and add more images, please select below</p>
<a href="/experiments">View All Experiments</a>
<p>To view based on classifications, please choose from the options below:</p>
<a href="/nonfoaming">NonFoaming</a>
<a href="/foaming">Foaming</a>
<a href="/unclassified">Unclassified</a>
</body>
</html>