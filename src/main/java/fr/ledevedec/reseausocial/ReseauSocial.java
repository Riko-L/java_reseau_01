package fr.ledevedec.reseausocial;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import fr.ledevedec.mainmenu.Menu;
import fr.ledevedec.submenu.SubMenuUpdateUser;

public class ReseauSocial {

	static private User user;
	static private Moderateur mod;
	static public User currentUser;
	static private Menu menu;
	static private SubMenuUpdateUser subMenu;

	static private int numeroProfil;
	static private int currentUserIndex;
	static private int numeroFriend;

	static private char reponse = 'O';

	static private Scanner scan;

	static private List<User> users = new ArrayList<User>();

	static private String nom;
	static private String prenom;
	static private String pseudo;
	static private String anneeNaissance;

	public ReseauSocial() {

		scan = new Scanner(System.in);

		start();

		subMenu = new SubMenuUpdateUser();
		menu = new Menu();
	}

	public static void main(String[] args) {

		ReseauSocial reseau = new ReseauSocial();

	}

	private static void start() {
		generateUsers();
		System.out.println("************************** APPLICATION RESEAU SOCIAL ******************************");
		System.out.println("***********************************************************************************");
		System.out.println();
		System.out.println();
		System.out.println("************************* CONNECTION / ENREGISTREMENT *****************************");

		char rep = ' ';
		while (rep != 'C' && rep != 'E') {

			System.out.println("Vous connecter [C] ou vous enregistrer [E]?");
			System.out.print("Saisissez votre choix : ");
			rep = scan.nextLine().charAt(0);
			if (rep == 'E') {
				System.out
						.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ENREGISTREMENT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				addUser();
			} else if (rep == 'C') {
				System.out
						.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONNECTION <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.print("Saisissez le nom et prénom de votre utilisateur \" Dupond Jean \" : ");
				String search = scan.nextLine();
				boolean isPresent = false;

				for (User user : users) {
					if (user.getFullName().toLowerCase().equals(search.toLowerCase())) {
						isPresent = true;
						ReseauSocial.user = user;
					}

				}
				if (isPresent) {
					currentUser = users.get(users.indexOf(user));
					currentUserIndex = users.indexOf(user);

					System.out.println(
							"*********************************************************************************");
					System.out.println("Vous etes connecté sous l'utilisateur : \"" + currentUser.getFullName() + "\"");
					System.out.println(
							"*********************************************************************************");

				} else if (!isPresent) {
					System.out.println(
							"*********************************************************************************");
					System.out.println("Votre utilisateur existe pas");
					rep = ' ';
					System.out.println(
							"*********************************************************************************");
				}
			}
		}

	}

	private static boolean list(String args, Optional<Integer> index) {
		Integer userIndex = index.isPresent() ? index.get() : null;
		boolean result = false;

		switch (args) {
		case "ALL":
			for (int i = 0; i < users.size(); i++) {
				if (!users.isEmpty() && users.get(i) != users.get(currentUserIndex)) {
					System.out.println(
							"Utilisateur [" + i + "] : " + users.get(i).getNom() + " | " + users.get(i).getPrenom()
									+ " | " + users.get(i).getDateDeNaissance() + " | " + users.get(i).getPseudo()
									+ " | " + users.get(i).isModerateur() + " | " + users.get(i).getNiveau());
				}
				if (!users.isEmpty() && users.get(i) == users.get(currentUserIndex)) {
					System.out.println(
							"Utilisateur [" + i + "]*: " + users.get(i).getNom() + " | " + users.get(i).getPrenom()
									+ " | " + users.get(i).getDateDeNaissance() + " | " + users.get(i).getPseudo()
									+ " | " + users.get(i).isModerateur() + " | " + users.get(i).getNiveau());
				}
			}
			break;
		case "FULLNAME":

			for (int i = 0; i < users.size(); i++) {

				if (!users.isEmpty() && users.get(i) != users.get(currentUserIndex)) {
					System.out.println(
							"Utilisateur [" + i + "] : " + users.get(i).getNom() + " " + users.get(i).getPrenom());
				}
			}

			break;

		case "FULLNAME+CURRENT":
			for (int i = 0; i < users.size(); i++) {
				if (!users.isEmpty()) {
					System.out.println(
							"Utilisateur [" + i + "] : " + users.get(i).getNom() + " " + users.get(i).getPrenom());
				}

			}
			break;

		case "MOD":
			for (int i = 0; i < users.size(); i++) {
				if (!users.isEmpty() && users.get(i).isModerateur()) {
					System.out.println("Utilisateur [" + i + "] : " + users.get(i).getNom() + " "
							+ users.get(i).getPrenom() + " | Niveau de modération : " + users.get(i).getNiveau());
				}
			}
			break;
		case "FULLNAME+NB-MESSAGE":
			for (int i = 0, nbmsg = 0; i < users.size(); i++) {
				if (!users.isEmpty()) { // ajouter !users.get(i).getMessages().isEmpty() pour lister juste les
										// utilisateurs avec des messages

					for (int m = 0; m < users.get(i).getMessages().size(); m++) {
						if (!users.get(i).getMessages().isEmpty()) {
							nbmsg++;
						}
					}
					System.out.println("Utilisateur [" + i + "] : " + users.get(i).getNom() + " "
							+ users.get(i).getPrenom() + " | Nombre de messages : [" + nbmsg + "]");
					nbmsg = 0;
				}
			}
			break;

		case "MESSAGE":
			if (userIndex != null && users.contains(users.get(userIndex))) {
				if (!users.get(userIndex).getMessages().isEmpty()) {
					for (int i = 0; i < users.get(userIndex).getMessages().size(); i++) {
						System.out.println(" Message  [" + i + "] : ");
						System.out.println(
								" 	From : \"" + users.get(userIndex).getMessages().get(i).getExpediteur() + "\"");
						System.out.println(
								" 	To : \"" + users.get(userIndex).getMessages().get(i).getDestinataire() + "\"");
						System.out.println(
								" 	Contenu : \"" + users.get(userIndex).getMessages().get(i).getContenu() + "\"");
						System.out.println("***************");
					}
				} else {
					System.out.println("Messagerie vide");
					result = true;

				}
			}

			break;

		case "MESSAGE_ENVOYE":
			if (userIndex != null && users.contains(users.get(userIndex))) {
				int nbre = 0;
				for (int i = 0; i < users.size(); i++) {

					if (users.get(i) != null) {

						for (int j = 0; j < users.get(i).getMessages().size(); j++) {
							if (users.get(i).getMessages() != null) {

								if (users.get(i).getMessages().get(j).getExpediteur()
										.contains(users.get(userIndex).getFullName())) {
									nbre += 1;
									System.out.println(users.get(i).getFullName());
									System.out.println(" Message  [" + j + "] : ");

									System.out.println(
											" 	To : \"" + users.get(i).getMessages().get(j).getDestinataire() + "\"");
									System.out.println(
											" 	Contenu : \"" + users.get(i).getMessages().get(j).getContenu() + "\"");
									System.out.println("***************");
								}
							}
						}
					}

				}

				System.out.println("Vous avez envoyé " + nbre + " messages");

			}

			break;

		case "FRIENDS":
			if (userIndex != null && users.contains(users.get(userIndex))) {

				for (int i = 0; i < users.get(userIndex).getFriends().size(); i++) {
					if (!users.get(userIndex).getFriends().isEmpty()) {

						System.out.println("Ami [" + i + "] : " + users.get(userIndex).getFriends().get(i).getNom()
								+ " " + users.get(userIndex).getFriends().get(i).getPrenom());
					}
				}
			}

			break;
		case "FULLNAME-USER-FRIENDS":

			for (int i = 0; i < users.size(); i++) {
				if (!users.isEmpty() && users.contains(currentUser) && users.get(i) != users.get(userIndex)) {

					if (!users.get(userIndex).getFriends().contains(users.get(i))) {

						System.out.println(
								"Utilisateur [" + i + "] : " + users.get(i).getNom() + " " + users.get(i).getPrenom());
					}
				}
			}
			break;

		}

		return result;

	}

	private static int choixClavier() {
		int choixMenu = -1;
		while (true) {
			System.out.print("Entrer un numéro : ");
			try {
				try {
					choixMenu = scan.nextInt();
					scan.nextLine();
					return choixMenu;
				} catch (InputMismatchException err) {
					throw new ErrSaisieException();
				}
			} catch (ErrSaisieException err) {
				System.out.println(err.getMessage());
				scan.next();
			}

		}

	}

	public static void changeLevel() {
		System.out.println("**************************** CHANGER LEVEL MODERATEUR *****************************");
		System.out.println("***********************************************************************************");
		list("MOD", Optional.empty());

		numeroProfil = choixClavier();

		if (users.get(numeroProfil) != null && users.get(numeroProfil) instanceof Moderateur) {

			System.out.println("Entrer le niveau à attribuer : [1] - [2]");

			int level;
			do {
				level = choixClavier();

				if (level == 1 || level == 2) {
					((Moderateur) users.get(numeroProfil)).setNiveau(level);
					System.out.println(
							"***********************************************************************************");
					System.out.println("Niveau moderateur modifié");
					System.out.println(
							"***********************************************************************************");

				} else {
					System.out.println("Niveau moderateur non valide  | niveau = 1 || niveau = 2");
				}
			} while (level != 1 && level != 2);

		}

	}

	public static void generateUsers() {
		// System.out.println("********************* GENERATION >> UTILISATEUR |
		// MODERATEUR **********************");
		// System.out.println("***********************************************************************************");
		users = new ArrayList<User>();

		mod = new Moderateur("LE DEVEDEC", "Eric", "riko74", "1982");
		mod.setNiveau(2);
		users.add(mod);
		user = new User("BIAGI", "Alexandre", "BigBoy", "1968");
		users.add(user);
		user = new User("DUPON", "Jean", "The Cat", "1945");
		users.add(user);
		user = new User("PIG", "Peppa", "Petit cochon", "1999");
		users.add(user);
		user = new User("BOND", "Jams", "251Hys", "1765");
		users.add(user);
		user = new User("PIGNION", "François", "PF2008", "2008");
		users.add(user);
		mod = new Moderateur("FILLION", "Charles", "Fcha74", "1972");
		mod.setNiveau(1);
		users.add(mod);

		// currentUser = users.get(0);
		// currentUserIndex = 0;
		// System.out.println("Génération terminé");
		// System.out.println("Nombre d'utilisateurs créer : " + users.size());
		// System.out.println("***********************************************************************************");

	}

	public static void addUser() {
		System.out.println("********************* CREATION >> UTILISATEUR | MODERATEUR **********************");
		System.out.println("*********************************************************************************");

		do {
			System.out.print("Entrer votre nom : ");
			nom = scan.nextLine();
		} while (!InputControl.lastNameControl(nom));

		do {
			System.out.print("Entrer votre Prénom : ");
			prenom = scan.nextLine();
		} while (!InputControl.firstNameControl(prenom));

		do {
			System.out.print("Entrer votre Pseudo : ");
			pseudo = scan.nextLine();
		} while (!InputControl.pseudoControl(pseudo));
		
		do {
			System.out.print("Entrer votre année de naissance :");
			anneeNaissance = scan.nextLine();
		} while (!InputControl.dateControl(anneeNaissance));
		
		
		System.out.print("Voulez-vous lui attribuer les droits MODERATEUR: [O] ou [N]");
		reponse = scan.nextLine().charAt(0);

		if (reponse == 'O') {

			System.out.print("Niveau de Modération: [1] ou [2]");
			int choix = choixClavier();
			mod = new Moderateur(nom, prenom, pseudo, anneeNaissance);
			mod.setNiveau(choix);

			users.add(mod);
			currentUser = users.get(users.indexOf(mod));
			currentUserIndex = users.indexOf(mod);
			System.out.println("\"Modérateur Créer\"");
			System.out.println("Nombre d'utilisateurs : " + users.size());
			System.out.println("***********************************************************************************");

			selectUser();

		} else if (reponse == 'N') {

			user = new User(nom, prenom, pseudo, anneeNaissance);
			users.add(user);
			currentUser = users.get(users.indexOf(user));
			currentUserIndex = users.indexOf(user);

			System.out.println("\"Utilisateur Créer\"");
			System.out.println("Nombre d'utilisateurs : " + users.size());
			System.out.println("***********************************************************************************");
		}

	}

	public static void showProfil() {
		if (currentUser.isModerateur()) {
			System.out.println("************************* AFFICHER INFOS UTILISATEURS ***************************");
			System.out.println("*********************************************************************************");
			list("FULLNAME+CURRENT", Optional.empty());

			numeroProfil = choixClavier();

			if (users.get(numeroProfil) != null) {

				System.out.println(">>>>>>>>>>>>>>>>>>>>     Voici les informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println("*********************************************************************************");
				System.out.println("* Nom : " + users.get(numeroProfil).getNom());
				System.out.println("* Prénom : " + users.get(numeroProfil).getPrenom());
				System.out.println("* Année de naissance : " + users.get(numeroProfil).getDateDeNaissance());
				System.out.println("*********************************************************************************");
			}

		} else {
			if (users.get(currentUserIndex) != null) {

				System.out.println(">>>>>>>>>>>>>>>>>>>>     Voici les informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println("*********************************************************************************");
				System.out.println("* Nom : " + currentUser.getNom());
				System.out.println("* Prénom : " + currentUser.getPrenom());
				System.out.println("* Année de naissance : " + currentUser.getDateDeNaissance());
				System.out.println("*********************************************************************************");
			}

		}
	}

	public static void updateProfil() {

		String updateInfo;

		if (currentUser.isModerateur()) {

			System.out.println("************************* MODIFIER INFOS UTILISATEURS ***************************");
			System.out.println("*********************************************************************************");
			list("FULLNAME+CURRENT", Optional.empty());

			numeroProfil = choixClavier();

			reponse = 'O';

			System.out.println("*********************************************************************************");
			System.out.println(
					"Modification de " + users.get(numeroProfil).getNom() + " " + users.get(numeroProfil).getPrenom());
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				subMenu.createMenu();

				switch (subMenu.getChoixUser()) {
				case 0:
					System.out.print("Entrer Nom:");
					updateInfo = scan.nextLine();
					users.get(numeroProfil).setNom(updateInfo);
					System.out.println("Enregister");
					break;
				case 1:
					System.out.print("Entrer Prénom:");
					updateInfo = scan.nextLine();
					users.get(numeroProfil).setPrenom(updateInfo);
					System.out.println("Enregister");
					break;
				case 2:
					System.out.print("Entrer Année de naissance:");
					updateInfo = scan.nextLine();
					users.get(numeroProfil).setDateDeNaissance(updateInfo);
					System.out.println("Enregistrer");
					break;
				case 3:
					reponse = ' ';
				}

				System.out.println(
						">>>>>>>>>>>>>>>>>>>>     Voici les nouvelles informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println(
						"*******************************************************************************************");
				System.out.println("* Nom : " + users.get(numeroProfil).getNom());
				System.out.println("* Prénom : " + users.get(numeroProfil).getPrenom());
				System.out.println("* Année de naissance : " + users.get(numeroProfil).getDateDeNaissance());
				System.out.println(
						"*******************************************************************************************");

			}
		} else {
			System.out.println("*********************************************************************************");
			System.out.println("Modification de " + currentUser.getNom() + " " + currentUser.getPrenom());
			System.out.println("*********************************************************************************");

			subMenu.createMenu();

			switch (subMenu.getChoixUser()) {
			case 0:
				System.out.print("Entrer Nom:");
				updateInfo = scan.nextLine();
				currentUser.setNom(updateInfo);
				System.out.println("Enregister");
				break;
			case 1:
				System.out.print("Entrer Prénom:");
				updateInfo = scan.nextLine();
				currentUser.setPrenom(updateInfo);
				System.out.println("Enregister");
				break;
			case 2:
				System.out.print("Entrer Année de naissance:");
				updateInfo = scan.nextLine();
				currentUser.setDateDeNaissance(updateInfo);
				System.out.println("Enregistrer");
				break;
			case 3:
				reponse = ' ';
			}
			System.out.println(
					">>>>>>>>>>>>>>>>>>>>     Voici les nouvelles informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println(
					"*******************************************************************************************");
			System.out.println("* Nom : " + currentUser.getNom());
			System.out.println("* Prénom : " + currentUser.getPrenom());
			System.out.println("* Année de naissance : " + currentUser.getDateDeNaissance());
			System.out.println(
					"*******************************************************************************************");

		}
	}

	public static void writeMessage() {
		reponse = 'O';
		System.out.println("****************************** ECRIRE UN MESSAGE ********************************");
		System.out.println("*********************************************************************************");

		while (reponse == 'O') {
			list("FULLNAME", Optional.empty());
			numeroProfil = choixClavier();

			if (users.get(numeroProfil) != null) {
				System.out.println("Entrer votre message pour " + users.get(numeroProfil).getNom() + " "
						+ users.get(numeroProfil).getPrenom() + " :");
				System.out.print(">> ");

				String message = scan.nextLine();

				users.get(numeroProfil).addMessages(new Message(users.get(currentUserIndex).getFullName(),
						users.get(numeroProfil).getFullName(), message));
				System.out.println("*********************************************************************************");
				System.out.println("Message Envoyé !");
				System.out.println("*********************************************************************************");
			}

			reponse = ' ';

			while (reponse != 'P' && reponse != 'O') {

				System.out.println("Envoyer un nouveau message O | Retour menu P");
				reponse = scan.nextLine().charAt(0);

			}
		}

	}

	public static void showMessage() {
		if (currentUser.isModerateur()) {
			reponse = 'O';
			System.out.println("************************** MESSAGES DES UTILISATEURS ****************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {
				list("FULLNAME+NB-MESSAGE", Optional.empty());

				numeroProfil = choixClavier();
				System.out.println("*********************************************************************************");
				if (users.get(numeroProfil) != null) {
					System.out.println("Messages de l'utilisateur : " + users.get(numeroProfil).getNom() + " "
							+ users.get(numeroProfil).getPrenom());

					list("MESSAGE", Optional.of(numeroProfil));
				}
				System.out.println("*********************************************************************************");
				reponse = ' ';

				while (reponse != 'P' && reponse != 'O') {

					System.out.print("Lire message autre profil O | Retour menu P");
					reponse = scan.nextLine().charAt(0);
				}
			}
		} else {
			System.out.println("Messages Reçu [1] - Messages Envoyé [2]");
			int choix = choixClavier();
			if (choix == 1) {
				System.out.println(
						"*******************************  MES MESSAGES RECU  **********************************");
				System.out.println(
						"**************************************************************************************");
				list("MESSAGE", Optional.of(currentUserIndex));
				System.out.println(
						"**************************************************************************************");
			} else if (choix == 2) {
				System.out.println(
						"*******************************  MES MESSAGES ENVOYE  **********************************");
				System.out.println(
						"**************************************************************************************");
				list("MESSAGE_ENVOYE", Optional.of(currentUserIndex));
				System.out.println(
						"**************************************************************************************");
			}
		}

	}

	public static void addFriend() {
		if (currentUser.isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* AJOUTER UN AMI **********************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				list("FULLNAME+CURRENT", Optional.empty());
				System.out.println("Selectionner l'utilisateur pour lequel vous souhaitez ajouter un ami");
				numeroProfil = choixClavier();
				System.out.println(
						"************************************************************************************************");
				list("FULLNAME-USER-FRIENDS", Optional.of(numeroProfil));

				System.out
						.println("Voici une liste des différents utilisateur.Entrer son numéro pour l'ajouter en ami");

				numeroFriend = choixClavier();

				users.get(numeroProfil).addFriends(users.get(numeroFriend));
				users.get(numeroFriend).addFriends(users.get(numeroProfil));
				System.out.println(
						"************************************************************************************************");
				System.out.println("Ami : " + users.get(numeroFriend).getNom() + " "
						+ users.get(numeroFriend).getPrenom() + " à bien été ajouté au profil de "
						+ users.get(numeroProfil).getNom() + " " + users.get(numeroProfil).getPrenom());
				System.out.println(
						"************************************************************************************************");

				reponse = ' ';
				while (reponse != 'P' && reponse != 'O') {

					System.out.println("ajouter un ami O | Retour menu P");
					reponse = scan.nextLine().charAt(0);

				}
			}

		} else {
			System.out.println("******************************* AJOUTER UN AMI **********************************");
			System.out.println("*********************************************************************************");

			list("FULLNAME-USER-FRIENDS", Optional.of(currentUserIndex));

			System.out.println("Voici une liste des différents utilisateur.Entrer son numéro pour l'ajouter en ami");

			numeroFriend = choixClavier();

			currentUser.addFriends(users.get(numeroFriend));
			users.get(numeroFriend).addFriends(users.get(currentUserIndex));
			System.out.println("*********************************************************************************");
			System.out.println("Ami : " + users.get(numeroFriend).getNom() + " " + users.get(numeroFriend).getPrenom()
					+ " à bien été ajouté");
			System.out.println("*********************************************************************************");
		}

	}

	public static void showFriend() {
		if (currentUser.isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* AFFICHER AMIS ***********************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				list("FULLNAME+CURRENT", Optional.empty());

				numeroProfil = choixClavier();

				if (users.get(numeroProfil) != null) {

					System.out.println("voici les amis de l'utilisateur " + users.get(numeroProfil).getNom() + " "
							+ users.get(numeroProfil).getPrenom());
					System.out.println(
							"*********************************************************************************");
					list("FRIENDS", Optional.of(numeroProfil));
					System.out.println(
							"*********************************************************************************");
				}
				reponse = ' ';

				while (reponse != 'P' && reponse != 'O') {

					System.out.println("voir les amis d'un autre profil O | Retour menu P ");
					reponse = scan.nextLine().charAt(0);

				}
			}

		} else {
			System.out.println("******************************* AFFICHER AMIS ***********************************");
			System.out.println("*********************************************************************************");

			list("FRIENDS", Optional.of(currentUserIndex));
			System.out.println("*********************************************************************************");
		}
	}

	public static void delFriend() {
		if (currentUser.isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* EFFACER AMIS ***********************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				list("FULLNAME+CURRENT", Optional.empty());

				numeroProfil = choixClavier();

				if (users.get(numeroProfil) != null) {

					System.out.println("voici les amis de l'utilisateur " + users.get(numeroProfil).getNom() + " "
							+ users.get(numeroProfil).getPrenom());
					System.out.println(
							"*********************************************************************************");
					list("FRIENDS", Optional.of(numeroProfil));
					System.out.println(
							"*********************************************************************************");
				}
				numeroFriend = choixClavier();

				users.get(numeroProfil).delFriend(numeroFriend);
				users.get(numeroFriend).delFriend(numeroProfil);

				System.out.println("Votre ami à été supprimé");

				reponse = ' ';

				while (reponse != 'P' && reponse != 'O') {

					System.out.println("voir les amis d'un autre profil O | Retour menu P ");
					reponse = scan.nextLine().charAt(0);

				}
			}

		} else {
			System.out.println("******************************* EFFACER AMIS ***********************************");
			System.out.println("*********************************************************************************");

			list("FRIENDS", Optional.of(currentUserIndex));

			numeroFriend = choixClavier();

			users.get(currentUserIndex).delFriend(numeroFriend);
			users.get(numeroFriend).delFriend(currentUserIndex);

			System.out.println("Votre ami à été supprimé");

			System.out.println("*********************************************************************************");
		}
	}

	public static void search() {
		System.out.println("******************************* RECHERCHE UTILISATEUR ***********************************");
		System.out.println("*****************************************************************************************");
		System.out.println("Rechercher un utilisateur par \" Nom - Prénom - Pseudo : \"");
		boolean isFind = false;
		List<User> searchList = new ArrayList<User>();
		String search = scan.nextLine();
		for (User user : users) {
			isFind = user.getNom().toLowerCase().equals(search.toLowerCase())
					|| user.getPrenom().toLowerCase().equals(search.toLowerCase())
					|| user.getPseudo().toLowerCase().equals(search.toLowerCase());
			if (isFind) {
				searchList.add(user);
			}
		}

		if (!searchList.isEmpty()) {
			for (int i = 0; i < searchList.size(); i++) {
				System.out.println(
						"*****************************************************************************************");
				System.out.println("L'utilisateur \"" + searchList.get(i).getFullName() + "\" avec l'ID ["
						+ users.indexOf(searchList.get(i)) + "] corresponds à votre recherhe");
				System.out.println(
						"*****************************************************************************************");
			}

		} else {
			System.out.println(
					"*****************************************************************************************");
			System.out.println("Utilisateur : \"" + search + "\" non trouvé");
			System.out.println(
					"*****************************************************************************************");
		}
	}

	public static void delMessage() {
		if (currentUser.isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* SUPPRIMER MESSAGE ***********************************");
			System.out.println("*************************************************************************************");
			while (reponse == 'O') {
				list("FULLNAME+NB-MESSAGE", Optional.empty());

				numeroProfil = choixClavier();
				System.out.println(
						"*************************************************************************************");
				boolean isEmptyList = list("MESSAGE", Optional.of(numeroProfil));
				System.out.println(
						"*************************************************************************************");
				if (!isEmptyList) {

					users.get(numeroProfil).delMessages(choixClavier());
					System.out.println(
							"*************************************************************************************");
					System.out.println("Message supprimé !");
					System.out.println(
							"*************************************************************************************");
				}

				reponse = ' ';

				while (reponse != 'P' && reponse != 'O') {

					System.out.println("Supprimer un autre message O | Retour menu P ");
					reponse = scan.nextLine().charAt(0);
				}
			}
		} else {
			System.out.println("******************************* SUPPRIMER MESSAGE ***********************************");
			System.out.println("*************************************************************************************");
			list("MESSAGE", Optional.of(currentUserIndex));
			currentUser.delMessages(choixClavier());
			System.out.println("*************************************************************************************");
			System.out.println("Message supprimé !");
			System.out.println("*************************************************************************************");
		}

	}

	public static void delUser() {
		if (currentUser.isModerateur() && currentUser.getNiveau() == 2) {
			System.out.println(
					"******************************* SUPPRIMER UTILISATEUR ***********************************");
			System.out.println(
					"*****************************************************************************************");

			list("FULLNAME", Optional.empty());
			users.remove(choixClavier());
			System.out.println(
					"*****************************************************************************************");

			System.out.println("Utilisateur Supprimé !");
			System.out.println(
					"*****************************************************************************************");

		} else {
			System.out.println(
					"******************************* SUPPRIMER UTILISATEUR ***********************************");
			System.out.println(
					"*****************************************************************************************");

			System.out.println(
					"*****************************************************************************************");
			System.out.println("Vous devez avoir le niveau de droits sur [2] pour pouvoir modifier cette ressource");

			System.out.println(
					"*****************************************************************************************");

		}

	}

	public static void delMessagerie() {
		if (currentUser.isModerateur()) {
			System.out.println(
					"******************************* VIDER MESSAGERIE UTILISATEUR ***********************************");
			System.out.println(
					"************************************************************************************************");
			list("FULLNAME+NB-MESSAGE", Optional.empty());

			numeroProfil = choixClavier();

			users.get(numeroProfil).viderMessages();
			System.out.println(
					"************************************************************************************************");
			System.out.println("La messagerie de l'utilisateur " + users.get(numeroProfil).getNom() + " "
					+ users.get(numeroProfil).getPrenom() + " est vide");
			System.out.println(
					"************************************************************************************************");
		}
	}

	public static void selectUser() {
		System.out.println(
				"**********************************  UTILISATEUR  COURANT  **************************************");
		System.out.println(
				"************************************************************************************************");

		list("FULLNAME", Optional.empty());

		System.out.println(
				"Voici une liste d'utilisateurs. Entrer un numéro pour le choisir comme utilisateur principal");

		int choix = choixClavier();
		currentUser = users.get(choix);
		currentUserIndex = choix;
		System.out.println(
				"************************************************************************************************");
		System.out.println(
				"Utilisateur >>> " + currentUser.getNom() + " " + currentUser.getPrenom() + " est bien selectionné");
		System.out.println(
				"************************************************************************************************");
	}

	public static void listUser() {
		if (currentUser.isModerateur() && currentUser.getNiveau() == 2) {
			System.out.println(
					"********************************  UTILISATEUR  DU RESEAU  **************************************");
			System.out.println(
					"************************************************************************************************");
			list("ALL", Optional.empty());

			System.out.println(
					"************************************************************************************************");
		} else {
			System.out.println(
					"************************************************************************************************");
			System.out
					.println("vous devez avoir le niveau de droits sur [2] pour pouvoir bénéficier de cette ressource");
			System.out.println(
					"************************************************************************************************");
		}
	}
}
