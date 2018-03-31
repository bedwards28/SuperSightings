<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Organization Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Hero Education and Relationship Organization</h1>
            <hr/>
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/supers">Super Beings</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>

            <div class="container">
                <h3 class="col-md-offset-1 col-md-11">Organization Details</h3>

                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Name:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.name}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Description:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.description}"/>
                    </div>
                </div>  
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Address Line 1:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.addressLine1}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Address Line 2:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.addressLine2}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">City:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.city}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Region:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.region}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Postal Code:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.postalCode}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Country:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.country}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Latitude:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.latitude}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Longitude:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.location.longitude}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Phone:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.phone}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Email:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${organization.email}"/>
                    </div>
                </div>
                <c:forEach var="currentMember" items="${organization.members}">
                    <div class="row">
                        <div class="col-md-2">
                            <p class="text-right">Member:</p>
                        </div>
                        <div class="col-md-10">
                            <c:out value="${currentMember.name}"/>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

