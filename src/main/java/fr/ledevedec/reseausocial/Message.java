package fr.ledevedec.reseausocial;

public class Message {

	private String expediteur;
	private String destinataire;
	private String contenu;
	
	
	public Message(String expediteur, String destinataire, String contenu) {
	this.expediteur = expediteur;
	this.destinataire = destinataire;
	this.contenu = contenu;
	}


	/**
	 * @return the expediteur
	 */
	public String getExpediteur() {
		return expediteur;
	}


	/**
	 * @param expediteur the expediteur to set
	 */
	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}


	/**
	 * @return the destinataire
	 */
	public String getDestinataire() {
		return destinataire;
	}


	/**
	 * @param destinataire the destinataire to set
	 */
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}


	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}


	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	
	
	
}
