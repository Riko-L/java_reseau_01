package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;

public class WriteMessage extends MenuEntry {

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
		ReseauSocial.writeMessage();

	}

}
