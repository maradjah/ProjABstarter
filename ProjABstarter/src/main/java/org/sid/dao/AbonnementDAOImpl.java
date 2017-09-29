package org.sid.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.sid.entities.Abonnement;

public class AbonnementDAOImpl implements IAbonnementDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addAbonnement(Abonnement ab) {
		entityManager.persist(ab);

	}

	@Override
	public List<Abonnement> listAbonnements(boolean actif) {
		Query query = entityManager
				.createQuery("select ab from Abonnement ab where ab.actif=:x");
		query.setParameter("x", actif);
		return query.getResultList();
	}

	@Override
	public List<Abonnement> listAbonnements(Date d1, Date d2) {
		Query query = entityManager
				.createQuery("select ab from Abonnement ab where ab.dateAbonnement between :x and :y");
		query.setParameter("x", d1);
		query.setParameter("y", d2);
		return query.getResultList();
	}

	@Override
	public Abonnement getAbonnement(Long idAb) {
		// TODO Auto-generated method stub
		return entityManager.find(Abonnement.class, idAb);
	}

	@Override
	public void deleteAbonnement(Long idAb) {
		Abonnement ab = getAbonnement(idAb);
		entityManager.remove(ab);

	}

	@Override
	public void updateAbonnement(Abonnement ab) {
		entityManager.merge(ab);

	}

	@Override
	public void consommer(Long idAb, double montant) {
		Abonnement ab = getAbonnement(idAb);
		if (ab.getSolde() >= montant)
			ab.setSolde(ab.getSolde() - montant);
	}

}
