<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Entete.jsp" %>
<c:if test="${!empty alert}">
	<div class="alert alert-success" data-dismiss="alert">
		${alert}
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	</div>
</c:if>
<ol class="breadcrumb">
  <li><a href="index.htm">Accueil</a></li>
  <li class="active">Séjour</li>
</ol>
    <div class="container">
    	<div class="jumbotron">
	    	<ul id="Tabs" class="nav nav-tabs">
				<li><a href="#Ajout" data-toggle="tab"><span class="glyphicon glyphicon-plus"></span></a></li>
				<li class="active"><a href="#Affiche" data-toggle="tab">Affiche les séjours</a></li>	
				<li><a href="#Periode" data-toggle="tab">Période</a></li>
			</ul>
	    	<div id="tabContent" class="tab-content">
				<div class="tab-pane fade" id="Ajout">
					<form method="post" action="sauverSejour.htm" onsubmit="return verif();" role="form">
						<div class="form-group" id="nosejour-group">
							<label class="control-label" for="nosejour">Numéro Séjour</label>
							<input type="text" name="nosejour" value="" id="nosejour" class="form-control" />
						</div>
						<div class="form-group" id="client-group">
							<label class="control-label" for="client" id="client-group">Client</label>
							<select name="client" id="client" class="form-control">
								<c:forEach items="${clients}" var="item">
									<option value="${item.noClient}">${item.prenomCl} ${item.nomCl}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group" id="vendeur-group">
							<label class="control-label" for="emplacement">Emplacement</label> 
							<select name="vendeur" id="emplacement" class="form-control">
							<c:forEach items="${typesemplacements}" var="item">
								<option value="${item.codeTypeE}">${item.libTypePl} ${item.tariftypepl}</option>
							</c:forEach>
							</select>
						</div>
						<div class="form-group" id="date-group">
							<label class="control-label" for="datedebut">Date Début</label>
							<input type="date" name="datedebut" value="" id="datedebut" class="form-control" placeholder="jj/MM/aaaa" />
						</div>
						<div class="form-group" id="date-group">
							<label class="control-label" for="datefin">Date Fin</label>
							<input type="date" name="datefin" value="" id="datefin" class="form-control" placeholder="jj/MM/aaaa" />
						</div>
						<div class="form-group" id="nbpersonne-group">
							<label class="control-label" for="nbpersonne">Nombre de Personnes</label>
							<input type="text" name="nbpersonne" value="" id="nbpersonne" class="form-control" />
						</div>
					
						<button type="submit" class="btn btn-default">Ajouter</button>
					</form>
				</div>
				<div class="tab-pane fade active in" id="Affiche">
					<table class="table">
						<tr>
							<th>Numéro Commande</th>
							<th>Numéro Client</th>
							<th>Numéro Vendeur</th>
							<th>Date Commande</th>
							<th>Facture</th>
							<th></th>
						</tr>
						<c:forEach items="${mescommandes}" var="item">
							<tr>
								<td>${item.noCommand}</td>
								<td>
									<button class="btn btn-link btn-lg" data-container="body" data-trigger="hover" data-toggle="popover" data-placement="bottom" title="Client ${item.clientel.noClient}"
										data-content="nom : ${item.clientel.nomCl} | société : ${item.clientel.societe}">
										${item.clientel.noClient}
									</button>
								</td>
								<td>
									<button class="btn btn-link btn-lg" data-container="body" data-trigger="hover" data-toggle="popover" data-placement="bottom" title="Vendeur ${item.vendeur.noVendeur}"
										data-content="nom : ${item.vendeur.nomVend} | ville : ${item.vendeur.villeVend}">
										${item.vendeur.noVendeur}
									</button>
								</td>
								<td><fmt:formatDate type="date" value="${item.dateCde}" pattern="dd/MM/yyyy"/></td>
								<td>${item.facture}</td>
								<%-- <td><a href='supprimerCommande.htm?id=${item.noCommand}'><span class="glyphicon glyphicon-remove"></span></a></td> --%>
								<td><a href='detailCommande.htm?id=${item.noCommand}'>Détail<span class="glyphicon glyphicon-chevron-right"></span></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="tab-pane fade" id="Periode">
					<form class="form form-inline">
						<div class="form-group">
							<label for="periodedebut">Date début</label>
							<input type="date" name="periodedebut" id="periodedebut" class="form-control" placeholder="jj/MM/aaaa" />
						</div>
						<div class="form-group">
							<label for="periodefin">Date fin</label>
							<input type="date" name="periodefin" id="periodefin" class="form-control" placeholder="jj/MM/aaaa" />
						</div>
						<button type="button" class="btn btn-default" onClick="demandePeriode()">Demander</button>
					</form>
					<div id="tablePeriode">
					</div>
				</div>
			</div>
		</div>
    </div>
    <script type="text/javascript">
	function verif() {
		var test1 = verifUnitaire("nocommande");
		var test2 = verifUnitaire("client");
		var test3 = verifUnitaire("vendeur");
		var test4 = verifDate("date");
		var table = document.getElementById("tableArticles");
		/*Test de la quantite des articles
		for (var row in table.rows){
			row.cells
		}
		*/
		return (test1 && test2 && test3 && test4 && table.rows.length > 1);
	}

	function addRow(tableID) {
		 
        var table = document.getElementById(tableID);
		
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        
        var cell1 = row.insertCell(0);
        cell1.innerHTML = "<select id='article"+rowCount+"' name='article"+rowCount+"'>"
        					+"<c:forEach items='${articles}' var='item'>"
        					+"<option value='${item.noArticle}'>${item.libArticle}</options>"
        					+"</c:forEach> "
        					+"</select>";
        					
        var cell2 = row.insertCell(1);
        cell2.innerHTML = "<input type='text' id='quantiteArticle"+rowCount+"' name='quantiteArticle"+rowCount+"'/>";

        var cell3 = row.insertCell(2);
        cell3.innerHTML = "<button type='button' class='btn btn-link' onclick=\"removeRow('"+tableID+"', '"+rowCount+"')\"><span class='glyphicon glyphicon-remove'></span></button>";
    }

    function removeRow(tableID, ligne){
    	document.getElementById(tableID).deleteRow(ligne);
   	}

	function demandePeriode(){
		if(verifDate("periodedebut") && verifDate("periodefin")) {
		    DemandeRecherche(document.getElementById("periodedebut").value, document.getElementById("periodefin").value);
		}else{
			document.getElementById("tablePeriode").innerHTML = ""; // on vide les resultats
		}
	}
</script>
<%@ include file="PiedPage.jsp" %>