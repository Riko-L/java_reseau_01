package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;


public class ShowMessage extends MenuEntry {

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Afficher mes messages";
			break;
		case 1:
			msg = "Afficher les messages d'un utilisateur";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.showMessage();

	}

}
