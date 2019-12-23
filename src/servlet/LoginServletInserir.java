package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.Validation;
import modelo.entidade.Login;
import modelo.entidade.service.LoginService;
import util.Utils;

@WebServlet("/LoginServletInserir.do")
public class LoginServletInserir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServletInserir() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login login = new Login();
		
		String id = request.getParameter("id");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		login.setId(Utils.converterParaInt(id));
		login.setUsuario(usuario);
		login.setSenha(senha);
		
		try {
			if(usuario.equals("") || usuario == null) {
				throw new Validation("Campo usuário não pode ser vazio");
			}
			if (senha.equals("") || senha == null) {
				throw new Validation("Campo senha não pode ser vazio");
			}
			
			LoginService loginService = new LoginService();
			
			if(!loginService.validarUsuario(login)) {
				loginService.inserir(login);
				request.setAttribute("msg","Cadastrado com sucesso !!");
				request.setAttribute("login",login);
				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-login.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("msg","Nao foi cadastrado, pois o nome ja tem cadastrado no banco");
				request.setAttribute("login",login);
				RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-login.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Validation e) {
			request.setAttribute("msg", "Validação dos campos: " + e.getMessage());
			request.setAttribute("login",login);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar-login.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
