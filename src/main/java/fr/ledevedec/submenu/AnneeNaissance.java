package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;



public class AnneeNaissance extends MenuEntry{

	public AnneeNaissance() {
		
	}

	@Override
	public String display() {
		msg = "Année de Naissance :";
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
