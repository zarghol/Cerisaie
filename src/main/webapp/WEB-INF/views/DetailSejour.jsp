<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Entete.jsp"%>
<ol class="breadcrumb">
  <li><a href="index.htm">Accueil</a></li>
  <li><a href="Sejour.htm">Sejour</a></li>
  <li class="active">Détail</li>
</ol>
<div class="container">
	<div class="jumbotron">
		N° Séjour : ${sejour.noSejour}<br/>
		Client : <a href="Client.htm?id="${sejour.client.noClient}">${sejour.client.nomClient}</a><br/>
		<!--  popover a faire  -->
		Emplacement : ${sejour.client.nomClient}<br/>
		Date début : <fmt:formatDate type="date" value="${sejour.dateDebut}" pattern="dd/MM/yyyy"/><br/>
		Date fin : <fmt:formatDate type="date" value="${sejour.datefin}" pattern="dd/MM/yyyy"/><br/>
		Nombre de personne : ${sejour.nbPersonne}<br/>
		<form class="form">
			<button type="button" class="btn btn-primary" onclick="addRow('tableArticle')">Ajouter un article</button>
			<table class="table" id="tableActivite">
				<tr>
					<th>Sport</th>
					<th>Date</th>
					<th>nombre de personnes concernés</th>
				</tr>		
			</table>
		</form>
		<a href="factureSejour.htm?id=${sejour.idSejour}">Facture du séjour</a><br/>
		<a href="factureSejour.htm?id=${sejour.idSejour}">Facture des activités</a>
	</div>
</div>
<%@ include file="PiedPage.jsp" %>