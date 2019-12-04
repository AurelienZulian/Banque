package fr.formation.m2.spring.banque.metier;

import java.util.List;

import fr.formation.m2.spring.banque.bdd.dao.ClientDAO;
import fr.formation.m2.spring.banque.bdd.dao.CompteDAO;
import fr.formation.m2.spring.banque.bdd.entities.Client;
import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;



public class BanqueServiceImpl implements BanqueService{
	
	private ClientDAO clientDAO;
	private CompteDAO compteDAO;
	
	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void setCompteDAO(CompteDAO compteDAO) {
		this.compteDAO = compteDAO;
	}

	

	public List<Compte> mesComptes(long idClient) throws BanqueException {
		try {
			Client client = clientDAO.getClientById(idClient);
			return compteDAO.getAllComptesByClient(client);
			} catch(Exception e) {
				e.printStackTrace();
				throw new BanqueException("Erreur lors de la récupération de vos comptes");
			}
	}

	public void virement(long numeroDebiter, long numeroACrediter, double montant) throws BanqueException {
		try {
			Compte CompteADebiter = compteDAO.getCompteByNumero(numeroDebiter);
			Compte CompteACrediter = compteDAO.getCompteByNumero(numeroACrediter);
			
			CompteADebiter.setSolde((Double)(CompteADebiter.getSolde() - montant));
			CompteACrediter.setSolde(CompteACrediter.getSolde() + montant);
			
			compteDAO.updateCompte(CompteADebiter);
			compteDAO.updateCompte(CompteACrediter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BanqueException("Erreur lors de l'enregistrement de votre virement");
		}
		
	}
	
	// @Override
	public Client authentifier(String identifiant, String motDePasse) throws BanqueException {
		try {
			Client client = clientDAO.getClientById(Long.parseLong(identifiant));
			if(client != null && client.getMotdepasse().equals(motDePasse)) {
				return client;
			} else {
				throw new BanqueException();
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new BanqueException("erreur d'authentification");
		}
	}

	
}
