package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;


public class ShowFriend extends MenuEntry {

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Afficher mes amis";
			break;
		case 1:
			msg = "Afficher les amis d'un utilisateur";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.showFriend();

	}

}
