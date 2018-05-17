/**
 * 
 */
package fr.ledevedec.reseausocial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Utilisateur
 *
 */
public class UserDAO extends DAO<User> {

	@Override
	public User find(long id) {
		User user = new User();
		List<User> listAmi = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			prepare.setLong(1, id);
			
			PreparedStatement getFriend = connect.prepareStatement("SELECT * FROM javatesting.ami" +
					  " INNER JOIN javatesting.user ON ami.id_ami = user.id " +
					  " WHERE ami.id_user = ?");
			getFriend.setLong(1, id);
			ResultSet resultSetFriend = getFriend.executeQuery();
			
			if(resultSetFriend.first()) {
				
				resultSetFriend.beforeFirst();
				while(resultSetFriend.next() && resultSetFriend.getLong("id") != 0) {
					listAmi.add(this.find(resultSetFriend.getLong("id")));
				}
			}
			
			
			ResultSet result = prepare.executeQuery();
			
			if(result.first()) {
				user = new User(
						id,
						result.getString("nom"),
						result.getString("prenom"),
						result.getString("pseudo"),
						result.getString("anneenaiossance"),
						listAmi);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User create(User obj) {
		try {
			int auto_id = -1;
			String sql = "INSERT INTO user (id, nom , prenom ,pseudo,ismod, level, anneenaissance) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement prepare = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			prepare.setLong(1, obj.getId());
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getPrenom());
			prepare.setString(4, obj.getPseudo());
			prepare.setBoolean(5, obj.isModerateur());
			prepare.setInt(6, obj.getNiveau());
			prepare.setString(7, obj.getDateDeNaissance());
			
			prepare.executeUpdate();
			
			
			ResultSet rs = prepare.getGeneratedKeys();
			rs.next();
			auto_id = rs.getInt(1);
				
			obj = this.find(auto_id);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub

	}

}
