package modelo.dao;

import db.DB;
import modelo.dao.impl.ContatoDaoJDBC;
import modelo.dao.impl.LoginDaoJDBC;

public class DaoFactory {

	public static LoginDao criarLogin() {
		return new LoginDaoJDBC(DB.getConnection());
	}
	
	public static ContatoDao criarContato() {
		return new ContatoDaoJDBC(DB.getConnection());
	}
}
