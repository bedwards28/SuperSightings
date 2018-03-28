<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Super Sightings</h1>
            <hr/>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/supers">Super Beings</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>

            <h3>Organization Details</h3>
            <p>
                Name: <c:out value="${organization.location.name}"/>
            </p>
            <p>
                Description: <c:out value="${organization.description}" />
            </p>
            <p>
                Address Line 1: <c:out value="${organization.location.addressLine1}" />
            </p>
            <p>
                Address Line 2: <c:out value="${organization.location.addressLine2}" />
            </p>
            <p>
                City: <c:out value="${organization.location.city}" />
            </p>
            <p>
                Region: <c:out value="${organization.location.region}" />
            </p>
            <p>
                Postal Code: <c:out value="${organization.location.postalCode}" />
            </p>
            <p>
                Country: <c:out value="${organization.location.country}" />
            </p>
            <p>
                Latitude: <c:out value="${organization.location.latitude}" />
            </p>
            <p>
                Longitude: <c:out value="${organization.location.longitude}" />
            </p>
            <p>
                Phone: <c:out value="${organization.phone}" />
            </p>
            <p>
                Email: <c:out value="${organization.email}" />
            </p>
            <p>
                Members: 
                <c:forEach var="currentMember" items="${organization.members}">
                    <c:out value="${currentMember.name}"/>
                </c:forEach>
            </p>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

