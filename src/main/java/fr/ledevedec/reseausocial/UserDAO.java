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

	private User findUserUnique(long id) {
		User user = new User();
		List<User> listAmi = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			prepare.setLong(1, id);

			ResultSet result = prepare.executeQuery();

			if (result.first() && !result.getBoolean("ismod")) {
				user = new User(id, result.getString("nom"), result.getString("prenom"), result.getString("pseudo"),
						result.getString("anneenaissance"), listAmi);
			} else {
				user = new Moderateur(id, result.getString("nom"), result.getString("prenom"),
						result.getString("pseudo"), result.getString("anneenaissance"), listAmi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User find(long id) {
		User user = new User();
		List<User> listAmi = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			prepare.setLong(1, id);

			PreparedStatement getFriend = connect.prepareStatement("SELECT * FROM javatesting.ami"
					+ " INNER JOIN javatesting.user ON ami.id_ami = user.id " + " WHERE ami.id_user = ?");
			getFriend.setLong(1, id);
			ResultSet resultSetFriend = getFriend.executeQuery();

			if (resultSetFriend.first()) {
				resultSetFriend.beforeFirst();
				while (resultSetFriend.next() && resultSetFriend.getLong("id") != 0) {
					listAmi.add(this.findUserUnique(resultSetFriend.getLong("id")));
				}
			}

			ResultSet result = prepare.executeQuery();

			if (result.first() && !result.getBoolean("ismod")) {
				user = new User(id, result.getString("nom"), result.getString("prenom"), result.getString("pseudo"),
						result.getString("anneenaissance"), listAmi);
			} else {
				user = new Moderateur(id, result.getString("nom"), result.getString("prenom"),
						result.getString("pseudo"), result.getString("anneenaissance"), listAmi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User find(User obj) {
		User user = null;

		try {
			String sql = "SELECT * FROM user WHERE nom = ? AND prenom = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getPrenom());

			ResultSet result = prepare.executeQuery();

			if (result.first()) {

				user = this.find(result.getLong("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User create(User obj) throws SQLException {

		int auto_id = -1;
		String sql = "INSERT INTO user (id, nom , prenom ,pseudo,ismod, level, anneenaissance) VALUES(default,?,?,?,?,?,?)";
		PreparedStatement prepare = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		prepare.setString(1, obj.getNom());
		prepare.setString(2, obj.getPrenom());
		prepare.setString(3, obj.getPseudo());
		prepare.setBoolean(4, obj.isModerateur());
		prepare.setInt(5, obj.getNiveau());
		prepare.setString(6, obj.getDateDeNaissance());

		prepare.executeUpdate();

		ResultSet rs = prepare.getGeneratedKeys();
		rs.next();
		auto_id = rs.getInt(1);

		obj = this.find(auto_id);

		return obj;
	}

	@Override
	public User update(User obj) {

		try {
			String sql = "UPDATE user SET nom= ? , prenom = ? ,pseudo = ? ,level = ? ,anneenaissance = ? WHERE id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getPrenom());
			prepare.setString(3, obj.getPseudo());
			prepare.setInt(4, obj.getNiveau());
			prepare.setString(5, obj.getDateDeNaissance());
			prepare.setLong(6, obj.getId());

			prepare.executeUpdate();

			obj = this.find(obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public void delete(User obj) {
		try {
			String sql = "DELETE FROM user WHERE id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			prepare.setLong(1, obj.getId());

			prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(long id) {
		try {
			String sql = "DELETE FROM user WHERE id = ?";
			PreparedStatement prepare = this.connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			prepare.setLong(1, id);

			prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<User> getAllUser() {

		List<User> allUser = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user";
			PreparedStatement prepare = this.connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				result.beforeFirst();
				while (result.next()) {
					allUser.add(this.find(result.getLong("id")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUser;
	}

	public void addfriend(User user, User ami) {

		try {
			PreparedStatement addfriend = connect.prepareStatement("insert into ami (id_user, id_ami) values (?, ?)");

			addfriend.setLong(1, user.getId());
			addfriend.setLong(2, ami.getId());

			addfriend.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delFriend(User ami) {
		try {
			PreparedStatement delfriend = connect.prepareStatement("DELETE FROM ami WHERE ami.id_ami= ?");

			delfriend.setLong(1, ami.getId());
			delfriend.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			
		}

	}
}
