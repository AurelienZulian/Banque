<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
	
	</head>
	<body>
	
<h1>
Ajouter un compte pour ${client.getNom()} ${client.getPrenom()} 
</h1>

<form:form method="POST" modelAttribute="compte" action="addCompteSuccess?Id=${client.id}">
	Numero : <form:input path="numero"/><br>
	Solde : <form:input path="solde"/><br>
	<input type="submit" value="submit"/>
</form:form>
		
	</body>
</html>