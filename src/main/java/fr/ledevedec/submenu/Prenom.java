package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;
import fr.ledevedec.reseausocial.ReseauSocial;


public class Prenom extends MenuEntry{


	@Override
	public String display() {
		msg = "Prénom :";
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
