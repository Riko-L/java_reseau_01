package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.User;

public abstract class MenuEntry {

	/**
	 * Defini le droit d'affichage du contenu de l'élément du menu
	 * 
	 * [0] pour utilisateur normale [1] pour utilisateur modérateur
	 */
	protected int display;

	/**
	 * Texte de l'élément du menu
	 */
	protected String msg;

	/**
	 * Defini le droit d'affichage d'un élément du menu
	 * 
	 * [0] pour utilisateur normale [1] pour utilisateur modérateur
	 */
	protected int acl = 0;

	/**
	 * 
	 * 
	 * @return Une chaine de caractère de l'élément du menu demandé
	 */
	public abstract String display();

	/**
	 * Execute une methode donnée en fonction du choix du menu
	 */
	public abstract void exec();

	/**
	 * 
	 * @param index
	 *            Choix du sous menu
	 * @return
	 */
	public int exec(int index) {
		return -1;
	}

	/**
	 * Retour vrai si l'utilisateur est un modérateur et paramètre le display en
	 * conséquence.
	 * 
	 * @param user
	 * @return
	 */
	public boolean isModerator(User user) {
		if (user.isModerateur()) {
			setDisplay(1);
		} else {
			setDisplay(0);
		}

		return user.isModerateur();
	}

	/**
	 * Obtenir le niveau de droits [0] pour utilisateur normale [1] pour utilisateur
	 * modérateur
	 * 
	 * @return
	 */
	public int getAcl() {
		return acl;
	}

	/**
	 * Affecter le niveau de droits [0] pour utilisateur normale [1] pour
	 * utilisateur modérateur
	 * 
	 * @param acl
	 */
	public void setAcl(int acl) {
		this.acl = acl;
	}

	/**
	 * definir le display [0] pour utilisateur normale [1] pour utilisateur
	 * modérateur
	 * 
	 * @param display
	 */
	public void setDisplay(int display) {
		this.display = display;
	}

}
