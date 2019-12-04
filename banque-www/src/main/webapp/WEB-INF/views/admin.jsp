<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
	
	</head>
	<body>
		<h3>Administration</h3>
		
		<table>
			<tr>
				<th>Action</th>
				<th>Description</th>
			</tr>
			<tr>
				<td>
					<a href="effacerClient">effacer</a>
				</td>
				<td>
					effacement d'un client
				</td>
				<td>
					<a href="editerClient">éditer</a>
				</td>
				<td>
					formulaire d'édition des données du client
				</td>
			</tr>
			<tr>
				<td>
					 <a href="/web/logout">Logout</a>
				</td>
				<td>
					quitter le mode admin
				</td>
			</tr>
		</table>
	</body>
</html>