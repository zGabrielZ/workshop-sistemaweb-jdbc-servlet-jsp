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
<link rel="stylesheet" href="paginas/css/estilo-cadastrar-login.css" >
</head>
<body>
	 <fieldset>
            <form action="LoginServletInserir.do" method="post" class="formLogin">
                <h1>Login</h1>

                <table>
                    <tbody>
                        <tr>
                            <td><input type="text" name="usuario" id="usuario" placeholder="Usuário" maxlength="30"></td>
                        </tr>

                        <tr>
                            <td><input type="password" name="senha" id="senha" placeholder="Senha" maxlength="30"></td>
                        </tr>

                        <tr>
                            <td colspan="2"><input type="submit" value="Cadastrar" class="btn"></td>
                        </tr>
                    </tbody>
                </table>
                <a href="login.jsp">Voltar</a>
            </form>
        </fieldset>

        <div class="aviso">
            <h2>${msg}</h2> 
        </div>
</body>
</html>