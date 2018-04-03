<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Organizations</title>
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
                        <li class="active"><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="col-md-6">
                    <h2>Organizations</h2>
                    <div class="content-table">
                        <table id="supersTable" class="table table-hover">
                            <tr>
                                <th width="40%">Organization</th>
                                <th width="30%">Location</th>
                                <th width="15%"></th>
                                <th width="15%"></th>
                            </tr>
                            <c:forEach var="currentOrg" items="${orgList}">
                                <tr>
                                    <td>
                                        <a href="organizationDetails?organizationId=${currentOrg.organizationId}">
                                            <c:out value="${currentOrg.name}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${currentOrg.location.city}"/>
                                    </td>
                                    <td>
                                        <a href="editOrganizationForm?organizationId=${currentOrg.organizationId}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteOrganization?organizationId=${currentOrg.organizationId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="col-md-6">
                    <h2>Add Organization</h2>
                    <sf:form class="form-horizontal" role="form" method="POST" 
                             modelAttribute="organization" action="createOrganization">

                        <div class="form-group">
                            <label for="select-loctation" class="col-md-4 control-label">Select a location:</label>
                            <div class="col-md-8">
                                <select class="form-control" id="select-location" name="select-location" required>
                                    <c:forEach var="currentLocation" items="${locationList}">
                                        <c:choose>
                                            <c:when test="${organization.location.locationId == currentLocation.locationId}">
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
                            <label for="add-name" class="col-md-4 control-label">Organization Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="name" path="name" placeholder="Name" />
                                <sf:errors path="name" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-phone" class="col-md-4 control-label">Phone:</label>
                                <div class="col-md-8">
                                <sf:input type="tel" minlength="12" maxlength="20" class="form-control" name="phone" path="phone" placeholder="123-456-7890" />
                                <sf:errors path="phone" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-email" class="col-md-4 control-label">Email:</label>
                                <div class="col-md-8">
                                <sf:input type="email" class="form-control" name="email" path="email" placeholder="JohnDoe@example.com" />
                                <sf:errors path="email" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-members" class="col-md-4 control-label">Members:</label>
                                <div class="col-md-8">
                                    <fieldset class="membersCheckbox">
                                    <c:forEach var="currentSuper" items="${superList}">
                                        <div class="form-control">
                                            <c:choose>
                                                <c:when test="${organization.members.contains(currentSuper)}">
                                                    <input type="checkbox" name="memberIds" value="${currentSuper.superId}" checked/>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" name="memberIds" value="${currentSuper.superId}" />
                                                </c:otherwise>
                                            </c:choose>
                                            <label>${currentSuper.name}</label>
                                        </div>
                                    </c:forEach>
                                </fieldset>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-primary" value="Create Organization"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

