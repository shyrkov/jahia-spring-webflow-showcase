<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="jobApplication" method="post" style="width: 50%;" enctype="multipart/form-data" autocomplete="off">
    <fieldset>
        <legend>Application</legend>
        <p>
            <label for="resume">Resume (PDF, max 10 MB):</label>
            <input type="file" id="resume" name="resume"/>
        </p>
        <p>
            <label for="coverLetter">Cover letter (PDF, max 1 MB):</label>
            <input type="file" id="coverLetter" name="coverLetter"/>
        </p>
        <div class="divButton">
            <button id="previous" type="submit" name="_eventId_previous">&lt;&lt; Previous</button>
            <button id="next" type="submit" name="_eventId_next">Next &gt;&gt;</button>
            <button style="float: left" id="cancel" type="submit" name="_eventId_cancel">Cancel</button>
        </div>
        <%@ include file="validation.jspf" %>
    </fieldset>
</form:form>
