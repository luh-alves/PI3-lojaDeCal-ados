<%-- 
    Document   : relatorioAnalitico
    Created on : 26/05/2020, 15:46:17
    Author     : Luciana Alves
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Relatorio Analitico</title>
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
            <div class="nome-aba">Relatorio Analitico</div>
            <div class="conteudo">

                <form action="${pageContext.request.contextPath}/produtos" method="GET">
                    <label>Nome Filial:</label>
                    <input name="produtoNome" type="text"/>                        
                    <button type="submit">Buscar</button>
                </form>

            </div><!--conteudo-->

            <div class="pesquisar">
                <table>
                    <tr>
                        <th class="nomeProduto">Produto</th>
                        <th class="quantidade">Quantidade</th>
                        <th class="valorUnitario">Valor Unitario</th>
                        <th class="total">Total</th>
                    </tr>
                    <c:forEach items="${listaProdutos}" var="produto" >
                        <tr class="hover">
                            <td class="nome" ><c:out value="${produto.getNome()}"/></td>
                            <td class="quantidade"><c:out value="${produto.getValor()}"/></td>
                            <td class="valorUnitario"><c:out value="${produto.getQuantidade()}"/></td>                           
                            <td class="total"><c:out value="${produto.getDescricao()}"/></td>
                        </tr>
                    </c:forEach>                       
                </table>

                <div class="valorTotal">
                    <label>Valor Total:</label>
                    <input name="produtoNome" type="text">
                </div>
            </div><!--pesquisar-->


            <div style="clear: both;"></div>
        </section><!--center-->
    </body>
</html>

