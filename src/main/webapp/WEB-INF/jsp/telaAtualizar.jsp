<%-- 
    Document   : telaAtualizar
    Created on : 19/05/2020, 18:48:17
    Author     : Luciana Alves
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Produtos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/produtoEstilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body id="duas-col">
        <header>
            <nav>
                <div class="center">
                    <ul>
                        <button>Voltar</button>
                        <li><a href="">Gestão de Clientes</a></li>
                        <li><a href="">Gestão de Produtos</a></li>
                        <li><a href="">Relatorio Financeiro</a></li>
                        <li><a href="">Vendas</a></li>
                    </ul>
                </div><!--center-->
            </nav>
        </header>
        <div id="espacador">
            <br>
        </div><!--espacador-->
        <section class="center">
            <div class="nome-aba">Atualização de Produtos</div>
            <div class="container">
                <div class="form-container">
                    <form action="${pageContext.request.contextPath}/produtos/atualizar" method="GET">
                        <div>
                            <label for="">Nome: </label>
                            <input type="text" name="nome" value="${produto.getNome()}">                           
                        </div>
                        <div>
                            <label for="">Valor: </label>
                            <input type="text" name="valor" value="${produto.getValor()}">                         
                        </div>     
                        <div>
                            <label for="">Quantidade: </label>
                            <input type="text" name="quantidade" value="${produto.getQuantidade()}">
                        </div>     
                        <div>
                            <label for="">Descrição: </label>
                            <input type="text" name="descricao" value="${produto.getDescricao()}">
                        </div> 

                        <div class="b">
                            <input name="id" value="${produto.getId()}" type="hidden"/> 
                            <button type="submit" name="salvarBtt">Atualizar Produto</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
