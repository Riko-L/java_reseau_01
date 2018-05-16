package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.MessageUtility;

public class ShowMessage extends MenuEntry {

	public ShowMessage(MessageUtility messageUtility) {
		this.messageUtility = messageUtility;
	}

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
		messageUtility.showMessage();

	}

}
