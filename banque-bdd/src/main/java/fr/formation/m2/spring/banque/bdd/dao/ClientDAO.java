package fr.formation.m2.spring.banque.bdd.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.m2.spring.banque.bdd.entities.Client;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public interface ClientDAO {

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=BanqueException.class)
	public abstract void addClient (Client client) throws BanqueException;

	@Transactional(propagation=Propagation.REQUIRED)
	public abstract Client getClientById (long id) throws BanqueException;

	@Transactional(propagation=Propagation.REQUIRED)
	public abstract List<Client> getAllClients() throws BanqueException;
}
