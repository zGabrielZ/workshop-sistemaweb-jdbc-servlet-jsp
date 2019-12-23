<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda</title>
<meta name="author" content="Gabriel">
<meta name="description" content="projeto web">
<meta name="keywords" content="html5,jsp,servlet,java,jdbc">
<link rel="stylesheet" href="paginas/css/estilo-agenda.css" >
</head>
<body>
	<header>
          <h1>Agenda de contatos</h1>
      </header>

      <div id="main">
          <section>
            <article>
              <div id="consulta">
                <form action="ContatoServlet.do" method="get">
                	<input type="hidden" name="acao" value="listartodos" >
                	<p>Procura pelo nome : </p>
                	<input type="text" class="procurar" name="txtFiltro">
                	<input type="submit" class="enviar" value="Enviar"/>
                	<p><a href="ContatoServlet.do?acao=listartodos">Listar contatos</a></p>
                	<p><a href="index.jsp">Voltar para o menu</a></p>
                </form>
              </div>
              
              <table>
                <caption>
                  Tabela de contato
                </caption>
                <thead>
                  <tr>
                  <th>Código</th>
                  <th>Nome</th>
                  <th>Sobrenome</th>
                  <th>Telefone</th>
                  <th>Email</th>
                  <th>Excluir</th>
                  <th>Atualizar</th>
                  </tr>
                </thead>

                <tfoot>
                  <tr>
                    <td colspan="7">
                      Valores sujeitos a alterações sem prévio
                    </td>
                  </tr>
                </tfoot>

                <tbody>
                	 <c:forEach var="contato" items="${lista_contatos}">
        				<tr>
        					<td><c:out value="${contato.id}"></c:out></td>
        					<td><c:out value="${contato.nome}"></c:out></td>
        					<td><c:out value="${contato.sobrenome}"></c:out></td>
        					<td><c:out value="${contato.telefone}"></c:out></td>
        					<td><c:out value="${contato.email}"></c:out></td>
        					<td><a href="ContatoServlet.do?acao=delete&contatoId=${contato.id}">Excluir</a></td>
        					<td><a href="ContatoServlet.do?acao=editar&contatoId=${contato.id}">Atualizar</a></td>
        				</tr>
        			</c:forEach>
                </tbody>
              </table>
            </article>
          </section>
      </div>
      
      <div class="aviso">
            <h2>${msg}</h2> 
        </div>
</body>
</html>