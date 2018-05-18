
package fr.ledevedec.reseausocial;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Utilisateur permet de créer des utilisateurs
 * 
 * @author eric
 *
 */
public class User extends Personne {

	/**
	 * @see #setPseudo(String)
	 */
	protected String pseudo;
	protected List<User> listAmi = new ArrayList<User>();
	protected List<Message> messages = new ArrayList<Message>();

	public User() {
	}

	public User(long id, String nom, String prenom, String pseudo, String dateDeNaissance, List<User> listAmi) {

		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.dateDeNaissance = dateDeNaissance;
		this.listAmi = listAmi;
	}

	public User(String nom, String prenom, String pseudo, String dateDeNaissance) {

		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * Retour l'id de l'utilisateur
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * Retourne le Nom de l'Utilisateur
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Affecter le Nom de l'utilisateur
	 * 
	 * @param nom
	 *            Nouveau nom de l'utilisateur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Obtenir le Prénom de l'utilisateur
	 * 
	 * @return prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Affecter le Prénom de l'utilisateur
	 * 
	 * @param prenom
	 *            Nouveau Prénom de l'utilisateur
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Obtenir l'année de naissance de l'utilisateur
	 * 
	 * @return dateNaissance
	 */
	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * Affecter l'année de naissance de l'utilisateur
	 * 
	 * @param dateNaissance
	 *            Nouvelle année de naissance de l'utilisateur
	 */
	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * Obtenir le Pseudo de l'utilisateur
	 * 
	 * @return Pseudo de l'utilisateur
	 * 
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Affecter un nouveau pseudo à l'utilisateur
	 * 
	 * @param pseudo
	 *            Nouveau pseudo de l'utilisateur
	 * 
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public List<User> getListAmi() {
		return listAmi;
	}

	public void setListAmi(List<User> listAmi) {
		this.listAmi = listAmi;
	}

	public void addAmi(User user) {
		this.listAmi.add(user);
	}

	public User getAmi(long index) {
		return this.listAmi.get((int) index);
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
		
	}
	public String toString() {

		String str = "*******************************\n";
		str += "NOM : " + this.getNom() + "\n";
		str += "*******************************\n";
		str += "LISTE DES AMIS : \n";
		for (User user : this.listAmi)
			str += user.toString() + "\n";
		return str;
	}

	/**
	 * Savoir si l'utilisateur est un modérateur
	 * 
	 * @return false
	 */
	public boolean isModerateur() {
		return false;

	}

	/**
	 * Retourn le niveau de modération 0 pour un utilisateur
	 * 
	 * @return 0
	 */
	public int getNiveau() {
		return 0;
	}

	public String getFullName() {
		return this.nom + " " + this.prenom;
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
