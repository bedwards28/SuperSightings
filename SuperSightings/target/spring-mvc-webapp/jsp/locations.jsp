<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Locations</title>
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
                        <li><a href="${pageContext.request.contextPath}/organizations">Organizations</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>
            <div class="row">
                <div class="col-md-6">
                    <h2>Locations</h2>
                    <div class="content-table">
                        <table id="supersTable" class="table table-hover">
                            <tr>
                                <th width="30%">Location</th>
                                <th width="25%">City</th>
                                <th width="25%">Region</th>
                                <th width="10%"></th>
                                <th width="10%"></th>
                            </tr>
                            <c:forEach var="currentLocation" items="${locationList}">
                                <tr>
                                    <td>
                                        <a href="locationDetails?locationId=${currentLocation.locationId}">
                                            <c:out value="${currentLocation.name}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${currentLocation.city}"/>
                                    </td>
                                    <td>
                                        <c:out value="${currentLocation.region}"/>
                                    </td>
                                    <td>
                                        <a href="editLocationForm?locationId=${currentLocation.locationId}">
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a href="deleteLocation?locationId=${currentLocation.locationId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="col-md-6">
                    <h2>Add Location</h2>
                    <sf:form class="form-horizontal" role="form" modelAttribute="location" 
                             method="POST" action="createLocation">
                        <div class="form-group">
                            <label for="add-location-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="name" path="name" placeholder="Location Name"/>
                                <sf:errors path="name" cssclas="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                <sf:textarea rows="3" cols="46" class="form-control" name="description" path="description" placeholder="Description"/>
                                <sf:errors path="description" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-address-line-1" class="col-md-4 control-label">Address Line 1:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="addressLine1" path="addressLine1" placeholder="Address Line 1"/>
                                <sf:errors path="addressLine1" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-address-line-2" class="col-md-4 control-label">Address Line 2:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="addressLine2" path="addressLine2" placeholder="Address Line 2"/>
                                <sf:errors path="addressLine2" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-city" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="city" path="city" placeholder="City"/>
                                <sf:errors path="city" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-region" class="col-md-4 control-label">Region:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="region" path="region" placeholder="Region"/>
                                <sf:errors path="region" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-postalcode" class="col-md-4 control-label">Postal Code:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="postalCode" path="postalCode" placeholder="Postal Code"/>
                                <sf:errors path="postalCode" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-country" class="col-md-4 control-label">Country:</label>
                                <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="country" path="country" placeholder="Country"/>
                                <sf:errors path="country" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-latitude" class="col-md-4 control-label">Latitude:</label>
                                <div class="col-md-8">
                                <sf:input type="number" max="90" min="-90" step=".0001" class="form-control" name="latitude" path="latitude" placeholder="Latitude"/>
                                <sf:errors path="latitude" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-location-longitude" class="col-md-4 control-label">Longitude:</label>
                                <div class="col-md-8">
                                <sf:input type="number" max="180" min="-180" step=".0001" class="form-control" name="longitude" path="longitude" placeholder="Longitude"/>
                                <sf:errors path="longitude" cssClass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-primary" value="Create Location"/>
                                </div>
                            </div>
                    </sf:form>
                </div>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/supersightings.js"></script>
    </body>
</html>

