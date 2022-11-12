<!DOCTYPE html>
<html lang="en">
<head>
    <title>eCommerce</title>
</head>
<body>
<form method="post" action="/">
    Search by title - <input type="text" name="title">
    <input type="submit" value="Search">
</form>
Online store project
<br/>
<#if user.email??>
    <h3>Your name: ${user.name}</h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Logout">
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Admin Panel</a>
    </#if>
<#else>
    <a href="/login">Login</a><br/>
    <a href="/registration">Register</a><br/>
</#if>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} | <a href="/product/${product.id}"> Details </a></p>
    </div>
<#else>
    <h3> Products not found </h3>
</#list>
<#if user.email??>
    <hr>
<#--TODO Вынести в отдельный файл-->
    <h3>Create new product</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Enter title: <input type="text" name="title"/><br/>
        Enter name: <input type="text" name="author"/><br/>
        Enter city: <input type="text" name="city"/><br/>
        Enter description: <input type="text" name="description"/><br/>
        Enter price: <input type="number" name="price"/><br/>
        First image (Preview) : <input type="file" name="file1"/><br/>
        Second image: <input type="file" name="file2"/><br/>
        Third image: <input type="file" name="file3"/><br/>
        <input type="submit" value="Submit"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <#--    TODO// labels -->
    </form>
</#if>
</body>
</html>