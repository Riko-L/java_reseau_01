package fr.ledevedec.reseausocial;

import java.util.List;

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
	
	
	public Moderateur() {
		super();
	}
	
	public Moderateur(long id, String nom, String prenom, String pseudo, String dateDeNaissance, List<User> listAmi) {
		super(id ,nom, prenom, pseudo, dateDeNaissance, listAmi);

	}
	
	public Moderateur(String nom, String prenom, String pseudo, String dateDeNaissance) {
		super(nom, prenom, pseudo, dateDeNaissance);

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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		if (prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!prenom.equals(other.prenom)) {
			return false;
		}
		if (pseudo == null) {
			if (other.pseudo != null) {
				return false;
			}
		} else if (!pseudo.equals(other.pseudo)) {
			return false;
		}
		return true;
	}
	

}
