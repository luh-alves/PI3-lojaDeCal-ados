<%-- 
    Document   : clientesAtualizar
    Created on : 27/05/2020, 14:14:00
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Clientes</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clientesEstilo.css">
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
            <div class="nome-aba">Atualização de Clientes</div>
            <div class="container">
                <div class="tela">
                    <form action="${pageContext.request.contextPath}/clientes/atualizar" method="GET">
                        <div>
                            <label for="">Nome: </label>
                            <input type="text" name="nome" value="${cliente.getNome()}">                           
                        </div>
                        <div>
                            <label for="">Cpf: </label>
                            <input type="text" name="cpf" value="${cliente.getCpf()}">                         
                        </div>     
                        <div>
                            <label for="">Email: </label>
                            <input type="text" name="email" value="${cliente.getEmail()}">
                        </div>     
                        <div>
                            <label for="">Sexo: </label>
                            <input type="text" name="sexo" value="${cliente.getSexo()}">
                        </div> 

                        <div class="b">
                            <input name="clienteId" value="${cliente.getId()}" type="hidden"/> 
                            <button type="submit" name="salvarBtt">Atualizar Cliente</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>


