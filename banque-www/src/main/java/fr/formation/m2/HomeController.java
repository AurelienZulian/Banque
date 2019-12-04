package fr.formation.m2;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.m2.spring.banque.bdd.dao.ClientDAO;
import fr.formation.m2.spring.banque.bdd.entities.Client;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	private static ApplicationContext contexte;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws BanqueException {
		contexte = new ClassPathXmlApplicationContext("bdd-context.xml");
		ClientDAO clientDAO = (ClientDAO)contexte.getBean("clientDAO");
		List<Client> liste = clientDAO.getAllClients();
		model.addAttribute("liste",liste);
		
		return "home";
	}
	
	@RequestMapping(value="/admin**", method=RequestMethod.GET)
	public String adminPage(Model model)
	{
		model.addAttribute("message", "Page d'administration");
		return "admin";
	}
	
	@RequestMapping(value = "/logout")
    public ModelAndView newLogOut(HttpServletRequest req , HttpServletResponse rep) {
        logger.info("Welcome at logout");
        return new ModelAndView("logout");
    }    

}
