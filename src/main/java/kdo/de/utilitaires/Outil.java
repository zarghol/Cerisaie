package kdo.de.utilitaires;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Outil {
	public static Date conversionChaineenDate(String unedate, String unformat) 
	// le format est une combinaison de MM dd yyyy avec / ou – 
	// exemple  dd/MM/yyyy
	     throws Exception 
	 {
	     Date datesortie;
	   // on définit un format de sortie  
	   SimpleDateFormat defFormat = new SimpleDateFormat(unformat);
	   datesortie = defFormat.parse(unedate);
	   return datesortie;
	}
}
