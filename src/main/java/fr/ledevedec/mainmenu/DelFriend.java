package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class DelFriend extends MenuEntry {

	public DelFriend(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Supprimer un ami";
			break;
		case 1:
			msg = "Supprimer un ami à un des utilisateurs";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		userUtility.delFriend();

	}
}
