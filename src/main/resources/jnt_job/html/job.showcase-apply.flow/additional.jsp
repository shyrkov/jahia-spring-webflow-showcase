<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="jobApplication" method="post" style="width: 50%;">
    <fieldset>
        <legend>Additional information</legend>
        <p>
            <label for="salary">Salary expectations (&euro;/year):</label>
            <form:input path="salary" type="text" id="salary" name="salary"/>
        </p>
        <p>
            <label for="startingDate">Earliest starting date (yyyy-MM-dd):</label>
            <form:input path="startingDate" type="text" id="startingDate" name="startingDate"/><br/>
        </p>
        <div class="divButton">
            <button id="previous" type="submit" name="_eventId_previous">&lt;&lt; Previous</button>
            <button id="next" type="submit" name="_eventId_next">Next &gt;&gt;</button>
            <button style="float: left" id="cancel" type="submit" name="_eventId_cancel">Cancel</button>
        </div>
        <%@ include file="validation.jspf" %>
    </fieldset>
</form:form>
