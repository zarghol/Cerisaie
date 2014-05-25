package kdo.de.noel;

import java.util.List;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kdo.de.meserreurs.MonException;
import kdo.de.utilitaires.JBossContext;
import model.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import traitements.GestionCerisaie;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends MultiActionController{
	
	

	@RequestMapping(value = "index.htm")
	public ModelAndView afficheindex(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("Accueil");
	}

	@RequestMapping(value = "Clients.htm")
	public ModelAndView clients(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<Client> mesClients = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt.lookup("BeanCerisaie");
		try {
			mesClients = uneCerisaie.listerTousLesClients();
			request.setAttribute("MesClients", mesClients);
			destinationPage = "Clients";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs", e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesClients", mesClients);
	}

	@RequestMapping(value = "Sejour.htm")
	public ModelAndView sejours(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<Sejour> mesSejours = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt.lookup("BeanCerisaie");
		try {
			mesSejours = uneCerisaie.listerTousLesSejours();
			request.setAttribute("MesSejours", mesSejours);
			destinationPage = "Sejours";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs", e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesSejour", mesSejours);
	}
	
	@RequestMapping(value = "PrestationsSportives.htm")
	public ModelAndView activitesSportives(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<ActiSport> mesPrestSport = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt.lookup("BeanCerisaie");
		try {
			mesPrestSport = uneCerisaie.listerTousLesActivitesSportives();
			request.setAttribute("MesPrestSport", mesPrestSport);
			destinationPage = "PrestationsSportives";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs", e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesActiSport", mesPrestSport);
	}
	
	@RequestMapping(value = "ActivitesSportivesSejour.htm")
	public ModelAndView activitesSportivesDunSejour(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<ActiSport> mesActiSportSej = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt.lookup("BeanCerisaie");
		try {
			mesActiSportSej = uneCerisaie.listerTousLesClients();
			request.setAttribute("MesActiSportSej", mesActiSportSej);
			destinationPage = "ActivitesSportivesSejour";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs", e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesActiSportSej", mesActiSportSej);
	}

	
	@RequestMapping(value = "AjouterClient.htm")
	public ModelAndView ajouterClient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage = "";
		Client unclient;
		try {
			unclient = new Client();
			request.setAttribute("client", unclient);
			destinationPage = "AjouterClient";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());

			destinationPage = "Erreur";
		}
		return new ModelAndView(destinationPage);

	}
	
	@RequestMapping(value = "supprimerClient.htm")
	public ModelAndView supprimerClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "index";
		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie unclientRemote = (GestionCerisaie) ctxt.lookup("BeanCommercial");
		try {
            String id = request.getParameter("id");
			if (! id.equals("")){
				unclientRemote.supprimerClient(id);
			}
			destinationPage = "index";
		} catch (MesException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs", e.getType() + " : " + e.getMessage());

		}
		return new ModelAndView(destinationPage);
	}
	
	@RequestMapping(value = "sauverClient.htm")
	public ModelAndView sauverClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destinationPage = "Accueil";
		Client unClient = new Client();
		try {
			Context ctxt = JBossContext.getInitialContext();
			GestionCerisaie unclientRemote = (GestionCerisaie) ctxt.lookup("BeanCommercial");
			// On rÃ©cupÃ¨re les donnÃ©es du formulaire
			 unClient.setNoClient(request.getParameter("noclient"));
		     unClient.setNomCl(request.getParameter("nomclient"));
		     unClient.setPrenomCl(request.getParameter("prenomclient"));
		     unClient.setSociete(request.getParameter("societe"));
		     unClient.setAdresseCl(request.getParameter("adresse"));
		     unClient.setCodePostCl(request.getParameter("cpostal"));
		     unClient.setVilleCl(request.getParameter("ville"));
		     System.out.println(unClient.getNomCl());
			unclientRemote.ajouter(unClient);
			//request.setAttribute("mesclients", unclientRemote.listerTousLesClients());
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs", e.getCause() + " : " + e.getMessage());

		}
		return new ModelAndView(destinationPage);

	}
	
}
