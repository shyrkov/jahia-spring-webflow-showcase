<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Position: ${fn:escapeXml(currentResource.node.displayableName)} (${fn:length(jobApplications)} applications)</h3>
<c:if test="${empty jobApplications}">
    <p>No applications were submitted for this position so far</p>
</c:if>
<c:if test="${not empty jobApplications}">
<ul>
<c:forEach var="jobApplication" items="${jobApplications}">
    <c:url var="reviewUrl" value="${flowExecutionUrl}">
        <c:param name="applicationId" value="${jobApplication.identifier}"/>
        <c:param name="_eventId" value="review"/>
    </c:url>
    <li>
        <a href="${reviewUrl}" title="Review this application">${jobApplication.properties['lastname'].string}&nbsp;${jobApplication.properties['firstname'].string}</a>
        (status: <span style="text-transform: uppercase; font-weight: bold;">${not empty jobApplication.properties['status'] ? jobApplication.properties['status'].string : 'none'}</span>)
    </li>
</c:forEach>
</ul>
</c:if>
