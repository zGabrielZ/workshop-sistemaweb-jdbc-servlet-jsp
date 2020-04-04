<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contato</title>
<meta name="author" content="Gabriel">
<meta name="description" content="projeto web">
<meta name="keywords" content="html5,jsp,servlet,java,jdbc">
<link rel="stylesheet" href="paginas/css/estilo-cadastro-contato.css" >
<script type="text/javascript" src="jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="jquery/jquery.mask.min.js"></script>
</head>
<body>
	 <div id="contato">
            <fieldset>
                <form action="ContatoServlet.do" method="post" class="formCadastrar">
                    <h1>Cadastrar contato</h1>

                    <table>
                        <tbody>
                            <tr>
                                <td><label for="codigo">Código:</label></td>
                                <td><input maxlength="30"  readonly="readonly" type="text" name="id" id="codigo" value='<c:out value="${contato.id}"></c:out>'></td>
                            </tr>

                            <tr>
                                <td><label for="nome">Nome:</label></td>
                                <td><input maxlength="30" type="text" name="nome" id="nome" value='<c:out value="${contato.nome}"></c:out>'></td>
                            </tr>

                            <tr>
                                <td><label for="sobrenome">Sobrenome:</label></td>
                                <td><input maxlength="30" type="text" name="sobrenome" id="sobrenome" value='<c:out value="${contato.sobrenome}"></c:out>'></td>
                            </tr>

                            <tr>
                                <td><label for="telefone">Telefone:</label></td>
                                <td><input type="text" name="telefone" id="telefone" value='<c:out value="${contato.telefone}"></c:out>'></td>
                            </tr>

                            <tr>
                                <td><label for="email">Email:</label></td>
                                <td><input maxlength="30" type="text" name="email" id="email" value='<c:out value="${contato.email}"></c:out>'></td>
                            </tr>

                            <tr>
                                <td colspan="2"><input type="submit" value="Enviar" class="btn"></td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="index.jsp">Voltar para o menu</a>
                </form>
            </fieldset>
        </div>

        <div class="aviso">
            <h2>${msg}</h2> 
        </div>
        
        <script type="text/javascript">
        	$(document).ready(function(){
        		$("#telefone").mask("(00) 0000-0000")
        	})
        </script>
        
        
        
</body>
</html>