package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;

public class AddFriend extends MenuEntry{
	

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Ajouter un ami";
			break;
		case 1:
			msg = "Ajouter un ami à un des utilisateurs";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.addFriend();
		
	}

	
}
