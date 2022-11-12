<!DOCTYPE html>
<html lang="en">
<head>
    <title>eCommerce</title>
</head>
<body>
<h3 align="center">${user.name}'s Details</h3>
<div align="center">
    <b>Phone number: </b> ${user.getPhoneNumber()} <br/>
    <b>Email: </b> ${user.email} <br/>
</div>
<h4 align="center">Another products of the ${user.name}</h4>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} | <a href="/product/${product.id}">Details</a></p>
    </div>
    <#else>
    <h3>No ads</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>