package fr.ledevedec.reseausocial;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputClavierUtility {

	private Scanner scan;

	public InputClavierUtility() {
		scan = new Scanner(System.in);
	}

	public int choixClavier() {
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

}
