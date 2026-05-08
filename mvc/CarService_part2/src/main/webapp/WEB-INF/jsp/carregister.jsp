<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Car Registration</title>
</head>
<body>

<h1>This is the signup page</h1>

<form:form action="done" modelAttribute="car">

    Registration Number:
    <form:input path="RegisterationNumber"/>
    <br/><br/>

    CarName
    <form:select path="CarName">
        <form:option value="Seltos"/>
        <form:option value="Sonet"/>
        <form:option value="Carens"/>
    </form:select>
    <br/><br/>

    CarDetails
    <form:select path="CarDetails">
        <form:option value="YES"/>
        <form:option value="NO"/>
    </form:select>
    <br/><br/>

    CarWork
    <form:input path="CarWork"/>
    <br/><br/>

    <input type="submit" value="Submit"/>

</form:form>

</body>
</html>