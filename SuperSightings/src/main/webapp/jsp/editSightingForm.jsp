<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Edit Sighting Form</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/supersightings.css" rel="stylesheet">
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
                        <li><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>
            <h3>Edit Sighting</h3>
            <sf:form class="form-horizontal" role="form" method="POST" action="editSighting" modelAttribute="sighting">
                <div class="form-group">
                    <label for="select-loctation" class="col-md-4 control-label">Select a location:</label>
                    <div class="col-md-8">
                        <select class="form-control" id="select-location" name="select-location" required>
                            <c:forEach var="currentLocation" items="${locationList}">
                                <c:choose>
                                    <c:when test="${sighting.location.locationId == currentLocation.locationId}">
                                        <option value="${currentLocation.locationId}" selected>${currentLocation.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${currentLocation.locationId}">${currentLocation.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="date" class="col-md-4 control-label">Date:</label>
                    <input type="date" path="date" name="date" id="date" value="${sighting.date}" required />
                </div>
                <div class="form-group">
                    <label for="add-supers" class="col-md-4 control-label">Super Beings:</label>
                    <div class="col-md-8">
                        <fieldset class="supersCheckbox">
                            <c:forEach var="currentSuper" items="${superList}">
                                <div class="form-control">
                                    <c:choose>
                                        <c:when test="${sighting.superBeings.contains(currentSuper)}">
                                            <input type="checkbox" name="superIds" value="${currentSuper.superId}" checked/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="superIds" value="${currentSuper.superId}" />
                                        </c:otherwise>
                                    </c:choose>
                                    <label>${currentSuper.name}</label>
                                </div>
                            </c:forEach>
                        </fieldset>
                    </div>
                </div>
                <div>
                    <sf:hidden path="sightingId"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-primary" value="Update Sighting"/>
                    </div>
                </div>
            </sf:form>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

