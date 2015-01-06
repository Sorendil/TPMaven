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

	public PersistentObjectImpl update(final PersistentObjectImpl updatedInstance) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(updatedInstance);
		em.flush();
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return updatedInstance;
	}

	
	public PersistentObjectImpl delete(final PersistentObjectImpl instanceToDelete) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(instanceToDelete));
		//em.remove(instanceToDelete);
		em.flush();
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return instanceToDelete;
	}

}
