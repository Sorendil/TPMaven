package fr.tp.rossi.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.tp.rossi.common.PersistentObjectImpl;

public class CommonDAO {

	public PersistentObjectImpl save(final PersistentObjectImpl newInstance) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(newInstance);
		em.flush();
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return newInstance;
	}

}
