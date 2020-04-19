<%-- 
    Document   : vendas
    Created on : 14 de abr. de 2020, 20:58:20
    Author     : rolucon
--%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CAECAE PI3</title>
        <link rel="stylesheet" href="vendaEstilo.css">
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
                    <input disabled type="text">
                </div><!--conteudo-->
                <form action="">
                    <div>
                        <label for="">Cliente: </label>
                        <input type="text">
                    </div>
                    <div>
                        <label for="">Produto: </label>
                        <input type="text">
                    </div>     
                    <div>
                        <label for="">Quantidade: </label>
                        <input type="text">
                    </div>     
                    <div>
                        <label for="">Valor: </label>
                        <input disabled type="text">
                    </div>     
                </form>
                <div class="botoes">
                    <button>Finalizar Venda</button>
                    <button>Cancelar Venda</button>
                    <button>Cadastrar Cliente</button>
                </div><!--botoes-->
            </div><!--primeira-->
            <div id="segunda">
                <div class="conteudo">
                    <div class="carrinho">
                        <div class="titulo">
                            Carrinho
                        </div><!--titulo-->
                        <table>
                            <tr>
                                <th class="prod">Produto</th>
                                <th class="qtd">Quantidade</th>
                                <th class="preco">Valor</th>
                            </tr>
                            <tr class="hover">
                                <td>Sapato</td>
                                <td class="qtd">01</td>
                                <td class="preco">R$ 1000,00</td>
                            </tr>
                            <tr class="hover">
                                <td>Sapato</td>
                                <td class="qtd">01</td>
                                <td class="preco">R$ 1000,00</td>
                            </tr>
                        </table>
                    </div><!--carrinho-->
                    <div class="botoes">
                        <button>Add Produto</button>
                        <button>Excluir Produto</button>
                    </div>
                    <div class="valor-total">
                        <div>Valor Total</div>
                        <input disabled type="text">
                    </div>
                </div><!--conteudo-->
            </div><!--segunda-->
            <div style="clear: both;"></div>
        </section><!--center-->
    </body>
</html>