package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;

public class UpdateLevel extends MenuEntry {

	int acl = 1;
	

	@Override
	public String display() {
		msg = "Modifier niveau de modération";
		return msg;
	}

	@Override
	public void exec() {
		ReseauSocial.changeLevel();
		
	}
	
	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}

