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
                        <button type="button" value="Voltar" onClick="history.go(-1)">Voltar</button>
                        <li><a href="/PI-3/clientes">Gestao de Cliente</a></li>
                        <li><a href="/PI-3/produtos">Gestao de Produtos</a></li>
                        <li><a href="/PI-3/relatorioAnalitico.jsp">Relatorio Financeiro</a></li>
                        <li><a href="/PI-3/vendas.jsp">Vendas</a></li>
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
                     <form method="post" action="${pageContext.request.contextPath}/clientes/salvar">
                    <div class="inputs">
                        <div>
                            <label for="">Nome: </label>
                            <input type="text" name="nome" required>
                        </div>
                        <div>
                            <label for="">CPF: </label>
                            <input type="text" name="cpf" required onkeypress="return isNumberKey(event)"/ required oninvalid="this.setCustomValidity('Digite um cpf válido')" 
onchange="try{setCustomValidity('')}catch(e){}"minlength="11" maxlength="11">
                        </div>     
                        <div>
                            <label for="">E-mail: </label>
                            <input type="text" name="email" required>
                        </div>     
                        <div>
                            <label class="labelsexo" for="">Sexo: </label>
                            <div class ="radiobuttons">
                                <input type="radio" id="masculino" name="sexo" value="M" required=""> 
                            <label for="male">Masculino</label>
                            <input type="radio" id="feminino" name="sexo" value="F">
                            <label for="female">Feminino</label>
                            <input type="radio" id="outro" name="sexo" value="O">
                            <label for="other">Outro</label>
                            </div>
                        </div>
                     <!--   <div>
                            <label for="">Dt. Nascimento: </label>
                            <input type="date" name="dtnascimento">
                        </div>-->
                    </div> 
                    <div class="botoes opcoes">
                        <button type="submit" class="salvar">Salvar</button>
                    </div><!--botoes-->
                </form>
                </div><!--conteudo-->
               


                <!--buscar--> 
                <form action="${pageContext.request.contextPath}/clientes" method="GET">    
                    <label for="">Buscar (CPF): </label>
                    <input  name="CPF" type="text">  <button type="submit">Buscar</button>
                </form>
                <!--fim buscar-->

                <div class="conteudo">
                    <div class="clientes">
                        <div class="titulo">
                            Cliente
                        </div><!--titulo-->
                        <table>
                            <tr>
                                <th class="nome">Nome</th>
                                <th class="cpf">CPF</th>
                                <th class="email">E-mail</th>
                                <th class="sexo">Sexo</th>
                                <th class="acoes">Ações</th>
                            </tr>
                            <c:forEach items="${listarClientes}" var="cliente" >
                                <tr class="hover">
                                    <td class="nome" ><c:out value="${cliente.getNome()}"/></td>
                                    <td class="cpf"><c:out value="${cliente.getCpf()}"/></td>
                                    <td class="email"><c:out value="${cliente.getEmail()}"/></td>
                                    <td class="sexo"><c:out value="${cliente.getSexo()}"/></td>
                                    <td class="buttonExcluir">
                                        <form action="${pageContext.request.contextPath}/clientes/excluir" method="GET">
                                            <input name="ID" value="${cliente.getId()}" type="hidden"/>
                                            <button type="submit" id ="actions">Excluir</button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/clientes/redireciona" method="GET">  
                                            <input name="clienteCpf" value="${cliente.getCpf()}" type="hidden"/>
                                            <button type="submit" id ="actions">Editar</button>
                                        </form>
                                    </td>
                                </tr>

                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <div style="clear: both;"></div>
        </section><!--center-->
    </body>
</html>

<script>
function isNumberKey(evt){
    const charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    
    return true;
}
</script>

<script>
    function isAtKey(evt){
        const charCode = (evt.which) ? evt.which :  evt.keyCode;
        if (charCode === 64)
            return true;
        return false;
    }
</script>

