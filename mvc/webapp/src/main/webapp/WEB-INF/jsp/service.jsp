<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Services</title>
</head>
<body>
    <h1>Welcome to Registration Services</h1>
    <a href="${pageContext.request.contextPath}/signup">Sign me up</a>
    <br/>
    <c:if test="${id != null && id != 'null' && id != ''}">
        <a href="${pageContext.request.contextPath}/support?id=${id}">
            Support
        </a>
        <br/>
    </c:if>
    <a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>