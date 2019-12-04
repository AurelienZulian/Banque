<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	${client.getNom()} ${client.getPrenom() } 
</h1>

<c:choose>
<c:when test="${empty liste}">
<div style="margin-bottom:20px;">Aucun compte n'est lié au client</div>
</c:when>
<c:otherwise>
<table>
<th>Numéro</th><th>Solde</th> 
<c:forEach var="entry" items="${liste}">
<tr><td>${entry.getNumero()} </td><td>${entry.getSolde()}</td></tr> 
</c:forEach>
</table>
</c:otherwise>

</c:choose>
</body>
</html>