<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<table>
	<tr><td>Sejours</td></tr>
	<c:forEach items="${listeRechercheSejour}" var="item">
		<tr>
			<td><a href="Controleur?action=modifierStage&id=${item.id}">${item.libelle}</a></td>
		</tr>
	</c:forEach>
	<tr><td>Clients</td></tr>
	<c:forEach items="${listeRechercheClient}" var="item">
		<tr>
			<td><a href="Controleur?action=modifierStage&id=${item.id}">${item.libelle}</a></td>
		</tr>
	</c:forEach>
</table>