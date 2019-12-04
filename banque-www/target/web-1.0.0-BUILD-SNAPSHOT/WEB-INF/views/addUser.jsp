<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
	
	</head>
	<body>
	
		<h3>Add User</h3>
		
		<form:form method="POST" modelAttribute="clientParDefaut" action="successaddclient">
		<table>
			<tr>
				<td><form:label path="nom">Nom</form:label></td>
				<td><form:input path="nom"/></td>
			</tr>
			<tr>
				<td><form:label path="prenom">Prenom</form:label></td>
				<td><form:input path="prenom"/></td>
			</tr>
			<tr>
				<td><form:label path="adresse">adresse</form:label></td>
				<td><form:input path="adresse"/></td>
			</tr>
			<tr>
				<td><form:label path="codepostal">Code Postal</form:label></td>
				<td><form:input path="codepostal"/></td>
			</tr>
			<tr>
				<td><form:label path="ville">Ville</form:label></td>
				<td><form:input path="ville"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="submit" /></td>
			</tr>			
		</table>
		</form:form>
		
	</body>
</html>