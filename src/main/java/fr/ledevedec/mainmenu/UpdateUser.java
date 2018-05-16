package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class UpdateUser extends MenuEntry {

	public UpdateUser(UserUtility userUtility) {
		this.userUtility = userUtility;
	}

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Modifier mes informations";
			break;
		case 1:
			msg = "Modifier les informations d'un utilisateur";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		userUtility.updateProfil();

	}

}
