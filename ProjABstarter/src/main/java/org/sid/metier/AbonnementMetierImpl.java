package org.sid.metier;

import java.util.Date;
import java.util.List;

import org.sid.dao.IAbonnementDAO;
import org.sid.entities.Abonnement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AbonnementMetierImpl implements IAbonnementMetier {

	private IAbonnementDAO dao;

	public IAbonnementDAO getDao() {
		return dao;
	}

	public void setDao(IAbonnementDAO dao) {
		this.dao = dao;
	}

	@Override
	public void addAbonnement(Abonnement ab) {
		dao.addAbonnement(ab);

	}

	@Override
	public List<Abonnement> listAbonnements(boolean actif) {
		return dao.listAbonnements(actif);
	}

	@Override
	public List<Abonnement> listAbonnements(Date d1, Date d2) {
		// TODO Auto-generated method stub
		return dao.listAbonnements(d1, d2);
	}

	@Override
	public Abonnement getAbonnement(Long idAb) {
		// TODO Auto-generated method stub
		return dao.getAbonnement(idAb);
	}

	@Override
	public void deleteAbonnement(Long ab) {
		dao.deleteAbonnement(ab);

	}

	@Override
	public void updateAbonnement(Abonnement ab) {
		dao.updateAbonnement(ab);
	}

	@Override
	public void consommer(Long idAb, double montant) {
		dao.consommer(idAb, montant);

	}

}
