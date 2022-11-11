<!DOCTYPE html>
<html lang="en">
<head>
    <title>${product.title}</title>
</head>
<body>
<p align="center"><b>Title: </b>${product.title}</p>
<br/>
<#list images as image>
    <img src="/images/${image.id}" height="60px"/>
</#list>
<hr>
<p align="center"><b>Author: </b>${product.author}</p>
<br/>
<p align="center"><b>City: </b>${product.city}</p>
<br/>
<p align="center"><b>Description: </b>${product.description}</p>
<br/>
<p align="center"><b>Price: </b>${product.price}</p>
<br/>
<div align="center">
<form action="/product/${product.id}" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
    <input type="submit" value="Delete product"/>
</form>
</div>
</body>
</html>