package donneeFichier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.isika.cda23.utilClass.ArbreStagiaire;
import fr.isika.cda23.utilClass.Stagiaire;

public class Donnee {

	ArrayList<String> list;
	Stagiaire stagiaire;
	ArrayList<Stagiaire> listStagiaire;
	ArbreStagiaire arbre;

	public Donnee() {
		list = new ArrayList<>();
		listStagiaire = new ArrayList<>();
		arbre = new ArbreStagiaire();
	}

	public void extraireDonne() {
		try {
			FileReader fr = new FileReader("src/main/java/ressource/STAGIAIRES.DON");

			BufferedReader br = new BufferedReader(fr);

			String contenu = "";

//				insérer ligne par ligne du fichier DON dans list
			while ((contenu = br.readLine()) != null) {

//				System.out.println(contenu);

				list.add(contenu);

			}

//				instancier tous les stagiaires et les insérer dans listStagiaire
			for (int i = 0; i < list.size(); i += 6) {
//				System.out.println(list.get(i));

				Stagiaire stagiaire = new Stagiaire(list.get(i), list.get(i + 1), list.get(i + 2), list.get(i + 3),
						list.get(i + 4));
				listStagiaire.add(stagiaire);
			}
//		arbre.ajouterNoeud(listStagiaire.get(0));

			for (Stagiaire i : listStagiaire) {
				arbre.ajouterNoeud(i);
			}
//			arbre.ajouterNoeud(new Stagiaire("LACROIX", "bb", "cc", "dd", "ee"));
			arbre.ajouterNoeud(new Stagiaire("LACROIX", "cc", "cc", "dd", "ee"));
//			arbre.ajouterNoeud(new Stagiaire("AA", "cc", "cc", "dd", "ee"));
			System.out.println(arbre.getRacine());
//			arbre.afficherArbre();
//			System.out.println(list.size());
			System.out.println(listStagiaire);
			System.out.println(listStagiaire.size());
			br.close();
			fr.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
