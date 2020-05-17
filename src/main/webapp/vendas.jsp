<%-- 
    Document   : vendas
    Created on : 14 de abr. de 2020, 20:58:20
    Author     : rolucon
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CAECAE PI3</title>
        <link rel="stylesheet" href="vendaEstilo.css">
        <!--<script src="selecionaLinha.js"></script>--> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body id="duas-col">
        <header>
            <nav>
                <div class="center">
                    <ul>
                        <button>Voltar</button>
                        <li><a href="">Gestao de Cliente</a></li>
                        <li><a href="">Gestao de Produtos</a></li>
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
            <div class="nome-aba">Vendas</div>
            <div id="primeira">
                <div class="conteudo">
                    <label for="">Filial: </label>
                    <input disabled type="text" name="filial">
                </div><!--conteudo-->
                <form id="form1" method="post" action="${pageContext.request.contextPath}/vendas" novalidate>
                    <div>
                        <label for="">Cliente: </label>
                        <input type="text" name="cliente" value="${clienteAtr}">
                    </div>
                    <div>
                        <label for="">Produto: </label>
                        <input type="text" name="produto">
                    </div>     
                    <div>
                        <label for="">Quantidade: </label>
                        <input type="text" name="qtd">
                    </div>     
                    <div>
                        <label for="">Valor: </label>
                        <input disabled type="text" name="valor">
                    </div> 
                    <input id="linhaSelec" type="text" name="linhaSelec" value="-1" hidden="true"></input>
                </form>
                <div class="botoes">
                    <button form="form1" type="submit" name="finalizarBtt">Finalizar Venda</button>
                    <button form="form1" type="submit" name="cancelarBtt"> Cancelar Venda</button>
                    <button formaction="${pageContext.request.contextPath}/cliente" type="submit">
                        Cadastrar Cliente</button>
                </div><!--botoes-->
            </div><!--primeira-->
            <div id="segunda">
                <div class="conteudo">
                    <div class="carrinho">
                        <div class="titulo">
                            Carrinho
                        </div><!--titulo-->
                        <table id="tabela">
                            <thead>
                                <th class="prod">Produto</th>
                                <th class="qtd">Quantidade</th>
                                <th class="preco">Valor</th>
                            </thead>
                            <c:forEach var="produto" items="${listaProd}">
                                <tr id="${produto.id}" class="hover">
                                    <td>${produto.nome}</td>
                                    <td class="qtd">${produto.quantidade}</td>
                                    <td class="preco">${produto.valor} R$</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div><!--carrinho-->
                    <div class="botoes">
                        <button form="form1" type="submit" name="addProdBtt">Add Produto</button>
                        <button form="form1" type="submit" name="removeBtt">Excluir Produto</button>
                    </div>
                    <div class="valor-total">
                        <div>Valor Total</div>
                        <input disabled type="text" name="total">
                    </div>
                </div><!--conteudo-->
            </div><!--segunda-->
            <div style="clear: both;"></div>
        </section><!--center-->
        
       
        <script>
            var tabela = document.getElementById("tabela");
            var linhas = tabela.getElementsByTagName("tr");
            var valor = document.getElementById("linhaSelec");
            
            for(var i = 0; i < linhas.length; i++){
                    var linha = linhas[i];
              linha.addEventListener("click", function(){
                            selLinha(this, false); 
                    });
            }

            function selLinha(linha, multiplos){
              if(!multiplos){
                    var linhas = linha.parentElement.getElementsByTagName("tr");
                    for(var i = 0; i < linhas.length; i++){
                       var linha_ = linhas[i];
                       linha_.classList.remove("selecionado");    
                    }
              }
              linha.classList.toggle("selecionado");
              valor.value = linha.id;
            }
        </script>
    </body>
</html>