package fr.ledevedec.submenu;

import fr.ledevedec.mainmenu.MenuEntry;



public class Enregistrer extends MenuEntry{


	@Override
	public String display() {
		msg = "Enregister les données :";
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
