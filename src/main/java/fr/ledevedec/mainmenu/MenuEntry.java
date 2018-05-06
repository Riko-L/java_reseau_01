package fr.ledevedec.mainmenu;

import fr.ledevedec.reseausocial.User;

public abstract class MenuEntry {
	
	/**
	 * Defini le droit d'affichage du contenu de l'élément du menu
	 * 
	 * [0] pour utilisateur normale
	 * [1] pour utilisateur modérateur
	 */
	protected int display;
	
	/**
	 *  Texte de l'élément du menu
	 */
	protected String msg;
	
	/**
	 * Defini le droit d'affichage d'un élément du menu
	 * 
	 * [0] pour utilisateur normale
	 * [1] pour utilisateur modérateur
	 */
	protected int acl = 0;

	/**
	 * 
	 * 
	 * @return Une chaine de caractère de l'élément du menu demandé
	 */
	public abstract String display();
	
	public abstract void exec();
	
	
	public  int exec(int index) {
		return -1;
	}

	
	public abstract boolean isModerator(User user);

	public abstract int getAcl();

	public abstract void setAcl(int acl);
	
	public abstract void setDisplay(int display);

	
}
