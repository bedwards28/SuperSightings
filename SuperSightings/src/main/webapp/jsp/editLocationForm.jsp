<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>H.E.R.O. - Edit Location Form</title>
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
                        <li class="active"><a href="${pageContext.request.contextPath}/locations">Locations</a></li>
                        <li><a href="${pageContext.request.contextPath}/sightings">Sightings</a></li>
                    </ul>
                </div>
            </nav>

            <h3>Edit Location</h3>

            <sf:form class="form-horizontal" role="form" modelAttribute="location" 
                     method="POST" action="editLocation">
                <div class="form-group">
                    <label for="add-name" class="col-md-4 control-label">Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-name" 
                                  name="name" path="name" placeholder="Location Name"/>
                        <sf:errors path="name" cssclas="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                    <sf:textarea rows="3" class="form-control" id="add-description" 
                                 name="description" path="description" placeholder="Description"/>
                    <sf:errors path="description" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-address-line-1" class="col-md-4 control-label">Address Line 1:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-address-line-1"
                              name="addressLine1" path="addressLine1" placeholder="Address Line 1"/>
                    <sf:errors path="addressLine1" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-address-line-2" class="col-md-4 control-label">Address Line 2:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-address-line-2"
                              name="addressLine2" path="addressLine2" placeholder="Address Line 2"/>
                    <sf:errors path="addressLine2" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-city" class="col-md-4 control-label">City:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-city"
                              name="city" path="city" placeholder="City"/>
                    <sf:errors path="city" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-region" class="col-md-4 control-label">Region:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-region" 
                              name="region" path="region" placeholder="Region"/>
                    <sf:errors path="region" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-postalcode" class="col-md-4 control-label">Postal Code:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-postalcode"
                              name="postalCode" path="postalCode" placeholder="Postal Code"/>
                    <sf:errors path="postalCode" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-country" class="col-md-4 control-label">Country:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-country"
                              name="country" path="country" placeholder="Country"/>
                    <sf:errors path="country" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                    <div class="col-md-8">
                    <sf:input type="number" max="90" min="-90" step=".0001" class="form-control" 
                              id="add-latitude" name="latitude" path="latitude" value="0.0" required="required" />
                    <sf:errors path="latitude" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                    <div class="col-md-8">
                    <sf:input type="number" max="180" min="-180" step=".0001" class="form-control" 
                              id="add-longitude" name="longitude" path="longitude" value="0.0" required="required" />
                    <sf:errors path="longitude" cssClass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-primary" value="Update Location"/>
                    </div>
                </div>
                <div>
                    <sf:hidden path="locationId"/>
                </div>
            </sf:form>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

