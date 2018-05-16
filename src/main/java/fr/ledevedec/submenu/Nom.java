package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;
import fr.ledevedec.reseausocial.ReseauSocial;


public class Nom extends MenuEntry {

	public Nom() {

	}

	@Override
	public String display() {
		msg = "Nom :";
		return msg;
	}


	public int exec(int index) {

		return index;
	}

	@Override
	public void exec() {
		// TODO Auto-generated method stub
		
	}

	

}
