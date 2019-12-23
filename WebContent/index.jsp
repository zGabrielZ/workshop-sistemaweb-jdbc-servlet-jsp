<%@page import="modelo.entidade.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Web Service</title>
<meta name="author" content="Gabriel">
<meta name="description" content="projeto web">
<meta name="keywords" content="html5,jsp,servlet,java,jdbc">
<link rel="stylesheet" href="paginas/css/estilo.css" >
</head>
<body>
<header class="cabecalho">
           <h1>Bem vindo ao nosso sistema JSP</h1>
       </header>

       <div id="main">
           <section class="sessao-01">
               <nav>
                   <ul>
                       <li><a href="cadastrar-contato.jsp">Cadastrar contato</a></li>
                       <li><a href="ContatoServlet.do?acao=listartodos">Listagem de contato</a></li>
                       <li><a href="deslogar.jsp">Deslogar</a></li>
                   </ul>
               </nav>
           </section>

           <section class="sessao-02">
               <article>
                   <header>
                       <h2><%Login login = (Login)session.getAttribute("login");
							if(login == null){
								response.sendRedirect("login.jsp");
							}
							else{
								out.print("Bem vindo, "+login.getUsuario()+"<br/>");
							}
						%></h2>
                   </header>

                <div class="conteudo">
                    <p>Sistema web desenvolvido por Gabriel Ferreira</p>
                </div>
              </article>
           </section>
       </div>

</body>
</html>