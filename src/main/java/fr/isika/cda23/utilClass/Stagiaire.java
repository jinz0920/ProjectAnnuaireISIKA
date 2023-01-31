package fr.isika.cda23.utilClass;

import java.util.ArrayList;
import java.util.Objects;

public class Stagiaire {
	private String nom;
	private String prenom;
	private String departement;
	private String formation;
	private String anneePromo;
	private Stagiaire doublon;

	public Stagiaire(String nom, String prenom, String departement, String formation, String anneePromo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneePromo = anneePromo;
		this.doublon = null;
	}

	// Constructeur vide:
	public Stagiaire() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getAnneePromo() {
		return anneePromo;
	}

	public void setAnneePromo(String anneePromo) {
		this.anneePromo = anneePromo;
	}

	public Stagiaire getDoublon() {
		return doublon;
	}

	public void setDoublon(Stagiaire doublon) {
		this.doublon = doublon;
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", formation="
				+ formation + ", anneePromo=" + anneePromo + ", doublon=" + doublon + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(anneePromo, departement, formation, nom, prenom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		return Objects.equals(anneePromo, other.anneePromo) && Objects.equals(departement, other.departement)
				&& Objects.equals(formation, other.formation) && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom);
	}

// 	méthode recursive pour ajouter un doublon qui est associé à l'arbre
	public void ajouterDoublon(Stagiaire stagiaire) {
		if (this.doublon == null)
			this.doublon = stagiaire;
		else
			this.doublon.ajouterDoublon(stagiaire);
	}

// 	méthode recursive pour ajouter tous les stagiaires dans une list depuis
// 	l'arbre;

	public void addList(ArrayList<Stagiaire> list) {
		if (this.doublon == null) {
			list.add(this);
		} else {
			list.add(this);
			this.doublon.addList(list);
		}
	}

// 	méthode recursive pour ajouter tous les stagiaires qui contiennet le meme ou
// 	une partie de leurs prenom
//	y compris les doublons dans une list depuis l'arbre;
	public void addListPrenom(ArrayList<Stagiaire> list, String prenom) {
		String prenomSansAccent = Utility.removeAccents(this.getPrenom().toLowerCase().replaceAll(Utility.regExp, ""));
		if (prenomSansAccent.contains(prenom)) {
			list.add(this);
		}
		if (this.doublon != null) {
			this.doublon.addListPrenom(list, prenom);
		}
	}

// 	méthode recursive pour ajouter tous les stagiaires qui ont le meme
// 	departement
//	y compris les doublons dans une list depuis l'arbre;
	public void addListDepartement(ArrayList<Stagiaire> list, String departement) {
		if (this.getDepartement().equals(departement)) {
			list.add(this);
		}
		if (this.doublon != null) {
			this.doublon.addListDepartement(list, departement);
		}
	}

// 	méthode recursive pour ajouter tous les stagiaires qui contiennet la meme ou
// 	une partie de leurs formation
//	y compris les doublons dans une list depuis l'arbre;
	public void addListFormation(ArrayList<Stagiaire> list, String formation) {
		if (this.getFormation().toLowerCase().replaceAll(" ", "").contains(formation)) {
			list.add(this);
		}
		if (this.doublon != null) {
			this.doublon.addListFormation(list, formation);
		}
	}

// 	méthode recursive pour ajouter tous les stagiaires qui ont le meme
// 	departement
//	y compris les doublons dans une list depuis l'arbre;
	public void addListAnneePromo(ArrayList<Stagiaire> list, String anneePromo) {
		if (this.getAnneePromo().equals(anneePromo)) {
			list.add(this);
		}
		if (this.doublon != null) {
			this.doublon.addListAnneePromo(list, anneePromo);
		}
	}

//  supprimer doublon
	public void supprimerDoublon(Stagiaire stagiaire) {
		if (this.getDoublon().getPrenom().compareTo(stagiaire.getPrenom()) == 0
				&& this.getDoublon().getFormation().compareTo(stagiaire.getFormation()) == 0) {
			this.setDoublon(this.doublon.getDoublon());
		} else {
			this.doublon.supprimerDoublon(stagiaire);
		}

	}
}
