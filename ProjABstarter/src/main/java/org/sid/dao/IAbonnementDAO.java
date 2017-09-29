package org.sid.dao;

import java.util.Date;
import java.util.List;

import org.sid.entities.Abonnement;

public interface IAbonnementDAO {

	public void addAbonnement(Abonnement ab);

	public List<Abonnement> listAbonnements(boolean actif);

	public List<Abonnement> listAbonnements(Date d1, Date d2);

	public Abonnement getAbonnement(Long idAb);

	public void deleteAbonnement(Long ab);
	
	public void updateAbonnement(Abonnement ab);

	public void consommer(Long idAb, double montant);

}
