package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;
import fr.ledevedec.reseausocial.User;

public class AnneeNaissance extends MenuEntry{

	public AnneeNaissance() {
		
	}

	@Override
	public String display() {
		msg = "Année de Naissance :";
		return msg;
	}

	@Override
	public void exec() {
			
	}
	
	public int exec(int index) {

		return index;
	}

}
