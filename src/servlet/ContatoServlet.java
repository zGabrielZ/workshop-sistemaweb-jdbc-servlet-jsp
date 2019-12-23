package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.Validation;
import modelo.entidade.Contato;
import modelo.entidade.service.ContatoService;
import util.Utils;

@WebServlet("/ContatoServlet.do")
public class ContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Contato contato = new Contato();
	
	private ContatoService contatoService = new ContatoService();

    public ContatoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		try {
				if(acao.equalsIgnoreCase("delete")) {
					int contatoId = Integer.parseInt(request.getParameter("contatoId"));
					contatoService.remover(contatoId);
					List<Contato> contatos = contatoService.encontrarTudo();
					request.setAttribute("msg","Removido com sucesso !!");
					request.setAttribute("lista_contatos",contatos);
					RequestDispatcher dispatcher = request.getRequestDispatcher("agenda-de-contatos.jsp");
					dispatcher.forward(request, response);
				}
				else if(acao.equalsIgnoreCase("editar")) {
					int contatoId = Integer.parseInt(request.getParameter("contatoId"));
					contato = contatoService.encontrarPeloId(contatoId);
					request.setAttribute("contato",contato);
					RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-contato.jsp");
					dispatcher.forward(request, response);	
				}
				else if(acao.equalsIgnoreCase("listartodos")) {
					List<Contato> contatos ;
					if(request.getParameter("txtFiltro") == null) {
						contatos = contatoService.encontrarTudo();
					}
					else {
						contatos = contatoService.encontrarPeloNome(request.getParameter("txtFiltro"));
					}
					
					request.setAttribute("lista_contatos",contatos);
					RequestDispatcher dispatcher = request.getRequestDispatcher("agenda-de-contatos.jsp");
					dispatcher.forward(request, response);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		contato.setId(Utils.converterParaInt(id));
		
		try {
			
			if(id == null || id.isEmpty()) {
				
				criarContato(request);
				
				if(contatoService.checarTelefone(contato)) {
					throw new Validation("Telefone que informou,já existe cadastrado");
				}
				
				contatoService.inserir(contato);
							
				request.setAttribute("msg","Cadastrado com sucesso !!");
				request.setAttribute("contato",contato);
				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-contato.jsp");
				dispatcher.forward(request, response);
			}
			else {
				
				criarContato(request);
				
				if(!contatoService.checarTelefoneAtualizado(contato)) {
					throw new Validation("Telefone que informou,já existe cadastrado");
				}
				
				contatoService.atualizar(contato);
				
				request.setAttribute("msg","Atualizado com sucesso !!");
				request.setAttribute("contato",contato);
				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-contato.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("contato",contato);
			request.setAttribute("msg", "Validação dos campos: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-contato.jsp");
			dispatcher.forward(request, response);
		}
		
		
		}
	
	private Contato criarContato(HttpServletRequest request) {
		
		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");
		String telefone = request.getParameter("telefone");
		String email = request.getParameter("email");
		
		contato.setNome(nome);
		contato.setSobrenome(sobrenome);
		contato.setTelefone(Utils.converterParaInt(telefone));
		contato.setEmail(email);	
		
		if(nome.equals("") || nome == null) {
			throw new Validation("Campo nome não pode ser vazio");
		}
		if(sobrenome.equals("") || sobrenome == null) {
			throw new Validation("Campo sobrenome não pode ser vazio");
		}
		if(telefone.equals("") || telefone == null) {
			throw new Validation("Campo telefone não pode ser vazio");
		}
		if(email.equals("") || email == null) {
			throw new Validation("Campo email não pode ser vazio");
		}
		if(!telefone.isEmpty() || telefone != null) {
			if(Utils.verificarNumero(telefone) == false) {
				throw new Validation("Campo telefone tem que ser digitado em números");
			}
		}
		
		return contato;
	}
		
}
