package fr.formation.m2.spring.banque.bdd.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import fr.formation.m2.spring.banque.bdd.entities.Client;
import fr.formation.m2.spring.banque.bdd.util.BanqueException;

public class HibernateClientDAO implements ClientDAO {
	
	private SessionFactory sessionFactory;


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addClient(Client client) throws BanqueException {
		try {
			 sessionFactory.getCurrentSession().persist(client);
		 } catch (HibernateException e) {
			 e.printStackTrace();
			 throw new BanqueException("Erreur d'ajout du client");
		 }
	}

	public Client getClientById(long id) throws BanqueException {
		try {
			 return (Client)sessionFactory.getCurrentSession().get(Client.class, id);
		 } catch (HibernateException e) {
			 throw new BanqueException("Erreur de recherche du client");
		 }
	}

	public List<Client> getAllClients() throws BanqueException {
		try {
			String hql = "from Client as c"; 
			return  sessionFactory.getCurrentSession().createQuery(hql).list();
		 } catch (HibernateException e) {
			 throw new BanqueException("Erreur recherche des clients");
		 }
	}

}
