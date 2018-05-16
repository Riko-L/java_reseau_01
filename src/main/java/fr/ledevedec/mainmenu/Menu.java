package fr.ledevedec.mainmenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.ledevedec.reseausocial.ErrSaisieException;
import fr.ledevedec.reseausocial.MessageUtility;
import fr.ledevedec.reseausocial.UserUtility;

public class Menu {

	private List <MenuEntry> menu;
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
	
	private UserUtility userUtility;
	private MessageUtility messageUtility;

	public Menu(UserUtility userUtility, MessageUtility messageUtility) {

		this.userUtility = userUtility;
		this.messageUtility = messageUtility;
		
		menu = new ArrayList<MenuEntry>();
		menuGenerate = new ArrayList<MenuEntry>();
		clavier = new Scanner(System.in);
		createUser = new CreateUser(userUtility);
		
		showUser = new ShowUser(userUtility);
		selectUser = new SelectUser(userUtility);
		updateUser = new UpdateUser(userUtility);
		delUser = new DelUser(userUtility);
		listAllUser = new ListAllUser(userUtility);
		writeMessage = new WriteMessage(messageUtility);
		showMessage = new ShowMessage(messageUtility);
		delMessage = new DelMessage(messageUtility);
		delAllMessage = new DelAllMessage(messageUtility);
		addFriend = new AddFriend(userUtility);
		showFriend = new ShowFriend(userUtility);
		delFriend = new DelFriend(userUtility);
		stopSoft = new StopSoft();
		updateLevel = new UpdateLevel(userUtility);
		searchUser = new SearchUser(userUtility);

		
		menu.add(createUser);
		menu.add(showUser);
		menu.add(searchUser);
		menu.add(selectUser);
		menu.add(updateUser);
		menu.add(delUser);
		menu.add(listAllUser);
		menu.add(writeMessage);
		menu.add(showMessage);
		menu.add(delMessage);
		menu.add(delAllMessage);
		menu.add(addFriend);
		menu.add(delFriend);
		menu.add(showFriend);
		menu.add(updateLevel);
		menu.add(stopSoft);

		createMenu();

	}

	private void createMenu() {

		int g = 0;
		menuGenerate = new ArrayList<MenuEntry>();
		
		for (int i = 0; i < menu.size(); i++) {
			if (menu.get(i) != null) {

				if (menu.get(i).isModerator(userUtility.getCurrentUser()) && (menu.get(i).getAcl() == 1 || menu.get(i).getAcl() == 0)) {
					menuGenerate.add(menu.get(i));
					System.out.println("[" + g + "] " + menuGenerate.get(g).display());
					g++;
				} else if (!menu.get(i).isModerator(userUtility.getCurrentUser()) && menu.get(i).getAcl() == 0) {
					menuGenerate.add(menu.get(i));
					System.out.println("[" + g + "] " + menuGenerate.get(g).display());
					g++;
				}

			}

		}

		do {

			choixInputClavier = choixClavier();

			if (choixInputClavier >= menuGenerate.size() || choixInputClavier < 0) {
				System.out.println("Merci d'entrer une valeur du menu");
				createMenu();
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
