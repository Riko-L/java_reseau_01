package fr.ledevedec.reseausocial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends DAO<Message> {

	/**
	 * Ne Pas Utiliser
	 */
	@Override
	public Message find(long id) {

		return null;
	}

	/**
	 * Permet d'obtenir une liste de tous les messages d'un destinataire
	 * 
	 * @param toDestinataire
	 * @return
	 */
	public List<Message> findAll(User toDestinataire) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		try {

			PreparedStatement getMessage = connect.prepareStatement("SELECT * FROM message"
					+ " INNER JOIN user ON message.expediteur = user.id " + " WHERE message.destinataire = ?");
			getMessage.setLong(1, toDestinataire.getId());

			ResultSet result = getMessage.executeQuery();

			if (result.first()) {
				result.beforeFirst();
				while (result.next()) {
					message = new Message(result.getLong("expediteur"), result.getLong("destinataire"),
							result.getString("contenu"));
					messages.add(message);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	
	@Override
	public Message create(Message obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message update(Message obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Message obj) {
		// TODO Auto-generated method stub

	}

}
