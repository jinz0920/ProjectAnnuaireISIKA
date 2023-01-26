package fr.isika.cda23.utilClass;

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

	// rajout des hash et equal
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

// méthode recursive pour ajouter un doublon
	public void ajouterDoublon(Stagiaire stagiaire) {
		if (this.doublon == null)
			this.doublon = stagiaire;
		else
			this.doublon.ajouterDoublon(stagiaire);
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", formation="
				+ formation + ", anneePromo=" + anneePromo + ", doublon=" + doublon + "]";
	}

}
