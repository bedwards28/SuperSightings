<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Edit Super Being Form</title>
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
                        <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/supers">Super Beings</a></li>
                        <li><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>
                    
            <h3>Edit Super Being</h3>

            <sf:form class="form-horizontal" role="form" method="POST" 
                     modelAttribute="superBeing" action="editSuperBeing">
                
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">
                        Super Being Name:
                    </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name" path="name" placeholder="Name"/>
                        <sf:errors path="name" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">
                        Description:
                    </label>
                    <div class="col-md-8">
                        <sf:textarea rows="3" cols="50" class="form-control" id="add-description" path="description" placeholder="Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                <div class="form-group">
                    <label for="add-identity" class="col-md-4 control-label">
                        Identity:
                    </label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-identity" path="identity" placeholder="Identity"/>
                        <sf:errors path="identity" cssclass="error"></sf:errors>
                    </div>
                </div>
                    
                <div class="form-group">
                    <label for="add-power" class="col-md-4 control-label">Powers:</label>
                    <div class="col-md-8">
                        <fieldset class="powersCheckbox">
                            <c:forEach var="currentPower" items="${powerList}">
                                <div class="form-control">
                                    <c:choose>
                                        <c:when test="${superBeing.powers.contains(currentPower)}">
                                            <input type="checkbox" name="powers" value="${currentPower.description}" checked />
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="powers" value="${currentPower.description}" />    
                                        </c:otherwise>
                                    </c:choose>
                                    <label>${currentPower.description}</label>
                                </div>
                            </c:forEach>
                        </fieldset>
                    </div>
                </div>
                    
                <div>
                    <sf:hidden path="superId"/>
                </div>
                    
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-primary" value="Update Super Being"/>
                    </div>
                </div>
            </sf:form>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

