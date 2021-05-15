<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
</head>
<body>
    <%exception.printStackTrace();%>
    <jsp:scriptlet>
        Something went wrong, check server logs for details
    </jsp:scriptlet>

</body>
</html>
