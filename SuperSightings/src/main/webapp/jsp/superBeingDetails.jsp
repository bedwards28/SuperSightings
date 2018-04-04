<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Super Being Details</title>
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
                        <li class="active"><a href="${pageContext.request.contextPath}/supers">Super Beings</a></li>
                        <li><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>

            <div class="container">
                <h3 class="col-md-offset-1 col-md-11">Super Hero/Villain Details</h3>
                
                <div class="col-md-offset-1 col-md-11">${superDetailsErrMsg}</div>
                <br/>
                
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Name:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${superBeing.name}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Description:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${superBeing.description}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <p class="text-right">Secret Identity:</p>
                    </div>
                    <div class="col-md-10">
                        <c:out value="${superBeing.identity}"/>
                    </div>
                </div>

                <c:forEach var="currentPower" items="${superBeing.powers}">
                    <div class="row">
                        <div class="col-md-2">
                            <p class="text-right">Power:</p>
                        </div>
                        <div class="col-md-10">
                            <c:out value="${currentPower.description}"/>
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

