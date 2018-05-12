package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;


public class ListAllUser extends MenuEntry {

	
	private int acl = 1;
	
	/* (non-Javadoc)
	 * @see com.campus.mainmenu.MenuEntry#display()
	 */
	@Override
	public String display() {
		msg = "Lister tous les utilisateurs";
		
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.campus.mainmenu.MenuEntry#exec()
	 */
	@Override
	public void exec() {
		ReseauSocial.listUser();

	}
	
	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}


}
