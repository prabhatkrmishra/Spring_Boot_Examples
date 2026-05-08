<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Signup</title>
</head>

<body>

<h1>Welcome to the Signup Webapp</h1>

<form:form action="registerUser" modelAttribute="user">

    <label>Name:</label>
    <form:input path="name"/>
    <br/><br/>

    <label>Gender:</label>
    <br/>

    <form:radiobutton path="gender" value="Male"/> Male
    <form:radiobutton path="gender" value="Female"/> Female
    <form:radiobutton path="gender" value="Other"/> Other

    <br/><br/>

    <label>Country:</label>

    <form:select path="location">

        <form:option value="India">India</form:option>
        <form:option value="United States">United States</form:option>
        <form:option value="Canada">Canada</form:option>
        <form:option value="United Kingdom">United Kingdom</form:option>
        <form:option value="Germany">Germany</form:option>
        <form:option value="France">France</form:option>
        <form:option value="Japan">Japan</form:option>
        <form:option value="South Korea">South Korea</form:option>
        <form:option value="Australia">Australia</form:option>
        <form:option value="Singapore">Singapore</form:option>
        <form:option value="UAE">UAE</form:option>
        <form:option value="Brazil">Brazil</form:option>

    </form:select>

    <br/><br/>

    <label>College:</label>

    <form:select path="college">

        <form:option value="IIT Bombay">IIT Bombay</form:option>
        <form:option value="IIT Delhi">IIT Delhi</form:option>
        <form:option value="IIT Madras">IIT Madras</form:option>
        <form:option value="NIT Trichy">NIT Trichy</form:option>
        <form:option value="BITS Pilani">BITS Pilani</form:option>
        <form:option value="Stanford University">Stanford University</form:option>
        <form:option value="MIT">MIT</form:option>
        <form:option value="Harvard University">Harvard University</form:option>
        <form:option value="Oxford University">Oxford University</form:option>
        <form:option value="Cambridge University">Cambridge University</form:option>
        <form:option value="National University of Singapore">
            National University of Singapore
        </form:option>

    </form:select>

    <br/><br/>

    <input type="submit" value="Register"/>

</form:form>

</body>
</html>