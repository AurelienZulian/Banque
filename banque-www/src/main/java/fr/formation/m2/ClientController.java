package fr.formation.m2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import fr.formation.m2.spring.banque.bdd.dao.ClientDAO;
import fr.formation.m2.spring.banque.bdd.dao.CompteDAO;
import fr.formation.m2.spring.banque.bdd.entities.Client;
import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

//Il manque la partie Virement entre comptes

@Controller
public class ClientController {
	
	private static ApplicationContext contexte;
	
	@ModelAttribute("clientParDefaut")
	 public Client clientParDefaut() 
	    {
	        Client customer = new Client();
	        customer.setPrenom("Aurélien");
	        customer.setNom("Zulian");
	        customer.setAdresse("test");
	        customer.setCodepostal("31820");
	        customer.setVille("Pibrac");
	        customer.setMotdepasse("xxxxxx");
	        return customer;
	    }
	
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String viewclient(@RequestParam("Id") long Id, Model model) throws BanqueException 
	{
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		ClientDAO clientDAO = (ClientDAO)contexte.getBean("clientDAO");
		CompteDAO compte = (CompteDAO)contexte.getBean("compteDAO");
		Client c = clientDAO.getClientById(Id);
	    List<Compte> liste = compte.getAllComptesByClient(c);
	    
	    model.addAttribute("liste", liste);
	    model.addAttribute("client", c);
	    
		return "client";
	}
	
	@RequestMapping(value = "/addUser")
	public String addclient() 
	{
		return "addUser";
	}
	
	@RequestMapping(value ="/successaddclient")
    public RedirectView successaddclient(@ModelAttribute(value="clientParDefaut") final Client c) throws BanqueException
    {
        contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
        ClientDAO clientDAO = (ClientDAO)contexte.getBean("clientDAO");
        clientDAO.addClient(c);
        return new RedirectView("/web/");
    }
	
	@RequestMapping(value = "/successaddcompte")
    public RedirectView succesaddcompte(@RequestParam("Id") long Id,@ModelAttribute(value="compte") final Compte compte) throws BanqueException{
        try {
        contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
        ClientDAO clientDAO = (ClientDAO)contexte.getBean("clientDAO");
        CompteDAO compteDAO = (CompteDAO)contexte.getBean("compteDAO");
        compte.setClient(clientDAO.getClientById(Id));
        compteDAO.addCompte(compte);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return new RedirectView("/web/");
    }
	
	@RequestMapping(value = "/addCompte")
    public String addCompte(@RequestParam("Id") long Id, Model model) throws BanqueException 
    {
        contexte= new ClassPathXmlApplicationContext("bdd-context.xml");
        ClientDAO clientDao = (ClientDAO)contexte.getBean("clientDAO");

        Client c = clientDao.getClientById(Id);

        model.addAttribute("client", c);
        model.addAttribute("compte", new Compte());

        return "compte_add";
    }
	
	@RequestMapping(value = "/addCompteSuccess")
	public RedirectView addCompteSuccess(@RequestParam("Id") long Id,@ModelAttribute(value="compte") final Compte compte) throws BanqueException
	{
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		ClientDAO clientDAO = (ClientDAO)contexte.getBean("clientDAO");
		CompteDAO compteDAO = (CompteDAO)contexte.getBean("compteDAO");
		compte.setClient(clientDAO.getClientById(Id));
		compteDAO.addCompte(compte);
		
		return new RedirectView("/web");
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String submit(@Validated @ModelAttribute("client")Client client,
			BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			return "error";
		}
		model.addAttribute("nom", client.getNom());
		model.addAttribute("prenom", client.getPrenom());
		model.addAttribute("adresse", client.getAdresse());
		model.addAttribute("codepostal", client.getCodepostal());
		model.addAttribute("ville", client.getVille());
//		model.addAttribute("motdepasse", client.getNom());
		return "client";
	}
}
