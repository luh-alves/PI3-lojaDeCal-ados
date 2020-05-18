<%-- 
    Document   : produtos
    Created on : 15/04/2020, 17:46:03
    Author     : Luciana Alves
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Produtos</title>
        <link rel="stylesheet" href="produtoEstilo.css">
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
            <div class="nome-aba">Cadastro de Produtos</div>
            <div class="container">
                <div class="form-container">
                    <form action="${pageContext.request.contextPath}/produtos/salvar" method="POST">
                        <div>
                            <label for="">Nome: </label>
                            <input type="text" name="nome">
                        </div>
                        <div>
                            <label for="">Valor: </label>
                            <input type="text" name="valor">
                        </div>     
                        <div>
                            <label for="">Quantidade: </label>
                            <input type="text" name="quantidade">
                        </div>     
                        <div>
                            <label for="">Descrição: </label>
                            <input type="text" name="descricao">
                        </div> 
                        <div>
                            <button type="submit" name="salvarBtt">Salvar Produto</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="conteudo">
                <label for="">Nome Produto: 
                    <form action="${pageContext.request.contextPath}/produtos" method="GET">
                        <input name="produtoNome" type="text"/>                        
                        <button type="submit">Buscar</button>
                    </form>
                </label>
            </div><!--conteudo-->

            <div class="pesquisar">
                <table>
                    <tr>
                        <th class="nomeProduto">Nome</th>
                        <th class="valor">Valor</th>
                        <th class="estoque">Estoque</th>
                        <th class="descricao">Descrição</th>
                        <th class="acoes">Ações</th>
                    </tr>
                    <c:forEach items="${listaProdutos}" var="produto" >
                        <tr class="hover">
                            <td class="nome" ><c:out value="${produto.getNome()}"/></td>
                            <td class="valor"><c:out value="${produto.getValor()}"/></td>
                            <td class="estoque"><c:out value="${produto.getQuantidade()}"/></td>                           
                            <td class="descricao"><c:out value="${produto.getDescricao()}"/></td>
                            <td class="buttonExcluir">
                                <form action="${pageContext.request.contextPath}/produtos/excluir" method="GET">
                                    <input name="produtoId" value="${produto.getId()}" type="hidden"/>                        
                                    <button type="submit">Excluir</button>
                                </form>
                                <form action="${pageContext.request.contextPath}/produtos/atualizar" method="GET">
                                    <input name="produtoId" value="${produto.getId()}" type="hidden"/>                        
                                    <button type="submit">Atualizar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div><!--pesquisar-->


            <div style="clear: both;"></div>
        </section><!--center-->
    </body>
</html>
