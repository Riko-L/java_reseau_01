package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;


public class SelectUser extends MenuEntry {


	@Override
	public String display() {
		msg = "Selection de l'utilisateur courrant";
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.selectUser();

	}
}
