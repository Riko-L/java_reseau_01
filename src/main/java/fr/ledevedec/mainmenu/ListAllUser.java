package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.ReseauSocial;
import fr.ledevedec.reseausocial.User;

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

	/* (non-Javadoc)
	 * @see com.campus.mainmenu.MenuEntry#isModerator(com.campus.reseausocial.User)
	 */
	@Override
	public boolean isModerator(User user) {
		if (user.isModerateur()) {
			setDisplay(1);
		} else {
			setDisplay(0);
		}

		return user.isModerateur();
	}

	/* (non-Javadoc)
	 * @see com.campus.mainmenu.MenuEntry#getAcl()
	 */
	public int getAcl() {
		return acl;
	}

	/* (non-Javadoc)
	 * @see com.campus.mainmenu.MenuEntry#setAcl(int)
	 */
	public void setAcl(int acl) {
		this.acl = acl;
	}

	/* (non-Javadoc)
	 * @see com.campus.mainmenu.MenuEntry#setDisplay(int)
	 */
	public void setDisplay(int display) {
		this.display = display;
	}
}
