<%--
  Created by IntelliJ IDEA.
  User: M.Chernyavskaya
  Date: 09.06.2020
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>New/Edit contact</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit contact</h1>
        <form:form action="save" method="post" modelAttribute="contact">
            <table cellpadding="5">
                <form:hidden path="id"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address"/></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><form:input path="phone"/></td>
            </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>
