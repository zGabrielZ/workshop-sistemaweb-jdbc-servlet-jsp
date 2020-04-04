package modelo.dao;

import java.util.List;

import modelo.entidade.Contato;

public interface ContatoDao {

	void inserir(Contato contato);
	void atualizar(Contato contato);
	void deletar(Integer id);
	List<Contato> encontrarTudo();
	List<Contato> encontrarPeloNome(String nome);
	boolean checarTelefone(String telefone);
	boolean checarTelefoneAtualizado(String telefone,Integer id);
	Contato encontrarPeloId(Integer id);
}
