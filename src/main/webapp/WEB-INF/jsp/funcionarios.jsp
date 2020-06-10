<%-- 
    Document   : funcionarios
    Created on : 31/05/2020, 23:13:03
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/clientesEstilo.css">
        <title>Funcionários</title>      
    </head>
    <body id="duas-col">
        <header>
            <nav>
                <div class="center">
                    <ul>
                        <button type="button" value="Voltar" onClick="history.go(-1)">Voltar</button>
                        <li><a href="/restrito/clientes">Gestao de Cliente</a></li>
                        <li><a href="/restrito/produtos">Gestao de Produtos</a></li>
                        <!--<li><a href="/restrito/relatorioAnalitico.jsp">Relatorio Financeiro</a></li>-->
                        <li><a href="/restrito/vendas.jsp">Vendas</a></li>
                    </ul>
                </div><!--center-->
            </nav>
        </header>
        <div id="espacador">
            <br>
        </div><!--espacador-->
        <section class="center">
            <div class="nome-aba">Funcionários</div>
            <div id="tela">
                <div class="conteudo">
                    <form method="post" action="${pageContext.request.contextPath}/restrito/funcionarios/salvar">
                        <div class="inputs">
                            <div>
                                <label for="">Nome: </label>
                                <input type="text" name="nome" required>
                            </div>
                            <div>
                                <label for="">CPF: </label>
                                <input type="text" name="cpf" required onkeypress="return isNumberKey(event)"/ required oninvalid="this.setCustomValidity('Digite um CPFválido')"
                                       onchange="try{setCustomValidity('')}catch(e){}"minlength="11" maxlength="11">
                            </div>     
                            <div>
                                <label for="">E-mail: </label>
                                <input type="text" name="email" required>
                            </div>
                             <div>
                                <label for="">Celular: </label>
                                <input type="text" name="celular" required>
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
                            <br>
                            <br>
                            <br>
                            <br>
                             <div>
                                <label for="">Cargo: </label>
                                <input type="text" name="cargo" required>
                            </div>
                             <div>
                                <label for="">Usuário: </label>
                                <input type="text" name="user" required>
                            </div>
                             <div>
                                <label for="">Senha: </label>
                                <input type="password" name="senha" required>
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
                <form action="${pageContext.request.contextPath}/restrito/funcionarios" method="GET">    
                    <label for="">Buscar (CPF): </label>
                    <input  name="CPF" type="text">  <button type="submit">Buscar</button>
                </form>
                <!--fim buscar-->

                <div class="conteudo">
                    <div class="clientes">
                        <div class="titulo">
                            Funcionario
                        </div><!--titulo-->
                        <table>
                            <tr>
                                <th class="nome">Nome</th>
                                <th class="cpf">CPF</th>
                                <th class="email">E-mail</th>
                                <th class="celular">Celular</th>
                                <th class="sexo">Sexo</th>
                                <th class="cargo">Cargo</th>
                                <th class="acoes">Ações</th>
                            </tr>
                            <c:forEach items="${listarFuncionarios}" var="funcionario" >
                                <tr class="hover">
                                    <td class="nome" ><c:out value="${funcionario.getNome()}"/></td>
                                    <td class="cpf"><c:out value="${funcionario.getCpf()}"/></td>
                                    <td class="email"><c:out value="${funcionario.getEmail()}"/></td>
                                    <td class="celular" ><c:out value="${funcionario.getCelular()}"/></td>
                                    <td class="sexo"><c:out value="${funcionario.getSexo()}"/></td>
                                    <td class="cargo" ><c:out value="${funcionario.getCargo()}"/></td>
                                    <td class="buttonExcluir">
                                        <form action="${pageContext.request.contextPath}/restrito/funcionarios/excluir" method="GET">
                                            <input name="ID" value="${funcionario.getId()}" type="hidden"/>
                                            <button type="submit" id ="actions">Excluir</button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/restrito/funcionarios/redireciona" method="GET">  
                                            <input name="funcionarioCpf" value="${funcionario.getCpf()}" type="hidden"/>
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
        <footer>
            <div>
                Felipe Ferreira Henriques, Luciana Alves, Matheus Makoto e Rogerio Lucon. 
            </div>
        </footer>
    </body>
</html>

<script>
    function isNumberKey(evt) {
        const charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
</script>

<script>
    function isAtKey(evt) {
        const charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode === 64)
            return true;
        return false;
    }
</script>


