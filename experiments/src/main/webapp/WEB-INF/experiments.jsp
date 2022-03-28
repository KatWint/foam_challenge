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
<title>Experiments</title>
</head>
<body>
<a href="/home">Go back to home page</a>
<div>
<form action="/saveimage" method="Post" enctype="application/x-www-form-urlencoded">
	<label>add the experiment</label>
	<input type="file" name="file" class="custom-file-input" id="customFile">
	<label class="customer-file-label" for="customFile">Choose File</label>
	<input type="hidden" name="classification" value="unclassified">
	<div>
	<button type="submit" class="btn btn-light">Submit</button>
	</div>
	
   
</form>
</div>
<div>
<table class="table table-striped table-light">
<thead>
	<tr>
		<th>Image</th>
		<th>Classification</th>
		<th>Action</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${allImages}" var="allimages">
		<tr>
		<td>${allimages.image}</td>
		<td>${allimages.classification} </td>
		<td><a href="/classify/${allimages.id}">Classify Image</a>
		</tr>
	
	</c:forEach>	
</tbody>

</table>
</div>
</body>
</html>