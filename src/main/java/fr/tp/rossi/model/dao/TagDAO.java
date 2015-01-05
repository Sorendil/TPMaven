package fr.tp.rossi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.tp.rossi.model.MBookmark;
import fr.tp.rossi.model.MTag;

public class TagDAO {

	public List<MTag> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		List<MTag> list = em.createQuery("from MTag").getResultList();
		em.close();
		emf.close();
		
		return list;
	}

}
