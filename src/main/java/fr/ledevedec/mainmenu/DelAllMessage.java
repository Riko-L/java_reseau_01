package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;

public class DelAllMessage extends MenuEntry {

	private int acl = 1;

	@Override
	public String display() {

		msg = "Effacer la messagerie d'un utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.delMessagerie();

	}
	
	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}
