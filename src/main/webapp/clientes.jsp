<%-- 
    Document   : clientes
    Created on : Apr 16, 2020, 10:45:27 AM
    Author     : felipeferreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/clientesEstilo.css">
        <title>Clientes</title>
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
            <div class="nome-aba">Clientes</div>
            <div id="tela">
                <div class="conteudo">
                </div><!--conteudo-->
                <form method="post" action="${pageContext.request.contextPath}/clientes/salvar">
                    <div class="inputs">
                        <div>
                            <label for="">Nome: </label>
                            <input type="text" name="nome">
                        </div>
                        <div>
                            <label for="">CPF: </label>
                            <input type="text" name="cpf">
                        </div>     
                        <div>
                            <label for="">E-mail: </label>
                            <input type="text" name="email">
                        </div>     
                        <div>
                            <label for="">Sexo: </label>
                            <input  type="text" name="sexo">
                        </div>
                        <div>
                            <label for="">Dt. Nascimento: </label>
                            <input type="text" name="dtnascimento">
                        </div>
                    </div>
                    <div class="botoes opcoes">
                        <button>Novo</button>
                        <button>Alterar</button>
                        <button>Excluir</button>
                        <button type="submit" class="salvar">Salvar</button>
                    </div><!--botoes-->
                </form>
                <label for="">Buscar (CPF): </label>
                <input type="text">  <button>Buscar</button>
                <div class="conteudo">
                    <div class="clientes">
                        <div class="titulo">
                            Cliente
                        </div><!--titulo-->
                        <table>
                            <tr>
                                <th class="dados">ID</th>
                                <th class="dados">CPF</th>
                                <th class="dados">Nome</th>
                                <th class="dados">Email</th>
                                <th class="dados">Dt. Nascimento</th>
                                <th class="dados">Sexo</th>
                            </tr>
                            <tr class="hover">
                                <td>01</td>
                                <td class="dados">12345</td>
                                <td class="dados">Joao</td>
                                <td class="dados">joao@email.com</td>
                                <td class="dados">24/09/1999</td>
                                <td class="dados">M</td>
                            </tr>
                            <tr class="hover">
                                <td>02</td>
                                <td class="dados">6789</td>
                                <td class="dados">Maria</td>
                                <td class="dados">maria@email.com</td>
                                <td class="dados">06/09/2000</td>
                                <td class="dados">F</td>
                            </tr>
                        </table>
                    </div><!--clientes-->
                </div><!--conteudo-->
            </div><!--primeira-->
            <div style="clear: both;"></div>
        </section><!--center-->
    </body>
</html>
