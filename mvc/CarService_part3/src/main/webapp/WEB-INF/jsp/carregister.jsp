<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>Car Registration</title>
</head>

<body>

    <h1>This is the signup page</h1>

    <%--@elvariable id="vehicle" type="Car"--%>

    <form:form action="done" modelAttribute="vehicle">

        <label>Car Number:</label>
        <form:input path="RegisterationNumber"/>

        <br/>
        <br/>

        <label>Car Name:</label>

        <form:select path="CarName">

            <form:option value="Seltos">Seltos</form:option>
            <form:option value="Sonet">Sonet</form:option>

        </form:select>

        <br/>
        <br/>

        <label>Covered In Warranty:</label>

        <form:select path="CarDetails">

            <form:option value="YES">YES</form:option>
            <form:option value="NO">NO</form:option>

        </form:select>

        <br/>
        <br/>

        <label>Any remarks :</label>

        <form:input path="CarWork"/>

        <br/>
        <br/>

        <input type="submit" value="Submit"/>

    </form:form>

</body>

</html>