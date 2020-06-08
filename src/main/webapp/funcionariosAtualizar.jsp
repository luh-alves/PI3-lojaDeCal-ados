<%-- 
    Document   : funcionariosAtualizar
    Created on : 08/06/2020, 10:53:52
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Funcionários</title>
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
            <div class="nome-aba">Atualização de Funcionarios</div>
            <div class="container">
                <div class="tela">
                    <form action="${pageContext.request.contextPath}/funcionarios/atualizar" method="GET">
                       <div>
                                <label for="">Nome: </label>
                                <input type="text" name="nome" value="${funcionario.getNome()}" required >
                            </div>
                            <div>
                                <label for="">CPF: </label>
                                <input type="text" name="cpf"  value="${funcionario.getCpf()}" required onkeypress="return isNumberKey(event)"/ required oninvalid="this.setCustomValidity('Digite um CPFválido')"
                                       onchange="try{setCustomValidity('')}catch(e){}"minlength="11" maxlength="11">
                            </div>     
                            <div>
                                <label for="">E-mail: </label>
                                <input type="text" name="email"  value="${funcionario.getEmail()}"required>
                            </div>
                             <div>
                                <label for="">Celular: </label>
                                <input type="text" name="celular" value="${funcionario.getCelular()}" required>
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
                                <input type="text" name="cargo" value="${funcionario.getCargo()}" required>
                            </div>
                             <div>
                                <label for="">Usuário: </label>
                                <input type="text" name="user" value="${funcionario.getUser()}" required>
                            </div>
                             <div>
                                <label for="">Senha: </label>
                                <input type="password" name="senha" value="${funcionario.getSenha()}" required>
                            </div>

                        <div class="b">
                            <input name="funcionarioId" value="${funcionario.getId()}" type="hidden"/> 
                            <button type="submit" name="salvarBtt">Atualizar funcionario</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
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

