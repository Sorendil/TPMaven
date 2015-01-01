package fr.tp.rossi.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

import fr.tp.rossi.model.MBookmark;
import fr.tp.rossi.service.IServiceBookmark;

@Service
public class ServiceBookmark implements IServiceBookmark {

	public MBookmark save(final MBookmark newInstance) {
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

	public List<MBookmark> getAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		List<MBookmark> list = em.createQuery("from MBookmark").getResultList();
		em.close();
		emf.close();
		
		return list;
	}

}
