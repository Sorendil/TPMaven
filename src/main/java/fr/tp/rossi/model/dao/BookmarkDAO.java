package fr.tp.rossi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	public boolean deleteAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		List<MBookmark> list = this.findAll();
		em.getTransaction().begin();
		for(MBookmark bookmark : list)
		{
			em.remove(em.merge(bookmark));
		}
		em.flush();
		em.getTransaction().commit();
		
		return true;
	}

	public MBookmark findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		MBookmark bookmark = em.find(MBookmark.class, id);
		em.close();
		emf.close();
		
		return bookmark;
	}
}
