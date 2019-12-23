package modelo.entidade.service;

import modelo.dao.DaoFactory;

import java.util.List;

import modelo.dao.ContatoDao;
import modelo.entidade.Contato;

public class ContatoService {

	private ContatoDao dao = DaoFactory.criarContato();
	
	public void inserir(Contato contato) {
		dao.inserir(contato);
	}
	
	public void atualizar(Contato contato) {
		dao.atualizar(contato);
	}
	
	public void remover(Integer id) {
		dao.deletar(id);
	}
	
	public List<Contato> encontrarTudo(){
		return dao.encontrarTudo();
	}
	
	public List<Contato> encontrarPeloNome(String nome){
		return dao.encontrarPeloNome(nome);
	}
	
	public boolean checarTelefone(Contato contato) {
		return dao.checarTelefone(contato.getTelefone());
	}
	
	public boolean checarTelefoneAtualizado(Contato contato) {
		return dao.checarTelefoneAtualizado(contato.getTelefone(),contato.getId());
	}
	
	public Contato encontrarPeloId(Integer id){
		return dao.encontrarPeloId(id);
	}
}
