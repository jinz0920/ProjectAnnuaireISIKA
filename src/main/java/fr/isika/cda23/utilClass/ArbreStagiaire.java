package fr.isika.cda23.utilClass;

public class ArbreStagiaire {

	private Noeud racine;

	public ArbreStagiaire() {
		racine = null;
	}

	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}
	
	

	@Override
	public String toString() {
		return "ArbreStagiaire [racine=" + racine + "]";
	}

	public void ajouterNoeud(Stagiaire stagiaire) {
		if (racine == null) {
			racine = new Noeud(stagiaire);
		} else {
			racine.ajouter(stagiaire);
		}
	}

	public void afficherArbre() {
		if (racine == null) {
			System.out.println("l'arbre est vide il n'y a rien à afficher");
		} else {
			racine.parcoursInfixe();
		}
	}


}
