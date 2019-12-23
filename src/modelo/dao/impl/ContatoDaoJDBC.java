package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import modelo.dao.ContatoDao;
import modelo.entidade.Contato;

public class ContatoDaoJDBC implements ContatoDao {

	private Connection conn = null;
	
	public ContatoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void inserir(Contato contato) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO contato (nomeContato,sobrenomeContato,telefoneContato,emailContato)"
					+ "VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1,contato.getNome());
			st.setString(2,contato.getSobrenome());
			st.setInt(3,contato.getTelefone());
			st.setString(4,contato.getEmail());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					contato.setId(id);
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
	public void atualizar(Contato contato) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE contato SET nomeContato = ? , sobrenomeContato = ? , telefoneContato = ? , emailContato = ?"
					+ "WHERE idContato = ?");
			st.setString(1,contato.getNome());
			st.setString(2,contato.getSobrenome());
			st.setInt(3,contato.getTelefone());
			st.setString(4,contato.getEmail());
			st.setInt(5,contato.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
		}
		
	}

	@Override
	public void deletar(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM contato WHERE idContato = ?");
			st.setInt(1,id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Contato> encontrarTudo() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM contato");
			rs = st.executeQuery();
			
			List<Contato> list = new ArrayList<>();
			
			while(rs.next()) {
				Contato contato = instanciacaoContato(rs);
				list.add(contato);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
	}

	@Override
	public List<Contato> encontrarPeloNome(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM contato WHERE nomeContato like ? ");
			st.setString(1,"%"+nome+"%");
			
			rs = st.executeQuery();
			
			List<Contato> list = new ArrayList<>();
			
			while(rs.next()) {
				Contato contato = instanciacaoContato(rs);
				list.add(contato);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
	}

	@Override
	public boolean checarTelefone(Integer telefone) {
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			st = conn.prepareStatement("SELECT * FROM contato WHERE telefoneContato = ?");
			
			st.setInt(1,telefone);
			
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
	
	private Contato instanciacaoContato(ResultSet rs) throws SQLException {
		Contato contato = new Contato();
		contato.setId(rs.getInt("idContato"));
		contato.setNome(rs.getString("nomeContato"));
		contato.setSobrenome(rs.getString("sobrenomeContato"));
		contato.setTelefone(rs.getInt("telefoneContato"));
		contato.setEmail(rs.getString("emailContato"));
		return contato;
	}

	@Override
	public Contato encontrarPeloId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM contato WHERE idContato = ?");
			st.setInt(1,id);
			
			rs = st.executeQuery();
			
			Contato contato = new Contato();
			if(rs.next()) {
				contato.setId(rs.getInt("idContato"));
				contato.setNome(rs.getString("nomeContato"));
				contato.setSobrenome(rs.getString("sobrenomeContato"));
				contato.setTelefone(rs.getInt("telefoneContato"));
				contato.setEmail(rs.getString("emailContato"));
			}
			
			return contato;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharStatement(st);
			DB.fecharResultSet(rs);
		}
	}

	@Override
	public boolean checarTelefoneAtualizado(Integer telefone,Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select count(1) as qtd from contato where telefoneContato = '"+telefone+"'and idContato <>"+id );
			rs = st.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("qtd") <= 0;
			}
		
	
			return false;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.fecharResultSet(rs);
			DB.fecharStatement(st);
		}
		
	}

}
