package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class ListAllUser extends MenuEntry {

	private int acl = 1;

	public ListAllUser(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {
		msg = "Lister tous les utilisateurs";

		return msg;
	}

	@Override
	public void exec() {
		userUtility.listUser();

	}

	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}
