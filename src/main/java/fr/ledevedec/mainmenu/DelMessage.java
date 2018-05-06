package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;
import fr.ledevedec.reseausocial.User;

public class DelMessage extends MenuEntry {

	@Override
	public String display() {
		switch (this.display) {
		case 0:
			msg = "Supprimer un message";
			break;
		case 1:
			msg = "Supprimer un message d'un utilisateur";
			break;
		}
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.delMessage();

	}

	@Override
	public boolean isModerator(User user) {
		if (user.isModerateur()) {
			setDisplay(1);
		} else {
			setDisplay(0);
		}

		return user.isModerateur();
	}

	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

}
