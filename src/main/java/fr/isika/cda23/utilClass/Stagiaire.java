package fr.isika.cda23.utilClass;

public class Stagiaire {
	String nom;
	String prenom;
	String departement;
	String formation;
	String anneePromo;
	public Stagiaire(String nom, String prenom, String departement, String formation, String anneePromo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.anneePromo = anneePromo;
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
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", formation="
				+ formation + ", anneePromo=" + anneePromo + "]";
	}
}
