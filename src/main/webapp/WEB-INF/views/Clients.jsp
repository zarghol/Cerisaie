<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Entete.jsp"%>
<c:if test="${!empty alert}">
<div class="alert alert-success" data-dismiss="alert">
${alert}
<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
</div>
</c:if>
<ol class="breadcrumb">
  <li><a href="index.htm">Accueil</a></li>
  <li class="active">Clients</li>
</ol>
<div class="container">
	<div class="jumbotron">
		<ul id="Tabs" class="nav nav-tabs">
			<li><a href="#Ajout" data-toggle="tab"><span class="glyphicon glyphicon-plus"></span></a></li>
			<li class="active"><a href="#Affiche" data-toggle="tab">Affiche les clients</a></li>	
		</ul>

		<div id="tabContent" class="tab-content">
			<div class="tab-pane fade" id="Ajout">
				<form method="post" action="sauverClient.htm" onsubmit="return verif();" role="form">
					<input type="hidden" name="type" id="type" value="ajout" />
					<div class="form-group" id="nomclient-group">
						<label class="control-label" for="nomclient">Nom du Client</label>
						<input type="text" name="nomclient" value="" id="nomclient" class="form-control" />
					</div>
					<div class="form-group" id="prenomclient-group">
						<label class="control-label" for="prenomclient">Prénom du Client</label> 
						<input type="text" name="prenomclient" value="" id="prenomclient" class="form-control" />
					</div>
	
					<!-- fieldset adresse -->
					<fieldset>
						<legend>Adresse</legend>
						<div class="form-group" id="adresse-group">
							<label class="control-label" for="adresse">Rue</label>
							<input type="text" name="adresse" value="" id="adresse" class="form-control" />
						</div>
						<div class="form-group" id="cpostal-group">
							<label class="control-label" for="cpostal">Code Postal</label>
							<input type="text" name="cpostal" value="" id="cpostal" class="form-control" />
						</div>
						<div class="form-group" id="ville-group">
							<label class="control-label" for="ville">Ville</label>
							<input type="text" name="ville" value="" id="ville" class="form-control" />
						</div>
					</fieldset>
					<fieldset>
						<legend>Pièce d'identité</legend>
						<div class="form-group" id="typepiece-group">
							<label class="control-label" for="typepiece">Type de pièce</label>
							<select name="typepiece" id="typepiece" class="form-control">
								<option value="carteid">Carte d'identité</option>
								<option value="passeport">Passeport</option>
							</select>
						</div>
						<div class="form-group" id="numpiece-group">
							<label class="control-label" for="numpiece">Numéro de la pièce</label>
							<input type="text" name="numpiece" value="" id="numpiece" class="form-control" />
						</div>
					</fieldset>
					<button type="submit" class="btn btn-default">Ajouter</button>
				</form>
			</div>
			<div class="tab-pane fade active in" id="Affiche">
				<table class="table">
					<tr>
						<th>Numéro Client</th>
						<th>Nom</th>
						<th>Adresse</th>
						<th>Code Postal</th>
						<th>Ville</th>
						<th>Pièce d'identité</th>
						<th>Nombre de personne</th>
						<th colspan="3"></th>
					</tr>
					<c:forEach items="${MesClients}" var="item">
						<tr>
							<td>${item.noClient}</td>
							<td>${item.nomCl}</td>
							<td>${item.adresseCl}</td>
							<td>${item.codePostCl}</td>
							<td>${item.villeCl}</td>
							<td>${item.pieceIdentite}</td>
							<td>${item.nombrePersonnes}</td>
							<td><a href='modifierClient.htm?id=${item.noClient}'><span class="glyphicon glyphicon-edit"></span></a></td>
							<td><a href='supprimerClient.htm?id=${item.noClient}'><span class="glyphicon glyphicon-remove"></span></a></td>
							<td><a href='afficherSejourClient.htm?noClient=${item.noClient}'><span class="glyphicon glyphicon-th-list">Séjour</span></a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function verif() {
		var test2 = verifUnitaire("nomclient");
		var test4 = verifUnitaire("adresse");
		var test5 = verifUnitaire("cpostal");
		var test6 = verifUnitaire("ville");
		var test7 = verifUnitaire("typepiece");
		var test8 = verifUnitaire("numpiece");
		return (test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8);
	}
</script>
<%@ include file="PiedPage.jsp" %>