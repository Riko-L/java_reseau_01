package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;
import fr.ledevedec.reseausocial.User;

public class DelAllMessage extends MenuEntry {

	private int acl = 1;

	@Override
	public String display() {

		msg = "Effacer la messagerie d'un utilisateur";
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.delMessagerie();

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
