package donneeFichier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.isika.cda23.utilClass.Stagiaire;

public class Donnee {

	ArrayList<String> list;
	Stagiaire stagiaire;
	ArrayList<Stagiaire> listStagiaire;

	public Donnee() {
		list = new ArrayList<>();
		listStagiaire = new ArrayList<>();
	}

	public void extraireDonne() {
		try {
			FileReader fr = new FileReader("src/main/java/ressource/STAGIAIRES.DON");

			BufferedReader br = new BufferedReader(fr);

			String contenu = "";

			while ((contenu = br.readLine()) != null) {

//				System.out.println(contenu);
				list.add(contenu);

			}

			for (int i = 0; i < list.size(); i += 6) {
//				System.out.println(list.get(i));
				Stagiaire stagiaire = new Stagiaire(list.get(i), list.get(i + 1), list.get(i + 2), list.get(i + 3),
						list.get(i + 4));
				listStagiaire.add(stagiaire);
			}

			for (Stagiaire i : listStagiaire) {
				System.out.println(i);

			}
//			System.out.println(list.size());
//			System.out.println(listStagiaire.size());

			br.close();
			fr.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
