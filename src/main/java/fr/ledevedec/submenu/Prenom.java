package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;
import fr.ledevedec.reseausocial.User;

public class Prenom extends MenuEntry{


	@Override
	public String display() {
		msg = "Prénom :";
		return msg;
	}

	@Override
	public void exec() {
	
	}
	
	public int exec(int index) {

		return index;
	}


}
