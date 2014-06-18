<!DOCTYPE html>
<html>
  <head>
    <title>Cerisaie</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<c:url value='/resources/css/bootstrap.min.Cerulean.css'/>" rel="stylesheet" media="screen">
    <link href="<c:url value='/resources/css/navbar-fixed-top.css'/>" rel="stylesheet">

    <!--[if lt IE 9]>
      <script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
      <script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="navbar navbar-default navbar-fixed-top " role="navigation">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Cerisaie.com</a>
        </div>
        <div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="index.htm">Accueil</a></li>
				<li><a href="Clients.htm">Clients</a></li>
				<li><a href="Sejour.htm">Séjours</a></li>
			</ul>
			<form class="navbar-form navbar-right" role="search" method="get" action="GestRecherche.htm"> 
				<label for="q" class="sr-only">Recherche :</label>       
	        	<input id="q" class="rech" type="text" autocomplete="off" name="q" placeholder="Rechercher" on/>
	        	<label for="selectionne" class="sr-only">rien :</label> 
	        	<input id="selectionne" type="hidden" name="selectionne" value="1" size="2"/>
	        	<input type="submit" style="display: none;" value="valider"/>
        	</form>
        	<div id="results" class="results">
        	</div>
		</div><!--/.nav-collapse -->
	</div>
	</div>