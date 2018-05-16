package fr.ledevedec.reseausocial;

import java.util.Scanner;

import fr.ledevedec.mainmenu.Menu;

public class ReseauSocial {

	private UserUtility userUtility;
	private MessageUtility messageUtility;
	private Scanner scan;

	private Menu menu;

	public ReseauSocial() {
		scan = new Scanner(System.in);
		userUtility = new UserUtility(this);
		messageUtility = new MessageUtility(userUtility);
		

	}

	public void start() {

		userUtility.generateUsers();

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
				userUtility.addUser();
			} else if (rep == 'C') {
				System.out
						.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONNECTION <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.print("Saisissez le nom et prénom de votre utilisateur \" Dupond Jean \" : ");
				String search = scan.nextLine();
				boolean isPresent = false;

				for (User user : userUtility.getUsers()) {
					if (user.getFullName().toLowerCase().equals(search.toLowerCase())) {
						isPresent = true;
						userUtility.setCurrentUser(user);
					}

				}
				if (isPresent) {
					userUtility.setCurrentUser(
							userUtility.getUsers().get(userUtility.getUsers().indexOf(userUtility.getCurrentUser())));
					userUtility.setCurrentUserIndex(userUtility.getUsers().indexOf(userUtility.getCurrentUser()));

					System.out.println(
							"*********************************************************************************");
					System.out.println("Vous etes connecté sous l'utilisateur : \""
							+ userUtility.getCurrentUser().getFullName() + "\"");
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

		menu = new Menu(userUtility, messageUtility);

	}

}
