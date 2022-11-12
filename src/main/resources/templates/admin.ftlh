<!DOCTYPE html>
<html lang="en">
<head>
    <title>eCommerce</title>
</head>
<body>
Admin Panel
<br/>
<#if user.email??>
    <h3>Your name: ${user.name}</h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Logout">
    </form>
<#else>
    <a href="/login">Login</a><br/>
    <a href="/registration">Register</a><br/>
</#if>
<table>
    <tr>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Activity</th>
        <th>Roles</th>
        <th>Ban</th>
        <th>Edit</th>
        <th>Details</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active> Active <#else> Inactive </#if></th>
            <th><#list user.roles as role><${role}<#sep>, </#list></th>
            <th>
                <form action="/user/ban/${user.id}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Ban">
                </form>
            </th>
            <th>
                <a href="/admin/user/edit/${user.id}">Edit roles</a>
            </th>
            <th>
                <a href="/user/${user.id}">Details</a>
            </th>
        </tr>
        <#else>
        <h3>Users not found</h3>
    </#list>
</table>
</body>
</html>