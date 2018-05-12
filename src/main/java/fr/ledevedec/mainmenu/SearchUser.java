package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;


public class SearchUser extends MenuEntry {

	@Override
	public String display() {

		msg = "Recherher un utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.search();

	}

}
