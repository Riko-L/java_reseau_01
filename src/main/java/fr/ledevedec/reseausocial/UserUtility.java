package fr.ledevedec.reseausocial;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

import fr.ledevedec.mainmenu.Menu;
import fr.ledevedec.submenu.SubMenuUpdateUser;

public class UserUtility {

	private User user;
	private Moderateur mod;
	private ReseauSocial reseau;
	private User currentUser;
	private long currentUserIndex;
	private SubMenuUpdateUser subMenu;

	private char reponse = 'O';
	private long numeroProfil;
	private long numeroFriend;

	// private List<User> users = new ArrayList<User>();

	private InputClavierUtility inputClavier;
	private Scanner scan;
	private UserDAO userDao;

	private String nom;
	private String prenom;
	private String pseudo;
	private String anneeNaissance;

	public UserUtility(ReseauSocial reseau) {
		this.reseau = reseau;
		userDao = new UserDAO();
		inputClavier = new InputClavierUtility();
		subMenu = new SubMenuUpdateUser();
		scan = new Scanner(System.in);

	}

	public boolean list(String args, Optional<Long> index) {
		Long userIndex = index.isPresent() ? index.get() : null;
		boolean result = false;
		List<User> users = null;

		users = userDao.getAllUser();

		for (User user : users) {

			switch (args) {
			case "ALL": // ok

				if (user == getCurrentUser()) {
					System.out.println("Utilisateur [" + user.getId() + "]*: " + user.getNom() + " | "
							+ user.getPrenom() + " | " + user.getDateDeNaissance() + " | " + user.getPseudo() + " | "
							+ user.isModerateur() + " | " + user.getNiveau());
				} else if (user != getCurrentUser()) {
					System.out.println("Utilisateur [" + user.getId() + "] : " + user.getNom() + " | "
							+ user.getPrenom() + " | " + user.getDateDeNaissance() + " | " + user.getPseudo() + " | "
							+ user.isModerateur() + " | " + user.getNiveau());

				}

				break;
			case "FULLNAME": // Ok

				if (user != getCurrentUser()) {
					System.out
							.println("Utilisateur [" + user.getId() + "] : " + user.getNom() + " " + user.getPrenom());
				}

				break;

			case "FULLNAME+CURRENT": // Ok

				System.out.println("Utilisateur [" + user.getId() + "] : " + user.getNom() + " " + user.getPrenom());

				break;

			case "MOD": // ok

				if (user.isModerateur()) {
					System.out.println("Utilisateur [" + user.getId() + "] : " + user.getNom() + " " + user.getPrenom()
							+ " | Niveau de modération : " + user.getNiveau());
				}

				break;

			case "MESSAGE":

					for (Message msg : user.getMessages()) {
							System.out.println("Message de :" + users.get((int)(msg.getDestinataire())) );
							System.out.println("From :" + users.get((int)(msg.getExpediteur())) );
							System.out.println("Contenu :" + msg.getContenu() );
					

				}
				break;

			/*
			 * case "MESSAGE": if (userIndex != null &&
			 * users.containsValue(users.get(userIndex))) { if
			 * (!users.get(userIndex).getMessages().isEmpty()) { for (int i = 0; i <
			 * users.get(userIndex).getMessages().size(); i++) {
			 * System.out.println(" Message  [" + i + "] : "); System.out.println(
			 * " 	From : \"" + users.get(userIndex).getMessages().get(i).getExpediteur() +
			 * "\""); System.out.println( " 	To : \"" +
			 * users.get(userIndex).getMessages().get(i).getDestinataire() + "\"");
			 * System.out.println( " 	Contenu : \"" +
			 * users.get(userIndex).getMessages().get(i).getContenu() + "\"");
			 * System.out.println("***************"); } } else {
			 * System.out.println("Messagerie vide"); result = true;
			 * 
			 * } }
			 * 
			 * break;
			 * 
			 * case "MESSAGE_ENVOYE": if (userIndex != null &&
			 * users.containsValue(users.get(userIndex))) { int nbre = 0; for (int i = 0; i
			 * < users.size(); i++) {
			 * 
			 * if (users.get(i) != null) {
			 * 
			 * for (int j = 0; j < users.get(i).getMessages().size(); j++) { if
			 * (users.get(i).getMessages() != null) {
			 * 
			 * if (users.get(i).getMessages().get(j).getExpediteur()
			 * .contains(users.get(userIndex).getFullName())) { nbre += 1;
			 * System.out.println(users.get(i).getFullName());
			 * System.out.println(" Message  [" + j + "] : ");
			 * 
			 * System.out.println(" 	To : \"" +
			 * users.get(i).getMessages().get(j).getDestinataire() + "\"");
			 * System.out.println(" 	Contenu : \"" +
			 * users.get(i).getMessages().get(j).getContenu() + "\"");
			 * System.out.println("***************"); } } } }
			 * 
			 * }
			 * 
			 * System.out.println("Vous avez envoyé " + nbre + " messages");
			 * 
			 * }
			 * 
			 * break;
			 */

			case "FRIENDS":

				if (user.getId() == userIndex) {
					for (User ami : user.getListAmi()) {

						System.out.println("Ami [" + ami.getId() + "] : " + ami.getFullName());

					}
				}
				break;

			case "FULLNAME-USER-FRIENDS": // OK

				if (user.listAmi.contains(currentUser)) {
					System.out
							.println("Utilisateur [" + user.getId() + "] : " + user.getNom() + " " + user.getPrenom());

				}

				break;

			}

		}

		return result;

	}

	public void changeLevel() {
		System.out.println("**************************** CHANGER LEVEL MODERATEUR *****************************");
		System.out.println("***********************************************************************************");
		list("MOD", Optional.empty());

		numeroProfil = inputClavier.choixClavier();
		User selectedUser = userDao.find(numeroProfil);
		if (selectedUser != null && selectedUser instanceof Moderateur) {

			System.out.println("Entrer le niveau à attribuer : [1] - [2]");

			int level;
			do {
				level = inputClavier.choixClavier();

				if (level == 1 || level == 2) {
					((Moderateur) selectedUser).setNiveau(level);
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

	public void generateUsers() {

		System.out.println("********************* GENERATION >> UTILISATEUR | MODERATEUR **********************");
		System.out.println("***********************************************************************************");

		try {
			mod = new Moderateur("LE DEVEDEC", "Eric", "riko74", "1982");
			mod.setNiveau(2);
			currentUser = userDao.create(mod);
			currentUserIndex = currentUser.getId();

			user = new User("BIAGI", "Alexandre", "BigBoy", "1968");
			userDao.create(user);
			user = new User("DUPON", "Jean", "The Cat", "1945");
			userDao.create(user);
			user = new User("PIG", "Peppa", "Petit cochon", "1999");
			userDao.create(user);
			user = new User("BOND", "Jams", "251Hys", "1765");
			userDao.create(user);
			user = new User("PIGNION", "François", "PF2008", "2008");
			userDao.create(user);
			mod = new Moderateur("FILLION", "Charles", "Fcha74", "1972");
			mod.setNiveau(1);
			userDao.create(mod);
		} catch (SQLException e) {

		}

		System.out.println("Génération terminé");

		System.out.println("***********************************************************************************");

	}

	public void addUser() {
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

		System.out.println("Voulez-vous lui attribuer les droits MODERATEUR: [O] ou [N]");
		reponse = scan.nextLine().charAt(0);

		if (reponse == 'O') {

			System.out.println("Niveau de Modération: [1] ou [2] ");
			int choix = inputClavier.choixClavier();
			mod = new Moderateur(nom, prenom, pseudo, anneeNaissance);
			mod.setNiveau(choix);
			try {
				userDao.create(mod);
			} catch (SQLException e) {

				e.printStackTrace();
			}

			System.out.println("\"Modérateur Créer\"");
			System.out.println("***********************************************************************************");

			selectUser();

		} else if (reponse == 'N') {

			user = new User(nom, prenom, pseudo, anneeNaissance);

			try {
				user = userDao.create(user);

			} catch (SQLException e) {

				e.printStackTrace();
			}
			setCurrentUser(user);
			setCurrentUserIndex(user.getId());

			System.out.println("\"Utilisateur Créer\"");
			System.out.println("***********************************************************************************");
		}

	}

	public void showProfil() {

		if (getCurrentUser().isModerateur()) {
			System.out.println("************************* AFFICHER INFOS UTILISATEURS ***************************");
			System.out.println("*********************************************************************************");
			list("FULLNAME+CURRENT", Optional.empty());

			numeroProfil = inputClavier.choixClavier();
			User selectedUser = userDao.find(numeroProfil);
			if (selectedUser != null) {

				System.out.println(">>>>>>>>>>>>>>>>>>>>     Voici les informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println("*********************************************************************************");
				System.out.println("* Nom : " + selectedUser.getNom());
				System.out.println("* Prénom : " + selectedUser.getPrenom());
				System.out.println("* Année de naissance : " + selectedUser.getDateDeNaissance());
				System.out.println("*********************************************************************************");
			}

		} else {
			if (getCurrentUser() != null) {

				System.out.println(">>>>>>>>>>>>>>>>>>>>     Voici les informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println("*********************************************************************************");
				System.out.println("* Nom : " + getCurrentUser().getNom());
				System.out.println("* Prénom : " + getCurrentUser().getPrenom());
				System.out.println("* Année de naissance : " + getCurrentUser().getDateDeNaissance());
				System.out.println("*********************************************************************************");
			}

		}
	}

	public void updateProfil() {

		String updateInfo;

		if (getCurrentUser().isModerateur()) {

			System.out.println("************************* MODIFIER INFOS UTILISATEURS ***************************");
			System.out.println("*********************************************************************************");
			list("FULLNAME+CURRENT", Optional.empty());

			numeroProfil = inputClavier.choixClavier();
			User selectedUser = userDao.find(numeroProfil);
			reponse = 'O';

			System.out.println("*********************************************************************************");
			System.out.println("Modification de " + selectedUser.getNom() + " " + selectedUser.getPrenom());
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				subMenu.createMenu();

				switch (subMenu.getChoixUser()) {
				case 0:
					System.out.print("Entrer Nom:");
					updateInfo = scan.nextLine();
					selectedUser.setNom(updateInfo);

					break;
				case 1:
					System.out.print("Entrer Prénom:");
					updateInfo = scan.nextLine();
					selectedUser.setPrenom(updateInfo);

					break;
				case 2:
					System.out.print("Entrer Année de naissance:");
					updateInfo = scan.nextLine();
					selectedUser.setDateDeNaissance(updateInfo);

					break;
				case 3:
					userDao.update(selectedUser);

					System.out.println("Enregistrer");
					System.out.println(
							">>>>>>>>>>>>>>>>>>>>     Voici les nouvelles informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					System.out.println(
							"*******************************************************************************************");
					System.out.println("* Nom : " + selectedUser.getNom());
					System.out.println("* Prénom : " + selectedUser.getPrenom());
					System.out.println("* Année de naissance : " + selectedUser.getDateDeNaissance());
					System.out.println(
							"*******************************************************************************************");
					break;
				case 4:
					reponse = ' ';
				}

			}
		} else {
			System.out.println("*********************************************************************************");
			System.out.println("Modification de " + getCurrentUser().getNom() + " " + getCurrentUser().getPrenom());
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {
				subMenu.createMenu();

				switch (subMenu.getChoixUser()) {
				case 0:
					System.out.print("Entrer Nom:");
					updateInfo = scan.nextLine();
					getCurrentUser().setNom(updateInfo);

					break;
				case 1:
					System.out.print("Entrer Prénom:");
					updateInfo = scan.nextLine();
					getCurrentUser().setPrenom(updateInfo);

					break;
				case 2:
					System.out.print("Entrer Année de naissance:");
					updateInfo = scan.nextLine();
					getCurrentUser().setDateDeNaissance(updateInfo);

					break;

				case 3:

					userDao.update(getCurrentUser());

					System.out.println("Enregistrer");
					System.out.println(
							">>>>>>>>>>>>>>>>>>>>     Voici les nouvelles informations    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
					System.out.println(
							"*******************************************************************************************");
					System.out.println("* Nom : " + getCurrentUser().getNom());
					System.out.println("* Prénom : " + getCurrentUser().getPrenom());
					System.out.println("* Année de naissance : " + getCurrentUser().getDateDeNaissance());
					System.out.println(
							"*******************************************************************************************");
					break;
				case 4:
					reponse = ' ';
					break;
				}
			}
		}
	}

	public void addFriend() {
		if (getCurrentUser().isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* AJOUTER UN AMI **********************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				list("FULLNAME+CURRENT", Optional.empty());
				System.out.println("Selectionner l'utilisateur pour lequel vous souhaitez ajouter un ami");
				numeroProfil = inputClavier.choixClavier();

				System.out.println(
						"************************************************************************************************");
				list("FULLNAME-USER-FRIENDS", Optional.of(numeroProfil));

				System.out
						.println("Voici une liste des différents utilisateur.Entrer son numéro pour l'ajouter en ami");

				numeroFriend = inputClavier.choixClavier();

				userDao.addfriend(userDao.find(numeroProfil), userDao.find(numeroFriend));
				userDao.addfriend(userDao.find(numeroFriend), userDao.find(numeroProfil));

				System.out.println(
						"************************************************************************************************");
				System.out.println("Ami ajouté");
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

			list("FULLNAME-USER-FRIENDS", Optional.of(getCurrentUserIndex()));

			System.out.println("Voici une liste des différents utilisateur.Entrer son numéro pour l'ajouter en ami");

			numeroFriend = inputClavier.choixClavier();

			userDao.addfriend(getCurrentUser(), userDao.find(numeroFriend));
			userDao.addfriend(userDao.find(numeroFriend), getCurrentUser());

			System.out.println("*********************************************************************************");
			System.out.println("Ami bien été ajouté");
			System.out.println("*********************************************************************************");
		}

	}

	public void showFriend() {
		if (getCurrentUser().isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* AFFICHER AMIS ***********************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				list("FULLNAME+CURRENT", Optional.empty());

				numeroProfil = inputClavier.choixClavier();

				User selectedUser = userDao.find(numeroProfil);

				System.out.println("voici les amis de l'utilisateur " + selectedUser.getFullName());
				System.out.println("*********************************************************************************");

				list("FRIENDS", Optional.of(numeroProfil));
				reponse = ' ';

				while (reponse != 'P' && reponse != 'O') {

					System.out.println("voir les amis d'un autre profil O | Retour menu P ");
					reponse = scan.nextLine().charAt(0);

				}
			}

		} else {
			System.out.println("******************************* AFFICHER AMIS ***********************************");
			System.out.println("*********************************************************************************");

			System.out.println("voici les amis de l'utilisateur " + getCurrentUser().getFullName());
			System.out.println("*********************************************************************************");
			list("FRIENDS", Optional.of(getCurrentUserIndex()));

			System.out.println("*********************************************************************************");
		}
	}

	public void delFriend() {
		if (getCurrentUser().isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* EFFACER AMIS ***********************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {

				list("FULLNAME+CURRENT", Optional.empty());

				numeroProfil = inputClavier.choixClavier();
				User selectedUser = userDao.find(numeroProfil);

				System.out.println("voici les amis de l'utilisateur " + selectedUser.getFullName());
				System.out.println("*********************************************************************************");

				list("FRIENDS", Optional.of(numeroProfil));

				System.out.println("*********************************************************************************");

				numeroFriend = inputClavier.choixClavier();
				User ami = userDao.find(numeroProfil);
				userDao.delFriend(ami);

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

			System.out.println("voici les amis de l'utilisateur " + getCurrentUser().getFullName());
			System.out.println("*********************************************************************************");
			list("FRIENDS", Optional.of(getCurrentUserIndex()));
			numeroFriend = inputClavier.choixClavier();
			User ami = userDao.find(numeroProfil);
			userDao.delFriend(ami);

			System.out.println("Votre ami à été supprimé");

			System.out.println("*********************************************************************************");
		}

	}

	public void search() {
		System.out.println("******************************* RECHERCHE UTILISATEUR ***********************************");
		System.out.println("*****************************************************************************************");
		System.out.println("Rechercher un utilisateur par \" Nom et Prénom \" : ");

		System.out.println("NON de l'utilisateur :");
		String nomS = scan.nextLine();
		System.out.println("PRENON de l'utilisateur :");
		String prenomS = scan.nextLine();

		User user = new User();
		user.setNom(nomS);
		user.setPrenom(prenomS);

		user = userDao.find(user);

		if (user != null) {
			System.out.println(
					"*****************************************************************************************");
			System.out.println("L'utilisateur \"" + user.getFullName() + "\" avec l'ID [" + user.getId()
					+ "] corresponds à votre recherhe");

			// if (getCurrentUser().getFriends().contains(searchList.get(i))) {
			// System.out.println("Vous êtes déjà ami avec cet utilisateur");
			// }
			System.out.println(
					"*****************************************************************************************");
		}

		System.out.println("Utilisateur non trouvé");

	}

	public void delUser() {
		if (getCurrentUser().isModerateur() && getCurrentUser().getNiveau() == 2) {
			System.out.println(
					"******************************* SUPPRIMER UTILISATEUR ***********************************");
			System.out.println(
					"*****************************************************************************************");

			list("FULLNAME", Optional.empty());
			long choix = inputClavier.choixClavier();
			userDao.delete(choix);

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

	public void selectUser() {
		System.out.println(
				"**********************************  UTILISATEUR  COURANT  **************************************");
		System.out.println(
				"************************************************************************************************");

		list("FULLNAME", Optional.empty());

		System.out.println(
				"Voici une liste d'utilisateurs. Entrer un numéro pour le choisir comme utilisateur principal");

		long choix = inputClavier.choixClavier();
		User user = userDao.find(choix);
		setCurrentUser(user);
		setCurrentUserIndex(choix);
		System.out.println(
				"************************************************************************************************");
		System.out.println("Utilisateur >>> " + getCurrentUser().getNom() + " " + getCurrentUser().getPrenom()
				+ " est bien selectionné");
		System.out.println(
				"************************************************************************************************");
	}

	public void listUser() {
		if (getCurrentUser().isModerateur() && getCurrentUser().getNiveau() == 2) {
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

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public long getCurrentUserIndex() {
		return currentUserIndex;
	}

	public void setCurrentUserIndex(long l) {
		this.currentUserIndex = l;
	}

	public boolean checkLogin(String InNom, String InPrenom) {

		boolean login = false;

		User user = new User();

		user.setNom(InNom);
		user.setPrenom(InPrenom);

		User selectedUser = userDao.find(user);

		if (selectedUser != null) {

			setCurrentUser(selectedUser);
			setCurrentUserIndex(selectedUser.getId());
			login = true;
		} else {
			login = false;
		}

		return login;
	}

}
