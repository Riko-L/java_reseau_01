package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class SelectUser extends MenuEntry {

	public SelectUser(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {
		msg = "Selection de l'utilisateur courrant";
		return msg;
	}

	@Override
	public void exec() {
		userUtility.selectUser();

	}
}
