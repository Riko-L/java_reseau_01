package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class UpdateLevel extends MenuEntry {

	int acl = 1;

	public UpdateLevel(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {
		msg = "Modifier niveau de modération";
		return msg;
	}

	@Override
	public void exec() {
		userUtility.changeLevel();

	}

	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}
