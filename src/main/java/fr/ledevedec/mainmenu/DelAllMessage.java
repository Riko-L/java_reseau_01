package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.MessageUtility;
import fr.ledevedec.reseausocial.UserUtility;

public class DelAllMessage extends MenuEntry {

	private int acl = 1;

	public DelAllMessage(MessageUtility messageUtility) {
		this.messageUtility = messageUtility;
	}

	@Override
	public String display() {

		msg = "Effacer la messagerie d'un utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		messageUtility.delMessagerie();

	}

	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}
