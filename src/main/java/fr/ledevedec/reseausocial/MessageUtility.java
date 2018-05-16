package fr.ledevedec.reseausocial;

import java.util.Optional;
import java.util.Scanner;

public class MessageUtility {

	private InputClavierUtility inputClavier;
	private UserUtility userUtility;
	private Scanner scan;
	
	private int numeroProfil;
	private char reponse;
	
	
	public MessageUtility(UserUtility userUtility) {
		scan = new Scanner(System.in);
		this.userUtility = userUtility;
		inputClavier = new InputClavierUtility();
	}
	
	public  void writeMessage() {
		reponse = 'O';
		System.out.println("****************************** ECRIRE UN MESSAGE ********************************");
		System.out.println("*********************************************************************************");

		while (reponse == 'O') {
			userUtility.list("FULLNAME", Optional.empty());
			numeroProfil = inputClavier.choixClavier();

			if (userUtility.getUsers().get(numeroProfil) != null) {
				System.out.println("Entrer votre message pour " + userUtility.getUsers().get(numeroProfil).getNom() + " "
						+ userUtility.getUsers().get(numeroProfil).getPrenom() + " :");
				System.out.print(">> ");

				String message = scan.nextLine();

				userUtility.getUsers().get(numeroProfil).addMessages(new Message(userUtility.getUsers().get(userUtility.getCurrentUserIndex()).getFullName(),
						userUtility.getUsers().get(numeroProfil).getFullName(), message));
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

	public  void showMessage() {
		if (userUtility.getCurrentUser().isModerateur()) {
			reponse = 'O';
			System.out.println("************************** MESSAGES DES UTILISATEURS ****************************");
			System.out.println("*********************************************************************************");

			while (reponse == 'O') {
				userUtility.list("FULLNAME+NB-MESSAGE", Optional.empty());

				numeroProfil = inputClavier.choixClavier();
				System.out.println("*********************************************************************************");
				if (userUtility.getUsers().get(numeroProfil) != null) {
					System.out.println("Messages de l'utilisateur : " + userUtility.getUsers().get(numeroProfil).getNom() + " "
							+ userUtility.getUsers().get(numeroProfil).getPrenom());

					userUtility.list("MESSAGE", Optional.of(numeroProfil));
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
			int choix = inputClavier.choixClavier();
			if (choix == 1) {
				System.out.println(
						"*******************************  MES MESSAGES RECU  **********************************");
				System.out.println(
						"**************************************************************************************");
				userUtility.list("MESSAGE", Optional.of(userUtility.getCurrentUserIndex()));
				System.out.println(
						"**************************************************************************************");
			} else if (choix == 2) {
				System.out.println(
						"*******************************  MES MESSAGES ENVOYE  **********************************");
				System.out.println(
						"**************************************************************************************");
				userUtility.list("MESSAGE_ENVOYE", Optional.of(userUtility.getCurrentUserIndex()));
				System.out.println(
						"**************************************************************************************");
			}
		}

	}

	public  void delMessagerie() {
		if (userUtility.getCurrentUser().isModerateur()) {
			System.out.println(
					"******************************* VIDER MESSAGERIE UTILISATEUR ***********************************");
			System.out.println(
					"************************************************************************************************");
			userUtility.list("FULLNAME+NB-MESSAGE", Optional.empty());

			numeroProfil = inputClavier.choixClavier();

			userUtility.getUsers().get(numeroProfil).viderMessages();
			System.out.println(
					"************************************************************************************************");
			System.out.println("La messagerie de l'utilisateur " + userUtility.getUsers().get(numeroProfil).getNom() + " "
					+ userUtility.getUsers().get(numeroProfil).getPrenom() + " est vide");
			System.out.println(
					"************************************************************************************************");
		}
	}

	public  void delMessage() {
		if (userUtility.getCurrentUser().isModerateur()) {
			reponse = 'O';
			System.out.println("******************************* SUPPRIMER MESSAGE ***********************************");
			System.out.println("*************************************************************************************");
			while (reponse == 'O') {
				userUtility.list("FULLNAME+NB-MESSAGE", Optional.empty());

				numeroProfil = inputClavier.choixClavier();
				System.out.println(
						"*************************************************************************************");
				boolean isEmptyList = userUtility.list("MESSAGE", Optional.of(numeroProfil));
				System.out.println(
						"*************************************************************************************");
				if (!isEmptyList) {

					userUtility.getUsers().get(numeroProfil).delMessages(inputClavier.choixClavier());
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
			userUtility.list("MESSAGE", Optional.of(userUtility.getCurrentUserIndex()));
			userUtility.getCurrentUser().delMessages(inputClavier.choixClavier());
			System.out.println("*************************************************************************************");
			System.out.println("Message supprimé !");
			System.out.println("*************************************************************************************");
		}

	}

	
}
