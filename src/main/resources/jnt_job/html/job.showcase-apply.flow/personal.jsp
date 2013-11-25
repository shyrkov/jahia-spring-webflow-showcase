<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="jobApplication" method="post" style="width: 50%;">
    <fieldset>
        <legend>Personal data</legend>
        <p>
            <label for="firstname">Firstname*:</label>
            <form:input path="firstname" type="text" id="firstname" name="firstname"/>
        </p>
        <p>
            <label for="lastname">Lastname*:</label>
            <form:input path="lastname" type="text" id="lastname" name="lastname"/>
        </p>
        <p>
            <label for="email">E-mail*:</label>
            <form:input path="email" type="text" id="email" name="email"/>
        </p>
        <p>
            <label for="phone">Phone:</label>
            <form:input path="phone" type="text" id="phone" name="phone"/>
        </p>
        <div class="divButton">
            <button id="next" type="submit" name="_eventId_next">Next &gt;&gt;</button>
            <button style="float: left" id="cancel" type="submit" name="_eventId_cancel">Cancel</button>
        </div>
        <%@ include file="validation.jspf" %>
    </fieldset>
</form:form>
