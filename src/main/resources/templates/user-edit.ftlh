<!DOCTYPE html>
<html lang="en">
<head>
    <title>eCommerce</title>
</head>
<body>
<form action="/admin/user/edit" method="post">
<#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>${role}</label>
    </div>
</#list>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="hidden" name="userId" value="${user.id}"/>
    <button type="submit">Save</button>
</form>
</body>
</html>