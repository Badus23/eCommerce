<!DOCTYPE html>
<html lang="en">
<head>
    <title>eCommerce</title>
</head>
<body>
<h3 align="center">Registration</h3>
<form action="/registration" method="post">
    <label for="name">Enter your name: </label>
    <input id="name" type="text" name="name"/>

    <label for="email">Enter your email: </label>
    <input id="email" type="email" name="email"/>

    <label for="phone">Enter your phone number: </label>
    <input id="phone" type="text" name="phone"/>

    <label for="password">Enter your password: </label>
    <input id="password" type="password" name="password"/><br/>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Register"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>