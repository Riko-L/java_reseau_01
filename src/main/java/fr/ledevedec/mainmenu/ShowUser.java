package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.UserUtility;

public class ShowUser extends MenuEntry {

	
	public ShowUser(UserUtility userUtility) {
		this.userUtility = userUtility;
	}
	
	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Afficher mes informations";
			break;
		case 1:
			msg = "Afficher les informations d'un utilisateur";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		userUtility.showProfil();

	}
}
