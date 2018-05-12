package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;


public class CreateUser extends MenuEntry {

	private int acl = 1;


	@Override
	public String display() {
		msg = "Créer un nouvel utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.addUser();
	}

	
	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}
