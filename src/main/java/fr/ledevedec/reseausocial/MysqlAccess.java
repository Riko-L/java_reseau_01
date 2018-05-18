package fr.ledevedec.reseausocial;

import java.sql.*;


public class MysqlAccess {

	private static String url = "jdbc:mysql://localhost/javatesting?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

	private static String user = "root";

	private static String passwd = "badbeat";

	private static Connection connect;

	public static Connection getInstance() {

		if (connect == null) {
			new MysqlAccess();
		}
		return connect;

	}

	private MysqlAccess() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

/**
 * Ajoute un utilisateur à la BDD
 * 
 * @param user
 *//*
	 * public void addUserToBdd(User user) {
	 * 
	 * try { PreparedStatement addUser = connect
	 * .prepareStatement("insert into javatesting.user values (default , ?, ?, ?, ? , ?, ?)"
	 * );
	 * 
	 * addUser.setString(1, user.getNom()); addUser.setString(2, user.getPrenom());
	 * addUser.setString(3, user.getPseudo()); addUser.setBoolean(4,
	 * user.isModerateur()); addUser.setInt(5, user.getNiveau());
	 * addUser.setString(6, user.getDateDeNaissance()); addUser.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); affiche(e.getMessage()); } }
	 * 
	 * public User getUserById(int id_user) { User user = null; try {
	 * PreparedStatement showUser =
	 * connect.prepareStatement("SELECT *  from javatesting.user WHERE id= ?");
	 * showUser.setInt(1, id_user); resultSet = showUser.executeQuery(); while
	 * (resultSet.next()) {
	 * 
	 * int id = resultSet.getInt("id"); String nom = resultSet.getString("nom");
	 * String prenom = resultSet.getString("prenom"); String pseudo =
	 * resultSet.getString("pseudo"); String anneenaissance =
	 * resultSet.getString("anneenaissance"); Boolean ismod =
	 * resultSet.getBoolean("ismod"); int level = resultSet.getInt("level");
	 * 
	 * if (ismod) {
	 * 
	 * user = new Moderateur(nom, prenom, pseudo, anneenaissance); ((Moderateur)
	 * user).setNiveau(level);
	 * 
	 * } else if (!ismod) {
	 * 
	 * user = new User(nom, prenom, pseudo, anneenaissance);
	 * 
	 * } } } catch (SQLException e) { affiche(e.getMessage()); }
	 * 
	 * return user; }
	 * 
	 * public void updateUser(User user, int id) { try { PreparedStatement
	 * updateUser = connect.prepareStatement("UPDATE javatesting.user  " +
	 * "SET nom= ? , prenom = ? , pseudo = ?, level = ?, anneenaissance = ? " +
	 * "WHERE id = ?");
	 * 
	 * updateUser.setString(1, user.getNom()); updateUser.setString(2,
	 * user.getPrenom()); updateUser.setString(3, user.getPseudo());
	 * updateUser.setInt(4, user.getNiveau()); updateUser.setString(5,
	 * user.getDateDeNaissance()); updateUser.setInt(6, id);
	 * updateUser.executeUpdate();
	 * 
	 * } catch (SQLException e) { affiche(e.getMessage()); }
	 * 
	 * }
	 * 
	 * public void delUser(int id) { try { PreparedStatement delUser =
	 * connect.prepareStatement("DELETE from user WHERE id= ?");
	 * 
	 * delUser.setInt(1, id);
	 * 
	 * delUser.executeUpdate();
	 * 
	 * } catch (SQLException e) { affiche(e.getMessage()); } }
	 * 
	 * public Map.Entry<Integer, User> getUserFromBdd(String InNom, String InPrenom)
	 * { User user = null; Map<Integer, User> tempUser = new HashMap<Integer,
	 * User>(); Map.Entry<Integer, User> entry = null; try { PreparedStatement
	 * showUser = connect
	 * .prepareStatement("SELECT *  from javatesting.user WHERE nom= ? AND prenom= ?"
	 * ); showUser.setString(1, InNom); showUser.setString(2, InPrenom); resultSet =
	 * showUser.executeQuery();
	 * 
	 * 
	 * if(resultSet.next() == false) {
	 * 
	 * entry = null;
	 * 
	 * }else {
	 * 
	 * 
	 * do {
	 * 
	 * int id = resultSet.getInt("id"); String nom = resultSet.getString("nom");
	 * String prenom = resultSet.getString("prenom"); String pseudo =
	 * resultSet.getString("pseudo"); String anneenaissance =
	 * resultSet.getString("anneenaissance"); Boolean ismod =
	 * resultSet.getBoolean("ismod"); int level = resultSet.getInt("level");
	 * 
	 * if (ismod) {
	 * 
	 * user = new Moderateur(nom, prenom, pseudo, anneenaissance); ((Moderateur)
	 * user).setNiveau(level);
	 * 
	 * tempUser.put(id, user); } else if (!ismod) {
	 * 
	 * user = new User(nom, prenom, pseudo, anneenaissance); tempUser.put(id, user);
	 * }
	 * 
	 * entry = tempUser.entrySet().iterator().next(); }while (resultSet.next()); }
	 * 
	 * } catch (SQLException e) { affiche(e.getMessage()); }
	 * 
	 * return entry;
	 * 
	 * }
	 * 
	 * public Map<Integer, User> getAllUser() { Map<Integer, User> tempList = new
	 * HashMap<Integer, User>(); User user = null; try { Statement getUser =
	 * connect.createStatement(); resultSet =
	 * getUser.executeQuery("SELECT * FROM javatesting.user");
	 * 
	 * while (resultSet.next()) { int id = resultSet.getInt("id"); String nom =
	 * resultSet.getString("nom"); String prenom = resultSet.getString("prenom");
	 * String pseudo = resultSet.getString("pseudo"); String anneenaissance =
	 * resultSet.getString("anneenaissance"); Boolean ismod =
	 * resultSet.getBoolean("ismod"); int level = resultSet.getInt("level");
	 * 
	 * if (ismod) {
	 * 
	 * user = new Moderateur(nom, prenom, pseudo, anneenaissance); ((Moderateur)
	 * user).setNiveau(level); tempList.put(id, user);
	 * 
	 * } else if (!ismod) {
	 * 
	 * user = new User(nom, prenom, pseudo, anneenaissance); tempList.put(id, user);
	 * }
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { affiche(e.getMessage()); }
	 * 
	 * return tempList;
	 * 
	 * }
	 * 
	 * public User createUserResultSet(ResultSet resultSet) throws SQLException {
	 * // @TODO ajputer champs unique en base User user = null; while
	 * (resultSet.next()) {
	 * 
	 * int id = resultSet.getInt("id"); String nom = resultSet.getString("nom");
	 * String prenom = resultSet.getString("prenom"); String pseudo =
	 * resultSet.getString("pseudo"); String anneenaissance =
	 * resultSet.getString("anneenaissance"); Boolean ismod =
	 * resultSet.getBoolean("ismod"); int level = resultSet.getInt("level");
	 * 
	 * if (ismod) {
	 * 
	 * user = new Moderateur(nom, prenom, pseudo, anneenaissance); ((Moderateur)
	 * user).setNiveau(level);
	 * 
	 * } else if (!ismod) {
	 * 
	 * user = new User(nom, prenom, pseudo, anneenaissance);
	 * 
	 * } } return user; }
	 * 
	 * public void addfriend(int id_user, int id_ami) {
	 * 
	 * try { PreparedStatement addfriend = connect
	 * .prepareStatement("insert into javatesting.ami (id_user, id_ami) values (?, ?)"
	 * );
	 * 
	 * addfriend.setInt(1, id_user); addfriend.setInt(2, id_ami);
	 * 
	 * addfriend.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); affiche(e.getMessage()); }
	 * 
	 * }
	 * 
	 * public void delFriend(int id_ami) { try { PreparedStatement delfriend =
	 * connect.prepareStatement("DELETE" + " FROM ami" + " WHERE ami.id_ami= ?");
	 * 
	 * delfriend.setInt(1, id_ami); delfriend.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); affiche(e.getMessage()); }
	 * 
	 * }
	 * 
	 * public Map<Integer, User> getFriend(int id_user) { Map<Integer, User>
	 * tempList = new HashMap<Integer, User>(); User user = null; try {
	 * PreparedStatement getFriend =
	 * connect.prepareStatement("SELECT * FROM javatesting.ami" +
	 * " INNER JOIN javatesting.user ON ami.id_ami = user.id " +
	 * " WHERE ami.id_user = ?"); getFriend.setInt(1, id_user); resultSet =
	 * getFriend.executeQuery();
	 * 
	 * while (resultSet.next()) { int id = resultSet.getInt("id"); String nom =
	 * resultSet.getString("nom"); String prenom = resultSet.getString("prenom");
	 * String pseudo = resultSet.getString("pseudo"); String anneenaissance =
	 * resultSet.getString("anneenaissance"); Boolean ismod =
	 * resultSet.getBoolean("ismod"); int level = resultSet.getInt("level");
	 * 
	 * if (ismod) {
	 * 
	 * user = new Moderateur(nom, prenom, pseudo, anneenaissance); ((Moderateur)
	 * user).setNiveau(level); tempList.put(id, user);
	 * 
	 * } else if (!ismod) {
	 * 
	 * user = new User(nom, prenom, pseudo, anneenaissance); tempList.put(id, user);
	 * }
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { affiche(e.getMessage()); }
	 * 
	 * return tempList;
	 * 
	 * }
	 * 
	 * private void writeMetaData(ResultSet resultSet) throws SQLException { // Now
	 * get some metadata from the database // Result set get the result of the SQL
	 * query ResultSetMetaData dma = resultSet.getMetaData();
	 * 
	 * affiche("The columns in the table are: ");
	 * 
	 * affiche("Table: " + dma.getTableName(1));
	 * 
	 * for (int i = 1; i <= dma.getColumnCount(); i++) { affiche("Column " + i + " "
	 * + dma.getColumnName(i)); }
	 * 
	 * }
	 * 
	 * private void affiche(String message) {
	 * 
	 * System.out.println(message);
	 * 
	 * }
	 */
