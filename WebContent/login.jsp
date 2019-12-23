<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<meta name="author" content="Gabriel">
<meta name="description" content="projeto web">
<meta name="keywords" content="html5,jsp,servlet,java,jdbc">
<link rel="stylesheet" href="paginas/css/estilo-login.css" >
</head>
<body>
	 <fieldset>
            <form action="LoginServlet.do" method="POST" class="formLogin">
                <h1>Login</h1>

                <table>
                    <tbody>
                        <tr>
                            <td><label for="usuario">Usuário:</label></td>
                            <td><input type="text" name="usuario" id="usuario"></td>
                        </tr>

                        <tr>
                            <td><label for="senha">Senha:</label></td>
                            <td><input type="password" name="senha" id="senha"></td>
                        </tr>

                        <tr>
                            <td colspan="2"><input type="submit" value="Logar" class="btn"></td>
                        </tr>
                    </tbody>
                </table>
                <a href="cadastrar-login.jsp">Inscreva-se</a>
            </form>
        </fieldset>

        <div class="aviso">
            <h2>${msg}</h2> 
        </div>
</body>
</html>