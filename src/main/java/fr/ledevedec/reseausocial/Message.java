package fr.ledevedec.reseausocial;

public class Message {

	
	private long expediteur;
	private long destinataire;
	private String contenu;
	
	
	public Message(long expediteur, long destinataire, String contenu) {
	this.expediteur = expediteur;
	this.destinataire = destinataire;
	this.contenu = contenu;
	}

	public Message() {}

	/**
	 * @return the expediteur
	 */
	public long getExpediteur() {
		return expediteur;
	}


	/**
	 * @param expediteur the expediteur to set
	 */
	public void setExpediteur(long expediteur) {
		this.expediteur = expediteur;
	}


	/**
	 * @return the destinataire
	 */
	public long getDestinataire() {
		return destinataire;
	}


	/**
	 * @param destinataire the destinataire to set
	 */
	public void setDestinataire(long destinataire) {
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
