package kdo.de.noel;

import java.util.List;

import model.*;
import traitements.*;
import meserreurs.*;

import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
/**
 * 	
 * @author Black
 *	
 */
public class HomeController extends MultiActionController {
	
	/**
	 * Affiche la page d'accueil.
	 * 
	 * @param request
	 *            message d'entrée
	 * @param response
	 *            Message de sortie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index.htm")
	public ModelAndView afficheindex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("Accueil");
	}

	/**
	 * Affiche la page des clients
	 * 
	 * @param request
	 *            Message d'entrée
	 * @param response
	 *            Message de sortie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "Clients.htm")
	public ModelAndView clients(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<Client> mesClients = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt
				.lookup("BeanCerisaie");
		try {
			mesClients = uneCerisaie.listerClients();
			request.setAttribute("MesClients", mesClients);
			destinationPage = "Clients";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesClients", mesClients);
	}

	/**
	 * Affiche la page des séjours.
	 * 
	 * @param request
	 *            Message d'entrée
	 * @param response
	 *            Message de sortie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "Sejours.htm")
	public ModelAndView sejours(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<Sejour> mesSejours = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt
				.lookup("BeanCerisaie");
		try {
			mesSejours = uneCerisaie.listerSejours();
			request.setAttribute("MesSejours", mesSejours);
			destinationPage = "Sejours";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesSejour", mesSejours);
	}

	/**
	 * Affiche les préstations sportives.
	 * 
	 * @param request
	 *            Message d'entrée
	 * @param response
	 *            Message de sortie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "PrestationsSportives.htm")
	public ModelAndView activitesSportives(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<Activite> mesPrestSport = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt
				.lookup("BeanCerisaie");
		try {
			mesPrestSport = uneCerisaie.listerActivitesSportives();
			request.setAttribute("MesPrestSport", mesPrestSport);
			destinationPage = "PrestationsSportives";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesActiSport", mesPrestSport);
	}

	/**
	 * Affiche la page des activités sportives d'un séjour.
	 * 
	 * @param request
	 *            Message d'entrée
	 * @param response
	 *            Message de sortie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "ActivitesSportivesSejour.htm")
	public ModelAndView activitesSportivesDunSejour(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String destinationPage = "";
		List<Activite> mesActiSportSej = null;

		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie uneCerisaie = (GestionCerisaie) ctxt
				.lookup("BeanCerisaie");
		try {
			mesActiSportSej = uneCerisaie.listerActivitesSportives();
			request.setAttribute("MesActiSportSej", mesActiSportSej);
			destinationPage = "ActivitesSportivesSejour";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getCause() + " : " + e.getMessage());
		}
		return new ModelAndView(destinationPage, "mesActiSportSej",
				mesActiSportSej);
	}

	/**
	 * Affiche la page permettant d'ajouter un client.
	 * 
	 * @param request
	 *            Message d'entrée
	 * @param response
	 *            Message de sortie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "AjouterClient.htm")
	public ModelAndView ajouterClient(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String destinationPage;
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

	/**
	 * Affiche la page de
	 * 
	 * @param request
	 *            Message d'entrée
	 * @param response
	 *            Message de sortie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "supprimerClient.htm")
	public ModelAndView supprimerClient(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String destinationPage = "index";
		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie unclientRemote = (GestionCerisaie) ctxt
				.lookup("BeanCommercial");
		try {
			String id = request.getParameter("id");
			if (!id.equals("")) {
				unclientRemote.supprimerClient(id);
			}
			destinationPage = "index";
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getType() + " : " + e.getMessage());

		}
		return new ModelAndView(destinationPage);
	}

	/**
	 *  Permet de modifier un client
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sauverClient.htm")
	public ModelAndView sauverClient(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String destinationPage = "Accueil";
		Client unClient = new Client();
		try {
			Context ctxt = JBossContext.getInitialContext();
			GestionCerisaie unclientRemote = (GestionCerisaie) ctxt
					.lookup("BeanCommercial");
			// On rÃƒÂ©cupÃƒÂ¨re les donnÃƒÂ©es du formulaire
			unClient.setNoClient(request.getParameter("noclient"));
			unClient.setNomCl(request.getParameter("nomclient"));
			unClient.setPrenomCl(request.getParameter("prenomclient"));
			unClient.setSociete(request.getParameter("societe"));
			unClient.setAdresseCl(request.getParameter("adresse"));
			unClient.setCodePostCl(request.getParameter("cpostal"));
			unClient.setVilleCl(request.getParameter("ville"));
			System.out.println(unClient.getNomCl());
			unclientRemote.ajouter(unClient);
			// request.setAttribute("mesclients",
			// unclientRemote.listerTousLesClients());
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getCause() + " : " + e.getMessage());

		}
		return new ModelAndView(destinationPage);

	}

	@RequestMapping(value = "AjouterSejour.htm")
	public ModelAndView ajouterSejour(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String destinationPage = "";
		Sejour unSejour;
		try {
			unSejour = new Sejour();
			request.setAttribute("sejour", unSejour);
			destinationPage = "AjouterSejour";
		} catch (Exception e) {
			request.setAttribute("MesErreurs", e.getMessage());

			destinationPage = "Erreur";
		}
		return new ModelAndView(destinationPage);

	}

	@RequestMapping(value = "supprimerSejour.htm")
	public ModelAndView supprimerSejour(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String destinationPage = "index";
		// On appelle notre EJB
		Context ctxt = JBossContext.getInitialContext();
		GestionCerisaie unSejourRemote = (GestionCerisaie) ctxt
				.lookup("BeanCommercial");
		try {
			String id = request.getParameter("id");
			if (!id.equals("")) {
				unSejourRemote.supprimerSejour(id);
			}
			destinationPage = "index";
		} catch (MesException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getType() + " : " + e.getMessage());

		}
		return new ModelAndView(destinationPage);
	}

	@RequestMapping(value = "sauverSejour.htm")
	public ModelAndView sauverSejour(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String destinationPage = "Accueil";
		Sejour unSejour = new Sejour();
		try {
			Context ctxt = JBossContext.getInitialContext();
			GestionCerisaie unclientRemote = (GestionCerisaie) ctxt
					.lookup("BeanCommercial");
			// On rÃƒÂ©cupÃƒÂ¨re les donnÃƒÂ©es du formulaire
			unSejour.setNumSej(request.getParameter("numSejour"));
			unSejour.setNumCl(request.getParameter("numclient"));
			unSejour.setNumEmpl(request.getParameter("prenomclient"));
			unSejour.setDateDebSej(request.getParameter("societe"));
			unSejour.setDateFinSej(request.getParameter("adresse"));
			unSejour.setNbPersonnes(request.getParameter("cpostal"));
			System.out.println(unSejour.getNomCl());
			unclientRemote.ajouter(unSejour);
			// request.setAttribute("mesclients",
			// unclientRemote.listerTousLesClients());
		} catch (MonException e) {
			destinationPage = "Erreur";
			request.setAttribute("MesErreurs",
					e.getCause() + " : " + e.getMessage());

		}
		return new ModelAndView(destinationPage);

	}

	@RequestMapping(value = "factureSejour.htm")
	public ModelAndView sauverSejour(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	}

	@RequestMapping(value = "factureActivites.htm")
	public ModelAndView sauverSejour(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
	}


}
