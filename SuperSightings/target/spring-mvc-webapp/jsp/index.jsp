<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Home</title>
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
                        <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/supers">Super Beings</a></li>
                        <li><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>
            <div class="col-md-12">
                <div class="col-md-6">
                    <h2>About H.E.R.O.</h2>
                    <p>H.E.R.O. is the #1 organization for tracking super hero and 
                    villain sightings.  The frequency of super being sightings has 
                    sky-rocketed in recent years and there hasn't been a central 
                    repository for tracking all of this activity, until now.</p>
                    
                    <p>The H.E.R.O. database is the most comprehensive ever built for 
                    recording all super hero/villain data and sightings throughout the 
                    world.  We also allow our users to enter and edit any data that 
                    they have collected about these beings and where/when they have 
                    been sighted.</p>
                    
                    <p>Come join our network and help us continue this vital work! </p>
                </div>
                
                <div class="col-md-6">
                    <h3>Newsfeed (The 10 Most Recent Sightings)</h3>
                    <table id="sighting-table" class="table table-hover">
                        <tr>
                            <th width="50%">Location</th>
                            <th width="30%">Date</th>
                            <th width="20%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="sightingDetails?sightingId=${currentSighting.sightingId}">
                                        <c:out value="${currentSighting.location.name}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.date}"/>
                                </td>
                                <td>
                                    <a href="editSightingForm?sightingId=${currentSighting.sightingId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/home.js"></script>

    </body>
</html>

