package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class DelUser extends MenuEntry {

	private int acl = 1;

	public DelUser(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {

		msg = "Supprimer un utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		userUtility.delUser();

	}

	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}
}
