package fr.ledevedec.submenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.ledevedec.mainmenu.MenuEntry;
import fr.ledevedec.reseausocial.ErrSaisieException;

public class SubMenuUpdateUser {

	private Scanner clavier;
	private MenuEntry nom;
	private MenuEntry prenom;
	private MenuEntry anneeNaissance;
	private MenuEntry enregistrer;
	private MenuEntry retourMenu;
	private List<MenuEntry> menu;

	private int choixInputClavier;
	private int choixUser;
	
	public SubMenuUpdateUser() {

		clavier = new Scanner(System.in);
		menu = new ArrayList<MenuEntry>();
		nom = new Nom();
		prenom = new Prenom();
		anneeNaissance = new AnneeNaissance();
		enregistrer = new Enregistrer();
		retourMenu = new RetourMenu();
	

		menu.add(nom);
		menu.add(prenom);
		menu.add(anneeNaissance);
		menu.add(enregistrer);
		menu.add(retourMenu);

	}

	public void createMenu() {

		if (!menu.isEmpty()) {
			for (MenuEntry item : menu) {
				System.out.println("[" + menu.indexOf(item) + "] " + item.display());

			}
		}

		do {

			choixInputClavier = choixClavier();

			if (choixInputClavier >= menu.size() || choixInputClavier < 0 ) {
				System.out.println("Merci d'entrer une valeur du menu");
			} else {

				choixUser = ((MenuEntry) menu.get(choixInputClavier)).exec(choixInputClavier);

			}

		} while (choixInputClavier >= menu.size());

	}

	private int choixClavier() {

		int choixMenu = -1;
		while (true) {
			System.out.print("Entrer un numéro : ");
			try {
				try {
					choixMenu = clavier.nextInt();
					clavier.nextLine();
					return choixMenu;
				} catch (InputMismatchException err) {
					throw new ErrSaisieException();
				}
			} catch (ErrSaisieException err) {
				System.out.println(err.getMessage());
				clavier.next();
			}

		}

	}

	public int getChoixUser() {
		return choixUser;
	}
}
