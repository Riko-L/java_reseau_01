package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.MessageUtility;

public class DelMessage extends MenuEntry {

	public DelMessage(MessageUtility messageUtility) {
		this.messageUtility = messageUtility;
	}

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Supprimer un message";
			break;
		case 1:
			msg = "Supprimer un message d'un utilisateur";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		messageUtility.delMessage();

	}

}
