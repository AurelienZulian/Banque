<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Menu
</h1>

<ul>
<li><a href="addUser">Ajouter un client</a></li>
<li><a href="./admin/">Panel admin</a></li>
</ul>

<h1>Liste des clients : </h1>
<c:forEach var="entry" items="${liste}">
<ul><li><a href="client?Id=${entry.id}">${entry.getNom()} ${entry.getPrenom() } </a> | <a href="addCompte?Id=${entry.id}">Ajouter un compte</a></li></ul>
</c:forEach>

</body>
</html>
