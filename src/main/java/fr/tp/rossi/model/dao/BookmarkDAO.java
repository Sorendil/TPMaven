package fr.tp.rossi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.tp.rossi.model.MBookmark;

public class BookmarkDAO {

	public List<MBookmark> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		List<MBookmark> list = em.createQuery("from MBookmark").getResultList();
		em.close();
		emf.close();
		
		return list;
	}

}
