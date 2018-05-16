package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class SearchUser extends MenuEntry {

	public SearchUser(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {

		msg = "Recherher un utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		userUtility.search();

	}

}
