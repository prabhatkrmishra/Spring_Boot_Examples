<html>
<head>
    <title>Details</title>
</head>
<body>
    <h1>Student Details</h1>
    <div>
        Student Id: ${sid}
        <br/>
        Name: ${name}
        <br/>
        Gender: ${gender}
        <br/>
        College: ${college}
        <br/>
        Location: ${location}
        <br/>
    </div>
    <br/>
    <a href="${pageContext.request.contextPath}/service?id=${sid}">Service Page</a>
</body>
</html>