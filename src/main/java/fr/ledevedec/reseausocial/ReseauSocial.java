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
				System.out.print("Saisissez le Nom de votre utilisateur : ");
				String searchNom = scan.nextLine();
				System.out.print("Saisissez le Prénom de votre utilisateur : ");
				String searchPrenom = scan.nextLine();
				boolean isPresent = false;

				isPresent = userUtility.checkLogin(searchNom, searchPrenom);

				if (isPresent) {

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
