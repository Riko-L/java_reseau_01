package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class ShowFriend extends MenuEntry {

	public ShowFriend(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Afficher mes amis";
			break;
		case 1:
			msg = "Afficher les amis d'un utilisateur";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		userUtility.showFriend();

	}

}
