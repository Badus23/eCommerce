<!DOCTYPE html>
<html lang="en">
<head>
    <title>eCommerce</title>
</head>
<body>
<form action="/login" method="post">
    <label for="email">Enter your email: </label>
    <input id="email" type="email" name="username"/>

    <label for="password">Enter your password: </label>
    <input id="password" type="password" name="password"/><br/>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Login"/>
</form>
</body>
</html>