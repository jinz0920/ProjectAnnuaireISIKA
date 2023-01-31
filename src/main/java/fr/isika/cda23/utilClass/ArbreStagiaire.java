package fr.isika.cda23.utilClass;

import java.util.ArrayList;

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

//	ajouter
	public void ajouterNoeud(Stagiaire stagiaire) {
		if (racine == null) {
			racine = new Noeud(stagiaire);
		} else {
			racine.ajouter(stagiaire);
		}
	}

//	rechercher par nom
	public Stagiaire rechercheNoeudParNom(String nom) {
		if (racine == null) {
			return null;
		} else {
			return racine.rechercheNom(nom);
		}
	}

// 	recherche multicritère
	public ArrayList<Stagiaire> rechercheMulticritere(Stagiaire stagiaire) {
		if (racine == null) {
			System.out.println("l'arbre est vide il n'y a rien à afficher");
		} else {
			return racine.rechercheMulti(stagiaire);
		}
		return null;
	}

//	afficher
	public void afficherArbre() {
		if (racine == null) {
			System.out.println("l'arbre est vide il n'y a rien à afficher");
		} else {
			racine.parcoursInfixeArbre();
		}
	}

//	Supprimer d'un noeud
	public void supprimer(Stagiaire stagiaire) {
		if (racine == null) {
			System.out.println("l'arbre est vide il n'y a rien à supprimer");
		} else {
			racine.supprimerNoeud(stagiaire);
		}
	}

//	Modifier d'un noeud
	public void modifier(Stagiaire stagiaire, String nom, String prenom, String departement, String formation,
			String anneePromo) {
		if (racine == null) {
			System.out.println("l'arbre est vide il n'y a rien à modifier");
		} else {
			racine.modifierNoeud(stagiaire, nom, prenom, departement, formation, anneePromo);
		}
	}

}
