package fr.ledevedec.mainmenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.ledevedec.reseausocial.ErrSaisieException;
import fr.ledevedec.reseausocial.ReseauSocial;

public class Menu {

	private MenuEntry[] menu;
	private List<MenuEntry> menuGenerate;
	private Scanner clavier;
	private CreateUser createUser;
	private ShowUser showUser;
	private SelectUser selectUser;
	private UpdateUser updateUser;
	private DelUser delUser;
	private ListAllUser listAllUser;
	private WriteMessage writeMessage;
	private ShowMessage showMessage;
	private DelMessage delMessage;
	private DelAllMessage delAllMessage;
	private AddFriend addFriend;
	private ShowFriend showFriend;
	private StopSoft stopSoft;
	private UpdateLevel updateLevel;
	private DelFriend delFriend;
	private SearchUser searchUser;

	private int choixInputClavier;

	public Menu() {

		menu = new MenuEntry[17];
		menuGenerate = new ArrayList<MenuEntry>();
		clavier = new Scanner(System.in);
		createUser = new CreateUser();
		
		showUser = new ShowUser();
		selectUser = new SelectUser();
		updateUser = new UpdateUser();
		delUser = new DelUser();
		listAllUser = new ListAllUser();
		writeMessage = new WriteMessage();
		showMessage = new ShowMessage();
		delMessage = new DelMessage();
		delAllMessage = new DelAllMessage();
		addFriend = new AddFriend();
		showFriend = new ShowFriend();
		delFriend = new DelFriend();
		stopSoft = new StopSoft();
		updateLevel = new UpdateLevel();
		searchUser = new SearchUser();

		int i = 0;
		this.menu[i += 1] = createUser;
		this.menu[i += 1] = showUser;
		this.menu[i += 1] = searchUser;
		this.menu[i += 1] = selectUser;
		this.menu[i += 1] = updateUser;
		this.menu[i += 1] = delUser;
		this.menu[i += 1] = listAllUser;
		this.menu[i += 1] = writeMessage;
		this.menu[i += 1] = showMessage;
		this.menu[i += 1] = delMessage;
		this.menu[i += 1] = delAllMessage;
		this.menu[i += 1] = addFriend;
		this.menu[i += 1] = delFriend;
		this.menu[i += 1] = showFriend;
		this.menu[i += 1] = updateLevel;
		this.menu[i += 1] = stopSoft;

		createMenu();

	}

	private void createMenu() {

		int g = 0;
		menuGenerate = new ArrayList<MenuEntry>();
		
		for (int i = 0; i < menu.length; i++) {
			if (menu[i] != null) {

				if (menu[i].isModerator(ReseauSocial.currentUser) && (menu[i].getAcl() == 1 || menu[i].getAcl() == 0)) {
					menuGenerate.add(menu[i]);
					System.out.println("[" + g + "] " + menuGenerate.get(g).display());
					g++;
				} else if (!menu[i].isModerator(ReseauSocial.currentUser) && menu[i].getAcl() == 0) {
					menuGenerate.add(menu[i]);
					System.out.println("[" + g + "] " + menuGenerate.get(g).display());
					g++;
				}

			}

		}

		do {

			choixInputClavier = choixClavier();

			if (choixInputClavier >= menuGenerate.size()) {
				System.out.println("Merci d'entrer une valeur du menu");
			} else {
					menuGenerate.get(choixInputClavier).exec();
					createMenu();

			}

		} while (choixInputClavier >= menuGenerate.size());

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

}
