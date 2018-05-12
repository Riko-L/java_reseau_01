package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;
import fr.ledevedec.reseausocial.User;

public class RetourMenu extends MenuEntry {


	@Override
	public String display() {
		msg = "Retour au menu :";
		return msg;
	}

	@Override
	public void exec() {

	}
	
	public int exec(int index) {

		return index;
	}

}
