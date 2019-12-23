package modelo.entidade.service;

import modelo.dao.DaoFactory;
import modelo.dao.LoginDao;
import modelo.entidade.Login;

public class LoginService {

	private LoginDao dao = DaoFactory.criarLogin();
	
	public void inserir(Login login) {
		dao.inserir(login);
	}
	
	public boolean validarLogin(Login login) {
		return dao.checarLogin(login.getUsuario(),login.getSenha());
	}
	
	public boolean validarUsuario(Login login) {
		return dao.checarUsuario(login.getUsuario());
	}
}
