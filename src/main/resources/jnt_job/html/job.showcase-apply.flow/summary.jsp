<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fieldset>
    <legend>Personal data</legend>
    <p>
        <span>Firstname: </span><span>${fn:escapeXml(jobApplication.firstname)}</span>
    </p>
    <p>
        <span>Lastname: </span><span>${fn:escapeXml(jobApplication.lastname)}</span>
    </p>
    <p>
        <span>E-mail: </span><span>${fn:escapeXml(jobApplication.email)}</span>
    </p>
    <p>
        <span>Phone: </span><span>${fn:escapeXml(jobApplication.phone)}</span>
    </p>
</fieldset>
<fieldset>
    <legend>Additional information</legend>
    <p>
        <span>Salary expectations (&euro;/year): </span><span>${fn:escapeXml(jobApplication.salary)}</span>
    </p>
    <p>
        <span>Earliest starting date: </span><span>${fn:escapeXml(jobApplication.startingDate)}</span>
    </p>
</fieldset>
<fieldset>
    <legend>Application</legend>
    <p>
        <span>Resume: </span><span>${fn:escapeXml(jobApplication.resume.originalFilename)}</span>
    </p>
    <p>
        <span>Cover letter: </span><span>${fn:escapeXml(jobApplication.coverLetter.originalFilename)}</span>
    </p>
</fieldset>
<form:form modelAttribute="jobApplication" method="post" style="width: 50%;">
    <div class="divButton">
        <button id="previous" type="submit" name="_eventId_previous">&lt;&lt; Previous</button>
        <button id="finish" type="submit" name="_eventId_finish">Finish</button>
        <button style="float: left" id="cancel" type="submit" name="_eventId_cancel">Cancel</button>
    </div>
</form:form>
