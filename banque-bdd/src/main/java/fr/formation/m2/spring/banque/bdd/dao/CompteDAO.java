package fr.formation.m2.spring.banque.bdd.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.m2.spring.banque.bdd.entities.Client;
import fr.formation.m2.spring.banque.bdd.entities.Compte;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public interface CompteDAO {
	
	@Transactional(propagation=Propagation.REQUIRED , rollbackFor=BanqueException.class)
	public abstract void addCompte (Compte compte) throws BanqueException;

	@Transactional(propagation=Propagation.REQUIRED)
	public abstract Compte getCompteByNumero (Long numero) throws BanqueException;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public abstract List<Compte> getAllComptesByClient(Client client) throws BanqueException;
	
	@Transactional(propagation=Propagation.REQUIRED , rollbackFor=BanqueException.class)
	public abstract void updateCompte(Compte compte) throws BanqueException;

}
