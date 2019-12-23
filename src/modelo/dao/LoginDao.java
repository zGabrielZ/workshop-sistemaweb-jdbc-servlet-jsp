package modelo.dao;

import modelo.entidade.Login;

public interface LoginDao {

	void inserir(Login login);
	boolean checarLogin(String usuario,String senha);
	boolean checarUsuario(String usuario);
}
