package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;


public class DelUser extends MenuEntry {

	private int acl = 1;

	@Override
	public String display() {

		msg = "Supprimer un utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.delUser();

	}
	
	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}
}

