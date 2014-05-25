function getRequeteHttp()
{
	var requeteHttp;
	if (window.XMLHttpRequest) {	// Mozilla
		requeteHttp = new XMLHttpRequest();
        if (requeteHttp.overrideMimeType) { // problËme firefox
        	requeteHttp.overrideMimeType('text/xml');
        }
	} else if (window.ActiveXObject) {	// C'est Internet explorer < IE7
		try {
			requeteHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {
			try {
				requeteHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e) {
				requeteHttp = null;
			}
		}
	}
	return requeteHttp;
}

function DemandeRecherche(dateDebut, dateFin)
{
	var requeteHttp = getRequeteHttp();
	if (requeteHttp == null) {
		alert("Impossible d'utiliser Ajax sur ce navigateur");
	} else {
		requeteHttp.open('GET', 'Periode.htm?dateDebut=' + dateDebut + '&dateFin=' + dateFin, true);
        requeteHttp.onreadystatechange = function() {
        	ReceptionRecherche(requeteHttp);
        };
        requeteHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        requeteHttp.send(null);
	}
	return;
}

function ReceptionRecherche(requete)
{
   if (requete.readyState == 4) {
      if (requete.status == 200) {
    	  document.getElementById("tablePeriode").innerHTML = requete.responseText;
      } else {
         alert("La requête ne s'est pas correctement exécutée");
      }
   }
}

$(function (){ 
	   $("[data-toggle='popover']").popover();
});

function verifUnitaire(nom){
	var erreur = false;
	if (document.getElementById(nom).value == "") {
		// rajouter le has-error au div correspondant
		$('#' + nom + '-group').removeClass('has-success');
		$('#' + nom + '-group').addClass('has-error');
		erreur = true;
	} else {
		$('#' + nom + '-group').removeClass('has-error');
		$('#' + nom + '-group').addClass('has-success');
	}
	return !erreur;
}

function verifDate(nom) {
	var regexDate = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
	var erreur = false;
	if (!regexDate.test(document.getElementById(nom).value)) {
		// rajouter le has-error au div correspondant
		$('#' + nom + '-group').removeClass('has-success');
		$('#' + nom + '-group').addClass('has-error');
		erreur = true;
	} else {
		$('#' + nom + '-group').removeClass('has-error');
		$('#' + nom + '-group').addClass('has-success');
	}
	return !erreur;
}