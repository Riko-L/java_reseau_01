package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.MessageUtility;

public class WriteMessage extends MenuEntry {

	public WriteMessage(MessageUtility messageUtility) {
		this.messageUtility = messageUtility;
	}

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Ecrire un message";
			break;
		case 1:
			msg = "Ecrire un message";
			break;
		}
		return msg;

	}

	@Override
	public void exec() {
		messageUtility.writeMessage();

	}

}
