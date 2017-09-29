package org.sid.web;

import java.util.Date;
import java.util.List;

import org.sid.entities.Abonnement;
import org.sid.entities.AbonnementGSM;
import org.sid.entities.AbonnementInternet;
import org.sid.metier.IAbonnementMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class AbonnementAction extends ActionSupport {
	@Autowired
	private IAbonnementMetier metier;

	public Long idAbonnement;
	public Date dateAbonnement;
	public double solde;
	public boolean actif;
	public String typeRadio;
	public int fidelio;
	public int debit;
	public String[] typesAb = new String[] { "GSM", "INTERNET" };
	public List<Abonnement> abonnements;

	public String index() {
		abonnements = metier.listAbonnements(true);
		return SUCCESS;
	}

	public String getTypeAb() {
		return SUCCESS;
	}

	public String save() {
		Abonnement ab;
		if (typeRadio.equals("GSM")) {
			ab = new AbonnementGSM(dateAbonnement, solde, actif, fidelio);
			emptyFields();
		} else {
			ab = new AbonnementInternet(dateAbonnement, solde, actif, debit);
			emptyFields();
		}
		metier.addAbonnement(ab);
		abonnements = metier.listAbonnements(true);
		return SUCCESS;
	}

	public void emptyFields() {
		fidelio = 0;
		debit = 0;
		typeRadio = "";
		solde = 0.0;
		dateAbonnement = null;
		actif = false;

	}
}
