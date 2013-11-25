<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions"%>
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
        </div>
        <p class="jobDescription">
            <span class="jobLabel"><fmt:message key="jnt_job.description"/>:</span>
            <span class="jobtxt">${functions:abbreviate(values.description, 500, 550, '...')}</span>
        </p>

        <c:if test="${!renderContext.editMode && renderContext.mainResource.template != 'job-apply' && renderContext.mainResource.template != 'job-review'}">
            <div class="jobAction">
                <a href="<c:url value='${url.base}${currentNode.path}.job-apply.html'/>" class="jobApply">Apply</a>
                <c:if test="${jcr:hasPermission(renderContext.mainResource.node, 'jcr:modifyProperties')}">
                        <a href="<c:url value='${url.base}${currentNode.path}.job-review.html'/>" class="jobApply">Review</a>
                </c:if>
                <div style="clear: both;"></div>
            </div>
        </c:if>
    </div>
</div>
<div style="clear: both;"></div>