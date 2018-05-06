package fr.ledevedec.reseausocial;

/**
 * Classe Modérateur permet de créer des modérateurs
 * 
 * @author eric
 *
 */
public class Moderateur extends User implements Salarie{

	private int niveau;

	/**
	 * @param nom
	 *            Nom du modérateur
	 * @param prenom
	 *            Prénom du Modérateur
	 * @param pseudo
	 *            Pseudo du Modérateur
	 * @param dateNaissance
	 *            Date de naissance du Modérateur
	 */
	public Moderateur(String nom, String prenom, String pseudo, String dateNaissance) {
		super(nom, prenom, pseudo, dateNaissance);

	}

	/**
	 * Obtenir le niveau de modération
	 * 
	 * <ul>
	 * <li>[1] - modifier/supprimer des messages</li>
	 * <li>[2] - a en plus le droit de supprimer des utilisateurs</li>
	 * </ul>
	 * 
	 * @return niveauDeDroit
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * Définir le niveau de droit modérateur
	 * <ul>
	 * <li>1 - modifier/supprimer des messages</li>
	 * <li>2 - a en plus le droit de supprimer des utilisateurs</li>
	 * </ul>
	 * 
	 * @param niveau
	 *            Niveau de droit Modérateur un chiffre 1 ou 2
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * Savoir si l'utilisateur est un modérateur
	 * 
	 * @return true
	 */
	@Override
	public boolean isModerateur() {
		return true;
	}

	@Override
	public void recevoirPaiement() {
		
		
	}

}
