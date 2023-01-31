package fr.isika.cda23.utilClass;

import java.util.ArrayList;
import java.util.Iterator;

public class Noeud {

	private Stagiaire cle;
	private Stagiaire stagiaireRem;

	public Stagiaire getStagiaireRem() {
		return stagiaireRem;
	}

	public void setStagiaireRem(Stagiaire stagiaireRem) {
		this.stagiaireRem = stagiaireRem;
	}

	private Noeud filsGauche;
	private Noeud filsDroit;
	public static ArrayList<Stagiaire> list = new ArrayList<>();
	Iterator<Stagiaire> it;

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

	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

	// Ajouter un stagiaire
	public void ajouter(Stagiaire stagiaire) {
		stagiaire.setNom(stagiaire.getNom().toUpperCase());
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

//	Rechercher stagiaire par nom
	public Stagiaire rechercheNom(String nom) {
		int comparaison = nom.compareTo(this.cle.getNom());
		if (comparaison < 0) {
			// le nom à rechercher est plus petit on va à gauche
			if (this.filsGauche == null) {
				System.out.println("Stagiaire " + nom + " n'exite pas dans l'annuaire");
			} else if (this.filsGauche.cle.getNom() == nom) {
				return this.filsGauche.cle;
			} else {
				return this.filsGauche.rechercheNom(nom);
			}
		} else if (comparaison == 0)
			return this.cle;
		else {
			// le nom à rechercher est plus grand on va à droit
			if (this.filsDroit == null) {
				System.out.println("Stagiaire " + nom + " n'exite pas dans l'annuaire");
			} else if (this.filsDroit.cle.getNom() == nom) {
				return this.filsDroit.cle;
			} else {
				return this.filsDroit.rechercheNom(nom);
			}
		}
		return null;
	}

// 	Rechercher stagiaire par multicritere
	public ArrayList<Stagiaire> rechercheMulti(Stagiaire stagiaire) {
		list.clear();
		if (!stagiaire.getNom().equals("")) {
			parcoursListParNom(stagiaire.getNom());
		}

		if (!stagiaire.getPrenom().equals("") && list.size() > 1) {
			it = list.iterator();
			while (it.hasNext()) {
				if (!it.next().getPrenom().toLowerCase().contains(stagiaire.getPrenom().toLowerCase())) {
					it.remove();
				}
			}

		} else if (!stagiaire.getPrenom().equals("") && list.size() == 0) {
			parcoursListParPrenom(stagiaire.getPrenom());
//			parcoursInfixeArbre();
		}

		if (!stagiaire.getDepartement().equals("") && list.size() > 1) {
			it = list.iterator();
			while (it.hasNext()) {
				if (!it.next().getDepartement().equals(stagiaire.getDepartement())) {
					it.remove();
				}
			}
		} else if (!stagiaire.getDepartement().equals("") && list.size() == 0) {

			parcoursListParDepartement(stagiaire.getDepartement());

		} else if (stagiaire.getAnneePromo().equals("") && stagiaire.getFormation().equals("")
				&& stagiaire.getDepartement().equals("") && stagiaire.getPrenom().equals("")
				&& stagiaire.getNom().equals("") && list.size() == 0) {

			parcoursListParDepartement(stagiaire.getDepartement());
		}
		if (!stagiaire.getFormation().equals("") && list.size() > 1) {
			it = list.iterator();
			while (it.hasNext()) {
				if (!it.next().getFormation().contains(stagiaire.getFormation().toLowerCase())) {
					it.remove();
				}
			}
		} else if (!stagiaire.getFormation().equals("") && list.size() == 0) {
			parcoursListFormation(stagiaire.getFormation());
		}
		if (!stagiaire.getAnneePromo().equals("") && list.size() > 1) {
			it = list.iterator();
			while (it.hasNext()) {
				if (!it.next().getAnneePromo().equals(stagiaire.getAnneePromo())) {
					it.remove();
				}
			}
		} else if (!stagiaire.getAnneePromo().equals("") && list.size() == 0) {

			parcoursListParAnneePromo(stagiaire.getAnneePromo());

		}
		return list;
	}

//	parcourt l'arbre
	public void parcoursInfixeArbre() {
		if (this.filsGauche != null) {
			this.filsGauche.parcoursInfixeArbre();
		}
		System.out.println(cle);
		if (this.filsDroit != null) {
			this.filsDroit.parcoursInfixeArbre();
		}
	}

// 	creation d'une liste de tous les stagiaires d'après l'arbre 
	public void parcoursList() {
		if (this.filsGauche != null) {
			this.filsGauche.parcoursList();
		}
		cle.addList(list);
//		for (Stagiaire i : list)
//			System.out.println(i);
		if (this.filsDroit != null) {
			this.filsDroit.parcoursList();
		}

	}

//	creation d'une liste de tous les stagiaires qui contiennet la meme partie de leurs noms 
//	ou qui ont le meme nom d'après l'arbre 
	public ArrayList<Stagiaire> parcoursListParNom(String varEntree) {

		// transformer toutes les lettres de varEntree en miniscule et effacer l'accent
		String lowerEntree = Utility.removeAccents(varEntree.toLowerCase().replaceAll(Utility.regExp, ""));
		// transformer toutes les lettres du nom de cle en miniscule et effacer l'accent
		String nomSansAccent = Utility.removeAccents(this.cle.getNom().toLowerCase().replaceAll(Utility.regExp, ""));

		if (this.filsGauche != null) {
			this.filsGauche.parcoursListParNom(lowerEntree);
		}
		if (nomSansAccent.contains(lowerEntree)) {

			this.cle.addList(list);
		}
		if (this.filsDroit != null) {
			this.filsDroit.parcoursListParNom(lowerEntree);
		}

		return list;

	}

//	creation d'une liste de tous les stagiaires qui contiennet la meme partie de leurs prenoms 
//	ou qui ont le meme prenom d'après l'arbre
	public ArrayList<Stagiaire> parcoursListParPrenom(String varEntree) {

		String lowerEntree = Utility.removeAccents(varEntree.toLowerCase().replaceAll(Utility.regExp, ""));

		String prenomSansAccent = Utility
				.removeAccents(this.cle.getPrenom().toLowerCase().replaceAll(Utility.regExp, ""));

		if (this.filsGauche != null) {
			this.filsGauche.parcoursListParPrenom(lowerEntree);
		}
		if (prenomSansAccent.contains(lowerEntree)) {
			list.add(this.cle);
			if (this.cle.getDoublon() != null) {
				this.cle.getDoublon().addListPrenom(list, lowerEntree);
			}
		}
		if (this.filsDroit != null) {
			this.filsDroit.parcoursListParPrenom(lowerEntree);
		}
		return list;
	}

//	creation d'une liste de tous les stagiaires qui ont le meme département d'après l'arbre
	public ArrayList<Stagiaire> parcoursListParDepartement(String varEntree) {
		if (this.filsGauche != null) {
			this.filsGauche.parcoursListParDepartement(varEntree);
		}

		if (this.cle.getDepartement().equals(varEntree)) {
			list.add(this.cle);
			if (this.cle.getDoublon() != null) {
				this.cle.getDoublon().addListDepartement(list, varEntree);
			}
		}
		if (this.filsDroit != null) {
			this.filsDroit.parcoursListParDepartement(varEntree);
		}
		return list;
	}

//	creation d'une liste de tous les stagiaires qui continnent la meme formation d'après l'arbre
	public ArrayList<Stagiaire> parcoursListFormation(String varEntree) {
		String lowerEntree = varEntree.toLowerCase();
		if (this.filsGauche != null) {
			this.filsGauche.parcoursListFormation(lowerEntree);
		}

		if (this.cle.getFormation().toLowerCase().replaceAll(" ", "").contains(lowerEntree)) {
			list.add(this.cle);
			if (this.cle.getDoublon() != null) {
				this.cle.getDoublon().addListFormation(list, lowerEntree);
			}
		}
		if (this.filsDroit != null) {
			this.filsDroit.parcoursListFormation(lowerEntree);
		}
		return list;
	}

//	creation d'une liste de tous les stagiaires qui ont la meme anneePromo d'après l'arbre
	public ArrayList<Stagiaire> parcoursListParAnneePromo(String varEntree) {
		if (this.filsGauche != null) {
			this.filsGauche.parcoursListParAnneePromo(varEntree);
		}

		if (this.cle.getAnneePromo().equals(varEntree)) {
			list.add(this.cle);
			if (this.cle.getDoublon() != null) {
				this.cle.getDoublon().addListAnneePromo(list, varEntree);
			}
		}
		if (this.filsDroit != null) {
			this.filsDroit.parcoursListParAnneePromo(varEntree);
		}
		return list;
	}

//  supprimer d'un noeud
	public void supprimerNoeud(Stagiaire stagiaire) {
		int comparaisonNom = stagiaire.getNom().compareTo(this.cle.getNom());
		int comparaisonPrenom = stagiaire.getPrenom().compareTo(this.cle.getPrenom());
		int comparaisonFormation = stagiaire.getFormation().compareTo(this.cle.getFormation());

		if (comparaisonNom < 0) {
			// le nom à supprimer est plus petit on va à gauche
			if (this.filsGauche != null) {
				this.filsGauche.supprimerNoeud(stagiaire);
			}

		} else if (comparaisonNom == 0) {
//			le nom,prenom,et formation du noeud à supprimer est égal aux ceux de la cle && doublon est null;
			if (comparaisonNom == 0 && comparaisonPrenom == 0 && comparaisonFormation == 0
					&& this.cle.getDoublon() == null) {
//			on regarde s'il possède des descendants?
				if (this.filsDroit == null && this.filsGauche == null) {
					this.cle = null;
				} else if (this.filsDroit != null) {
					this.setStagiaireRem(this.filsDroit.parcoursInfixeFils());
					this.setCle(this.stagiaireRem);
				} else if (this.filsGauche != null && this.filsDroit == null) {
					this.setCle(this.filsGauche.getCle());
					this.setFilsGauche(this.filsGauche.filsGauche);
					this.setFilsDroit(this.filsGauche.filsDroit);
				}
			} else if (comparaisonNom == 0 && comparaisonPrenom == 0 && comparaisonFormation == 0
					&& this.cle.getDoublon() != null) {
				this.setCle(this.cle.getDoublon());

			} else if (comparaisonPrenom != 0 && this.cle.getDoublon() != null
					|| comparaisonFormation != 0 && this.cle.getDoublon() != null) {
				this.cle.supprimerDoublon(stagiaire);
			}
		} else {
			// le nom à supprimer est plus grand on va à droit
			if (this.filsDroit != null) {
				this.filsDroit.supprimerNoeud(stagiaire);

			}

		}
	}

//	parcourt l'arbre par droit puis tout gauche
	public Stagiaire parcoursInfixeFils() {
		if (this.filsGauche == null) {
			return this.getCle();
		} else if (this.filsGauche == null && this.filsDroit == null) {
			this.setCle(null);
		} else if (this.filsGauche == null && this.filsDroit != null) {
			this.setCle(this.filsDroit.getCle());
			this.setFilsGauche(this.filsDroit.filsGauche);
			this.setFilsDroit(this.filsDroit.filsDroit);
		} else {
			return this.filsGauche.parcoursInfixeFils();
		}
		return cle;
	}

//  modifier d'un noeud
	public void modifierNoeud(Stagiaire stagiaire, String nom, String prenom, String departement, String formation,
			String anneePromo) {
				this.supprimerNoeud(stagiaire);
				this.ajouter(new Stagiaire(nom, prenom, departement, formation, anneePromo));
			

	}

	@Override
	public String toString() {
		return "Noeud [cle=" + cle + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + "]";
	}

}
