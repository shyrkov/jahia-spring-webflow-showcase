<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<template:addResources type="css" resources="job.css"/>
<c:set var="values" value="${currentNode.propertiesAsString}"/>
<div class="spacer">
    <h2>${fn:escapeXml(values['jcr:title'])}</h2>

    <div class="jobListItem">
        <div class="jobInfo">
            <p class="jobLocation">
                <span class="jobLabel"><fmt:message key="jnt_job.location"/>: </span>
                <span class="jobtxt">${fn:escapeXml(values.town)},&nbsp;<jcr:nodePropertyRenderer node="${currentNode}" name="country" renderer="flagcountry"/></span>
            </p>

            <p class="jobType">
                <span class="jobLabel"><fmt:message key="jnt_job.contract"/>: </span>
                <span class="jobtxt"><jcr:nodePropertyRenderer node="${currentNode}" name="contract" renderer="resourceBundle"/></span>
            </p>

            <p class="jobBusinessUnit">
                <span class="jobLabel"><fmt:message key="jnt_job.businessUnit"/>: </span>
                <span class="jobtxt">${fn:escapeXml(values.businessUnit)}</span>
            </p>

            <p class="jobReference">
                <span class="jobLabel"><fmt:message key="jnt_job.reference"/>: </span>
                <span class="jobtxt">${fn:escapeXml(values.reference)}</span>
            </p>

            <p class="educationLevel">
                <span class="jobLabel"><fmt:message key="jnt_job.educationLevel"/>: </span>
                <span class="jobtxt">${fn:escapeXml(values.educationLevel)}</span>
            </p>
        </div>
        <p class="jobDescription">
            <span class="jobLabel"><fmt:message key="jnt_job.description"/>:</span>
            <span class="jobtxt">${values.description}</span>
        </p>

        <p class="jobSkills">
            <span class="jobLabel"><fmt:message key="jnt_job.skills"/>:</span>
            <span class="jobtxt">${values.skills}</span>
        </p>

        <c:set var="writeable" value="${!renderContext.editMode && renderContext.mainResource.template != 'job-apply'}" />
        <c:if test="${writeable}">
            <div class="jobAction">
                <a href="<c:url value='${url.base}${currentNode.path}.job-apply.html'/>" class="jobApply">Apply</a>
                <div style="clear: both;"></div>
            </div>
        </c:if>
    </div>
</div>
<div style="clear: both;"></div>