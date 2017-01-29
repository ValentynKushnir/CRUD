<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>User page</title>
    <style type="text/css">
        h1 {
            font-family: Andalus, Sans-Serif;
            font-size: x-large;
            margin-bottom: 0px;
            margin-top: 0px;
            display: inline-block;
            margin-left: 40px;
            margin-right: 40px;
        }
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-radius: 10px;
            border-spacing: 0;
            text-align: center;
        }
        input {
            display: inline-block;
            color: black;
            font-size: 100%;
            font-weight: 700;
            text-decoration: none;
            user-select: none;
            padding: .12em .25em;
            outline: none;
            border: 1px solid rgb(250,172,17);
            border-radius: 7px;
            background: rgb(255,212,3) linear-gradient(rgb(255,212,3), rgb(248,157,23));
            box-shadow: inset 0 -2px 1px rgba(0,0,0,0), inset 0 1px 2px rgba(0,0,0,0), inset 0 0 0 60px rgba(255,255,0,0);
            transition: box-shadow .2s, border-color .2s;
        }
        th {
            color: white;
            text-shadow: 0 1px 1px #2D2020;
            padding: 5px 10px;
            border: 1px solid rgb(250,172,17);
            border-top-left-radius: 7px;
            border-top-right-radius: 7px;
            background: rgb(255,212,3) linear-gradient(rgb(85, 131, 255), rgb(40, 217, 248));
            box-shadow: inset 0 -2px 1px rgba(0,0,0,0), inset 0 1px 2px rgba(0,0,0,0), inset 0 0 0 60px rgba(255,255,0,0);
            transition: box-shadow .2s, border-color .2s;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        th:first-child {
            border-top-left-radius: 10px;
        }
        th:last-child {
            border-top-right-radius: 10px;
            border-right: none;
        }
        td {
            padding: 5px 10px;
            background: #F8E391;
        }
        tr:last-child td:first-child {
            border-radius: 0 0 0 10px;
        }
        tr:last-child td:last-child {
            border-radius: 0 0 10px 0;
        }
        tr td:last-child {
            border-right: none;
        }
        table tr:last-child {
            border-radius: 10px;
        }
    </style>
</head>
<body>
<a href="index.jsp">Back to main menu</a>

<br>
<br>
    <table>
        <th colspan="3">
            <h1 align="left">User list</h1>
            <c:if test="${!empty listUsers}">
                <a href="clearTable"><input type="submit" value="<spring:message text="Clear table"/>"/></a>
            </c:if>
                <a href="fillFakeData"><input type="submit" value="<spring:message text="Fill with fake data"/>"/></a>
        </th>
    <c:if test="${!empty listUsers}">
        <tr>
            <th width="80">ID</th>
            <th width="200">Name</th>
            <th width="120">Age</th>
            <th width="120">Admin</th>
            <th width="120">Created date</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items = "${listUsers}" var="user">
            <tr>
                <td align="center">${user.id}</td>
                <td align="center"><a href = "userdata/${user.id}" target="_blank">${user.name}</a></td>
                <td align="center">${user.age}</td>
                <td align="center"><c:if test="${user.isAdmin}">Yes</c:if><c:if test="${!user.isAdmin}">No</c:if></td>
                <td align="center">${user.createdDate}</td>
                <td align="center"><a href = "<c:url value="/edit/${user.id}"/>">Edit</a></td>
                <td align="center"><a href = "<c:url value="/remove/${user.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </c:if>
    </table>

<c:if test="${empty listUsers}">
    <table>
        <tr>
            <td colspan="3" width="300">Empty</td>
        </tr>
    </table>
</c:if>
<br>



<c:url var="addAction" value="/users/add"/>

<form:form action="${addAction}" commandName="user">
    <table>
        <th colspan="2">
            <h1>Add a User</h1>
        </th>
        <c:if test="${!empty user.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="age">
                    <spring:message text="Age"/>
                </form:label>
            </td>
            <td>
                <form:input path="age"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="isAdmin">
                    <spring:message text="Admin"/>
                </form:label>
            </td>
            <td>
                <form:radiobutton path="isAdmin" value="true"/>Yes
                <form:radiobutton path="isAdmin" value="false"/>No
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="createdDate">
                    <spring:message text="Created date"/>
                </form:label>
            </td>
            <td>
                <input type = "date" name="createdDate"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td align="right">
                <c:if test="${!empty user.name}">
                    <input type="submit" value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.name}">
                    <input type="submit" value="<spring:message text="Add User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
