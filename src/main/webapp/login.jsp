<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="/Login.css">
    </head>
    <body>
    </body>
    <div class="login-page">
        <div class="form">
            <form class="login-form" method="post"action="${pageContext.request.contextPath}/login/validate" >
                <input type="text" name="user" id="loginUser" placeholder="user" autofocus required>
                <input type="password" name="senha" id="loginSenha" placeholder="senha" required>
                <button type="submit" name="LogBtn">login</button>
            </form>
        </div>
    </div>
</html>
