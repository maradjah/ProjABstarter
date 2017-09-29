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

	public Long refDelete;
	public Long refUpdate;
	public Long idAbonnement;
	public Date dateAbonnement;
	public double solde;
	public boolean actif;
	public String typeRadio;
	public int fidelio;
	public int debit;
	public boolean editMode = false;
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
		/* mode Update */

		if (editMode == true) {
			Abonnement ab = metier.getAbonnement(idAbonnement);
			ab.setDateAbonnement(dateAbonnement);
			ab.setSolde(solde);
			ab.setActif(actif);
			switch (typeRadio) {
			case "GSM":
				try {
					((AbonnementGSM) ab).setFidelio(fidelio);
					emptyFields();
					metier.updateAbonnement(ab);

				} catch (Exception e) {
					editMode = false;
					e.printStackTrace();
					emptyFields();
					abonnements = metier.listAbonnements(true);
					return SUCCESS;
				}
				break;
			case "INTERNET":
				try {
					((AbonnementInternet) ab).setDebit(debit);
					emptyFields();
					metier.updateAbonnement(ab);
				} catch (Exception e) {
					editMode = false;
					e.printStackTrace();
					emptyFields();
					abonnements = metier.listAbonnements(true);
					return SUCCESS;
				}
				break;
			default:
				break;
			}
			/* mode Add */
		} else {
			switch (typeRadio) {
			case "GSM":
				Abonnement abg = new AbonnementGSM(dateAbonnement, solde,
						actif, fidelio);
				metier.addAbonnement(abg);
				emptyFields();
				break;
			case "INTERNET":
				Abonnement abi = new AbonnementInternet(dateAbonnement, solde,
						actif, debit);
				metier.addAbonnement(abi);
				emptyFields();
				break;
			default:
				break;
			}
		}
		abonnements = metier.listAbonnements(true);
		return SUCCESS;
	}

	public String delete() {
		metier.deleteAbonnement(refDelete);
		emptyFields();
		abonnements = metier.listAbonnements(true);
		return SUCCESS;
	}

	public String update() {
		editMode = true;
		Abonnement ab = metier.getAbonnement(refUpdate);
		idAbonnement = refUpdate;
		dateAbonnement = ab.getDateAbonnement();
		solde = ab.getSolde();
		actif = ab.isActif();
		if (ab instanceof AbonnementGSM) {
			fidelio = ((AbonnementGSM) ab).getFidelio();
			typeRadio = "GSM";
		} else {
			debit = ((AbonnementInternet) ab).getDebit();
			typeRadio = "INTERNET";
		}
		abonnements = metier.listAbonnements(true);
		return SUCCESS;

	}

	public void emptyFields() {
		fidelio = 0;
		debit = 0;
		typeRadio = "";
		solde = 0.0;
		dateAbonnement = null;
		actif = true;

	}
}
