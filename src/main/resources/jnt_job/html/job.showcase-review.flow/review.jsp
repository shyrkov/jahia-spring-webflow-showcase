<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"%>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>

<c:set var="props" value="${jobApplication.properties}"/>

<fieldset>
    <legend>Status</legend>
    <p style="text-transform: uppercase; font-weight: bold;">
        ${not empty jobApplication.properties['status'] ? jobApplication.properties['status'].string : 'none'}
    </p>
</fieldset>
<fieldset>
    <legend>Personal data</legend>
    <p>
        <span>Firstname: </span><span>${fn:escapeXml(props.firstname.string)}</span>
    </p>
    <p>
        <span>Lastname: </span><span>${fn:escapeXml(props.lastname.string)}</span>
    </p>
    <p>
        <span>E-mail: </span><span>${fn:escapeXml(props.email.string)}</span>
    </p>
    <p>
        <span>Phone: </span><span>${fn:escapeXml(props.phone.string)}</span>
    </p>
</fieldset>
<fieldset>
    <legend>Additional information</legend>
    <p>
        <span>Salary expectations (&euro;/year): </span><span>${props.salary.long}</span>
    </p>
    <p>
        <span>Earliest starting date: </span><span><fmt:formatDate value="${props.startingDate.date.time}" pattern="yyyy-MM-dd" /></span>
    </p>
</fieldset>
<fieldset>
    <legend>Application</legend>
    <p>
        <span>Resume: </span><br/>
        <jcr:node var="resume" path="${jobApplication.path}/resume"/>
        <c:if test="${not empty resume}">
            <jcr:node var="thumbnail" path="${jobApplication.path}/resume/thumbnail"/>
            <c:if test="${not empty thumbnail}">
                <c:url var="thumbnailUrl" value="${resume.url}" context="/">
                    <c:param name="t" value="thumbnail"/>
                </c:url>
                <img alt=" " src="${thumbnailUrl}" width="${thumbnail.properties['j:width']}"  width="${thumbnail.properties['j:height']}"/>
            </c:if>
            <span><a href="<c:url value='${resume.url}' context='/'/>">download</a> (${functions:humanReadableByteCount(resume.fileContent.contentLength)})</span>
        </c:if>
        <c:if test="${empty resume}">
            <span>not provided</span>
        </c:if>
    </p>
    <p>
        <span>Cover letter: </span><br/>
        <jcr:node var="coverLetter" path="${jobApplication.path}/coverLetter"/>
        <c:if test="${not empty coverLetter}">
            <jcr:node var="thumbnail" path="${jobApplication.path}/coverLetter/thumbnail"/>
            <c:if test="${not empty thumbnail}">
                <c:url var="thumbnailUrl" value="${coverLetter.url}" context="/">
                    <c:param name="t" value="thumbnail"/>
                </c:url>
                <img alt=" " src="${thumbnailUrl}" width="${thumbnail.properties['j:width']}"  width="${thumbnail.properties['j:height']}"/>
            </c:if>
            <span><a href="<c:url value='${coverLetter.url}' context='/'/>">download</a> (${functions:humanReadableByteCount(coverLetter.fileContent.contentLength)})</span>
        </c:if>
        <c:if test="${empty coverLetter}">
            <span>not provided</span>
        </c:if>
    </p>
</fieldset>
<form:form method="post" style="width: 50%;">
    <div class="divButton">
        <button id="finish" type="submit" name="_eventId_accept">Accept</button>
        <button id="finish" type="submit" name="_eventId_reject">Reject</button>
        <button style="float: left" id="cancel" type="submit" name="_eventId_cancel">Cancel</button>
    </div>
</form:form>
