package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import modelo.dao.LoginDao;
import modelo.entidade.Login;

public class LoginDaoJDBC implements LoginDao{

	private Connection conn = null;
	
	public LoginDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Login login) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO login (usuarioLogin,senhaLogin) VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1,login.getUsuario());
			st.setString(2,login.getSenha());
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					login.setId(id);
				}
			}
			else {
				throw new DbException("erro,nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
		}
	}

	@Override
	public boolean checarLogin(String usuario,String senha) {
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			st = conn.prepareStatement("SELECT * FROM login WHERE login.usuarioLogin = ? and login.senhaLogin = ?");
			
			st.setString(1,usuario);
			st.setString(2,senha);
			
			rs = st.executeQuery();
			
			check = rs.next();			
			return check;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharResultSet(rs);
			DB.fecharStatement(st);
		}
	}

	@Override
	public boolean checarUsuario(String usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			st = conn.prepareStatement("SELECT * FROM login WHERE login.usuarioLogin = ?");
			
			st.setString(1,usuario);
			
			rs = st.executeQuery();
			
			check = rs.next();			
			return check;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharResultSet(rs);
			DB.fecharStatement(st);
		}
	}

}
