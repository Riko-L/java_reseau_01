package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class CreateUser extends MenuEntry {

	private int acl = 1;

	public CreateUser(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {
		msg = "Créer un nouvel utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		userUtility.addUser();
	}

	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}
