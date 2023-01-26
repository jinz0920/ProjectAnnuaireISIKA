package fr.isika.cda23.utilClass;

public class Noeud {

	private Stagiaire cle;
	private Noeud filsGauche;
	private Noeud filsDroit;

	public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit) {
		super();
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	public Noeud(Stagiaire cle) {
		this.cle = cle;
		this.filsGauche = null;
		this.filsDroit = null;
	}

	@Override
	public String toString() {
		return "Noeud [cle=" + cle + "]";
	}

//Ajouter un stagiaire
	public void ajouter(Stagiaire stagiaire) {
		int comparaison = stagiaire.getNom().compareTo(this.cle.getNom());
		if (comparaison < 0) {
			// le nom à ajouter est plus petit on va à gauche
			if (this.filsGauche == null) {
				this.filsGauche = new Noeud(stagiaire);
			} else {
				this.filsGauche.ajouter(stagiaire);
			}
//			le nom à ajouter est égal au nom de la cle, on regarde si la possède un doublon(qui a le meme nom);
		} else if (comparaison == 0 && this.cle.getDoublon() == null) {
			this.cle.setDoublon(stagiaire);
		} else if (comparaison == 0 && this.cle.getDoublon() != null) {
			this.cle.getDoublon().ajouterDoublon(stagiaire);
		} else {
			if (this.filsDroit == null) {
				this.filsDroit = new Noeud(stagiaire);
			} else {
				this.filsDroit.ajouter(stagiaire);
			}
		}
	}

	public void parcoursInfixe() {
		if (this.filsGauche != null) {
			this.filsGauche.parcoursInfixe();
		}
//		System.out.println(this.cle);
		if (this.filsDroit != null) {
			this.filsDroit.parcoursInfixe();
		}
	}

}
