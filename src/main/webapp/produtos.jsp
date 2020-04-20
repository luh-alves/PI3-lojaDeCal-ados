<%-- 
    Document   : produtos
    Created on : 15/04/2020, 17:46:03
    Author     : Luciana Alves
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CAECAE PI3</title>
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
                    <form action="">
                        <div>
                            <label for="">Nome: </label>
                            <input type="text">
                        </div>
                        <div>
                            <label for="">Valor: </label>
                            <input type="text">
                        </div>     
                        <div>
                            <label for="">Quantidade: </label>
                            <input type="text">
                        </div>     
                        <div>
                            <label for="">Descrição: </label>
                            <input disabled type="text">
                        </div>  
                    </form>
                </div>
                <div class="buttons-container">
                    <button>Salvar Produto</button>
                    <button>Atualizar Produto</button>
                    <button>Excluir Produto</button>
                </div>
            </div>
            <div class="conteudo">
                <label for="">Nome Produto: </label>
                <input disabled type="text">
                <button>Buscar</button> 
            </div><!--conteudo-->

            <div class="pesquisar">
                <table>
                    <tr>
                        <th class="nomeProduto">Nome</th>
                        <th class="valor">Valor</th>
                        <th class="estoque">Estoque</th>
                        <th class="descricao">Descrição</th>
                    </tr>
                    <tr class="hover">
                        <td>Sapato Scarpin Dela</td>
                        <td class="valor">R$ 60,00</td>
                        <td class="estoque">8</td>
                        <td class="descricao">Sapato femenino, preto, bico fino</td>
                    </tr>
                </table>
            </div><!--pesquisar-->


            <div style="clear: both;"></div>
        </section><!--center-->
    </body>
</html>
