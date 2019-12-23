package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.Validation;
import modelo.entidade.Login;
import modelo.entidade.service.LoginService;

@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LoginServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login login = new Login();
		
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
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
			
			if(loginService.validarLogin(login)) {
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session = req.getSession();
				session.setAttribute("login",login);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("login",login);
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			
			
		} catch (Validation e) {
			request.setAttribute("msg", "Validação dos campos: " + e.getMessage());
			request.setAttribute("login",login);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	

}
