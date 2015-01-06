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

	public MTag findById(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		MTag tag = em.find(MTag.class, id);
		em.close();
		emf.close();
		
		return tag;
	}

	/** Récupère le tag qui a pour nom name
	 * Ceci est une méthode temporaire qui n'est pas du tout optimale
	 * Mais je n'ai pas réussi à faire la requête via Hibernate directement
	 * TODO : Faire la requête via Hibernate
	 */
	public MTag findByName(String name) {
		List<MTag> list = this.findAll();
		
		for(MTag tag : list)
			if (tag.getName().equals(name))
				return tag;
		return null;
	}
}
