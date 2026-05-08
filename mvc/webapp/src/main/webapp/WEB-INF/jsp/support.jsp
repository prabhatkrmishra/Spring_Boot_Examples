<html>
<head>
    <title>Support</title>
</head>
<body>
    <h1>Welcome to Support Page</h1>
    <div>
        ${message}
    </div>
    <% String code = "This is a java code"; %>
    <%= code %>
    <br/>
    <a href="${pageContext.request.contextPath}/service?id=${id}">Service Page</a>
</body>
</html>