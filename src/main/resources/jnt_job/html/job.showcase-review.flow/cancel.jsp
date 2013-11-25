<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>

<div class="jobAction">
    <a href="<c:url value='${url.base}${currentNode.path}.job-apply.html'/>" class="jobApply">Apply</a>
    <c:if test="${jcr:hasPermission(renderContext.mainResource.node, 'jcr:modifyProperties')}">
        <a href="<c:url value='${url.base}${currentNode.path}.job-review.html'/>" class="jobApply">Review</a>
    </c:if>
    <div style="clear: both;"></div>
</div>
