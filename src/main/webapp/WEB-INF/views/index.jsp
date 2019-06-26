<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Beverly S Hill</title>
	<LINK REL=stylesheet HREF='<c:url value="resources/sample.css" />' TYPE="text/css"/>
</head>
<body>
	<fmt:bundle basename="application"></fmt:bundle> 
	<fmt:bundle basename="messages"></fmt:bundle> 
	<section id="banner">
		<article>
				<h3>
				<c:if test="${not empty message}">
					<c:forEach items="${message.message}" var="message">
	        			<c:out value="${message}"/>
	    			</c:forEach>
    		</c:if>  
    		</h3>
			</article>
		<h1><fmt:message key='header_text'/></h1>  
	</section> 
		<section id="pitches">
			<article class="pagesListing">
				<c:forEach items="${pages}" var="pages">
					<h6>
					</h6>
				<h2>
				  <c:out value="${pages.textDesc}"/>
				</h2>	
				</c:forEach>
				<h6>
				</h6>
			</article>
		</section>
</body>
</html>
