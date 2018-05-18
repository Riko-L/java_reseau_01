package fr.ledevedec.reseausocial;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO<T> {

	
	public Connection connect = MysqlAccess.getInstance();
	
	/**
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T find(long id);
	
	/**
	 * Permet de créer une entrée dans la base de donée
	 * @param obj
	 * @return
	 */
	public abstract T create(T obj ) throws SQLException;
	
	/**
	 * Permet de mettre à jour les données d'une entrée de la base
	 * @param obj
	 * @return
	 */
	public abstract T update(T obj);
	
	/**
	 * Permer la suppression d'une entré de la base
	 * @param obj
	 */
	public abstract void delete(T obj);

	
	
	
	
}
