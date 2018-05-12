package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;
import fr.ledevedec.reseausocial.ReseauSocial;
import fr.ledevedec.reseausocial.User;

public class Nom extends MenuEntry {

	public Nom() {

	}

	@Override
	public String display() {
		msg = "Nom :";
		return msg;
	}

	@Override
	public void exec() {

	}

	public int exec(int index) {

		return index;
	}

	

}
