
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

	/**
	 * Tableau contenant la liste des amis Maximum 11 Amis
	 */

	private List<User> friends = new ArrayList<User>();

	/**
	 * Tableau contenant la liste des messages Maximum 11 messages
	 */

	private List<Message> messages = new ArrayList<Message>();

	/**
	 * Initialisation de d'un utilisateur
	 * 
	 * @param nom
	 *            Nom de l'utilisateur
	 * @param prenom
	 *            Prénom de l'utilisateur
	 * @param dateNaissance
	 *            Année de naissance de l'utilisateur
	 */
	public User(String nom, String prenom, String pseudo, String dateDeNaissance) {

		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.pseudo = pseudo;

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

	/**
	 * Obtenir la liste des amis
	 * 
	 * @return friends
	 */
	public List<User> getFriends() {
		return friends;
	}

	/**
	 * Ajout d'un ami.
	 *
	 * @param friend
	 *            Ami à ajouter
	 * 
	 */
	public void addFriends(User friend) {
		this.friends.add(friend);
	}

	public void delFriend(int numeroFriend) {
		this.friends.remove(numeroFriend);

	}

	/**
	 * Obtenir la liste des messages
	 * 
	 * @return messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * Ajoute un message
	 * 
	 * @param message
	 *            Message pour l'utilisateur
	 * 
	 */
	public void addMessages(Message message) {
		this.messages.add(message);
	}

	/**
	 * @param messages
	 *            Tableau de messages
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	/**
	 * Vide la messagerie de l'utilisateur
	 * 
	 */
	public void viderMessages() {
		this.setMessages(new ArrayList<Message>());
	}

	/**
	 * Supprime un message
	 * 
	 * @param nbre
	 *            Numéro de l'index à supprimer
	 */
	public void delMessages(int index) {
		this.messages.remove(index);
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


	/*public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;

		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		}
		if (nom == null) {
			if (other.nom != null)
				return false;
		}
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		}

		if (!pseudo.toLowerCase().equals(other.pseudo.toLowerCase()) && !nom.toLowerCase().equals(other.nom.toLowerCase()) && !prenom.toLowerCase().equals(other.prenom.toLowerCase())) {
			return false;
			
		}

		return true;
	}*/

}
